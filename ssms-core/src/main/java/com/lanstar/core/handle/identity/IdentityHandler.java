/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AuthHandler.java
 * 创建时间：2015-04-05
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.identity.impl.AuditIdentity;
import com.lanstar.core.handle.identity.impl.CompanyIdentity;
import com.lanstar.core.handle.identity.impl.GovernmentIdentity;
import com.lanstar.core.handle.identity.impl.SystemIdentity;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.resolver.RenderResolver;
import com.lanstar.core.render.resolver.RenderResolverFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 处理身份认证
 */
public class IdentityHandler implements Handler {
    @Override
    public void handle( HandlerContext context, HandleChain next ) throws ServletException, IOException {
        RequestContext requestContext = context.getRequestContext();
        if ( !requestContext.hasIdentityContext() && !requestContext.getUri().equals( "/logout" )) {
            // TODO: 增加身份认证过程
            // query...
            // 如果身份认证通过，那么使用nextHandle继续处理后续的，否则应抛出无权访问的异常（考虑直接重定向？）。
            if ( false ) throw new HandleException( "身份认证未通过" ).errorCode( HttpServletResponse.SC_UNAUTHORIZED );
            
            String userSID = context.getValue( "username" );
            if (StringHelper.isBlank( userSID )) {
                RenderResolver resolver = RenderResolverFactory.me().getResolver("html");
                ViewAndModel vam = new ViewAndModel();
                vam.view( "/s/home/login.ftl" );
                Render render = resolver.getRender( vam, requestContext );
                render.render();
            }
            // 创建或者获取身份标识,临时测试使用
            Identity identity;
            if (userSID.startsWith( "E" ))
                identity = new CompanyIdentity();
            else if (userSID.startsWith( "R" ))
                identity = new AuditIdentity();
            else if (userSID.startsWith( "G" ))
                identity = new GovernmentIdentity();
            else 
                identity = new SystemIdentity();
            
            // 绑定到请求上下文中
            requestContext.bindIdentity( new IdentityContextImpl( identity ) );

            // 使用带有身份标识的上下文继续后续处理
            next.doHandle( context );
            return;
        }

        // 使用带有身份标识的上下文继续后续处理
        next.doHandle( context );
    }
}
