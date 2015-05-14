/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：navController.java
 * 创建时间：2015-04-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.controller.TableProcessor;
import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;

public class navController extends DefaultController {
    public navController() {
        super( "SYS_NAV" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return null;
    }

    public ViewAndModel tree( HandlerContext context ) {
        JdbcRecordSet records = context.SYSTEM_DB.withTable( TABLENAME )
                                                 .orderby( "R_SID, N_INDEX" )
                                                 .queryList();
        return context.returnWith().set( EasyUIControllerHelper.toTree("0", records, "SID", "R_SID", "C_NAME" ) );
    }

    @Override
    public ViewAndModel list( HandlerContext context ) {
        return list( context, new TableProcessor() {
            @Override
            public void process( ARTable arTable ) {
                arTable.orderby( "R_SID, N_INDEX" );
            }
        } );
    }
}
