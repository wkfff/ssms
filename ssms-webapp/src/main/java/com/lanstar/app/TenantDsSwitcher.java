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
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.TenantType;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.tlds.ThreadLocalDataSourceSwitcher;

public class TenantDsSwitcher extends ThreadLocalDataSourceSwitcher {
    @Override
    public void intercept( ActionInvocation ai ) {
        Controller controller = ai.getController();
        IdentityContext context = IdentityContext.getIdentityContext( controller );
        if ( context == null ) {
            ai.invoke();
            return;
        }

        if ( context.getTenantType() == TenantType.SYSTEM ) {
            Config config = DbKit.getConfig();
            switchDataSource( ai, config.getDataSource() );
        } else {
            Config config = DbKit.getConfig( Const.TENANT_DB_NAME );
            switchDataSource( ai, config.getDataSource(), context.getTenantDbCode() );
        }
    }
}
