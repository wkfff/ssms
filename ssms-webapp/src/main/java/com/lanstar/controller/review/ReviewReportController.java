/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewReportController.java
 * 创建时间：2015年6月26日 上午9:47:28
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.core.render.JsonRender;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.tenant.ReviewReport;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Record;

/**
 * 评审报告
 *
 */
public class ReviewReportController extends SimplateController<ReviewReport> {
    @Override
    protected ReviewReport getDao() {
        return ReviewReport.dao;
    }

    
    /**
     * 评审报告编辑
     */
    @Override
    public void rec() {
        ReviewReport model = null;
        String sid = this.getPara( "sid" );
        if ( StrKit.isEmpty( sid ) ) sid = this.getPara( "SID" );
        if ( sid == null ) {
            sid = this.getPara( "R_SID" );
            if (!StrKit.isBlank( sid )) model = this.getDao().getReport( Integer.parseInt( sid ) );
        } else model = this.getDao().findById( sid );

        if ( model != null ) this.setAttrs( ModelKit.toMap( model ) );
    }

    /**
     * 查看企业自评报告
     */
    public void rep() {
        String sid = this.getPara( "sid" );
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        Record r = identityContext.getReviewService().getEnterpriseContext().getTenantDb().findFirst( "select * from SSM_GRADE_REPORT where r_sid=?", sid );
        if (r!=null)
            this.setAttr( "C_CONTENT", r.getStr( "C_CONTENT" ) );
        render( "view.ftl" );
    }

    /**
     * 查看评审报告
     */
    public void view() {
        int sid = this.getParaToInt( "sid" );
        ReviewReport model = ReviewReport.dao.getReport( sid );
        if (model!=null)
            this.setAttr( "C_CONTENT", model.getContent() );
    }
    
    @Override
    public void save() {
        ReviewReport model = getModel();
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) model.save();
        else {
            model.setState( 1 );
            model.update();
        }
        sid = model.getInt( "SID" );
        this.setAttr( "SID", sid );
        this.render( new JsonRender( sid ).forIE() );
    }
}
