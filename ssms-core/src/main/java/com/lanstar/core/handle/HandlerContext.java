/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandlerContext.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.app.App;
import com.lanstar.common.exception.WebException;
import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;

import java.io.IOException;
import java.io.Writer;

public abstract class HandlerContext {
    public static final String TEMPLATE_SUFFIX = App.config().getTemplateSuffix();

    private final RequestContext context;
    private String viewName;
    private Object model;

    /**
     * 初始化实例。只能在包内初始化，因此请使用{@link com.lanstar.core.handle.HandlerHelper}来实例化。
     *
     * @param context 请求上下文
     *
     * @see com.lanstar.core.handle.HandlerHelper
     */
    protected HandlerContext( RequestContext context ) {
        this.context = context;
    }

    /**
     * 获取请求上下文
     */
    public RequestContext getRequestContext() {
        return context;
    }

    /**
     * 获取输出流
     */
    public Writer getOutput() {
        try {
            return context.getResponse().getWriter();
        } catch ( IOException e ) {
            throw new WebException( e );
        }
    }

    /**
     * 获取View
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * 覆盖View设置
     */
    protected void setViewName( String viewName ) {
        this.viewName = viewName;
    }

    /**
     * 获取模型
     */
    Object getModel() {
        return this.model;
    }

    /**
     * 设置模型
     */
    void setModel( Object model ) {
        this.model = model;
    }

    /**
     * 获取View路径
     */
    public abstract String getViewPath();

    /**
     * 获取Render
     */
    public abstract String getRender();

    public ViewAndModel returnWith( String viewName, Object model ) {
        ViewAndModel viewAndModel = new ViewAndModel();
        viewAndModel.setViewName( viewName );
        viewAndModel.setModel( model );
        return viewAndModel;
    }

    public ViewAndModel returnWith( Object model ) {
        return returnWith( getViewName(), model );
    }

    public Object getValue( String key ) {
        // TODO: 添加取值代码
        return "abc";
    }
}
