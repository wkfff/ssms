/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_dController.java
 * 创建时间：2015年4月22日 下午3:03:28
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import java.util.Map;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.dialect.JdbcPageRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;

/**
 * 在线自评
 *
 */
public class grade_dController extends DefaultController {
    public grade_dController() {
        super( "SSM_GRADE_E_D" );
    }

    @Override
    public void setFilterFields() {
        this.filterFields
                .put( "score",
                        " IFNULL(N_SCORE_REAL,0)=? AND C_PROJECT<>\"小计\" AND C_PROJECT<>\"总计\" " );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_dValidator.class;
    }

    /**
     * 保存时统计项
     */
    @Override
    public ViewAndModel save( HandlerContext context ) {
        // 先验证下参数
        this.validatePara( context );
        String sid = context.getValue( "sid" );
        ARTable table = context.DB.withTable( this.TABLENAME );
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        // 根据sid的存在设置where语句
        table.where(
                !StringHelper.isBlank( sid ) && StringHelper.vaildValue( sid ),
                "SID=?", sid ).save();
        String r_sid = context.getValue( "R_SID" );
        String sql = "call P_GRADE_SUM(?)";
        context.DB.getDBSession().execute( sql, new Object[] { r_sid } );
        return context.returnWith();
    }

    public ViewAndModel sum( HandlerContext context ) {
        return context.returnWith();
    }

    @Override
    public ViewAndModel list( HandlerContext context ) {
        ARTable arTable = context.DB.withTable( this.TABLENAME );
        arTable.columns( "SID,R_SID,C_CATEGORY,C_PROJECT,C_CONTENT,N_SCORE,C_METHOD,C_DESC,B_BLANK,N_SCORE_REAL" );
        Map<String, String> filter = this.getFilter( context );
        if ( !filter.isEmpty() ) {
            arTable.where(
                    StringHelper.join( filter.keySet(), " and ", false ),
                    filter.values().toArray() );
        }
        arTable.orderby( "N_INDEX,SID" );
        DBPaging paging = context.getPaging();
        JdbcPageRecordSet list = arTable.queryPaging( paging );
        return context.returnWith().set(
                EasyUIControllerHelper.toDatagridResult( list ) );
    }

}
