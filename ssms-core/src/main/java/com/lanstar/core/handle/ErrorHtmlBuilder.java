/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ErrorHtmlBuilder.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

public class ErrorHtmlBuilder {
    private final HandleException e;

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

    public ErrorHtmlBuilder( HandleException e ) {
        this.e = e;
    }

    public String build() {
        int errorCode = e.getErrorCode();
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

    public static ErrorHtmlBuilder newInstance( HandleException e ) {
        return new ErrorHtmlBuilder( e );
    }
}
