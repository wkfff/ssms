/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：LoginHandler.java
 * 创建时间：2015年4月21日 下午2:14:48
 * 创建用户：林峰
 */
package com.lanstar.core.handle;

import java.io.IOException;

import javax.servlet.ServletException;

import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.resolver.RenderResolver;
import com.lanstar.core.render.resolver.RenderResolverFactory;

/**
 * 登录登出
 *
 */
public class LoginHandler implements Handler {

    /* (non-Javadoc)
     * @see com.lanstar.core.handle.Handler#handle(com.lanstar.core.handle.HandlerContext, com.lanstar.core.handle.HandleChain)
     */
    @Override
    public void handle( HandlerContext context, HandleChain next )
            throws ServletException, IOException {
        RequestContext requestContext = context.getRequestContext();
        String uri = requestContext.getUri();
        RenderResolver resolver = RenderResolverFactory.me().getResolver("html");
        ViewAndModel vam = new ViewAndModel();
        if ( uri.equals( "/login" )) {
            vam.view( "/s/home/login.ftl" );
            Render render = resolver.getRender( vam, requestContext );
            render.render();
            return;
        }
        else if (uri.equals( "/logout" )) {
            if ( requestContext.hasIdentityContext() ) {
                requestContext.bindIdentity( null );
            }
            vam.view( "/s/home/login.ftl" );
            Render render = resolver.getRender( vam, requestContext );
            render.render();
            return;
        }
        next.doHandle( context );
    }

}
