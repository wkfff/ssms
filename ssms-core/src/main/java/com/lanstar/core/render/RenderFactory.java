/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderFactory.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.common.helper.Asserts;
import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.action.ActionMeta;

public class RenderFactory {
    private static RenderFactory instance = new RenderFactory();

    public static RenderFactory me() {
        return instance;
    }

    public Render getViewRender( ActionMeta meta, ViewAndModel vam, HandlerContext context ) {
        return new FreemarkerRender( new ViewAndModelContext( meta, vam, context ) );
    }

    public Render getJsonRender( ModelBean model, RequestContext context ) {
        return new JsonRender( new ModelRenderContext( model, context ) );
    }

    public Render getErrorRender( HandleException exception, RequestContext requestContext ) {
        return new ErrorRender( new ErrorRenderContext( exception, requestContext ) );
    }

    public Render getErrorRender( int errorCode, RequestContext requestContext ) {
        return new ErrorRender( new ErrorRenderContext( errorCode, requestContext ) );
    }

    public Render getActionRender( ActionMeta meta, ViewAndModel vam, HandlerContext context ) {
        String render = meta.getRender();
        Asserts.notEmpty( render, "Render不能为空" );
        if ( "html".equalsIgnoreCase( render ) )
            return getViewRender( meta, vam, context );
        else if ( "json".equalsIgnoreCase( render ) )
            return getJsonRender( vam == null ? null : vam.getModel(), context.getRequestContext() );

        throw new NotSupportedRenderException( String.format( "Render[%s]不支持", render ) );
    }
}
