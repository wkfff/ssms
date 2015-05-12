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
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.identity.impl.AuditIdentity;
import com.lanstar.core.handle.identity.impl.EnterpriseIdentity;
import com.lanstar.core.handle.identity.impl.GovernmentIdentity;
import com.lanstar.core.handle.identity.impl.SystemIdentity;
import com.lanstar.db.JdbcRecord;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 处理身份认证
 */
public class IdentityHandler implements Handler {
    @Override
    public void handle( HandlerContext context, HandleChain next ) throws ServletException, IOException {
        RequestContext requestContext = context.getRequestContext();
        if ( !requestContext.hasIdentityContext() ) {
            // 登录信息无效要重定向到登录页
            requestContext.redirect( "/login" );
            return;
        }

        // 使用带有身份标识的上下文继续后续处理
        next.doHandle( context );
    }

    public static boolean login( RequestContext context, String tenentCode, String username, String password ) {
        // TODO: 向数据库查询用户名和密码信息
        if ( StringHelper.isBlank( username ) ) {
            return false;
        }
        // 创建或者获取身份标识,临时测试使用
        Identity identity;
        if ( username.startsWith( "E" ) )
            identity = new EnterpriseIdentity(new JdbcRecord());
        else if ( username.startsWith( "R" ) )
            identity = new AuditIdentity();
        else if ( username.startsWith( "G" ) )
            identity = new GovernmentIdentity();
        else
            identity = new SystemIdentity();

        // 绑定到请求上下文中
        context.bindIdentity( new IdentityContextImpl( identity ) );
        return true;
    }
}
