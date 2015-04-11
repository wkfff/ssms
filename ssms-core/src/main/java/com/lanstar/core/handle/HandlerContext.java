/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandlerContext.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.common.helper.Asserts;
import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;
import com.lanstar.core.VAR_SCOPE;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.db.impl.SystemDbContext;
import com.lanstar.core.handle.db.impl.TanentDbContext;

import java.util.HashMap;
import java.util.Map;

public class HandlerContext {
    private final RequestContext context;
    /**
     * 租户库上下文
     */
    public final HandlerDbContext TANENT_DB;
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
        TANENT_DB = new TanentDbContext( context );
        SYSTEM_DB = new SystemDbContext();
        DB = TANENT_DB;
    }

    /**
     * 获取请求上下文
     */
    public RequestContext getRequestContext() {
        return context;
    }

    public Object getValue( String name ) {
        return getRequestContext().getValue( name );
    }

    public Object getValue( String name, VAR_SCOPE scope ) {
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
    public Map<String, Object> getParameterMap() {
        Map<String, Object> map = new HashMap<>();
        Map<String, String[]> p = getRequestContext().getRequest().getParameterMap();
        for ( String key : p.keySet() ) {
            String[] values = p.get( key );
            String value = values == null ? "" : values[0];
            map.put( key, value );
        }
        return map;
    }
}
