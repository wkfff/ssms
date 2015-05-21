/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityInterceptor.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.core.ActionInvocation;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.Interceptor;

public class IdentityInterceptor implements Interceptor {
    @Override
    public final void intercept( ActionInvocation invocation ) {
        Controller controller = invocation.getController();
        if ( IdentityContext.hasIdentityContext( controller ) == false ) {
            controller.redirect( "/login" );
            return;
        }
        invocation.invoke();
    }
}
