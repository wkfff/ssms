/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DefaultHandler.java
 * 创建时间：2015-04-10
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.app.App;
import com.lanstar.common.log.LogHelper;
import com.lanstar.core.RequestContext;
import org.springframework.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

public class DefaultHandler implements Handler {
    /**
     * Default Servlet name used by Tomcat, Jetty, JBoss, and GlassFish
     */
    private static final String COMMON_DEFAULT_SERVLET_NAME = "default";

    /**
     * Default Servlet name used by Google App Engine
     */
    private static final String GAE_DEFAULT_SERVLET_NAME = "_ah_default";

    /**
     * Default Servlet name used by Resin
     */
    private static final String RESIN_DEFAULT_SERVLET_NAME = "resin-file";

    /**
     * Default Servlet name used by WebLogic
     */
    private static final String WEBLOGIC_DEFAULT_SERVLET_NAME = "FileServlet";

    /**
     * Default Servlet name used by WebSphere
     */
    private static final String WEBSPHERE_DEFAULT_SERVLET_NAME = "SimpleFileServlet";

    private final ServletContext servletContext;
    private String defaultServletName;

    public DefaultHandler() {
        this.servletContext = App.getServletContext();
        if ( !StringUtils.hasText( this.defaultServletName ) ) {
            if ( this.servletContext.getNamedDispatcher( COMMON_DEFAULT_SERVLET_NAME ) != null ) {
                this.defaultServletName = COMMON_DEFAULT_SERVLET_NAME;
            } else if ( this.servletContext.getNamedDispatcher( GAE_DEFAULT_SERVLET_NAME ) != null ) {
                this.defaultServletName = GAE_DEFAULT_SERVLET_NAME;
            } else if ( this.servletContext.getNamedDispatcher( RESIN_DEFAULT_SERVLET_NAME ) != null ) {
                this.defaultServletName = RESIN_DEFAULT_SERVLET_NAME;
            } else if ( this.servletContext.getNamedDispatcher( WEBLOGIC_DEFAULT_SERVLET_NAME ) != null ) {
                this.defaultServletName = WEBLOGIC_DEFAULT_SERVLET_NAME;
            } else if ( this.servletContext.getNamedDispatcher( WEBSPHERE_DEFAULT_SERVLET_NAME ) != null ) {
                this.defaultServletName = WEBSPHERE_DEFAULT_SERVLET_NAME;
            } else {
                throw new IllegalStateException( "Unable to locate the default servlet for serving static content. " +
                        "Please set the 'defaultServletName' property explicitly." );
            }
        }
    }

    @Override
    public void handle( HandlerContext context, HandleChain next ) throws ServletException, IOException {
        RequestDispatcher rd = this.servletContext.getNamedDispatcher( this.defaultServletName );
        if ( rd == null ) {
            throw new IllegalStateException( "A RequestDispatcher could not be located for the default servlet '" +
                    this.defaultServletName + "'" );
        }
        RequestContext requestContext = context.getRequestContext();
        LogHelper.debug( getClass(), "使用容器默认的Servlet处理资源[%s]", context.getRequestContext().getTarget() );
        rd.forward( requestContext.getRequest(), requestContext.getResponse() );
    }
}
