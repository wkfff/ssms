/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradePlanController.java
 * 创建时间：2015年5月27日 上午11:43:02
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

import java.util.ArrayList;
import java.util.List;

import com.lanstar.app.Const;
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.GradePlanR;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;
import com.lanstar.plugin.sqlinxml.SqlKit;

/**
 * 评审
 */
public class GradePlanController extends SimplateController<GradePlanR> {
    boolean isNew = false;

    @Override
    protected GradePlanR getDao() {
        return GradePlanR.dao;
    }

    /**
     * 列出待评审的企业
     */
    public void select() {

    }

    /**
     * 待评审的企业数据
     */
    public void list_e() {
        SqlBuilder select = SQL.SELECT( " SID,C_NAME,C_CODE,R_REVIEW,S_REVIEW " );
        SqlBuilder from = new SqlBuilder().FROM( "SYS_TENANT_E" );
        SqlBuilder where = this.buildWhere();
        if ( where != null ) from.append( where );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {

            Page<Record> paginate = Db.paginate( this.getParaToInt( PAGE_INDEX ), this.getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(), fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Record> list = Db.find( selectStatement.getSql() + " " + fromStatement.getSql(),
                    fromStatement.getParams() );
            this.renderJson( list );
        }
    }

    @Override
    protected void beforeSave( GradePlanR model, boolean[] handled ) {
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) {
            this.isNew = true;
            model.setTitle( model.getStartDate() + "评审" );
            model.setState( ReviewState.DOING.getValue() );
        }
    }

    @Override
    protected void afterSave( GradePlanR model ) {
        Integer sid = model.getInt( "SID" );
        String eid = this.getPara( "R_EID" );// 企业的SID
        String myDbCode = this.identityContext.getTenantDbCode(); // 评审的
        Config config = DbKit.getConfig( Const.TENANT_DB_NAME );
        if ( this.isNew && eid != null ) {
            // TODO:切换到企业租户库,根据企业编码取企业租户数据库
            // DsKit.switchDs( config.getDataSource(), myDbCode );

            // 从企业租户库中获取指定企业的自评数据
            String sql = SqlKit.sql( "tenant.grade.getDataFromEnterprise" );
            // List<Record> rs = Db.use( Const.TENANT_DB_NAME ).find( sql, eid
            // );
            // 切换回评审端租户库
            // DsKit.switchDs( config.getDataSource(), myDbCode );
            List<Record> rs = Db.find( sql, eid );
            List<String> sqls = new ArrayList<String>();
            for ( Record r : rs ) {
                // 写入到评审租户库的评审明细表
                // sql = SqlKit.sql( "tenant.grade.insertToReview" );
                sql = "insert into ssm_grade_r_d (R_SID,C_CATEGORY,C_PROJECT,C_REQUEST,C_CONTENT,N_SCORE,C_METHOD,C_DESC,B_BLANK,N_SCORE_REAL,C_DESC_REVIEW,N_SCORE_REVIEW) values("
                        + sid
                        + ",'"
                        + r.getStr( "C_CATEGORY" )
                        + "','"
                        + r.getStr( "C_PROJECT" )
                        + "','"
                        + r.getStr( "C_REQUEST" )
                        + "','"
                        + r.getStr( "C_CONTENT" )
                        + "',"
                        + r.getInt( "N_SCORE" )
                        + ",'"
                        + r.getStr( "C_METHOD" )
                        + "','"
                        + r.getStr( "C_DESC" )
                        + "','"
                        + (r.getStr( "B_BLANK" ) == null || r.getStr( "B_BLANK" ).equals( "null" ) ? "" : r
                                .getStr( "B_BLANK" ))
                                + "',"
                                + r.getInt( "N_SCORE_REAL" )
                                + ",'"
                                + (r.getStr( "C_DESC_REVIEW" ) == null || r.getStr( "C_DESC_REVIEW" ).equals( "null" ) ? "" : r
                                        .getStr( "C_DESC_REVIEW" )) + "'," + r.getStr( "N_SCORE_REVIEW" ) + ")";
                sqls.add( sql );
            }
            if ( sqls.size() > 0 ) Db.use( Const.TENANT_DB_NAME ).batch( sqls, 500 );
        }
    }

    /**
     * 评审列表，要根据省市县过滤，所以从视图查询
     */
    public void list_r() {
        SqlBuilder select = SQL.SELECT( "*" );
        SqlBuilder from = new SqlBuilder().FROM( "V_GRADE_R_M" );
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
        builder.WHERE()._If( this.isParaExists( "P_PRO" ), "P_PRO = ?", this.getPara( "P_PRO" ) )
        ._If( this.isParaExists( "P_CITY" ), "P_CITY = ?", this.getPara( "P_CITY" ) )
        ._If( this.isParaExists( "P_COUNTY" ), "P_COUNTY = ?", this.getPara( "P_COUNTY" ) )
        ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara( "N_STATE" ) )
        ._If( this.isParaBlank( "C_NAME" ) == false, "C_NAME like ?", "%" + this.getPara( "C_NAME" ) + "%" );
        return builder;
    }

    public void rec_new() {
        this.setAttr( "S_TENANT", this.identityContext.getTenantName() );
    }

    public void recdata() {
        super.rec();
        this.renderJson();
    }

    /**
     * 验证评审内容是否都已经填写,N大于0时说明还有未填写内容
     */
    public void check() {
        Record r = this.tenantDb.findFirst( "SELECT F_GRADE_CHECK_R(?) N", new Object[] { this.getModel().getId() } );
        this.setAttr( "N", r == null ? 0 : r.getInt( "N" ) );
        this.renderJson();
    }

    /**
     * 评审完成
     */
    public void complete() {
        this.tenantDb.callProcedure( "P_GRADE_COMPLETE_R", this.getModel().getId(),ReviewState.END.getValue() );
        this.setAttr( "result", "OK" );
        this.renderJson();
    }

    /**
     * 撤销误评审
     */
    public void undo() {
        Integer sid = this.getParaToInt( "sid" );
        if ( sid != null ) {
            Db.update( "UPDATE sys_tenant_e  SET N_STATE=? WHERE sid=(SELECT r_eid FROM SSM_GRADE_R_M WHERE sid=?)",
                    ReviewState.NOSTART.getValue(), sid );
            this.setAttr( "result", "OK" );
        }
        this.renderJson();
    }
    /**
     * 评审报告
     */
    public void report_rec(){
        rec();
    }
}
