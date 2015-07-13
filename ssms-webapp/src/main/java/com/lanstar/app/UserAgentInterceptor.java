/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：UserAgentHandle.java
 * 创建时间：2015-07-10
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import com.lanstar.core.ActionInvocation;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.Interceptor;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentInterceptor implements Interceptor {

    public static final String USER_AGENT = "User-Agent";

    @Override
    public void intercept( ActionInvocation invocation ) {
        Controller controller = invocation.getController();
        UserAgent userAgent = UserAgent.parseUserAgentString( controller.getRequest().getHeader( USER_AGENT ) );
        Browser browser = userAgent.getBrowser();
        if ( browser.getGroup() == Browser.IE ) {
            if ( browser.getId() < Browser.IE8.getId() ) {
                controller.redirect( "/browsers" );
                return;
            }
        }
        invocation.invoke();
    }
}
