/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_repController.java
 * 创建时间：2015年4月29日 上午9:56:41
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;

/**
 * 自评报告
 *
 */
public class grade_repController extends DefaultController {
    public grade_repController() {
        super( "SSM_GRADE_REPORT" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_repValidator.class;
    }

    @Override
    public ViewAndModel index( HandlerContext context ) {
        context.setValue( "_FLAG_","0");
        JdbcRecordSet rs = context.DB.withTable( "SSM_GRADE_E_M" ).orderby( "N_INDEX DESC,SID DESC" ).queryList();
        return super.index( context ).put( "rs", rs );
    }

    @Override
    public ViewAndModel rec( HandlerContext context ) {
        // TODO：根据是否历史报告设置只读
        context.setValue( "_FLAG_","0");
        String r_sid = (String) context.getValue( "r_sid" );
        return context.returnWith().set(
                context.DB.withTable( this.TABLENAME ).where( "R_SID=?", r_sid )
                        .query() );
    }
}
