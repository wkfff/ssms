/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController04.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.Asserts;
import com.lanstar.controller.SimplateController;
import com.lanstar.controller.system.attachtext.AttachTokenGenerator;
import com.lanstar.core.aop.Before;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFile04;
import com.lanstar.model.system.TemplateText;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.sun.tools.javac.util.Assert;

public class TemplateFile04Controller extends SimplateController<TemplateFile04> {

    @Override
    public void rec() {
        Integer pid = getParaToInt( "pid" );
        Asserts.notNull( pid, "id connot null" );
        TemplateFile file = TemplateFile.dao.findById( pid );
        setAttr( "file", file );
        String content = null;
        if ( getParaToInt( "sid" ) != null ) content = TemplateText.getContent( file, getParaToInt( "sid" ) );
        setAttr( "C_CONTENT", content );
        super.rec();
    }

    @Override
    protected void afterSave( TemplateFile04 model ) {
        String content = getPara( "htmlContent" );
        TemplateText.saveContent( model, content, identityContext );
    }

    @Before(AttachTokenGenerator.class)
    public void view() {
        com.lanstar.model.system.archive.TemplateFile file = getAttr( "file" );
        ArchiveModel<?> model = file.getTemplateModel();
        setAttr( "title", model.getName() );
        String content = com.lanstar.model.system.archive.TemplateText.getContent( file, model.getSid() );
        setAttr( "C_CONTENT", content );
        setAttrs( ModelKit.toMap( model ) );
        render( "view.ftl" );
    }

    @Override
    protected TemplateFile04 getDao() {
        return TemplateFile04.dao;
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
