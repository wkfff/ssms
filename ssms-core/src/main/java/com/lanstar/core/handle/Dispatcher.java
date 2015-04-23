/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Diapatcher.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.app.container.ContainerHelper;
import com.lanstar.common.exception.WebException;
import com.lanstar.common.log.Logger;
import com.lanstar.core.RequestContext;
import com.lanstar.core.render.RenderException;
import com.lanstar.core.render.resolver.RenderResolverFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Dispatcher {
    private final Logger log = new Logger( Dispatcher.class );

    private final Handlers handlers;

    public static Dispatcher me() {
        return ContainerHelper.getBean( Dispatcher.class );
    }

    private Dispatcher( List<Handler> list ) {
        // 加载所有的handler
        handlers = new Handlers();
        handlers.add( list );
        // 一定要加上默认的处理器用户处理默认资源，而且这个处理器是最后一个处理器。
        handlers.add( new DefaultHandler() );
    }

    public void dispatch( RequestContext requestContext ) throws ServletException, IOException {
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        String target = requestContext.getTarget();
        try {
            handlers.handle( requestContext );
        } catch ( RenderException e ) {
            if ( log.isErrorEnabled() ) {
                String qs = request.getQueryString();
                log.error( qs == null ? target : target + "?" + qs, e );
            }
        } catch ( HandleException e ) {
            int errorCode = e.getErrorCode();
            if ( errorCode == 404 && log.isWarnEnabled() ) {
                String qs = request.getQueryString();
                log.warn( "404 Not Found: %s", qs == null ? target : target + "?" + qs );
            } else if ( errorCode == 401 && log.isWarnEnabled() ) {
                String qs = request.getQueryString();
                log.warn( "401 Unauthorized: %s", qs == null ? target : target + "?" + qs );
            } else if ( errorCode == 403 && log.isWarnEnabled() ) {
                String qs = request.getQueryString();
                log.warn( "403 Forbidden: %s", qs == null ? target : target + "?" + qs );
            } else if ( log.isErrorEnabled() ) {
                String qs = request.getQueryString();
                log.error( e, qs == null ? target : target + "?" + qs );
            }
            RenderResolverFactory.me().getResolver( "html" ).getErrorRender( e, requestContext ).render();
        } catch ( WebException e ) {
            if ( log.isErrorEnabled() ) {
                String qs = request.getQueryString();
                log.error( e, qs == null ? target : target + "?" + qs );
            }
            HandleException exception = new HandleException( e ).errorCode( 500 );
            RenderResolverFactory.me().getResolver( "html" ).getErrorRender( exception, requestContext ).render();
        }
    }
}
