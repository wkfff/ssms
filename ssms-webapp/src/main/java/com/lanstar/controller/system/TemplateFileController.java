/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.StandardTemplateFileKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.MultiPara;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFolder;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;
import com.lanstar.service.Parameter;

import java.util.ArrayList;
import java.util.List;

public class TemplateFileController extends SimplateController<TemplateFile> {
    @Override
    public void rec() {
        if ( isParaBlank( "pid" ) == false ) {
            SqlBuilder builder = SQL.SELECT( "C_CODE, C_VALUE" )
                                    .FROM( "SYS_PARA_MULTI" )
                                    .WHERE( "C_NAME=?", "SYS_CYCLE" );
            SqlStatement statement = builder.toSqlStatement();
            List<MultiPara> multiParas = MultiPara.dao.find( statement.getSql(), statement.getParams() );
            List<Parameter> list = new ArrayList<>();
            for ( MultiPara multiPara : multiParas ) {
                String code = multiPara.getCode();
                String value = multiPara.geValue();
                Parameter parameter = new Parameter( code, value );
                list.add( parameter );
            }
            TemplateFolder folder = TemplateFolder.dao.findById( getParaToInt( "pid" ) );
            setAttr( "SYS_CYCLE", list );
            setAttr( "folder", folder );
        }
        setAttr( "tmpfiles", StandardTemplateFileKit.listStandardTemplate() );
        super.rec();
    }

    @Override
    protected TemplateFile getDao() {
        return TemplateFile.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE()._If( isParaBlank( "R_SID" ) == false,
                "R_SID=?", getPara( "R_SID" ) );
    }
}
