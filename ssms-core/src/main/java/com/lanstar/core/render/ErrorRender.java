/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ErrorRender.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.Const;

import java.io.IOException;
import java.io.PrintWriter;

public class ErrorRender extends Render {

    protected static final String contentType = "text/html; charset=" + getEncoding();

    protected static final String version =
            "<center><a href='/'><b>Powered by Lanstar " + Const.RAPIDWARE_VERSION + "</b></a></center>";

    protected static final String html404 =
            "<html><head><title>404 Not Found</title></head><body bgcolor='white'><center><h1>404 Not Found</h1></center><hr>"
                    + version + "</body></html>";
    protected static final String html500 =
            "<html><head><title>500 Internal Server Error</title></head><body bgcolor='white'><center><h1>500 Internal Server Error</h1></center><hr>"
                    + version + "</body></html>";

    protected static final String html401 =
            "<html><head><title>401 Unauthorized</title></head><body bgcolor='white'><center><h1>401 Unauthorized</h1></center><hr>"
                    + version + "</body></html>";
    protected static final String html403 =
            "<html><head><title>403 Forbidden</title></head><body bgcolor='white'><center><h1>403 Forbidden</h1></center><hr>"
                    + version + "</body></html>";

    protected int errorCode;

    public ErrorRender( int errorCode, String view ) {
        this.errorCode = errorCode;
        this.view = view;
    }

    public void render() {
        response.setStatus( getErrorCode() );    // HttpServletResponse.SC_XXX_XXX

        // render with view
        String view = getView();
        if ( view != null ) {
            RenderFactory.me().getRender( view ).setContext( request, response ).render();
            return;
        }

        // render with html content
        PrintWriter writer = null;
        try {
            response.setContentType( contentType );
            writer = response.getWriter();
            writer.write( getErrorHtml() );
            writer.flush();
        } catch ( IOException e ) {
            throw new RenderException( e );
        } finally {
            if ( writer != null )
                writer.close();
        }
    }

    public String getErrorHtml() {
        int errorCode = getErrorCode();
        if ( errorCode == 404 )
            return html404;
        if ( errorCode == 500 )
            return html500;
        if ( errorCode == 401 )
            return html401;
        if ( errorCode == 403 )
            return html403;
        return "<html><head><title>" + errorCode + " Error</title></head><body bgcolor='white'><center><h1>" + errorCode
                + " Error</h1></center><hr>" + version + "</body></html>";
    }

    public int getErrorCode() {
        return errorCode;
    }
}