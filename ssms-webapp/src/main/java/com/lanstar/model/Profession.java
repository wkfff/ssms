/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Profession.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.model;

import com.lanstar.identity.IdentityException;
import com.lanstar.identity.TenantType;
import com.lanstar.plugin.activerecord.Model;

import java.util.List;

public class Profession extends Model<Profession> {
    public static final Profession dao = new Profession();

    public static List<Profession> list( TenantType tenantType, int tenantId ) {
        if ( tenantType != TenantType.ENTERPRISE )
            throw new IdentityException( "current tenant type is " + tenantType + ", but need 'ENTERPRISE'" );
        return dao.find( "SELECT B.* FROM SYS_TENANT_E_PROFESSION A INNER JOIN SYS_PROFESSION B ON A.P_PROFESSION = B.SID WHERE A.R_TENANT = ?", tenantId );
    }

    public String getName(){
        return getStr( "C_NAME" );
    }
}
