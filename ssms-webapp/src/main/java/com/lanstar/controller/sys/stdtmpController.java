/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：stdtmpController.java
 * 创建时间：2015-04-18
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;

public class stdtmpController extends DefaultController {
    public stdtmpController() {
        super( "SYS_STDTMP_FOLDER" );
    }

    public ViewAndModel tree( HandlerContext context ) {
        String profession = context.getValue( "profession" );
        JdbcRecordSet folder = context.SYSTEM_DB.withTable( "SYS_STDTMP_FOLDER" )
                                                .where( "P_PROFESSION=?", profession )
                                                .queryList();
        return context.returnWith().set( EasyUIControllerHelper.toTree( folder, "SID", "R_SID", "C_NAME" ) );
    }

    @Override
    public ViewAndModel rec( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        JdbcRecordSet records = context.SYSTEM_DB.withTable( "SYS_STDTMP_FOLDER" )
                                                 .where( "R_SID=?", sid )
                                                 .queryList();
        JdbcRecordSet records2 = context.SYSTEM_DB.withTable( "SYS_STDTMP_FILE" )
                                                  .where( "R_SID=?", sid )
                                                  .queryList();

        return super.rec( context )
                    .put( "subitems", records )
                    .put( "fileitems", records2 );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return null;
    }

    public ViewAndModel subitem( HandlerContext context ) {
        String pid = context.getValue( "pid" );
        JdbcRecord record = context.SYSTEM_DB.withTable( "SYS_STDTMP_FOLDER" )
                                             .where( "SID=?", pid )
                                             .query();
        return context.returnWith().set( record );
    }
}
