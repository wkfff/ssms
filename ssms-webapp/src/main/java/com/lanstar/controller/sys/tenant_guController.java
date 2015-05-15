/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：tenant_guController.java
 * 创建时间：下午2:21:26
 * 创建用户：苏志亮
 */
package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecord;

/**
 * @author Administrator
 *
 */
public class tenant_guController extends DefaultController {

    /**
     * @param tablename
     */
    public tenant_guController() {
        super( "SYS_TENANT_G_USER" );
        // TODO Auto-generated constructor stub
    }

    public void psw( HandlerContext context ) {
    }

    public ViewAndModel updtePSW( HandlerContext context ) {
        String sid = context.getValue( "SID" );
        String oldPwd = context.getValue( "oldPwd" );
        String newPwd = context.getValue( "newPwd" );

        JdbcRecord record = context.DB.withTable( this.TABLENAME )
                                      .where( "SID=? and UPPER(C_PASSWD)=?",
                                              sid, oldPwd.toUpperCase() )
                                      .query();
        boolean success = false;
        if ( record != null ) {
            success = context.DB.withTable( this.TABLENAME )
                                .value( "C_PASSWD", newPwd )
                                .where( "SID=?", sid )
                                .update() > 0;
        }
        return context.returnWith().set( success );
    }

    @Override
    public ViewAndModel rec( HandlerContext context ) {
        String pid = context.getValue( "pid" );
        JdbcRecord record = context.DB.withTable( "SYS_TENANT_G" )
                                      .where( "SID=?", pid ).query();
        return super.rec( context ).put( "tenant", record );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        // TODO Auto-generated method stub
        return tenant_guValidator.class;
    }

}
