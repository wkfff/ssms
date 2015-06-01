/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IndustryController.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.Industry;
import com.lanstar.model.system.Template;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;
import com.lanstar.service.Parameter;

public class IndustryController extends SimplateController<Industry> {
    @Override
    protected Industry getDao() {
        return Industry.dao;
    }
    public void reg(){
        rec();
    }
    @Override
    protected SqlBuilder buildWhere() {
        if(isParaBlank( "C_NAME" ) == false){
            return new SqlBuilder().WHERE( "C_NAME=?", getPara( "C_NAME" )  );
        }
        return null;
    }
    /**
     * 表单数据
     */
    @Override
    public void rec() {
        List<Parameter> Tlist = new ArrayList<>();
        SqlBuilder builder = SQL.SELECT( "*" ).FROM( "SYS_TEMPLATE" );
        SqlStatement statement=builder.toSqlStatement();
        List<Template> templates=Template.dao.find( statement.getSql() );
        for(Template template:templates){
            String templateName=template.getName();
            int sid=template.getId();
            Parameter parameter=new Parameter( Integer.toString( sid ), templateName );
            Tlist.add(parameter);
        }
        setAttr( "template", Tlist );
        super.rec();
    }
}
