/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateGradeController.java
 * 创建时间：下午4:25:34
 * 创建用户：苏志亮
 */
package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.TemplateGrade;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

/**
 * @author Administrator
 *
 */
public class TemplateGradeController extends SimplateController<TemplateGrade>{

    @Override
    protected TemplateGrade getDao() {
        // TODO Auto-generated method stub
        return TemplateGrade.dao;
    }
    
    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE()
               ._If( isParaExists( "R_SID" ), "R_SID = ?", getPara( "R_SID" ) );
        return builder;
    }

    /* (non-Javadoc)
     * @see com.lanstar.controller.SimplateController#buildOrder()
     */
    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder builder = new SqlBuilder();
        builder.ORDER_BY( "N_INDEX" );
        return builder;
    }

}
