/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewService.java
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

package com.lanstar.service.review;

import com.lanstar.controller.review.ReviewGradeState;
import com.lanstar.identity.Identity;
import com.lanstar.identity.TenantContext;
import com.lanstar.model.system.AttachText;
import com.lanstar.model.tenant.ReviewPlan;
import com.lanstar.service.AttachTextService;

public class ReviewService {
    private final TenantContext reviewContext;
    private TenantContext enterpriseContext;

    public ReviewService( TenantContext reviewContext, TenantContext enterpriseContext ) {
        this.reviewContext = reviewContext;
        this.enterpriseContext = enterpriseContext;
    }

    public TenantContext getReviewContext() {
        return reviewContext;
    }

    public TenantContext getEnterpriseContext() {
        return enterpriseContext;
    }

    public void setEnterpriseContext( TenantContext enterpriseContext ) {
        this.enterpriseContext = enterpriseContext;
    }

//    public boolean sync(int r_sid,int sid){
//        return ReviewSyncService.sync( enterpriseContext, reviewContext,r_sid,sid );
//    }
    
    public boolean syncContentFromEnterprise(int srcPlanId,int descPlanId){
        return ReviewSyncService.syncContentFromEnterprise( enterpriseContext, reviewContext,srcPlanId,descPlanId );
    }
    
    /**
     * 根据企业自评方案编号获取评审方案编号
     */
    public int getPlanId(int id){
        String sql = "select sid from SSM_REVIEW_PLAN where R_SID=?";
        ReviewPlan model = ReviewPlan.dao.findFirst( sql,id);
        return model==null?-1:model.getId();
    }
    /**
     * 评审完成
     */
    public void setComplete(ReviewPlan model){
        if (model==null) return;
        model.setState( ReviewGradeState.END.getValue() );
        model.update();
    }
    
    /**
     * 同步评审方案到企业端
     */
    public boolean syncPlan(ReviewPlan model){
        return ReviewSyncService.syncPlan( enterpriseContext, reviewContext,model );
    }
    
    /**
     * 同步评审小组成员到企业端
     * @param sid
     * @return
     */
    public boolean syncPlanMembers(ReviewPlan model){
        return ReviewSyncService.syncPlanMembers( enterpriseContext, reviewContext,model );
    }
    
    /**
     * 同步评审内容到企业端
     */
    public boolean syncContents(ReviewPlan model){
        return ReviewSyncService.syncContents( enterpriseContext, reviewContext,model );
    }
    
    /**
     * 同步评审报告到企业端
     */
    public boolean syncReport(ReviewPlan model){
        return ReviewSyncService.syncReport( enterpriseContext, reviewContext,model );
    }
    
    /**
     * 同步评审证书到企业端
     */
    public boolean syncCert(ReviewPlan model){
        return ReviewSyncService.syncCert( enterpriseContext, reviewContext,model );
    }
    
    /**
     * 根据专业从评审报告模板生成评审报告
     * @return
     */
    public boolean syncReportTemplate(int pro,int planId,Identity identity){
        String sql = " SELECT C_CONTENT FROM SYS_REPORT_TEMPLATE T1 INNER JOIN SYS_TEMPLATE T2  ON T1.R_SID = T2.SID INNER JOIN SYS_PROFESSION T3  ON T3.R_TEMPLATE = T2.SID WHERE T3.SID = ? AND T1.Z_TYPE=2";
        AttachText template = AttachText.dao.findFirst( sql,pro );
        if (template!=null){
            AttachTextService service = reviewContext.getAttachTextService();
            service.save( "SSM_REVIEW_REPORT", "C_CONTENT", planId, template.getStr( "C_CONTENT" ), identity);
        }
        return true;
    }
}


