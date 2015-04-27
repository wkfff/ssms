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
import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;
import com.lanstar.core.VAR_SCOPE;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.db.impl.SystemDbContext;
import com.lanstar.core.handle.db.impl.TenantDbContext;
import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.DBPaging;
import com.lanstar.service.attachtext.AttachTextService;
import com.lanstar.service.file.FileService;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HandlerContext implements AutoCloseable{
    private final RequestContext context;
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
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getParameterMap() {
        Map<String, String> map = new HashMap<>();
        Map<String, String[]> p = getRequestContext().getRequest().getParameterMap();
        for ( String key : p.keySet() ) {
            String[] values = p.get( key );
            String value = "";
            if (values==null || (values.length==1 && StringHelper.isBlank( values[0] ))) {
                if (key.equals( "R_UPDATE" )||key.equals( "S_UPDATE" )||key.equals( "T_UPDATE" )||key.equals( "R_CREATE" )||key.equals( "S_CREATE" )||key.equals( "T_CREATE" )) continue;
                if (key.startsWith( "N_" )) value = "0"; 
            }
            else
                value = StringHelper.join( values, ",", false );
            map.put( key, value );
        }
        return map;
    }

    /**
     * 获取分页信息
     *
     * @return
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
     *
     * @return
     */
    public Map<String, String> getFilter() {
        Map<String, String> filter = new LinkedHashMap<String, String>();
        Map<String, String> para = getParameterMap();
        for ( String key : para.keySet() ) {
            if ( !key.equals( DBPaging.PAGE_INDEX ) &&  !key.equals( DBPaging.PAGE_SIZE )) {
                String value = para.get( key );
                if ( Strings.isNullOrEmpty( value ) ) continue;
                filter.put( key, value );
            }
        }
        return filter;
    }

    /**
     * 获取文件服务
     */
    public FileService getFileService() {
        return new FileService( getRequestContext().getIdentityContxt().getIdentity() );
    }

    /**
     * 获取附加文本服务
     */
    public AttachTextService getAttachTextService() {
        return new AttachTextService( getRequestContext().getIdentityContxt().getIdentity() );
    }

    public Identity getIdentity() {
        return getRequestContext().getIdentityContxt().getIdentity();
    }

    @Override
    public void close() throws Exception {
        DB.close();
        SYSTEM_DB.close();
        TENANT_DB.close();
    }
}
