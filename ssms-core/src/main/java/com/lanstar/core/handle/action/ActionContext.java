/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionContext.java
 * 创建时间：2015-04-05
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.google.common.base.Joiner;
import com.lanstar.app.App;
import com.lanstar.common.exception.WebException;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.core.MapModelBean;
import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.render.RenderFactory;
import com.lanstar.core.render.Renderable;

import java.io.IOException;
import java.io.Writer;

class ActionContext implements Renderable {
    public static final String TEMPLATE_SUFFIX = App.config().getTemplateSuffix();
    private final ActionMeta meta;
    private HandlerContext context;
    private String viewName;
    private ModelBean model;

    public ActionContext( HandlerContext context ) {
        this.context = context;
        this.meta = ActionMeta.parseUrl( context.getRequestContext().getUri() );
        viewName = (meta != null ? meta.getAction() : null);
    }

    /**
     * 获取请求的元数据信息
     *
     * @return 元数据
     */
    public ActionMeta getMeta() {
        return meta;
    }

    public void setViewAndModel( ViewAndModel bag ) {
        if ( bag == null ) return;
        if ( !StringHelper.isBlank( bag.getViewName() ) ) this.viewName = bag.getViewName();
        this.model = bag.getModel();
    }

    public boolean isActionRequest() {
        return meta != null;
    }

    public void render() {
        RenderFactory.me().render( this );
    }

    /**
     * 获取View路径
     */
    @Override
    public String getViewPath() {
        return Joiner.on( '/' ).join( meta.getModule(), meta.getController(), viewName + TEMPLATE_SUFFIX );
    }

    /**
     * 从上下文中取值
     *
     * @param key key
     *
     * @return value
     */
    @Override
    public Object getValue( String key ) {
        Object value = null;
        if ( model != null ) {
            value = model.getValue( key );
        }
        if ( value == null ) value = context.getRequestContext().getValue( key );
        return value;
    }

    /**
     * 获取模型
     */
    @Override
    public ModelBean getModel() {
        // 如果模型为空白，则获取上下文中的所有本地变量作为输出
        if ( model == null ) return new MapModelBean( context.getValues() );
        return this.model;
    }

    /**
     * 获取请求上下文
     */
    @Override
    public RequestContext getRequestContext() {
        return context.getRequestContext();
    }

    /**
     * 获取输出流
     */
    @Override
    public Writer getOutput() {
        try {
            return context.getRequestContext().getResponse().getWriter();
        } catch ( IOException e ) {
            throw new WebException( e );
        }
    }

    /**
     * 获取Render
     */
    @Override
    public String getRender() {
        return meta.getRender();
    }
}
