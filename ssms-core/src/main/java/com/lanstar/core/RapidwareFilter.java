/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CoreFilter.java
 * 创建时间：2015年3月31日 下午9:13:20
 * 创建用户：林峰
 */
package com.lanstar.core;

import com.lanstar.common.Asserts;
import com.lanstar.common.log.Logger;
import com.lanstar.config.Constants;
import com.lanstar.config.RapidwareConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 */
public final class RapidwareFilter implements Filter {
    private static Logger log;
    private RapidwareConfig rapidwareConfig;
    private Rapidware rapidware = Rapidware.me();
    private Constants constants;
    private Dispatcher dispatcher;
    private String encoding;
    private int contextPathLength;

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        createRapidwareConfig( filterConfig.getInitParameter( "configClass" ) );
        if ( rapidware.init( filterConfig.getServletContext(), rapidwareConfig ) == false ) {
            throw new RuntimeException( "rapidware init error!" );
        }

        constants = rapidware.getConstants();
        encoding = constants.getEncoding();
        dispatcher = rapidware.getDispatcher();

        String contextPath = filterConfig.getServletContext().getContextPath();
        contextPathLength = (contextPath == null || "/".equals( contextPath ) ? 0 : contextPath.length());
    }

    @Override
    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding( encoding );

        String target = request.getRequestURI();
        if ( contextPathLength != 0 )
            target = target.substring( contextPathLength );

        DispatchContext context = new DispatchContext( target, request, response );
        try {
            dispatcher.dispatch( context );
        } catch ( Exception e ) {
            if ( log.isErrorEnabled() ) {
                String qs = request.getQueryString();
                log.error( qs == null ? target : target + "?" + qs, e );
            }
        }

        if ( context.isHandled() == false )
            chain.doFilter( request, response );
    }

    private void createRapidwareConfig( String configClass ) {
        Asserts.notBlank( configClass, "Please set configClass parameter of RapidwareFilter in web.xml" );

        Object temp;
        try {
            temp = Class.forName( configClass ).newInstance();
        } catch ( Exception e ) {
            throw new RuntimeException( "Can not create instance of class: " + configClass, e );
        }

        if ( temp instanceof RapidwareConfig )
            rapidwareConfig = (RapidwareConfig) temp;
        else
            throw new RuntimeException(
                    "Can not create instance of class: " + configClass + ". Please check the config in web.xml" );
    }

    @Override
    public void destroy() {
        rapidware.stopPlugins();
    }

    public static void initLogger() {
        log = Logger.getLogger( RapidwareFilter.class );
    }
}
