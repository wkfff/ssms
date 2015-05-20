/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionHandler.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.common.log.Logger;
import com.lanstar.config.Constants;
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderException;
import com.lanstar.core.render.RenderFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

final class ActionHandler implements Handler {

    private final boolean devMode;
    private final ActionMapping actionMapping;
    private static final RenderFactory renderFactory = RenderFactory.me();
    private static final Logger log = Logger.getLogger( ActionHandler.class );

    public ActionHandler( ActionMapping actionMapping, Constants constants ) {
        this.actionMapping = actionMapping;
        this.devMode = constants.getDevMode();
    }

    /**
     * handle
     * 1: Action action = actionMapping.getAction(target)
     * 2: new ActionInvocation(...).invoke()
     * 3: render(...)
     */
    @Override
    public void handle( DispatchContext context, HandleChain next ) {
        String target = context.getTarget();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        if ( target.indexOf( '.' ) != -1 ) {
            return;
        }

        context.setHandled( true );
        String[] urlPara = { null };
        Action action = actionMapping.getAction( target, urlPara );

        if ( action == null ) {
            if ( log.isWarnEnabled() ) {
                String qs = request.getQueryString();
                log.warn( "404 Action Not Found: " + (qs == null ? target : target + "?" + qs) );
            }
            renderFactory.getErrorRender( 404 ).setContext( request, response ).render();
            return;
        }

        try {
            Controller controller = action.getControllerClass().newInstance();
            controller.init( request, response, urlPara[0] );

            if ( devMode ) {
                boolean isMultipartRequest = ActionReporter.reportCommonRequest( controller, action );
                new ActionInvocation( action, controller ).invoke();
                if ( isMultipartRequest ) ActionReporter.reportMultipartRequest( controller, action );
            } else {
                new ActionInvocation( action, controller ).invoke();
            }

            Render render = controller.getRender();
            if ( render instanceof ActionRender ) {
                String actionUrl = ((ActionRender) render).getActionUrl();
                if ( target.equals( actionUrl ) )
                    throw new RuntimeException( "The forward action url is the same as before." );
                else
                    handle( new DispatchContext( actionUrl, request, response ), next );
                return;
            }

            if ( render == null )
                render = renderFactory.getDefaultRender( action.getViewPath() + action.getMethodName() );
            render.setContext( request, response, action.getViewPath() ).render();
        } catch ( RenderException e ) {
            if ( log.isErrorEnabled() ) {
                String qs = request.getQueryString();
                log.error( qs == null ? target : target + "?" + qs, e );
            }
        } catch ( ActionException e ) {
            int errorCode = e.getErrorCode();
            if ( errorCode == 404 && log.isWarnEnabled() ) {
                String qs = request.getQueryString();
                log.warn( "404 Not Found: " + (qs == null ? target : target + "?" + qs) );
            } else if ( errorCode == 401 && log.isWarnEnabled() ) {
                String qs = request.getQueryString();
                log.warn( "401 Unauthorized: " + (qs == null ? target : target + "?" + qs) );
            } else if ( errorCode == 403 && log.isWarnEnabled() ) {
                String qs = request.getQueryString();
                log.warn( "403 Forbidden: " + (qs == null ? target : target + "?" + qs) );
            } else if ( log.isErrorEnabled() ) {
                String qs = request.getQueryString();
                log.error( qs == null ? target : target + "?" + qs, e );
            }
            e.getErrorRender().setContext( request, response ).render();
        } catch ( Throwable t ) {
            if ( log.isErrorEnabled() ) {
                String qs = request.getQueryString();
                log.error( qs == null ? target : target + "?" + qs, t );
            }
            renderFactory.getErrorRender( 500 ).setContext( request, response ).render();
        }
    }
}