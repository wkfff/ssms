/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewResultContentController.java
 * 创建时间：2015年6月24日 上午11:54:38
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.ReviewResultContent;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

/**
 * 评审内容结果
 *
 */
public class ReviewResultContentController  extends SimplateController<ReviewResultContent> {

    @Override
    protected ReviewResultContent getDao() {
        return ReviewResultContent.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE( "R_TENANT = ?", identityContext.getTenantId() )
               .WHERE( "P_TENANT = ?", identityContext.getTenantType().getName() )
               .WHERE( "R_SID = ?", getPara( "R_SID" ) )
               ._If( isParaExists( "N_STATE" ), "N_STATE = ?", getPara( "N_STATE" ) );
        return builder;
    }

    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder builder = new SqlBuilder();
        builder.ORDER_BY( " N_INDEX,SID " );
        return builder;
    }
}
