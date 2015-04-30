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
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.statement.SqlBuilder;

/**
 * 自评历史
 *
 */
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
    public ViewAndModel rec( HandlerContext context ) {
        context.setValue( "_FLAG_", "0" );  //_FLAG_:0新建，-1历史，1草稿
        context.setValue( "S_TENANT", context.getIdentity().getTenantName());
        // TODO:根据企业的专业来设置
        context.setValue( "P_PROFESSION", "1" );
        return super.rec( context );
    }

    @Override
    public ViewAndModel save( HandlerContext context ) {
        this.validatePara( context );
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );

        ARTable table = context.DB.withTable( this.TABLENAME );
        if ( MergerType.withSid( sid ).compareTo( MergerType.forInsert ) == 0 ) {
            table.value( "C_TITLE", context.getValue( "T_START" ) + "企业自评" );
            table.value( "N_STATE", 0);
        }
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        table.where( !StringHelper.isBlank( sid ) && !sid.equals( "null" ),
                "SID=?", sid ).save();

        if ( StringHelper.isBlank( sid ) || sid.equals( "null" ) ) {
            sid = Integer.toString( context.DB.getSID() );
            this.init( context, sid, "1" );
        }

        return context.returnWith().set( sid );
    }

    /**
     * 初始化企业自评表
     * 
     * @param context
     * @return
     */
    public ViewAndModel init( HandlerContext context, String sid,
            String profession ) {
        // TODO:根据企业的专业从评分标准表中复制
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder
                .INSERT_INTO(
                        "SSM_GRADE_E_D(R_SID,R_STD,R_CATEGORY,S_CATEGORY,R_PROJECT,S_PROJECT,C_CONTENT,N_SCORE,C_METHOD,R_TENANT,S_TENANT,P_TENANT,N_STATE) "
                                + "select ?,SID,R_CATEGORY,S_CATEGORY,R_PROJECT,S_PROJECT,C_CONTENT,N_SCORE,C_METHOD,?,?,?,0 from SSM_GRADE_STD where P_PROFESSION=?",
                        new Object[] { sid,
                                context.getIdentity().getTenantId(),
                                context.getIdentity().getTenantName(),
                                context.getIdentity().getTenantType().getName(),
                                profession } );
        context.DB.execute( sqlBuilder );
        return context.returnWith();
    }

    public ViewAndModel history( HandlerContext context ) {
        return context.returnWith();
    }
    
    public ViewAndModel rec_history( HandlerContext context ) {
        context.setValue( "_FLAG_", "-1" );
        return context.returnWith().view( "rec" );
    }

    public ViewAndModel draft( HandlerContext context ) {
        return context.returnWith();
    }
    
    public ViewAndModel rec_draft( HandlerContext context ) {
        context.setValue( "_FLAG_", "1" );
        return context.returnWith().view( "rec" );
    }
    
    public ViewAndModel complete( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        context.DB.getDBSession().execute( "call P_GRADE_COMPLETE_SELF(?)", new Object[]{sid} );
        return context.returnWith().set( "" );
    }
    
    public ViewAndModel rec_result( HandlerContext context ) {
        context.setValue( "_FLAG_", "-2" );
        return context.returnWith().view( "rec" );
    }
    
    public ViewAndModel result( HandlerContext context ) {
        context.setValue( "_FLAG_", "-1" );
        return context.returnWith();
    }
}
