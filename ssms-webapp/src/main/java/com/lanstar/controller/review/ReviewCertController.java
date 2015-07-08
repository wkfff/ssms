/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewCertController.java
 * 创建时间：2015年6月18日 上午10:45:12
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.ReviewCert;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

/**
 * 评审方案控制器
 *
 */
public class ReviewCertController extends SimplateController<ReviewCert> {

    @Override
    protected ReviewCert getDao() {
        return ReviewCert.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        Integer tenantId=identityContext.getTenant().getTenantId();
        return new SqlBuilder().WHERE("R_TENANT=?",tenantId);
    }

    @Override
    public void rec() {
        ReviewCert model = null;
        String sid = getPara( "sid" );
        if ( StrKit.isEmpty( sid ) ) sid = getPara( "SID" );
        if ( sid == null ) {
            sid = getPara( "R_SID" );
            model = getDao().findFirst( "select * from ssm_review_cert where r_sid=?", sid );
        } else model = getDao().findById( sid );

        if ( model != null ) setAttrs( ModelKit.toMap( model ) );
    }

    public void view() {
        this.rec();
        render( "view.ftl" );
    }
}
