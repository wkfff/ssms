/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Government.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.tenant;

import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantType;
import com.lanstar.plugin.activerecord.ModelExt;

public class Government extends ModelExt<Government> implements Tenant {
    public static final Government dao = new Government();

    @Override
    public boolean delete() {
        set( "B_DELETE", 1 );
        return update();
    }

    @Override
    public boolean deleteById( Object id ) {
        Government model = dao.findById( id );
        model.set( "B_DELETE", 1 );
        return update();
    }

    public String getCountyCode() {
        return getStr( "P_COUNTY" );
    }

    @Override
    public int getTenantId() {
        return getId();
    }

    @Override
    public String getTenantName() {
        return getName();
    }

    @Override
    public TenantType getTenantType() {
        return TenantType.GOVERNMENT;
    }

    @Override
    public String getTenantDbCode() {
        // TODO: 从数据库中读取DB_CODE
        return "tenant01";
    }

    public String getTenantCode() {
        return getStr( "C_CODE" );
    }

    public void setTenantCode( String tenantCode ) {
        set( "C_CODE", tenantCode );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

}
