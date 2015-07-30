/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewSyncService.java
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

package com.lanstar.service.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lanstar.identity.TenantContext;
import com.lanstar.model.tenant.ReviewCert;
import com.lanstar.model.tenant.ReviewContent;
import com.lanstar.model.tenant.ReviewMember;
import com.lanstar.model.tenant.ReviewPlan;
import com.lanstar.model.tenant.ReviewResultCert;
import com.lanstar.model.tenant.ReviewResultContent;
import com.lanstar.model.tenant.ReviewResultMember;
import com.lanstar.model.tenant.ReviewResultPlan;
import com.lanstar.model.tenant.ReviewResultReport;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.Table;
import com.lanstar.plugin.activerecord.TableMapping;

class ReviewSyncService {
    private final TenantContext source;
    private final TenantContext target;

    private ReviewSyncService( TenantContext source, TenantContext target ) {
        this.source = source;
        this.target = target;
    }

//    public static boolean sync( TenantContext source, TenantContext target, int r_sid, int sid ) {
//        return new ReviewSyncService( source, target ).execute( r_sid, sid );
//    }

    /**
     * 同步企业的自评数据到评审中 
     * @param srcPlanId 企业自评方案的编号
     * @param descPlanId  评审方案的编号
     */
    public static int syncContentFromEnterprise( TenantContext source, TenantContext target, int srcPlanId, int descPlanId ) {
        try {
            String sql = "select * from ssm_review_content where r_sid=?";
            List<ReviewContent> rcs = ReviewContent.dao.find( sql, descPlanId );
            // 目前先简单处理，后面进行比对处理，不在这个集合中的就添加
            if (!rcs.isEmpty()) return -1;
            
            sql = "select * from ssm_grade_content where r_sid=?";
            List<Record> list = source.getTenantDb().find( sql,srcPlanId);           
            for ( Record r : list ) {
                ReviewContent rc = new ReviewContent();
                Map<String,Object> row = r.getColumns();
                row.remove( "SID" );
                row.remove( "R_STD" );
                row.put( "R_SID", descPlanId );
                rc.setAttrs( row  );
                rcs.add( rc );
            }
            int[] r = ModelKit.batchSave( target.getTenantDb(), rcs);
            return r.length;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 同步评审方案到企业端
     */
    public static boolean syncPlan( TenantContext enterpriseContext, TenantContext reviewContext, ReviewPlan model ) {
        ReviewResultPlan descPlan = new ReviewResultPlan();
        ModelKit.clone( model, descPlan );
        Table tableInfo = TableMapping.me().getTable(ReviewResultPlan.class);
        enterpriseContext.getTenantDb().deleteById( tableInfo.getName(),"SID", descPlan.getId() );
        return ModelKit.save( enterpriseContext.getTenantDb(), descPlan );
    }

    /**
     * 同步评审小组成员到企业端
     */
    public static boolean syncPlanMembers( TenantContext enterpriseContext, TenantContext reviewContext, ReviewPlan model ) {
        List<ReviewResultMember> memberlist = new ArrayList<ReviewResultMember>();
        List<ReviewMember> members = model.getMembers();
        for ( ReviewMember src : members ) {
            ReviewResultMember desc = new ReviewResultMember();
            ModelKit.clone( src, desc );
            memberlist.add( desc );
        }
        if (!memberlist.isEmpty())
            ModelKit.batchSave( enterpriseContext.getTenantDb(), memberlist );
        return true;
    }

    /**
     * 同步评审内容到企业端
     */
    public static boolean syncContents( TenantContext enterpriseContext, TenantContext reviewContext, ReviewPlan model ) {
        List<ReviewResultContent> contentlist = new ArrayList<ReviewResultContent>();
        List<ReviewContent> contents = model.getContents();
        for ( ReviewContent src : contents ) {
            ReviewResultContent desc = new ReviewResultContent();
            ModelKit.clone( src, desc );
            contentlist.add( desc );
        }
        if (!contentlist.isEmpty()){
            enterpriseContext.getTenantDb().update( "delete from ssm_result_content where r_sid=? ",model.getId());
            ModelKit.batchSave( enterpriseContext.getTenantDb(), contentlist );
        }
        return true;
    }

    /**
     * 同步评审报告到企业端
     */
    public static boolean syncReport( TenantContext enterpriseContext, TenantContext reviewContext, ReviewPlan model ) {
        ReviewResultReport rep = new ReviewResultReport();
        rep.setContent( model.getReport() );
        Table tableInfo = TableMapping.me().getTable(ReviewResultReport.class);
        enterpriseContext.getTenantDb().deleteById( tableInfo.getName(),"R_SID", model.getId() );
        return ModelKit.save( enterpriseContext.getTenantDb(), rep );
    }
    
    
    /**
     * 同步评审证书到企业端
     */
    public static boolean syncCert( TenantContext enterpriseContext, TenantContext reviewContext, ReviewPlan model ) {
        ReviewCert cert = model.getCert();
        ReviewResultCert descCert = new ReviewResultCert();
        ModelKit.clone( cert, descCert );
        Table tableInfo = TableMapping.me().getTable(ReviewResultCert.class);
        enterpriseContext.getTenantDb().deleteById( tableInfo.getName(),"SID", descCert.getId() );
        return ModelKit.save( enterpriseContext.getTenantDb(), descCert );
    }
}
