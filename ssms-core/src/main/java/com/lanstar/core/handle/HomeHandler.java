/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HomeHandler.java
 * 创建时间：2015年4月21日 上午9:27:34
 * 创建用户：林峰
 */
package com.lanstar.core.handle;

import com.lanstar.core.RequestContext;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 首页
 *
 */
public class HomeHandler implements Handler {
    @Override
    public void handle( HandlerContext context, HandleChain next )
            throws ServletException, IOException {
        RequestContext requestContext = context.getRequestContext();
        String target = requestContext.getTarget();
        if ( target.equals( "/" ) || target.startsWith( "/index" ) ) {
            String tenantType = context.getIdentity().getTenantType().getName().toLowerCase();
            /*RenderResolver resolver = RenderResolverFactory.me().getResolver("html" );
            ViewAndModel vam = new ViewAndModel().view( "/"+tenantType+"/home/index.ftl" );
            Render render = resolver.getRender( vam, requestContext );
            render.render();*/
            context.getRequestContext().forward( "/"+tenantType+"/home/index.html" );
        }
        else next.doHandle( context );
    }
}
