/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Enterprise.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.tenant;

import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.EnterpriseProfession;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

public class Enterprise extends ModelExt<Enterprise> implements Tenant {
    public static final Enterprise dao = new Enterprise();

    public EnterpriseUser getUser( String username, String password ) {
        return EnterpriseUser.dao.findFirst( SqlKit.sql( "system.enterprise.getuser" ), username.toUpperCase(),
                password.toUpperCase(), this.getStr( "SID" ) );
    }

    public static Enterprise findByCode( String code ) {
        return dao.findFirst( SqlKit.sql( "system.enterprise.findByCode" ), code.toUpperCase() );
    }

    public List<EnterpriseProfession> listProfession() {
        return EnterpriseProfession.listProfession( this.getId() );
    }

    public String getCountyCode() {
        return this.getStr( "P_COUNTY" );
    }

    @Override
    public int getTenantId() {
        return this.getId();
    }

    @Override
    public String getTenantName() {
        return this.getName();
    }

    @Override
    public String getTenantCode() {
        return this.getStr( "C_CODE" );
    }

    public void setTenantCode( String tenantCode ) {
        this.set( "C_CODE", tenantCode );
    }

    public Integer getId() {
        return this.getInt( "SID" );
    }

    public String getName() {
        return this.getStr( "C_NAME" );
    }

    @Override
    public TenantType getTenantType() {
        return TenantType.ENTERPRISE;
    }

    @Override
    public boolean delete() {
        this.set( "B_DELETE", 1 );
        return this.update();
    }

    @Override
    public boolean deleteById( Object id ) {
        Enterprise model = dao.findById( id );
        model.set( "B_DELETE", 1 );
        return this.update();
    }

    /**
     * 获取企业状态
     */
    public int getState() {
        return this.getInt( "P_STATE" );
    }

    /**
     * 设置企业状态
     */
    public void setState( int value ) {
        this.set( "P_STATE", value );
    }

    /**
     * 获取企业自评状态
     */
    public int getGradeState() {
        return this.getInt( "N_STATE" );
    }

    /**
     * 设置企业自评状态
     */
    public void setGradeState( int value ) {
        this.set( "N_STATE", value );
    }

    public void setReview( Tenant tenant ) {
        this.set( "R_REVIEW", tenant.getTenantId() );
        this.set( "S_REVIEW", tenant.getTenantName() );
    }
}
