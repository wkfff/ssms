/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantDsSwitcher.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import com.lanstar.core.ActionInvocation;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityHolder;
import com.lanstar.identity.IdentityKit;
import com.lanstar.identity.TenantKit;

public class TenantDsSwitcher implements Interceptor {
    @Override
    public void intercept( ActionInvocation ai ) {
        Controller controller = ai.getController();
        IdentityHolder holder = IdentityKit.getIdentityHolder( controller );
        if ( holder == null ) {
            ai.invoke();
            return;
        }

        holder.runAs( new IdentityHolder.Action() {
            @Override
            public void invoke( Identity identity ) {
                TenantKit.switchDs(identity.getTenant());
            }
        } );
        ai.invoke();
    }
}
