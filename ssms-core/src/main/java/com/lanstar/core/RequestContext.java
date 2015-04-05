/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RequestContext.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.google.common.base.Strings;
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

    /**
     * 转向请求到给定的地址
     *
     * @param path 转向地址
     */
    public void forward( String path ) {
        try {
            request.getRequestDispatcher( path ).forward( request, response );
        } catch ( ServletException | IOException e ) {
            throw new HandleException( e );
        }
    }

    /**
     * 将给定的路径转换为上下文的路径
     *
     * @param path 路径
     *
     * @return 带上下文路径
     */
    public String getPath( String path ) {
        return getPath( path, false );
    }

    /**
     * 将给定的地址转换为上下文中的地址
     *
     * @param path 路径
     * @param real 如果为true则返回实际路径，否则返回上下文路径。
     *
     * @return 路径
     */
    public String getPath( String path, boolean real ) {
        if ( !path.startsWith( "/" ) ) path = "/" + path;
        String contextPath = request.getContextPath();
        if ( !path.startsWith( contextPath ) ) path = contextPath + path;
        if ( real ) return getRealPath( path );
        return path;
    }

    /**
     * 获取给定路径的真实路径
     *
     * @param path 路径
     *
     * @return 路径
     */
    public String getRealPath( String path ) {
        return App.getServletContext().getRealPath( path );
    }

    /**
     * 获取资源目录（真实路径）
     *
     * @return 资源目录
     */
    public String getResourceFolder() {
        return getResourceFolder( true );
    }

    /**
     * 获取资源目录
     *
     * @param real 如果为true则表示获取的是真是路径，否则表示获取上下文路径。
     *
     * @return 路径
     */
    public String getResourceFolder( boolean real ) {
        String path = getPath( App.config().getResourceFolder() );
        if ( real ) return getRealPath( path );
        return path;
    }

    /**
     * 获取视图目录（真实路径）
     *
     * @return 路径
     */
    public String getViewsFolder() {
        return getViewsFolder( true );
    }

    /**
     * 获取视图目录
     *
     * @param real 如果为true则表示获取的是真是路径，否则表示获取上下文路径
     *
     * @return 路径
     */
    public String getViewsFolder( boolean real ) {
        String path = getPath( App.config().getViewFolder() );
        if ( real ) return getRealPath( path );
        return path;
    }

    /**
     * 从上下文中取值
     *
     * @param key 值的key
     *
     * @return 值
     */
    public Object getValue( String key ) {
        // 顺序：
        // 1. Param
        // 2. Attr
        // 3. Session
        Object value = request.getParameter( key );
        if( Strings.isNullOrEmpty((String)value)) value = request.getAttribute( key );
        if ( value == null) value = request.getSession().getAttribute( key );
        return value;
    }
}
