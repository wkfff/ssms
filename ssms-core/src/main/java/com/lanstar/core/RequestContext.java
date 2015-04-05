/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RequestContext.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.app.App;
import com.lanstar.core.handle.HandleException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestContext {
    private final String uri;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public RequestContext( String uri, HttpServletRequest request, HttpServletResponse responst ) {
        this.uri = uri;
        this.request = request;
        this.response = responst;
    }

    public String getUri() {
        return uri;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void forward( String path ) {
        try {
            request.getRequestDispatcher( path ).forward( request, response );
        } catch ( ServletException | IOException e ) {
            throw new HandleException( e );
        }
    }

    public String getPath( String path ) {
        return getPath( path, false );
    }

    public String getPath( String path, boolean real ) {
        if ( !path.startsWith( "/" ) ) path = "/" + path;
        String contextPath = request.getContextPath();
        if ( !path.startsWith( contextPath ) ) path = contextPath + path;
        if ( real ) return getRealPath( path );
        return path;
    }

    public String getRealPath( String path ) {
        return App.getServletContext().getRealPath( path );
    }

    public String getResourceFolder() {
        return getResourceFolder( true );
    }

    public String getResourceFolder( boolean real ) {
        String path = getPath( App.config().getResourceFolder() );
        if ( real ) return getRealPath( path );
        return path;
    }

    public String getViewsFolder() {
        return getViewsFolder( true );
    }

    public String getViewsFolder( boolean real ) {
        String path = getPath( App.config().getViewFolder() );
        if ( real ) return getRealPath( path );
        return path;
    }
}
