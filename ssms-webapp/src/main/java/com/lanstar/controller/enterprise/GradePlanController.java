/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeController.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import java.util.List;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.GradeContent;
import com.lanstar.model.tenant.GradePlan;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.service.ProfessionService;

public class GradePlanController extends SimplateController<GradePlan> {
    boolean isNew = false;
    @Override
    public void index() {
        // 本年度未开始自评时直接转到开始自评页面
        String sql = "SELECT COUNT(*) N FROM SSM_GRADE_E_M WHERE R_TENANT=? AND P_TENANT='E' AND YEAR(T_START)=YEAR(NOW())";
        Record r =  tenantDb.findFirst( sql, new Object[]{identityContext.getTenantId()});
        if (r==null || r.getLong( "N" )==6 ){
            this.redirect( "/e/grade_m/rec_new" );
        }
        else super.index();
    }
    /**
     * 开始新的自评
     */
    public void rec_new() {
    }
    
    /**
     * 自评
     */
    @Override
    public void rec() {
        super.rec();
        if ( isParaExists( "json" ) == true )
            renderJson();
    }
    
    /**
     * 自评报告
     */
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
               ._If( isParaBlank( "T_START" ) == false, "T_START >= ?", getPara( "T_START" ) )
               ._If( isParaBlank( "T_END" ) == false, "T_END <= ?", getPara( "T_END" ) );
        return builder;
    }

    @Override
    protected GradePlan getDao() {
        return GradePlan.dao;
    }

    @Override
    protected void beforeSave( GradePlan model, boolean[] handled ) {
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) { // for insert
            isNew = true;
            model.setTitle( model.getStartDate() + "企业自评" );
            model.setState( 0 );
        }
    }

    @Override
    protected void afterSave( GradePlan model ) {
        ProfessionService service = identityContext.getEnterpriseService().getProfessionService();
        if (isNew)
            tenantDb.callProcedure( "P_GRADE_INIT", model.getId(), service.getId(), identityContext.getTenantId(),
                identityContext.getTenantType().getName() );
        int sid = model.get( "SID" );
        this.setAttr( "SID", sid );
        renderJson();
    }

    @Override
    public void del() {
        //删除自评内容
        GradeContent.dao.deleteById( this.getModel().getId(),"R_SID");
        //TODO:删除自评报告
        
        super.del();
    }
    
    /**
     * 验证自评内容是否都已经填写,N大于0时说明还有未填写内容
     */
    public void check(){
        Record r =  tenantDb.findFirst("SELECT F_GRADE_CHECK_E(?) N", new Object[] { this.getModel().getId() } );
        this.setAttr( "N", r==null?0:r.getInt( "N" ) );
        renderJson();
    }
    
    /**
     * 自评完成
     */
    public void complete() {
        tenantDb.callProcedure( "P_GRADE_COMPLETE_E", this.getModel().getId() );
        this.setAttr( "result", "OK" );
        renderJson();
    }
}
