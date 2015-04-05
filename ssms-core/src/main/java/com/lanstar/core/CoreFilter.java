/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CoreFilter.java
 * 创建时间：2015年3月31日 下午9:13:20
 * 创建用户：林峰
 */
package com.lanstar.core;

import com.lanstar.core.handle.action.ActionMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 */
public class CoreFilter implements Filter {
    private String[] expaths;
    private Dispatcher dispatcher;

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        //排除路径
        expaths = filterConfig.getInitParameter( "exclude-paths" ).split( "," );
        dispatcher = Dispatcher.me();
        ActionMapping.init();
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        RequestContext requestContext = new RequestContext( uri, req, res );

        // 视图目录禁止访问
        if ( uri.startsWith( requestContext.getViewsFolder( false ) ) )
        {
            res.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }
        // 资源目录使用默认调度方式
        if ( uri.startsWith( requestContext.getResourceFolder( false ) ) ){
            chain.doFilter( request, response );
            return;
        }

        for ( String p : expaths ) {
            String path = requestContext.getPath( p );
            if ( uri.startsWith( path ) ) {
                chain.doFilter( request, response );
                return;
            }
        }
        dispatcher.dispatch( requestContext );
    }

    @Override
    public void destroy() {
    }
}
