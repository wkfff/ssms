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
    protected Class<? extends ActionValidator> getValidator() {
        return grade_dValidator.class;
    }

    @Override
    public ViewAndModel save( HandlerContext context ) {
        // 统计项
        String r_sid = context.getValue( "R_SID" ); 
        String sql = "call P_GRADE_SUM(?)";
        context.DB.getDBSession().execute( sql,new Object[]{r_sid} );
        return super.save( context );
    }
    
    public ViewAndModel sum(HandlerContext context ) {
        return context.returnWith();
    }

    /* (non-Javadoc)
     * @see com.lanstar.controller.DefaultController#list(com.lanstar.core.handle.HandlerContext)
     */
    @Override
    public ViewAndModel list( HandlerContext context ) {
        String r_sid = context.getValue( "R_SID" );
        String type = context.getValue( "type" );
        ARTable arTable = context.DB.withTable( this.TABLENAME );
        //扣分
        if (type.equals( "0" )){
            arTable.where( " N_SCORE_REAL<N_SCORE and R_SID=?" ,r_sid);
        }else if (type.equals( "1" )){//得分
            arTable.where( " N_SCORE_REAL=N_SCORE and R_SID=?" ,r_sid );
        }else if (type.equals( "2" )){
            arTable.where( " B_BLANK='1' and R_SID=?"  ,r_sid);
        }else{
            Map<String, String> filter = this.getFilter( context );
            if ( !filter.isEmpty() ) {
                arTable.where(
                        StringHelper.join( filter.keySet(), " and ", false ),
                        filter.values().toArray() );
            }
        }
        DBPaging paging = context.getPaging();
        JdbcPageRecordSet list = arTable.queryPaging( paging );
        return context.returnWith().set(
                EasyUIControllerHelper.toDatagridResult( list ) );
    }
    
    
}
