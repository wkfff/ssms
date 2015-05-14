/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：homeController.java
 * 创建时间：2015-04-29
 * 创建用户：张铮彬
 */
package com.lanstar.controller.e;

import com.lanstar.controller.HomeController;
import com.lanstar.model.Profession;
import com.lanstar.core.ViewAndMapModel;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;

/**
 * 首页
 */
public class homeController extends HomeController {
    @Override
    public ViewAndModel index( HandlerContext context ) {
        boolean needChooseProfessions = !context.getIdentityContxt()
                                                .has( Profession.class );
        if ( needChooseProfessions ) {
            int tenantId = context.getTenant().getTenantId();
            JdbcRecordSet professions = getProfessions( context.SYSTEM_DB, tenantId );
            if ( professions.size() == 1 ) {
                context.setValue( "profession", professions.get( 0 ).get( "SID" ) );
                setTemplate( context );
                needChooseProfessions = false;
            }
        }

        ViewAndMapModel result = super.index( context ).put( "needChooseProfessions", needChooseProfessions );
        if ( !needChooseProfessions ) result.put( "profession", context.getIdentityContxt().get( Profession.class ) );
        return result;
    }

    public ViewAndModel getProfessions( HandlerContext context ) {
        int tenantId = context.getTenant().getTenantId();

        return context.returnWith().set( getProfessions( context.SYSTEM_DB, tenantId ) );
    }

    private JdbcRecordSet getProfessions( HandlerDbContext context, int tenantId ) {
        return context.query( "SELECT B.SID, B.C_NAME FROM SYS_TENANT_E_PROFESSION A INNER JOIN SYS_PROFESSION B ON A.P_PROFESSION = B.SID WHERE A.R_TENANT = ?", tenantId );
    }

    public void setTemplate( HandlerContext context ) {
        JdbcRecord profession = context.SYSTEM_DB.withTable( "SYS_PROFESSION" )
                                                 .where( "SID=?", context.getValue( "profession" ) )
                                                 .query();
        try {
            context.getIdentityContxt().set( new Profession( context.getIdentityContxt(), profession ) );
        } catch ( Exception e ) {
            throw new HandleException( e );
        }
    }
}
