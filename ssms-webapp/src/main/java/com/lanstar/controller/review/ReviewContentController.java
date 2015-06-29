/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewContentController.java
 * 创建时间：2015年6月26日 上午9:24:14
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.ReviewContent;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

/**
 * 评审内容
 *
 */
public class ReviewContentController extends SimplateController<ReviewContent> {

    @Override
    protected ReviewContent getDao() {
        return ReviewContent.dao;
    }
    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder builder = new SqlBuilder();
        // builder.WHERE( "R_TENANT = ?", this.identityContext.getTenantId() )
        // .WHERE( "P_TENANT = ?",
        // this.identityContext.getTenantType().getName() )
        // .WHERE( "R_SID = ?", this.getPara( "R_SID" ) )
        // ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara(
        // "N_STATE" ) )
        // ._If( this.isParaBlank( "NOCOMPLETE" ) == false,
        // " IFNULL(N_SCORE_REAL,0) = ?", 0 );
        builder.WHERE( "R_SID = ?", this.getPara( "R_SID" ) )
                ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara( "N_STATE" ) )
                ._If( this.isParaBlank( "NOCOMPLETE" ) == false, " IFNULL(N_SCORE_REVIEW,0) = ? AND IFNULL(B_BLANK,'0')<>'1'", 0 );
        return builder;
    }

    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder builder = new SqlBuilder();
        builder.ORDER_BY( "N_INDEX" );
        return builder;
    }

    @Override
    protected void afterSave( ReviewContent model ) {
        this.tenantDb.callProcedure( "P_GRADE_SUM_R", model.getInt( "R_SID" ) );
    }
}
