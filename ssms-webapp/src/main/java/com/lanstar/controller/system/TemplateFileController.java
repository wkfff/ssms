/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.service.MultiParaType;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFolder;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.template.TemplatePropPlugin;

public class TemplateFileController extends SimplateController<TemplateFile> {
    @Override
    public void rec() {
        if ( isParaBlank( "pid" ) == false ) {
            TemplateFolder folder = TemplateFolder.dao.findById( getParaToInt( "pid" ) );
            setAttr( "folder", folder );
        }
        setAttr( "SYS_CYCLE", MultiParaType.SYS_CYCLE.parameters() );
        setAttr( "tmpfiles", TemplatePropPlugin.me().listParameter() );
        super.rec();
    }

    @Override
    protected TemplateFile getDao() {
        return TemplateFile.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE()._If( isParaBlank( "R_SID" ) == false,
                "R_SID=?", getPara( "R_SID" ) ).ORDER_BY( "N_INDEX, SID" );
    }
}
