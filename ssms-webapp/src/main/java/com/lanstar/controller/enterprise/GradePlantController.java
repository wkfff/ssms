/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeController.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.GradePlant;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.service.ProfessionService;

public class GradePlantController extends SimplateController<GradePlant> {
    public void rec_new() {
    }

    @Override
    public void rec() {
        super.rec();
        if ( isParaExists( "json" ) == true )
            renderJson();
    }

    public void draft_rec() {
        rec();
    }

    public void draft() {

    }

    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE( "R_TENANT = ?", identityContext.getTenantId() )
               .WHERE( "P_TENANT = ?", identityContext.getTenantType().getName() )
               ._If( isParaExists( "N_STATE" ), "N_STATE = ?", getPara( "N_STATE" ) )
               ._If( isParaBlank( "T_START" ) == false, "T_START >= ?", getPara( "T_START" ) )
               ._If( isParaBlank( "T_END" ) == false, "T_END <= ?", getPara( "T_END" ) );
        return builder;
    }

    @Override
    protected GradePlant getDao() {
        return GradePlant.dao;
    }

    @Override
    protected void beforeSave( GradePlant model, boolean[] handled ) {
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) { // for insert
            model.setTitle( model.getStartDate() + "企业自评" );
            model.setState( 0 );
        }
    }

    @Override
    protected void afterSave( GradePlant model ) {
        ProfessionService service = identityContext.getProfessionService();
        tenantDb.callProcedure( "P_GRADE_INIT", model.getId(), service.getId(), identityContext.getTenantId(),
                identityContext.getTenantType().getName() );
    }
}
