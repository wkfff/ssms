/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseService.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.identity.TenantType;
import com.lanstar.model.system.*;
import com.lanstar.plugin.activerecord.Db;

public class TenantService {
    private static TenantService me = new TenantService();

    public static TenantService me() {
        return me;
    }

    public String buildSignature( TenantType tenantType, String countyCode ) {
        // 得到租户特征码：租户类型+县级编码
        String sign = tenantType.getName().toUpperCase() + countyCode;
        // 生成租户编码tenantCode：租户特征码+4位顺序码
        Object[] params = { sign, 4, sign + "@" };
        Object[] objects = Db.callProcedure( "SP_GETSEQVALUE", params );
        return (String) objects[2];
    }

    public void addAdminUser( Enterprise model ) {
        EnterpriseUser user = new EnterpriseUser();
        user.setName( "管理员" );
        user.setUsername( "admin" );
        user.setEnterprise( model );
        user.initPassword();
        user.save();
    }

    public void addAdminUser( Government model ) {
        GovernmentUser user = new GovernmentUser();
        user.setName( "管理员" );
        user.setUsername( "admin" );
        user.setGovernment( model );
        user.initPassword();
        user.save();
    }

    public void addAdminUser( Review model ) {
        ReviewUser user = new ReviewUser();
        user.setName( "管理员" );
        user.setUsername( "admin" );
        user.setReview( model );
        user.initPassword();
        user.save();
    }
}
