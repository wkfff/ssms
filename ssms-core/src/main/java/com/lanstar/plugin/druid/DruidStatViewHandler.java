/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DruidStatViewHandler.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.lanstar.common.kit.HandlerKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.DispatchContext;
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 替代 StatViewServlet
 */
public class DruidStatViewHandler implements Handler {

    private IDruidStatViewAuth auth;
    private String visitPath = "/druid";
    private StatViewServlet servlet = new JFinalStatViewServlet();

    public DruidStatViewHandler( String visitPath ) {
        this( visitPath,
                new IDruidStatViewAuth() {
                    public boolean isPermitted( HttpServletRequest request ) {
                        return true;
                    }
                } );
    }

    public DruidStatViewHandler( String visitPath, IDruidStatViewAuth druidStatViewAuth ) {
        if ( StrKit.isBlank( visitPath ) )
            throw new IllegalArgumentException( "visitPath can not be blank" );
        if ( druidStatViewAuth == null )
            throw new IllegalArgumentException( "druidStatViewAuth can not be null" );

        visitPath = visitPath.trim();
        if ( !visitPath.startsWith( "/" ) )
            visitPath = "/" + visitPath;
        this.visitPath = visitPath;
        this.auth = druidStatViewAuth;
    }

    public void handle( DispatchContext context, HandleChain next ) {
        String target = context.getTarget();
        if ( target.startsWith( visitPath ) ) {
            context.setHandled( true );

            if ( target.equals( visitPath ) && !target.endsWith( "/index.html" ) ) {
                HandlerKit.redirect( target + "/index.html", context );
                return;
            }

            try {
                servlet.service( context.getRequest(), context.getResponse() );
            } catch ( Exception e ) {
                throw new RuntimeException( e );
            }
        } else {
            next.doHandle( context );
        }
    }

    class JFinalStatViewServlet extends StatViewServlet {

        private static final long serialVersionUID = 2898674199964021798L;

        public boolean isPermittedRequest( HttpServletRequest request ) {
            return auth.isPermitted( request );
        }

        public void service( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
            String contextPath = request.getContextPath();
            // String servletPath = request.getServletPath();
            String requestURI = request.getRequestURI();

            response.setCharacterEncoding( "utf-8" );

            if ( contextPath == null ) { // root context
                contextPath = "";
            }
            // String uri = contextPath + servletPath;
            // String path = requestURI.substring(contextPath.length() + servletPath.length());
            int index = contextPath.length() + visitPath.length();
            String uri = requestURI.substring( 0, index );
            String path = requestURI.substring( index );

            if ( !isPermittedRequest( request ) ) {
                path = "/nopermit.html";
                returnResourceFile( path, uri, response );
                return;
            }

            if ( "/submitLogin".equals( path ) ) {
                String usernameParam = request.getParameter( PARAM_NAME_USERNAME );
                String passwordParam = request.getParameter( PARAM_NAME_PASSWORD );
                if ( username.equals( usernameParam ) && password.equals( passwordParam ) ) {
                    request.getSession().setAttribute( SESSION_USER_KEY, username );
                    response.getWriter().print( "success" );
                } else {
                    response.getWriter().print( "error" );
                }
                return;
            }

            if ( isRequireAuth() //
                    && !ContainsUser( request )//
                    && !("/login.html".equals( path ) //
                    || path.startsWith( "/css" )//
                    || path.startsWith( "/js" ) //
                    || path.startsWith( "/img" )) ) {
                if ( contextPath.equals( "" ) || contextPath.equals( "/" ) ) {
                    response.sendRedirect( "/druid/login.html" );
                } else {
                    if ( "".equals( path ) ) {
                        response.sendRedirect( "druid/login.html" );
                    } else {
                        response.sendRedirect( "login.html" );
                    }
                }
                return;
            }

            if ( "".equals( path ) ) {
                if ( contextPath.equals( "" ) || contextPath.equals( "/" ) ) {
                    response.sendRedirect( "/druid/index.html" );
                } else {
                    response.sendRedirect( "druid/index.html" );
                }
                return;
            }

            if ( "/".equals( path ) ) {
                response.sendRedirect( "index.html" );
                return;
            }

            if ( path.contains( ".json" ) ) {
                String fullUrl = path;
                if ( request.getQueryString() != null && request.getQueryString().length() > 0 ) {
                    fullUrl += "?" + request.getQueryString();
                }
                response.getWriter().print( process( fullUrl ) );
                return;
            }

            // find file in resources path
            returnResourceFile( path, uri, response );
        }
    }
}
