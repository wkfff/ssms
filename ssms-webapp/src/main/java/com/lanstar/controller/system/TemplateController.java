/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateController.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.Template;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class TemplateController extends SimplateController<Template> {
    @Override
    protected Template getDao() {
        return Template.dao;
    }
    @Override
    public void index() {
        SqlBuilder select = SQL.SELECT( "*" ).FROM( table.getName() );
        SqlStatement selectStatement = select.toSqlStatement();
        List<Template> list = getDao().find(selectStatement.getSql() );
        setAttr( "templates", list );
    }
    @Override
    public void save() {
        // TODO Auto-generated method stub
        Template template;
        String sid = getPara("id");
        String name=getPara("name");
        if(sid!=null){
            template=getDao().findById( sid );
            template.set( "C_NAME", name );
            template.update();
            renderJson( true );
        }else if(name!=null&&name!="") {
            template=getModel();
            template.set( "C_NAME", name );
            template.save();
            renderJson( true );
        }else{
            renderJson( false );
        }
    }
}
