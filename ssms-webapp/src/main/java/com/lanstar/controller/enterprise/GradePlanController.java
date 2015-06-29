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

import com.lanstar.common.kit.DateKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.AttachText;
import com.lanstar.model.tenant.GradeContent;
import com.lanstar.model.tenant.GradePlan;
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
        this.identityContext.getEnterpriseService().syncContent( pro, model.getId() );
        //同步自评报告
        this.identityContext.getEnterpriseService().syncReport( pro, model.getId(),this.identityContext.getIdentity() );
        
        this.setAttr( "SID",  model.getId() );
        renderJson();
    }
    
    @Override
    public void index() {
        // 本年度未开始自评时直接转到开始自评页面
        if (!this.identityContext.getEnterpriseService().hasPlan())
            this.redirect( "/e/gradeplan/loading" );
        else 
            super.index();
    }
    
    public void loading(){
        
    }
    
    public void tabs(){
        String sid = this.getPara( 0 );
        if (!StrKit.isBlank( sid ))
            this.setAttr( "sid", sid );
    }
    
    public void create(){
        this.redirect( "/e/gradeplan/loading" );
//        int sid = init();
//        this.redirect( "/e/gradeplan/tabs/"+sid );
    }
    /**
     * 开始新的自评
     */
    public void rec_new() {
        String d = DateKit.toStr( new Date() );
        setAttr("T_START",d);
        setAttr("T_END",d);
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
    protected void beforeSave( GradePlan model, boolean[] handled ) {
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) { // for insert
            isNew = true;
            model.setTitle( model.getStartDate() + "企业自评" );
            model.setState( 0 );
        }
    }

    @Override
    public void del() {
        //删除自评内容
        GradeContent.dao.deleteById( this.getModel().getId(),"R_SID");
        //删除自评报告
        AttachText at = AttachText.dao.findFirst( "select * from sys_attach_text where r_table='SSM_GRADE_REPORT' and r_field='C_CONTENT' and R_SID=?",this.getModel().getId() );
        if (at!=null) at.delete();
        super.del();
    }
    
    /**
     * 验证自评内容是否都已经填写,N大于0时说明还有未填写内容
     * N为-1时说明自评报告未完成
     */
    public void check(){
        int r=0;
        Record rec =  tenantDb.findFirst("SELECT F_GRADE_CHECK_E(?) N", new Object[] { this.getModel().getId() } );
        r = rec==null?0:rec.getInt( "N" );
        
        if (r==0){
            AttachText at = AttachText.dao.findFirst( "select * from sys_attach_text where r_table='SSM_GRADE_REPORT' and r_field='C_CONTENT' and R_SID=?",this.getModel().getId() );
            r = at==null?-1:0;
        }
        this.setAttr( "N", r );
        renderJson();
    }
    
    /**
     * 自评完成
     */
    public void complete() {
        GradePlan model = this.getModel();
//        if (StrKit.isBlank( model.getStr( "R_LEADER" )) ){
//            this.setAttr( "result", "自评方案未完成，请填写！" );
//        }
//        
//        Record rec =  tenantDb.findFirst("SELECT F_GRADE_CHECK_E(?) N", new Object[] { this.getModel().getId() } );
//        int r = rec==null?0:rec.getInt( "N" );
//        if(r>0){
//            this.setAttr( "result", "自评内容未完成，请填写！" );
//        }
        
        if (model.getState()==EnterpriseGradeState.END.getValue()) 
            this.setAttr( "result", "自评已经完成！" );
        else{
            model.setState( EnterpriseGradeState.END.getValue() );
            model.update();
            this.setAttr( "result", "OK" );
        }
        renderJson();
    }
    /**
     * 评审结果列表
     */
    public void review_result(){
        
    }
    /**
     * 评审结果查看
     */
    public void review_tabs(){
        
    }
    /**
     * 自评历史
     */
    public void history(){
        
    }
}
