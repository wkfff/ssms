/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_dController.java
 * 创建时间：2015年5月11日 下午3:39:02
 * 创建用户：林峰
 */
package com.lanstar.controller.r;

import java.util.Map;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;

public class grade_dController extends DefaultController {

    public grade_dController() {
        super( "SSM_GRADE_R_D" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_dValidator.class;
    }
    
    @Override
    public void setFilterFields() {
        this.filterFields.put( "score", " IFNULL(N_SCORE_REAL,0)=? AND C_PROJECT<>\"小计\" AND C_PROJECT<>\"总计\" " );
    }
    
    /**
     * 评审内容
     */
    public ViewAndModel list_detail( HandlerContext context) {
        ARTable arTable = context.DB.withTable( "V_GRADE_R_D" );
        Map<String, String> filter = this.getFilter( context );
        if ( !filter.isEmpty() ) {
            arTable.where(
                    StringHelper.join( filter.keySet(), " and ", false ),
                    filter.values().toArray() );
        }
        JdbcRecordSet list = arTable.queryList();
        return context.returnWith().set( list );
    }
}
