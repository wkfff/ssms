/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandlerKit.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import com.lanstar.core.DispatchContext;
import com.lanstar.core.render.RenderException;
import com.lanstar.core.render.RenderFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HandlerKit {

    private HandlerKit() {}

    public static void renderError404( String view, DispatchContext contex ) {
        contex.setHandled( true );

        contex.getResponse().setStatus( HttpServletResponse.SC_NOT_FOUND );
        RenderFactory.me().getRender( view ).setContext( contex.getRequest(), contex.getResponse() ).render();
    }

    public static void renderError404( DispatchContext context ) {
        context.setHandled( true );

        RenderFactory.me().getErrorRender( 404 ).setContext( context.getRequest(), context.getResponse() ).render();
    }

    public static void redirect301( String url, DispatchContext context ) {
        context.setHandled( true );

        String queryString = context.getRequest().getQueryString();
        if ( queryString != null )
            url += "?" + queryString;

        context.getResponse().setStatus( HttpServletResponse.SC_MOVED_PERMANENTLY );
        context.getResponse().setHeader( "Location", url );
        context.getResponse().setHeader( "Connection", "close" );
    }

    public static void redirect( String url, DispatchContext context ) {
        context.setHandled( true );

        String queryString = context.getRequest().getQueryString();
        if ( queryString != null )
            url = url + "?" + queryString;

        try {
            context.getResponse().sendRedirect( url );    // always 302
        } catch ( IOException e ) {
            throw new RenderException( e );
        }
    }
}