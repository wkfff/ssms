/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ErrorRenderContext.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;
import com.lanstar.core.handle.HandleException;
import com.lanstar.plugin.json.JsonHelper;

import java.util.HashMap;
import java.util.Map;

class ErrorRenderContext extends RequestRenderContext {
    private final int errorCode;
    private String msg;

    public ErrorRenderContext( int errorCode, RequestContext context ) {
        super( context );
        this.errorCode = errorCode < 0 ? 500 : errorCode;
    }

    public ErrorRenderContext( HandleException exception, RequestContext requestContext ) {
        this( exception.getErrorCode(), requestContext );
        msg = exception.getLocalizedMessage();
    }

    public int getErrorCode() {
        return errorCode;
    }

    protected static final String version =
            "<center><b>Powered by Lanstar</b></center>";

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

    public String getErrorJson() {
        Map<String, Object> json = new HashMap<>();
        json.put( "errorCode", this.errorCode );
        json.put( "errorMsg", this.msg == null ? "系统出现了异常" : this.msg );
        return JsonHelper.toJson( json );
    }
}
