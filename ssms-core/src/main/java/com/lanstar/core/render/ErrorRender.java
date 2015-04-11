/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ErrorRender.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;
import com.lanstar.core.handle.action.ActionMeta;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

class ErrorRender extends AbstractRender {
    private final ErrorRenderContext context;
    private final ActionMeta meta;

    public ErrorRender( ErrorRenderContext context ) {
        super( context );
        this.context = context;
        meta = ActionMeta.parseUrl( context.getRequestContext().getUri() );
    }

    @Override
    protected void renderHeader( RequestContext requestContext ) {
        if ( "json".equalsIgnoreCase( meta != null ? meta.getRender() : null ) )
            RenderHelper.setJsonHeader( requestContext );
        else
            RenderHelper.setHtmlHeader( requestContext );
        HttpServletResponse response = requestContext.getResponse();
        response.setStatus( context.getErrorCode() );    // HttpServletResponse.SC_XXX_XXX
    }

    @Override
    protected void innerRender() throws Exception {
        // render with html content
        Writer writer = null;
        try {
            writer = context.getOutput();
            if ( "json".equalsIgnoreCase( meta != null ? meta.getRender() : null ) )
                writer.write( context.getErrorJson() );
            else
                writer.write( context.getErrorHtml() );
            writer.flush();
        } finally {
            if ( writer != null )
                writer.close();
        }
    }
}
