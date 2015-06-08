/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile06Controller.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.TemplateFile06;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class TemplateFile06Controller extends SimplateController<TemplateFile06> {
    @Override
    public void rec() {
        super.rec();


    }

    @Override
    protected TemplateFile06 getDao() {
        return TemplateFile06.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE( "R_TMPFILE=?", getParaToInt( "R_SID" ) );
    }

    @Override
    protected SqlBuilder buildOrder() {
        return new SqlBuilder().ORDER_BY( "T_CREATE DESC" );
    }
}
