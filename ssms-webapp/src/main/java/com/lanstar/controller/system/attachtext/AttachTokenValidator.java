/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachTokenValidator.java
 * 创建时间：2015-06-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system.attachtext;

import com.lanstar.app.Const;
import com.lanstar.core.ActionInvocation;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.identity.IdentityContext;

public class AttachTokenValidator implements Interceptor {
    @Override
    public void intercept( ActionInvocation ai ) {
        Controller controller = ai.getController();
        if ( "SYSTEM".equals( controller.getCookie( Const.ATTACH_TEXT_TOKEN_KEY ) ) ) {
            controller.setAttr( Const.IDENTITY_KEY, IdentityContext.SYSTEM );
        } else {
            IdentityContext context = IdentityContext.getIdentityContext( controller );
            controller.setAttr( Const.IDENTITY_KEY, context );
        }
        controller.removeCookie( Const.ATTACH_TEXT_TOKEN_KEY );
        ai.invoke();
    }
}
