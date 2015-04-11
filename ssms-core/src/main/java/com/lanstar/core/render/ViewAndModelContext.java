/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ViewAndModelContext.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.google.common.base.Joiner;
import com.lanstar.app.App;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.core.ModelBean;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.action.ActionMeta;

class ViewAndModelContext extends RequestRenderContext {
    public static final String TEMPLATE_SUFFIX = App.config().getTemplateSuffix();
    private final String viewPath;
    private ModelBean model;

    public ViewAndModelContext( ActionMeta meta, ViewAndModel vam, HandlerContext context ) {
        super( context.getRequestContext() );

        // build View Path
        String viewName = null;
        if ( vam != null ) {
            viewName = vam.getViewName();
            // cache model
            model = vam.getModel();
        }
        if ( StringHelper.isBlank( viewName ) ) viewName = meta.getAction();
        viewPath = Joiner.on( '/' ).join( meta.getModule(), meta.getController(), viewName + TEMPLATE_SUFFIX );
    }

    public String getViewPath() {
        return viewPath;
    }

    public Object getValue( String key ) {
        Object value = null;
        if ( model != null ) {
            value = model.getValue( key );
        }
        if ( value == null ) value = getRequestContext().getValue( key );
        return value;
    }
}
