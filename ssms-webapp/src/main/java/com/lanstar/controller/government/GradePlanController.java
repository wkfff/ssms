/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradePlanController.java
 * 创建时间：2015年5月27日 上午11:43:02
 * 创建用户：林峰
 */
package com.lanstar.controller.government;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.model.system.Profession;
import com.lanstar.model.tenant.ReviewCert;
import com.lanstar.model.tenant.ReviewPlan;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

/**
 * 企业监管
 */
public class GradePlanController extends SimplateController<ReviewPlan> {
    boolean isNew = false;

    @Override
    protected ReviewPlan getDao() {
        return ReviewPlan.dao;
    }

    /**
     * 待评审的企业数据
     */
    public void list_e() {
        SqlBuilder select = SQL.SELECT( " * " );
        SqlBuilder from = new SqlBuilder().FROM( "V_TENANT_E" );
        SqlBuilder where = this.buildWhere();
        if ( where != null ) from.append( where );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {

            Page<Record> paginate = Db.paginate( this.getParaToInt( PAGE_INDEX ), this.getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(), fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Record> list = Db.find( selectStatement.getSql() + " " + fromStatement.getSql(),
                    fromStatement.getParams() );
            this.renderJson( list );
        }
    }

    @Override
    protected SqlBuilder buildWhere() {
        String name = this.getPara( "C_NAME" );
        try {
            if (!StrKit.isBlank( name )) name = URLDecoder.decode( name, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {
            
        }
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE()
                ._If( this.isParaExists( "P_PRO" ) && !StrKit.isBlank( this.getPara( "P_PRO" ) ), "P_PROVINCE = ?",
                        this.getPara( "P_PRO" ) )
                ._If( this.isParaExists( "P_CITY" ) && !StrKit.isBlank( this.getPara( "P_CITY" ) ), "P_CITY = ?",
                        this.getPara( "P_CITY" ) )
                ._If( this.isParaExists( "P_COUNTY" ) && !StrKit.isBlank( this.getPara( "P_COUNTY" ) ), "P_COUNTY = ?",
                        this.getPara( "P_COUNTY" ) )
                ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara( "N_STATE" ) )
                ._If( this.isParaBlank( "C_NAME" ) == false, "C_NAME like ?", "%" + name + "%" )
                ._If( isParaBlank( "T_START" ) == false, "T_START >= ?", getPara( "T_START" ) )
                ._If( isParaBlank( "T_END" ) == false, "T_END <= ?", getPara( "T_END" ) );
        return builder;
    }

  
    public void recdata() {
        super.rec();
        this.renderJson();
    }


    /**
     * 评审报告
     */
    public void report_rec() {
        this.rec();
    }

    /**
     * 上传评审结果
     */
    public void upload_rec() {
        this.rec();
    }

    /**
     * 评审历史
     */
    public void history() {

    }

    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder builder = new SqlBuilder();
        builder.ORDER_BY( "T_END DESC" );
        return builder;
    }

    public void tabs() {
        // 企业
        int eid = this.getParaToInt( "sid" );
        // 专业
        int pro = this.getParaToInt( "pro" );
        Enterprise enterprise = Enterprise.dao.findById( eid );
        Profession profession = Profession.dao.findById( pro );
        this.identityContext.initReviewService( enterprise, profession );
        
        // 根据企业编号获取最后一次完成的评审编号
        int gradePlanId = this.identityContext.getReviewService().getEnterpriseContext().getEnterpriseService().getPlanId( eid, pro );
        this.setAttr( "gradePlanId", gradePlanId );
        // 评审方案
        int reviewPlanId = this.identityContext.getReviewService().getPlanId( gradePlanId );
        this.setAttr( "reviewPlanId",reviewPlanId);
        
        ReviewCert model = ReviewCert.dao.findFirst( "select * from ssm_review_cert where r_sid=?", reviewPlanId );
        if ( model != null ) {
            this.setAttr( "certId", model.getId() );
        }
        
    }
    
    public void result(){
        
    }
    
    /**
     * 评审历史查看.评分汇总表
     */
    public void sum(){
        int sid = this.getModel().getId();
        List<Record> list = tenantDb.find( "select * from V_GRADE_SUM_R where R_SID=?", new Object[]{sid} );
        this.setAttr( "list", list );
        this.setAttr( "S_TENANT", this.identityContext.getTenantName() );
    }
    
    /**
     *  评审历史查看.扣分项汇总表
     */
    public void sum_ded(){
        int sid = this.getModel().getId();
        List<Record> list = tenantDb.find( "select * from V_GRADE_SUM_DED_R where R_SID=?", new Object[]{sid} );
        this.setAttr( "list", list );
        this.setAttr( "S_TENANT", this.identityContext.getTenantName() );
    }
    
    /**
     * 评审历史查看.评审报告
     */
    public void history_rep(){
        
    }
    
    public void rec_view(){
        
    }
}
