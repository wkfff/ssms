/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderHelper.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;

import javax.servlet.http.HttpServletResponse;

public class RenderHelper {
    public static final String HTML_CONTENT_TYPE = "text/html; charset=UTF-8";
    public static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";

    public static void setHtmlHeader( RequestContext context ) {
        HttpServletResponse response = context.getResponse();
        // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
        response.setHeader( "Pragma", "no-cache" );
        response.setHeader( "Cache-Control", "no-cache" );
        response.setDateHeader( "Expires", 0 );
        response.setContentType( HTML_CONTENT_TYPE );
    }

    public static void setJsonHeader( RequestContext context ) {
        HttpServletResponse response = context.getResponse();
        // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
        response.setHeader( "Pragma", "no-cache" );
        response.setHeader( "Cache-Control", "no-cache" );
        response.setDateHeader( "Expires", 0 );
        response.setContentType( JSON_CONTENT_TYPE );
    }
}
