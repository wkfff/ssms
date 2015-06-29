/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewReportController.java
 * 创建时间：2015年6月26日 上午9:47:28
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;

/**
 * 评审报告
 *
 */
public class ReviewReportController extends Controller {
    /**
     * 评审报告编辑
     */
    public void rec() {

    }

    /**
     * 查看企业自评报告
     */
    public void rep() {
        String sid = this.getPara( "sid" );
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        String content = identityContext.getReviewService().getEnterpriseContext().getAttachTextService()
                .getContent( "SSM_GRADE_REPORT", "C_CONTENT", Integer.parseInt( sid ) );
        this.setAttr( "content", content );
        render( "view.ftl" );
    }

    /**
     * 查看评审报告
     */
    public void view() {
        String sid = this.getPara( "sid" );
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        String content = identityContext.getAttachTextService().getContent( "SSM_REVIEW_REPORT", "C_CONTENT", Integer.parseInt( sid ) );
        this.setAttr( "content", content );
    }
}
