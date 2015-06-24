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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.kit.DateKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.TenantContext;
import com.lanstar.model.system.AttachText;
import com.lanstar.model.system.Enterprise;
import com.lanstar.model.system.Profession;
import com.lanstar.model.tenant.GradeContentR;
import com.lanstar.model.tenant.GradePlan;
import com.lanstar.model.tenant.GradePlanR;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;
import com.lanstar.service.review.ReviewService;

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

    public void select2() {

    }

    /**
     * 待评审的企业数据
     */
    public void list_e() {
        SqlBuilder select = SQL.SELECT( " * " );
        SqlBuilder from = new SqlBuilder().FROM( "V_TENANT_E" );
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
            model.setState( ReviewState.START.getValue() );
        }
    }

    @Override
    protected void afterSave( GradePlanR model ) {
        int sid = model.getInt( "SID" );
        int mid = model.getInt( "R_EID" ); // 自评编号
        Integer eid = model.getInt( "R_TENANT_E" ); // 企业编号
        // String eid = this.getPara( "R_EID" ); // 企业的SID

        if ( this.isNew && eid != null ) {
            // 设置企业的评审状态与评审机构
            Enterprise enterprise = Enterprise.dao.findById( eid );
            enterprise.setReview( enterprise );
            enterprise.setGradeState( ReviewState.START.getValue() );

            // 获取评审服务
            ReviewService service = this.identityContext.getReviewService();
            // 同步企业的评审数据
            service.sync( mid, sid );
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
        String name = this.getPara( "C_NAME" );
        try {
            if ( !StrKit.isBlank( name ) ) name = URLDecoder.decode( name, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {

        }
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE()
        ._If( this.isParaExists( "P_PRO" ) && !StrKit.isBlank( this.getPara( "P_PRO" ) ), "P_PROVINCE = ?",
                this.getPara( "P_PRO" ) )
                ._If( this.isParaExists( "P_CITY" ) && !StrKit.isBlank( this.getPara( "P_CITY" ) ), "P_CITY = ?",
                        this.getPara( "P_CITY" ) )
                        ._If( this.isParaExists( "P_COUNTY" ) && !StrKit.isBlank( this.getPara( "P_COUNTY" ) ), "P_COUNTY = ?",
                                this.getPara( "P_COUNTY" ) )
                                ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara( "N_STATE" ) )
                                ._If( this.isParaBlank( "C_NAME" ) == false, "C_NAME like ?", "%" + name + "%" )
                                ._If( this.isParaBlank( "R_TENANT_E" ) == false, "R_TENANT_E = ?", this.getPara( "R_TENANT_E" ) )
                                ._If( this.isParaBlank( "T_START" ) == false, "T_START >= ?", this.getPara( "T_START" ) )
                                ._If( this.isParaBlank( "T_END" ) == false, "T_END <= ?", this.getPara( "T_END" ) );

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
        this.tenantDb.callProcedure( "P_GRADE_COMPLETE_R", this.getModel().getId(), ReviewState.END.getValue() );
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
                    ReviewState.NONE.getValue(), sid );
            this.setAttr( "result", "OK" );
        }
        this.renderJson();
    }

    /**
     * 评审报告
     */
    public void report_rec() {
        this.rec();
    }

    /**
     * 上传评审结果
     */
    public void upload_rec() {
        this.rec();
    }

    /**
     * 评审历史
     */
    public void history() {

    }

    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder builder = new SqlBuilder();
        builder.ORDER_BY( "T_END DESC" );
        return builder;
    }

    public void tabs() {
        // 企业
        int eid = this.getParaToInt( "sid" );
        // 专业
        int pro = this.getParaToInt( "pro" );
        Enterprise enterprise = Enterprise.dao.findById( eid );
        Profession profession = Profession.dao.findById( pro );
        this.identityContext.initReviewService( enterprise, profession );

        // 根据企业编号获取企业最后一次完成的自评编号
        String sql = "SELECT SID FROM SSM_GRADE_E_M WHERE R_TENANT=? AND N_STATE=3  ORDER BY T_UPDATE DESC LIMIT 1";
        GradePlan gp = GradePlan.dao.findFirst( sql, eid );
        if ( gp != null ) {
            this.setAttr( "gradeid", gp.getId() );

            // 判断是否已经开始评审了
            sql = "SELECT SID FROM SSM_GRADE_R_M WHERE R_TENANT_E=? AND R_EID=? ORDER BY T_UPDATE DESC LIMIT 1";
            GradePlanR gpr = GradePlanR.dao.findFirst( sql, eid, gp.getId() );
            if ( gpr == null ) {
                gpr = new GradePlanR();
                gpr.setTitle( enterprise == null ? "" : enterprise.getName() );
                gpr.set( "R_TENANT_E", eid );
                gpr.set( "S_TENANT_E", enterprise == null ? "" : enterprise.getName() );
                gpr.set( "R_EID", gp.getId() );
                String d = DateKit.toStr( new Date() );
                gpr.set( "T_START", d );
                gpr.set( "T_END", d );
                gpr.save();
                this.isNew = true;
                this.afterSave( gpr );
            }
            this.setAttr( "graderid", gpr.getId() );
        }
    }

    public void result() {

    }

    /**
     * 评审历史查看.评分汇总表
     */
    public void sum() {
        int sid = this.getModel().getId();
        List<Record> list = this.tenantDb.find( "select * from V_GRADE_SUM_R where R_SID=?", new Object[] { sid } );
        this.setAttr( "list", list );
        this.setAttr( "S_TENANT", this.identityContext.getTenantName() );
    }

    /**
     * 评审历史查看.扣分项汇总表
     */
    public void sum_ded() {
        int sid = this.getModel().getId();
        List<Record> list = this.tenantDb.find( "select * from V_GRADE_SUM_DED_R where R_SID=?", new Object[] { sid } );
        this.setAttr( "list", list );
        this.setAttr( "S_TENANT", this.identityContext.getTenantName() );
    }

    /**
     * 评审历史查看.评审报告
     */
    public void history_rep() {

    }
}
