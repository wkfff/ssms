/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Grade.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.common.kit.DateKit;
import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.Model;

import java.util.Date;

public class GradePlan extends Model<GradePlan> {
    public static GradePlan dao = new GradePlan();

    public String getTitle() {
        return getStr( "C_TITLE" );
    }

    public void setTitle( String title ) {
        set( "C_TITLE", title );
    }

    public Integer getState() {
        return getInt( "N_STATE" );
    }

    public void setState( int state ) {
        set( "N_STATE", state );
    }

    public String getStartDate() {
        return DateKit.toStr( getTimestamp( "T_START" ) );
    }

    public void setStartDate( Date date ) {
        set( "T_START", date );
    }

    public String getEndDate() {
        return DateKit.toStr( getTimestamp( "T_END" ) );
    }

    public void setEndtDate( Date date ) {
        set( "T_END", date );
    }

    public Integer getId() {
        return getInt( "SID" );
    }
    
    public void setProfessionId(int pro){
        set("P_PROFESSION",pro);
    }
    
    public void setProfessionName(String proName){
        set("S_PROFESSION",proName);
    }
    
    public void setTenant(Tenant tenant){
        set("R_TENANT",tenant.getTenantId());
        set("S_TENANT",tenant.getTenantName());
        set("P_TENANT",tenant.getTenantType().getName());
    }
    
    public void setGradeState( String state ) {
        set( "P_STATE", state );
    }
}
