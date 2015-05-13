/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandlerContext.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.google.common.base.Strings;
import com.lanstar.common.helper.Asserts;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.common.log.LogHelper;
import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;
import com.lanstar.core.VAR_SCOPE;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.db.impl.SystemDbContext;
import com.lanstar.core.handle.db.impl.TenantDbContext;
import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.DBPaging;
import com.lanstar.service.IdentityContext;
import com.lanstar.service.StandardTemplateFileService;
import com.lanstar.service.TenantService;
import com.lanstar.service.attachtext.AttachTextService;
import com.lanstar.service.enterprise.EnterpriseTenantService;
import com.lanstar.service.file.FileService;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HandlerContext implements AutoCloseable {
    private final RequestContext context;
    private final Map<Class<? extends TenantService>, TenantService> serviceMap = new LinkedHashMap<>();
    /**
     * 租户库上下文
     */
    public final HandlerDbContext TENANT_DB;
    /**
     * 租户库上下文(只是CLENT_DB的别名字段)
     */
    public final HandlerDbContext DB;
    /**
     * 系统公用库上下文
     */
    public final HandlerDbContext SYSTEM_DB;

    /**
     * 初始化实例。
     *
     * @param context 请求上下文
     */
    HandlerContext( RequestContext context ) {
        this.context = context;
        TENANT_DB = new TenantDbContext( context );
        SYSTEM_DB = new SystemDbContext();
        DB = TENANT_DB;
    }

    /**
     * 获取请求上下文
     */
    public RequestContext getRequestContext() {
        return context;
    }

    public <T> T getValue( String name ) {
        return getRequestContext().getValue( name );
    }

    public <T> T getValue( String name, VAR_SCOPE scope ) {
        return getRequestContext().getValue( name, scope );
    }

    public Map<String, Object> getValues() {
        return getRequestContext().getValues();
    }

    public HandlerContext setValue( String name, Object value, VAR_SCOPE scope ) {
        getRequestContext().setValue( name, value, scope );
        return this;
    }

    public HandlerContext setValue( String name, Object value ) {
        getRequestContext().setValue( name, value );
        return this;
    }

    public RequestContext removeValue( String name, VAR_SCOPE scope ) {
        return context.removeValue( name, scope );
    }

    /**
     * 响应返回结果
     *
     * @param viewName 视图名称
     * @param model    模型名称
     *
     * @return {@link ViewAndModel}实例
     */
    public ViewAndModel returnWith( String viewName, ModelBean model ) {
        return new ViewAndModel().view( viewName ).model( model );
    }

    /**
     * 响应返回结果
     *
     * @param model 模型名称
     *
     * @return {@link ViewAndModel}实例
     */
    public ViewAndModel returnWith( ModelBean model ) {
        return returnWith( null, model );
    }

    /**
     * 响应返回结果
     *
     * @param viewName 视图名称
     *
     * @return {@link ViewAndModel}实例
     */
    public ViewAndModel returnWith( String viewName ) {
        Asserts.notBlank( viewName, "ViewName不能为空" );
        return returnWith( viewName, ModelBean.EMPTY );
    }

    /**
     * 响应返回结果
     *
     * @return {@link ViewAndModel}实例
     */
    public ViewAndModel returnWith() {
        return returnWith( ModelBean.EMPTY );
    }

    /**
     * 获取参数
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getParameterMap() {
        Map<String, String> map = new LinkedCaseInsensitiveMap<>();
        Map<String, String[]> p = getRequestContext().getRequest().getParameterMap();
        for ( String key : p.keySet() ) {
            String[] values = p.get( key );
            String value = "";
            if ( values == null || (values.length == 1 && StringHelper.isBlank( values[0] )) ) {
                // TODO 粗暴的处理
                if ( key.equals( "R_UPDATE" ) || key.equals( "S_UPDATE" ) || key.equals( "T_UPDATE" ) || key
                        .equals( "R_CREATE" ) || key.equals( "S_CREATE" ) || key.equals( "T_CREATE" ) ) continue;
                if ( key.startsWith( "N_" ) ) value = "0";
                else if ( key.startsWith( "T_" ) ) continue;
            } else
                value = StringHelper.join( values, ",", false );
            value = StringHelper.removeBlank( value );
            // 全局忽略null、undefined             by 张铮彬#2015-5-7
            if ( !StringHelper.vaildValue( value ) ) continue;
            map.put( key, value );
        }
        return map;
    }

    /**
     * 获取分页信息
     */
    public DBPaging getPaging() {
        DBPaging paging = new DBPaging();
        Map<String, String> para = getParameterMap();
        String pageIndex = para.get( DBPaging.PAGE_INDEX );
        if ( Strings.isNullOrEmpty( pageIndex ) ) return null;
        paging.setPageIndex( Integer.parseInt( pageIndex ) );
        paging.setPageSize( Integer.parseInt( para.get( DBPaging.PAGE_SIZE ) ) );
        return paging;
    }

    /**
     * 获取过滤条件
     */
    public Map<String, String> getFilter() {
        Map<String, String> filter = new LinkedHashMap<>();
        Map<String, String> para = getParameterMap();
        for ( String key : para.keySet() ) {
            if ( !key.equals( DBPaging.PAGE_INDEX ) && !key.equals( DBPaging.PAGE_SIZE ) ) {
                String value = para.get( key );
                if ( Strings.isNullOrEmpty( value ) ) continue;
                filter.put( key, value );
            }
        }
        return filter;
    }

    @SuppressWarnings("unchecked")
    public <T extends TenantService> T getService( Class<T> type ) {
        TenantService service = serviceMap.get( type );
        if ( service != null ) return (T) service;
        try {
            Constructor<T> constructor = type.getConstructor( IdentityContext.class );
            T instance = constructor.newInstance( new IdentityContext( getIdentity(), DB ) );
            serviceMap.put( type, instance );
            return instance;
        } catch ( NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e ) {
            LogHelper.error( getClass(), e, "获取服务时发生了异常" );
            return null;
        }
    }

    /**
     * 获取文件服务
     */
    public FileService getFileService() {
        return getService( FileService.class );
    }

    /**
     * 获取附加文本服务
     */
    public AttachTextService getAttachTextService() {
        return getService( AttachTextService.class );
    }

    public StandardTemplateFileService getStandardTemplateService() {
        return getService( StandardTemplateFileService.class );
    }

    public EnterpriseTenantService getEnterpriseTenantService() {
        return getService( EnterpriseTenantService.class );
    }

    public Identity getIdentity() {
        return getRequestContext().getIdentityContxt().getIdentity();
    }

    @Override
    public void close() throws Exception {
        DB.close();
        SYSTEM_DB.close();
        TENANT_DB.close();
        for ( TenantService tenantService : serviceMap.values() ) {
            tenantService.close();
        }
    }
}
