/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeContentController.java
 * 创建时间：2015年5月27日 下午3:07:28
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

import java.util.List;

import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.GradeContentR;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

/**
 * 评审明细
 *
 */
public class GradeContentController extends SimplateController<GradeContentR> {

    @Override
    protected GradeContentR getDao() {
        return GradeContentR.dao;
    }

    public void list_detail() {
        SqlBuilder select = SQL.SELECT( "*" );
        SqlBuilder from = new SqlBuilder().FROM( "V_GRADE_R_D" );
        SqlBuilder where = this.buildWhere();
        if ( where != null ) from.append( where );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {

            Page<Record> paginate = this.tenantDb.paginate( this.getParaToInt( PAGE_INDEX ),
                    this.getParaToInt( PAGE_SIZE ), selectStatement.getSql(), fromStatement.getSql(),
                    fromStatement.getParams() );
            this.renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Record> list = this.tenantDb.find( selectStatement.getSql() + " " + fromStatement.getSql(),
                    fromStatement.getParams() );
            this.renderJson( list );
        }
    }

    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder builder = new SqlBuilder();
//        builder.WHERE( "R_TENANT = ?", this.identityContext.getTenantId() )
//                .WHERE( "P_TENANT = ?", this.identityContext.getTenantType().getName() )
//                .WHERE( "R_SID = ?", this.getPara( "R_SID" ) )
//                ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara( "N_STATE" ) )
//                ._If( this.isParaBlank( "NOCOMPLETE" ) == false, " IFNULL(N_SCORE_REAL,0) = ?", 0 );
        builder.WHERE( "R_SID = ?", this.getPara( "R_SID" ) )
        ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara( "N_STATE" ) )
        ._If( this.isParaBlank( "NOCOMPLETE" ) == false, " IFNULL(N_SCORE_REAL,0) = ?", 0 );
        return builder;
    }

}
