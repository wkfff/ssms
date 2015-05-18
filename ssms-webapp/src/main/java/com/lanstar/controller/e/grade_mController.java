/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_mController.java
 * 创建时间：2015年4月23日 上午10:45:42
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import com.google.common.base.Strings;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.ar.ARTable;
import com.lanstar.model.Profession;

public class grade_mController extends DefaultController {

    public grade_mController() {
        super( "SSM_GRADE_E_M" );
    }

    @Override
    public void setFilterFields() {
        this.filterFields.put( "T_START", "T_START >= ?" );
        this.filterFields.put( "T_END", "T_END <= ?" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_mValidator.class;
    }

    @Override
    public ViewAndModel index( HandlerContext context ) {
        
        return super.index( context );
    }

    @Override
    public ViewAndModel rec( HandlerContext context ) {
        Profession pro = context.getIdentityContxt().get( Profession.class );
        context.setValue( "P_PROFESSION",pro.getProfessionId());
        return super.rec( context );
    }
    
    public ViewAndModel rec_new( HandlerContext context ) {
        context.setValue( "S_TENANT", context.getIdentity().getTenantName() );
        Profession pro = context.getIdentityContxt().get( Profession.class );
        context.setValue( "P_PROFESSION",pro.getProfessionId());
        return super.rec( context );
    }

    /**
     * 保存
     */
    @Override
    public ViewAndModel save( HandlerContext context ) {
        this.validatePara( context );
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        boolean isNew = MergerType.withSid( sid ).compareTo( MergerType.forInsert ) == 0;
        ARTable table = context.DB.withTable( this.TABLENAME );
        if ( isNew ) {
            table.value( "C_TITLE", context.getValue( "T_START" ) + "企业自评" );
            table.value( "N_STATE", 0 );
        }
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        table.where( !isNew,"SID=?", sid ).save();

        if ( isNew ) {
            sid = Integer.toString( context.DB.getSID() );
            Profession pro = context.getIdentityContxt().get( Profession.class );
            this.init( context, sid, pro.getProfessionId() );
        }

        return context.returnWith().put( "SID", sid );
    }

    /**
     * 初始化企业自评表
     *
     * @param context
     * @return
     */
    public ViewAndModel init( HandlerContext context, String sid,
            int profession ) {
        context.DB.getDBSession().execute( "call P_GRADE_INIT(?,?,?,?)",
                new Object[] { sid,profession,context.getIdentity().getTenantId(),context.getIdentity().getTenantType().getName() } );
        return context.returnWith();
    }

    /**
     * 自评历史.列表
     */
    public ViewAndModel history( HandlerContext context ) {
        return context.returnWith();
    }

    /**
     * 自评历史.查看自评结果
     */
    public ViewAndModel history_rec( HandlerContext context ) {
        return super.rec( context );
    }

    /**
     * 自评历史.查看自评报告
     */
    public ViewAndModel history_rep( HandlerContext context ) {
        return super.rec( context );
    }

    /**
     * 自评草稿.列表
     */
    public ViewAndModel draft( HandlerContext context ) {
        return context.returnWith();
    }

    /**
     * 自评草稿.编辑
     */
    public ViewAndModel draft_rec( HandlerContext context ) {
        return super.rec( context );
    }

    /**
     * 自评报告.编辑
     */
    public ViewAndModel report_rec( HandlerContext context ) {
        return super.rec( context );
    }

    /**
     * 自评完成
     */
    public ViewAndModel complete( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        context.DB.getDBSession().execute( "call P_GRADE_COMPLETE_E(?)",
                new Object[] { sid } );
        return context.returnWith().set( "" );
    }
    /**
     * 自评结果
     */
    public ViewAndModel result( HandlerContext context ) {
        return context.returnWith();
    }

    /**
     * 验证自评内容是否都已经填写
     * 
     * @param context
     * @return 大于0时说明还有未填写内容
     */
    public ViewAndModel check( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        JdbcRecord r = context.DB.getDBSession().first(
                "SELECT F_GRADE_CHECK_E(?) N", new Object[] { sid } );
        return context.returnWith().set( r.get( "N" ) );
    }

    /**
     * 删除主表时同时删除从表
     */
    @Override
    public ViewAndModel del( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        if ( !Strings.isNullOrEmpty( sid ) ) {
            context.DB.withTable( "SSM_GRADE_E_D" ).where( "R_SID = ?", sid ).delete();
            context.DB.withTable( "SYS_ATTACH_TEXT" ).where( "R_SID = ? AND R_TABLE='SSM_GRADE_REPORT' AND R_FIELD='content'", sid ).delete();
        }
        return super.del( context );
    }

    /**
     * 评分汇总表
     */
    public ViewAndModel sum( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        ARTable arTable = context.DB.withTable( this.TABLENAME );
        String name = StringHelper.empty( arTable.where( "SID = ?", sid )
                .query().getString( "S_TENANT" ), "佚名" );
        arTable = context.DB.withTable( "V_GRADE_SUM" );
        arTable.where( "R_SID = ?", sid );
        return context.returnWith().put( "list", arTable.queryList() )
                .put( "S_TENANT", name );
    }

    /**
     * 扣分项汇总表
     */
    public ViewAndModel sum_ded( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        ARTable arTable = context.DB.withTable( this.TABLENAME );
        String name = StringHelper.empty( arTable.where( "SID = ?", sid )
                .query().getString( "S_TENANT" ), "佚名" );
        arTable = context.DB.withTable( "V_GRADE_SUM_DED" );
        arTable.where( "R_SID = ?", sid );
        return context.returnWith().put( "list", arTable.queryList() )
                .put( "S_TENANT", name );
    }
}
