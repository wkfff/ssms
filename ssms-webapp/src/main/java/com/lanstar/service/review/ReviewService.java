/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewService.java
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

package com.lanstar.service.review;

import com.lanstar.identity.TenantContext;

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

    public boolean sync(int r_sid,int sid){
        return ReviewSyncService.sync( enterpriseContext, reviewContext,r_sid,sid );
    }
}
