/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewPlan.java
 * 创建时间：2015年6月17日 下午8:25:03
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import java.util.Date;

import com.lanstar.common.kit.DateKit;
import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.Model;

/**
 * 评审结果.方案
 *
 */
public class ReviewResultPlan extends Model<ReviewResultPlan> {
    public static ReviewResultPlan dao = new ReviewResultPlan();
    
    public Integer getId() {
        return getInt( "SID" );
    }

    public int getState() {
        return getInt( "N_STATE" );
    }

    public void setState( int state ) {
        set( "N_STATE", state );
    }

    public int getEnterpriseId() {
        return getInt( "R_ENTERPRISE" );
    }

    public void setEnterpriseId( int enterpriseId ) {
        set( "R_ENTERPRISE", enterpriseId );
    }

    public String getEnterpriseName() {
        return getStr("S_ENTERPRISE");
    }

    public void setEnterpriseName( String enterpriseName ) {
        set("S_ENTERPRISE",enterpriseName);
    }

    public String getAddr() {
        return getStr("C_ADDR");
    }

    public void setAddr( String addr ) {
        set("C_ADDR",addr);
    }

    public String getStartDate() {
        return DateKit.toStr( getTimestamp( "T_START" ) );
    }

    public void setStartDate( Date date) {
        set( "T_START", DateKit.toStr( date ) );
    }

    public String getEndDate() {
        return DateKit.toStr( getTimestamp( "T_END" ) );
    }

    public void setEndDate( Date date ) {
        set( "T_END", date );
    }

    public int getLeaderId() {
        return getInt("R_LEADER");
    }

    public void setLeaderId( int leaderId ) {
        set( "R_LEADER", leaderId );
    }

    public String getLeaderName() {
        return getStr("S_LEADER");
    }

    public void setLeaderName( String leaderName ) {
        set("S_LEADER",leaderName);
    }


    public int getRsid() {
        return getInt("R_SID");
    }

    public void setRsid( int rsid ) {
        set("R_SID",rsid);
    }
    
    public void setTenant(Tenant tenant){
        set("R_TENANT",tenant.getTenantId());
        set("S_TENANT",tenant.getTenantName());
        set("P_TENANT",tenant.getTenantType().getName());
    }
    
    public void setTenantId(int tenantId){
        set("R_TENANT",tenantId);
    }
    
    public void setTenantName(String tenantName){
        set("S_TENANT",tenantName);
    }
    
    public void setTenantType(String tenantType){
        set("P_TENANT",tenantType);
    }
    
    public int getTenantId() {
        return getInt("R_TENANT");
    }

    public String getTenantName() {
        return getStr("S_TENANT");
    }
    
    public String getContact(){
        return getStr("C_CONTACT");
    }
    
    public void setContact(String contact){
        set("C_CONTACT",contact);
    }

}
