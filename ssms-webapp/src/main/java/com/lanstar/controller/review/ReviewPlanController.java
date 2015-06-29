/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewPlanController.java
 * 创建时间：2015年6月18日 上午10:45:12
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.Enterprise;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Review;
import com.lanstar.model.tenant.ReviewCert;
import com.lanstar.model.tenant.ReviewPlan;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

/**
 * 评审方案控制器
 *
 */
public class ReviewPlanController extends SimplateController<ReviewPlan> {

    @Override
    protected ReviewPlan getDao() {
        return ReviewPlan.dao;
    }

    /**
     * 初始化评审服务
     */
    public void initService( int eid, int pro ) {
        Enterprise enterprise = Enterprise.dao.findById( eid );
        Profession profession = Profession.dao.findById( pro );
        this.identityContext.initReviewService( enterprise, profession );
    }
    /**
     * 初始化评审方案
     */
    public int initPlan( Enterprise enterprise, int pro ,int enterprisePlanId) {
        // 2查询评审方案编号，如果未开始则自动创建
        int reviewPlanId = this.identityContext.getReviewService().getPlanId( enterprisePlanId );
        if ( reviewPlanId == -1 ) {
            ReviewPlan model = new ReviewPlan();
            model.setEnterpriseId( enterprise.getId() );
            model.setEnterpriseName( enterprise.getName() );
            model.setEnterpriseAddr( enterprise.getStr( "C_ADDR" ) );
            
            model.setRsid( enterprisePlanId );
            model.setStartDate( new Date() );
            model.setEndDate( new Date() );
            model.setTenant( this.identityContext.getTenant() );
            Review review = Review.dao.findById( this.identityContext.getTenantId() );
            if (review!=null){
                model.setReviewAddr( review.getStr( "C_ADDR" ));
                model.set( "C_EMAIL", review.getStr( "C_EMAIL" ) );
                model.set( "C_TEL", review.getStr( "C_TEL" ) );
                model.set( "C_FAX", review.getStr( "C_FAX" ) );
            }
            model.save();
            this.setAttr( "sid", model.getId() );
            return model.getId();
        } else {
            this.setAttr( "sid", reviewPlanId );
            return reviewPlanId;
        }
    }
    
    public boolean initContent(int enterprisePlanId, int planId ){
        return this.identityContext.getReviewService().syncContentFromEnterprise( enterprisePlanId, planId );
    }
    
    public boolean initCert( int planId ) {
        ReviewCert model = ReviewCert.dao.findFirst( "select * from ssm_review_cert where r_sid=?", planId );
        if ( model == null ) {
            model = new ReviewCert();
            model.setRsid( planId );
            model.setEnterpriseName( this.identityContext.getReviewService().getEnterpriseContext().getTenantName() );
            model.setProfessionId( this.identityContext.getReviewService().getEnterpriseContext().getEnterpriseService()
                    .getProfessionService().getId() );
            model.setProfessionName( this.identityContext.getReviewService().getEnterpriseContext().getEnterpriseService()
                    .getProfessionService().getName() );
            model.save();
        }
        this.setAttr( "certId", model.getId() );
        return true;
    }

    public void init() throws Exception{
        // 企业
        int eid = this.getParaToInt( "eid" );
        // 专业
        int pro = this.getParaToInt( "pro" );
        // 初始化评审服务
        this.initService( eid, pro );
        
        Enterprise enterprise = Enterprise.dao.findById( eid );
        if ( enterprise == null ) {
           throw new Exception("无效的企业！");
        }
        // 查询出企业的自评方案编号
        int enterprisePlanId = this.identityContext.getReviewService().getEnterpriseContext().getEnterpriseService().getPlanId( eid, pro );
        if (enterprisePlanId==-1) {
            this.setAttr( "err", "企业还没有已经完成的自评!" );
            renderJson();
            return;
        }
        //企业自评
        this.setAttr( "gradePlanId", enterprisePlanId );
        
        // 初始化评审方案
        int planId = this.initPlan( enterprise, pro,enterprisePlanId );
        // 初始化评审内容
        this.initContent( enterprisePlanId,planId );
        // 评审报告
        this.identityContext.getReviewService().syncReportTemplate( pro,planId,this.identityContext.getIdentity());
        // 初始化评审证书
        this.initCert( planId );
        
        this.setAttr( "SID",  planId );
        renderJson();
    }
    
    public void loading(){
        
    }
    
    public void tabs() {
        String sid = this.getPara( 0 );
        if (!StrKit.isBlank( sid ))
            this.setAttr( "sid", sid );
        String eid = this.getPara( 1 );
        if (!StrKit.isBlank( eid ))
            this.setAttr( "eid", eid );
        String gradePlanId = this.getPara( 2 );
        if (!StrKit.isBlank( gradePlanId ))
            this.setAttr( "gradePlanId", gradePlanId );
    }
    
    public void view(){
        super.rec();
    }
    /**
     * 评审完成后的处理
     */
    public void complete() {
        // 1.变更状态为评审完成
        this.identityContext.getReviewService().setComplete( this.getModel() );

        // 2.拷贝评审方案到企业库的评审方案结果表
        this.identityContext.getReviewService().syncPlan( this.getModel() );

        // 3.拷贝评审到企业库的评审小组成员表
        this.identityContext.getReviewService().syncPlanMembers( this.getModel() );

        // 4.拷贝评审内容到企业库的评审内容结果表
        this.identityContext.getReviewService().syncContents( this.getModel() );

        // 5.拷贝评审报告到企业库的评审报告结果表
        this.identityContext.getReviewService().syncReport( this.getModel() );

        // 6.拷贝评审证书到企业库的评审证书结果表
        this.identityContext.getReviewService().syncCert( this.getModel() );

        this.setAttr( "SID", this.getModel().getId() );
        this.renderJson();
    }
    
    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder builder = new SqlBuilder();
        builder.ORDER_BY( "T_END DESC" );
        return builder;
    }
    
    @Override
    protected SqlBuilder buildWhere() {
        String name = this.getPara( "C_NAME" );
        try {
            if ( !StrKit.isBlank( name ) ) name = URLDecoder.decode( name, "UTF-8" );
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
                                ._If( this.isParaExists( "P_STATE" ), "P_STATE = ?", this.getPara( "P_STATE" ) )
                                ._If( this.isParaBlank( "C_NAME" ) == false, "C_NAME like ?", "%" + name + "%" )
                                ._If( this.isParaBlank( "S_ENTERPRISE" ) == false, "S_ENTERPRISE like ?", "%" + name + "%" )
                                ._If( this.isParaBlank( "R_TENANT_E" ) == false, "R_TENANT_E = ?", this.getPara( "R_TENANT_E" ) )
                                ._If( this.isParaBlank( "T_START" ) == false, "T_START >= ?", this.getPara( "T_START" ) )
                                ._If( this.isParaBlank( "T_END" ) == false, "T_END <= ?", this.getPara( "T_END" ) );

        return builder;
    }
}
