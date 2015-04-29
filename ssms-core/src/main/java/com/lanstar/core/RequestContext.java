/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RequestContext.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.app.App;
import com.lanstar.app.ServletHelper;
import com.lanstar.core.handle.Dispatcher;
import com.lanstar.core.handle.identity.IdentityContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestContext {
    public static final String LANSTAR_IDENTITY = "LANSTAR_IDENTITY";

    private final String target;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final Map<String, Object> localVars = new HashMap<>();

    /**
     * 实例化请求上下文
     *
     * @param target   请求目标
     * @param request  {@code HttpServletRequest}
     * @param responst {@code HttpServletResponse}
     */
    public RequestContext( String target, HttpServletRequest request, HttpServletResponse responst ) {
        this.target = target;
        this.request = request;
        this.response = responst;
    }

    /**
     * 获取请求目标。（移除上下文路径后的URL）
     */
    public String getTarget() {
        return target;
    }

    public String getReferer() {
        return request.getHeader( "Referer" );
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * 重定向请求到指定路径
     *
     * @throws IOException
     */
    public void redirect( String location ) throws IOException {
        String contextPath = ServletHelper.getContextPath();
        if ( location.startsWith( "/" ) && !location.startsWith( contextPath ) )
            location = contextPath + location;
        response.sendRedirect( location );
    }

    /**
     * 转向请求到给定的地址
     *
     * @param location 转向地址
     */
    public void forward( String location ) throws ServletException, IOException {
        String contextPath = ServletHelper.getContextPath();
        if ( location.startsWith( "/" ) && location.startsWith( contextPath ) )
            location = location.substring( contextPath.length() );

        Dispatcher.me().dispatch( new RequestContext( location, request, response ) );
    }

    private Object getParameter( String name ) {
        // TODO: 解码处理
        return request.getParameter( name );
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
     * 从局部上下文中取值
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue( String name ) {
        // 取值的顺序：本地变量 --> request --> url参数 --> header --> session --> servletContext
        Object v = localVars.get( name );
        if ( v == null ) v = request.getAttribute( name );
        if ( v == null ) v = getParameter( name );
        // 增加从Header中取值的行为              by 张铮彬#2015-4-21
        if ( v == null ) v = request.getHeader( name );
        if ( v == null ) v = request.getSession().getAttribute( name );
        if ( v == null ) v = App.getServletContext().getAttribute( name );
        return (T) v;
    }

    /**
     * 从指定的范围中取值
     *
     * @see VAR_SCOPE
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue( String name, VAR_SCOPE scope ) {
        Object value = null;
        switch ( scope ) {
            case LOCAL:
                value = localVars.get( name );
                break;
            case REQUEST:
                value = request.getAttribute( name );
                if ( value == null ) value = getParameter( name );
                // 增加从Header中取值的行为              by 张铮彬#2015-4-21
                if ( value == null ) value = request.getHeader( name );
                break;
            case SESSION:
                value = request.getSession().getAttribute( name );
                break;
            case APPLICATION:
                value = App.getServletContext().getAttribute( name );
                break;
        }
        return (T) value;
    }

    public Map<String, Object> getValues() {
        return localVars;
    }

    /**
     * 将值设置到上下文中
     */
    public RequestContext setValue( String name, Object value ) {
        return setValue( name, value, VAR_SCOPE.LOCAL );
    }

    /**
     * 将值设置到指定范围中
     *
     * @see VAR_SCOPE
     */
    public RequestContext setValue( String name, Object value, VAR_SCOPE scope ) {
        switch ( scope ) {
            case LOCAL:
                localVars.put( name, value );
                break;
            case REQUEST:
                request.setAttribute( name, value );
                break;
            case SESSION:
                request.getSession().setAttribute( name, value );
                break;
            case APPLICATION:
                App.getServletContext().setAttribute( name, value );
                break;
        }
        return this;
    }

    /**
     * 绑定身份标识到会话中。
     *
     * @param context 身份标识上下文
     */
    public void bindIdentity( IdentityContext context ) {
        setValue( LANSTAR_IDENTITY, context, VAR_SCOPE.SESSION );
    }

    public IdentityContext getIdentityContxt() {
        return (IdentityContext) getValue( LANSTAR_IDENTITY, VAR_SCOPE.SESSION );
    }

    public boolean hasIdentityContext() {
        return getIdentityContxt() != null;
    }
}
