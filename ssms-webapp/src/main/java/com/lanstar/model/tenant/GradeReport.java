/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeReport.java
 * 创建时间：2015年7月1日 上午9:47:58
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import com.lanstar.model.TenantModel;

/**
 * 自评报告
 *
 */
public class GradeReport extends TenantModel<GradeReport> {
    public static GradeReport dao = new GradeReport();
    
    public String getContent(){
        return this.getStr( "C_CONTENT" );
    }
    
    public void setContent(String content){
        this.set( "C_CONTENT", content );
    }
    
    public void setPlanId(int value){
        this.set( "R_SID", value );
    }
    
    public boolean isSaved(){
        Integer r = this.getInt( "N_STATE" );
        return r!=null && r.intValue()==1;
    }
    
    public void setState(int value){
        this.set( "N_STATE", value );
    }
    
    public GradeReport getReport(int planId){
        return GradeReport.dao.findFirstByColumn( "R_SID", planId );
    }
    
    public boolean delByPlanId(int planId){
        GradeReport model = getReport(planId);
        if (model!=null) return model.delete();
        return false;
    }
}
