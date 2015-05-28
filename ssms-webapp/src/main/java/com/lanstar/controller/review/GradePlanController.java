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
import com.lanstar.app.TenantDsSwitcher;
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
import com.lanstar.plugin.tlds.DsKit;

/**
 * 评审
 *
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
        SqlBuilder select = SQL.SELECT( " SID,C_NAME,C_CODE " );
        SqlBuilder from = new SqlBuilder().FROM( "SYS_TENANT_E" );
        SqlBuilder where = buildWhere();
        if ( where != null ) from.append( where );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {
            
            Page<Record> paginate = Db.paginate( getParaToInt( PAGE_INDEX ), getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(),
                    fromStatement.getSql(),
                    fromStatement.getParams() );
            renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Record> list = Db.find(
                    selectStatement.getSql() + " " + fromStatement.getSql(), fromStatement.getParams() );
            renderJson( list );
        }
    }
    
    @Override
    protected void beforeSave( GradePlanR model, boolean[] handled ) {
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) {
            isNew = true;
        }
    }

    @Override
    protected void afterSave( GradePlanR model ) {
        Integer sid = model.getInt( "SID" );
        String eid = this.getPara( "R_EID" );//企业的CODE
        String myDbCode =  this.identityContext.getTenantDbCode(); //评审的
        Config config = DbKit.getConfig( Const.TENANT_DB_NAME );
        if (isNew && eid!= null){
            //TODO:切换到企业租户库,根据企业编码取企业租户数据库
            DsKit.switchDs( config.getDataSource(), myDbCode );

            //从企业租户库中获取指定企业的自评数据
            String sql = SqlKit.sql( "tenant.grade.getDataFromEnterprise" );
            List<Record> rs = Db.use( Const.TENANT_DB_NAME ).find( sql, eid );
            //切换回评审端租户库
            DsKit.switchDs( config.getDataSource(), myDbCode );
            List<String> sqls = new ArrayList<String>();
            for(Record r:rs){
                //写入到评审租户库的评审明细表
                sql = SqlKit.sql( "tenant.grade.insertToReview" );
                SqlBuilder sb = new SqlBuilder();
                sql = sb._( sql, r ).toString();
                sqls.add( sql );
            }
            if (sqls.size()>0)
                Db.use( Const.TENANT_DB_NAME ).batch( sqls, 500 );
        }
    }
    
    /**
     * 评审列表，要根据省市县过滤，所以从视图查询
     */
    public void list_r() {
        SqlBuilder select = SQL.SELECT( "*" );
        SqlBuilder from = new SqlBuilder().FROM( "V_GRADE_R_M" );
        SqlBuilder where = buildWhere();
        if ( where != null ) from.append( where );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {
            Page<Record> paginate = this.tenantDb.paginate( getParaToInt( PAGE_INDEX ), getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(),
                    fromStatement.getSql(),
                    fromStatement.getParams() );
            renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Record> list = this.tenantDb.find(
                    selectStatement.getSql() + " " + fromStatement.getSql(), fromStatement.getParams() );
            renderJson( list );
        }
    }
    
    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE()
               ._If( isParaExists( "P_PRO" ), "P_PRO = ?", getPara( "P_PRO" ) )
               ._If( isParaExists( "P_CITY" ), "P_CITY = ?", getPara( "P_CITY" ) )
               ._If( isParaExists( "P_COUNTY" ), "P_COUNTY = ?", getPara( "P_COUNTY" ) )
               ._If( isParaExists( "N_STATE" ), "N_STATE = ?", getPara( "N_STATE" ) )
               ._If( isParaBlank( "T_START" ) == false, "T_START >= ?", getPara( "T_START" ) )
               ._If( isParaBlank( "T_END" ) == false, "T_END <= ?", getPara( "T_END" ) );
        return builder;
    }
    
    public void rec_new(){
        this.setAttr( "S_TENANT",this.identityContext.getTenantName());
    }
    
    public void recdata() {
        super.rec();
        renderJson();
    }
}
