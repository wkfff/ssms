/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AreaParaController.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.AreaPara;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class AreaParaController extends SimplateController<AreaPara> {
    @Override
    protected AreaPara getDao() {
        return AreaPara.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder()
                .WHERE("C_CODE LIKE ''35%''") // TODO: 暂时限定只显示福建的数据
                ._If( isParaBlank( "N_LEVEL" ) == false, "N_LEVEL=?", getPara( "N_LEVEL" ) )
                ._If( isParaBlank( "R_CODE" ) == false, "R_CODE=?", getPara( "R_CODE" ) );
        
        
    }

    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder builder = new SqlBuilder();
        builder.ORDER_BY( "N_INDEX,C_CODE" );
        return builder;
    }
}
