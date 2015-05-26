/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Enterprise.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.identity.TenantType;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

public class Enterprise extends Model<Enterprise> {
    public static final Enterprise dao = new Enterprise();

    public EnterpriseUser getUser( String username, String password ) {
        return EnterpriseUser.dao.findFirst( SqlKit.sql( "system.enterprise.getuser" ), username.toUpperCase(), password
                .toUpperCase(), getStr( "SID" ) );
    }

    public static Enterprise findByCode( String code ) {
        return dao.findFirst( SqlKit.sql( "system.enterprise.findByCode" ), code.toUpperCase() );
    }

    public List<EnterpriseProfession> listProfession() {
        return EnterpriseProfession.listProfession( getId() );
    }

    public String getCountyCode() {
        return getStr( "P_COUNTY" );
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

    public TenantType getTenantType() {
        return TenantType.ENTERPRISE;
    }

    @Override
    public boolean delete() {
        set( "B_DELETE", 1 );
        return update();
    }

    @Override
    public boolean deleteById( Object id ) {
        Enterprise model = dao.findById( id );
        model.set( "B_DELETE", 1 );
        return update();
    }
}
