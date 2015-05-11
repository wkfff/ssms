/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：gradeController.java
 * 创建时间：2015年5月8日 下午4:15:29
 * 创建用户：林峰
 */
package com.lanstar.controller.r;

import java.util.Map;

import com.google.common.base.Strings;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.dialect.JdbcPageRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;

/**
 * 评审
 *
 */
public class grade_mController extends DefaultController {

    public grade_mController() {
        super( "SSM_GRADE_R_M" );
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
    /**
     * 选择企业
     */
    public ViewAndModel select( HandlerContext context ) {
        return context.returnWith();
    }
    /**
     * 列出待评审的企业自评记录
     */
    public ViewAndModel list_e( HandlerContext context) {
        ARTable arTable = context.DB.withTable( "V_GRADE_E_M" );
        Map<String, String> filter = this.getFilter( context );
        if ( !filter.isEmpty() ) {
            arTable.where(
                    StringHelper.join( filter.keySet(), " and ", false ),
                    filter.values().toArray() );
        }
        DBPaging paging = context.getPaging();
        if ( paging == null ) {
            JdbcRecordSet list = arTable.queryList();
            return context.returnWith().set( list );
        } else {
            JdbcPageRecordSet list = arTable.queryPaging( paging );
            return context.returnWith().set(
                    EasyUIControllerHelper.toDatagridResult( list ) );
        }
    }
    /**
     * 评审新建
     */
    public ViewAndModel rec_new( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        JdbcRecord record = null;
        if ( StringHelper.vaildValue( sid ) ) {
            record = context.DB.withTable( this.TABLENAME )
                    .where( "SID=?", sid ).query();
        }
        return context.returnWith().set( record );
    }
    /**
     * 评审完成
     */
    public ViewAndModel complete( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        context.DB.getDBSession().execute( "call P_GRADE_COMPLETE_R(?)",
                new Object[] { sid } );
        return context.returnWith().set( "" );
    }
    
    /**
     * 验证评审内容是否都已经填写
     * 
     * @param context
     * @return 大于0时说明还有未填写内容
     */
    public ViewAndModel check( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        JdbcRecord r = context.DB.getDBSession().first(
                "SELECT F_GRADE_CHECK_R(?) N", new Object[] { sid } );
        return context.returnWith().set( r.get( "N" ) );
    }
}
