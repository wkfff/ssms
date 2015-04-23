/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：LoginHandler.java
 * 创建时间：2015年4月21日 下午2:14:48
 * 创建用户：林峰
 */
package com.lanstar.core.handle;

import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.resolver.RenderResolver;
import com.lanstar.core.render.resolver.RenderResolverFactory;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 登录登出
 */
public class LoginHandler implements Handler {
    @Override
    public void handle( HandlerContext context, HandleChain next )
            throws ServletException, IOException {
        RequestContext requestContext = context.getRequestContext();
        // 获取请求目标
        String target = requestContext.getTarget();
        RenderResolver resolver = RenderResolverFactory.me().getResolver( "html" );
        ViewAndModel vam = new ViewAndModel();
        if ( target.equals( "/login" ) ) {
            vam.view( "/s/home/login.ftl" );
            Render render = resolver.getRender( vam, requestContext );
            render.render();
            return;
        } else if ( target.equals( "/logout" ) ) {
            if ( requestContext.hasIdentityContext() ) {
                // 直接将当前会话无效化掉
                requestContext.getRequest().getSession().invalidate();
            }
            requestContext.redirect( "/login" );
            return;
        }
        next.doHandle( context );
    }

}
