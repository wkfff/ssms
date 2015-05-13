/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：LoginHandler.java
 * 创建时间：2015年4月21日 下午2:14:48
 * 创建用户：林峰
 */
package com.lanstar.core.handle;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.common.validatecode.ValidateCode;
import com.lanstar.core.RequestContext;
import com.lanstar.core.VAR_SCOPE;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.identity.IdentityHandler;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.resolver.RenderResolver;
import com.lanstar.core.render.resolver.RenderResolverFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
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
        if ( target.equals( "/vcode" ) ) {
            HttpServletResponse response = requestContext.getResponse();
            // 设置响应的类型格式为图片格式
            response.setContentType( "image/jpeg" );
            //禁止图像缓存。
            response.setHeader( "Pragma", "no-cache" );
            response.setHeader( "Cache-Control", "no-cache" );
            response.setDateHeader( "Expires", 0 );

            ValidateCode vCode = new ValidateCode( 120, 40, 5, 100 );
            context.setValue( "vCode", vCode.getCode(), VAR_SCOPE.SESSION );
            vCode.write( response.getOutputStream() );
            return;
        } else if ( target.equals( "/login" ) ) {
            String vCode = context.getRequestContext().getRequest().getParameter( "yzm" );
            String username = context.getValue( "username" );
            String password = context.getValue( "password" );
            if ( !StringHelper.isBlank( username, password ) ) {
                RenderResolver resolver = RenderResolverFactory.me().getResolver( "do" );
                ViewAndModel model = new ViewAndModel();

                // 解析用户名格式
                String[] strings = StringHelper.split( username, "@" );
                if ( !context.getValue( "vCode", VAR_SCOPE.SESSION ).equals( vCode ) )
                    model.put( "state", "error" ).put( "msg", "验证码不正确。" );
                else if ( strings.length != 2 ) {
                    model.put( "state", "error" ).put( "msg", "用户名格式不正确。" );
                } else {
                    context.removeValue( "vCode", VAR_SCOPE.SESSION );
                    username = strings[0];
                    String tenant = strings[1];
                    // 登录
                    if ( IdentityHandler.login( context, tenant, username, password ) )
                        model.put( "state", "success" );
                    else model.put( "state", "error" ).put( "msg", "用户名或密码不正确" );
                }

                Render render = resolver.getRender( model, requestContext );
                render.render();
            } else {
                RenderResolver resolver = RenderResolverFactory.me().getResolver( "html" );
                Render render = resolver.getRender( new ViewAndModel().view( "login.ftl" ), requestContext );
                render.render();
            }
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
