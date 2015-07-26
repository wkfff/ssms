/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantKit.java
 * 创建时间：2015-07-24
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.app.Const;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.tlds.DsKit;

public class TenantKit {
    public static void switchDs( Tenant tenant ) {
        if ( tenant.getTenantType() != TenantType.SYSTEM ) {
            Config config = DbKit.getConfig( Const.TENANT_DB_NAME );
            DsKit.switchDs( config.getDataSource(), tenant.getTenantDbCode() );
        }
    }

    public static DbPro getTenantDb( Tenant tenant ) {
        if ( tenant == TenantType.SYSTEM ) return DbPro.use();

        Config config = DbKit.getConfig( Const.TENANT_DB_NAME );
        DsKit.switchDs( config.getDataSource(), tenant.getTenantDbCode() );
        return Db.use( Const.TENANT_DB_NAME );
    }

    public static TenantContext getContext( Tenant tenant ) {
        return new TenantContextImpl( tenant );
    }
}
