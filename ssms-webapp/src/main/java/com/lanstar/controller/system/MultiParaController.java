/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MultiParaController.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.MultiPara;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import java.util.List;

public class MultiParaController extends SimplateController<MultiPara> {
    @Override
    protected MultiPara getDao() {
        return MultiPara.dao;
    }

    @Override
    public void list() {
        SqlBuilder builder = SQL.SELECT( "*" ).FROM( "sys_para_multi" ).GROUP_BY( "C_NAME" );
        String name = this.getPara( "C_NAME" );
        if ( StrKit.isEmpty( name ) == false ) {
            builder.HAVING( "C_NAME = ?", name );
        }

        SqlStatement statement = builder.toSqlStatement();
        List<MultiPara> multiParas = getDao().find( statement.getSql(), statement.getParams() );
        renderJson( multiParas );
    }
}
