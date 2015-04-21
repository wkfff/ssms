/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：stdtmp_fileController.java
 * 创建时间：2015-04-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecord;

public class stdtmp_fileController extends DefaultController {
    public stdtmp_fileController() {
        super( "SYS_STDTMP_FILE" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return null;
    }

    /**
     * 表单数据
     */
    @Override
    public ViewAndModel rec( HandlerContext context ) {
        String sid = context.getValue( "pid" );
        JdbcRecord record = context.SYSTEM_DB.withTable( "SYS_STDTMP_FOLDER" )
                                             .where( "SID=?", sid )
                                             .query();

        resolveMultiParameter(context, "SYS_CYCLE");
        return super.rec( context )
                    .put( "folder", record );
    }
}
