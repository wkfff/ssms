/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeController.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import java.util.Date;
import java.util.List;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.GradeContent;
import com.lanstar.model.tenant.GradePlan;
import com.lanstar.model.tenant.GradeReport;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class GradePlanController extends SimplateController<GradePlan> {
    boolean isNew = false;
    
    public void init(){
        int pro = this.identityContext.getEnterpriseService().getProfessionService().getId();
        GradePlan model = new GradePlan();
        model.setState( EnterpriseGradeState.START.getValue() );
        model.setProfessionId( pro );
        model.setProfessionName( this.identityContext.getEnterpriseService().getProfessionService().getName() );
        model.setStartDate( new Date() );
        model.setEndtDate( new Date() );
        model.setTenant(this.identityContext.getTenant());
        model.save();
        //同步自评内容
        int n = this.identityContext.getEnterpriseService().syncContent( pro, model.getId() );
        //总共多少项，用于判断是否还有未完成项
        model.set( "N_COUNT", n );
        model.update();
        //同步自评报告
        this.identityContext.getEnterpriseService().syncReport( pro, model.getId(),this.identityContext.getIdentity() );
        
        this.setAttr( "SID",  model.getId() );
        renderJson();
    }
    
    @Override
    public void index() {
        // 本年度未开始自评时直接开始自评
        if (!this.identityContext.getEnterpriseService().hasPlan())
            this.setAttr( "autoCreate", "1" );
    }
    
    public void tabs(){
        String sid = this.getPara( 0 );
        if (!StrKit.isBlank( sid ))
            this.setAttr( "sid", sid );
    }
       
    /**
     * 自评报告
     */
    public void rep(){
        
    }
    
    public void report_rec(){
        rec();
    }
    
    /**
     * 自评历史查看
     */
    public void result(){
        rec();
    }
    
    /**
     * 自评历史查看.评分汇总表
     */
    public void sum(){
        int sid = this.getModel().getId();
        List<Record> list = tenantDb.find( "select * from V_GRADE_SUM where R_SID=?", new Object[]{sid} );
        this.setAttr( "list", list );
        this.setAttr( "S_TENANT", this.identityContext.getTenantName() );
    }
    
    /**
     *  自评历史查看.扣分项汇总表
     */
    public void sum_ded(){
        int sid = this.getModel().getId();
        List<Record> list = tenantDb.find( "select * from V_GRADE_SUM_DED where R_SID=?", new Object[]{sid} );
        this.setAttr( "list", list );
        this.setAttr( "S_TENANT", this.identityContext.getTenantName() );
    }
    
    /**
     * 自评历史查看.自评报告
     */
    public void history_rep(){
        rec();
    }
    
    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE( "R_TENANT = ?", identityContext.getTenantId() )
               .WHERE( "P_TENANT = ?", identityContext.getTenantType().getName() )
               ._If( isParaExists( "N_STATE" ), "N_STATE = ?", getPara( "N_STATE" ) )
               ._If( isParaExists( "P_STATE" ), "P_STATE = ?", getPara( "P_STATE" ) )
               ._If( isParaBlank( "T_START" ) == false, "T_START >= ?", getPara( "T_START" ) )
               ._If( isParaBlank( "T_END" ) == false, "T_END <= ?", getPara( "T_END" ) );
        return builder;
    }

    @Override
    protected GradePlan getDao() {
        return GradePlan.dao;
    }

    @Override
    public void del() {
        //删除自评内容
        GradeContent.dao.deleteById( this.getModel().getId(),"R_SID");
        //删除自评报告
        GradeReport.dao.delByPlanId( this.getModel().getId() );
        
        super.del();
    }
    
    /**
     * 自评完成
     */
    public void complete() {
        GradePlan model = this.getModel();
        if (StrKit.isBlank( model.getStr( "C_LEADER" )) || StrKit.isBlank( model.getStr( "C_MEMBERS" ))){
            this.setAttr( "msg", "自评方案还有未填写的项，填写后才能完成自评！" );
            renderJson();
            return;
        };

        if (!model.isContentComplete()){
            this.setAttr( "msg", "自评内容还有未填写的项，填写后才能完成自评！" );
            renderJson();
            return;
        };
        
        GradeReport rep = GradeReport.dao.getReport( model.getId() );
        if(rep==null || !rep.isSaved() ) {
            this.setAttr( "msg", "自评报告未完成，填写后才能完成自评！" );
            renderJson();
            return;
        };
        
        model.setState( EnterpriseGradeState.END.getValue() );
        model.update();
        this.setAttr( "msg", "自评完成！" );
        renderJson();
    }
    /**
     * 自评历史
     */
    public void history(){
        
    }
}
