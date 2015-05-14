/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：tenant_euController.java
 * 创建时间：下午4:45:28
 * 创建用户：苏志亮
 */
package com.lanstar.controller.sys;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecord;

/**
 * @author Administrator
 *
 */
public class tenant_euController extends DefaultController {
    public tenant_euController() {
        super( "SYS_TENANT_E_USER" );
    }

    public void psw( HandlerContext context ) {
    }

    public ViewAndModel updtePSW( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        String passwd1=context.getValue( "C_PASSWD1" );
        String passwd=context.getValue( "C_PASSWD" );
        JdbcRecord record = null;
        if ( StringHelper.vaildValue( sid ) ) {
            record = context.DB.withTable( this.TABLENAME )
                               .where( "SID=?", sid ).query();
            String password=record.getString( "C_PASSWD" );
            if(password.equals(passwd1)){
                context.DB.withTable( this.TABLENAME ).value( "C_PASSWD", passwd )
                        .where( "SID=?", sid ).update();
            }
        }
                return context.returnWith().set( record );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lanstar.controller.DefaultController#rec(com.lanstar.core.handle.
     * HandlerContext)
     */
    @Override
    public ViewAndModel rec( HandlerContext context ) {
        String pid = context.getValue( "pid" );
        JdbcRecord record = context.DB.withTable( "SYS_TENANT_E" )
                                      .where( "SID=?", pid ).query();
        return super.rec( context ).put( "tenant", record );
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lanstar.controller.BaseController#getValidator()
     */
    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return tenant_euValidator.class;
    }
}
