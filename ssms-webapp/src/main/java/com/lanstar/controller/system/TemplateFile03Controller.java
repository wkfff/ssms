/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController03.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.controller.system.attachtext.AttachTokenGenerator;
import com.lanstar.core.aop.Before;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFile03;
import com.lanstar.model.system.TemplateText;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

public class TemplateFile03Controller extends SimplateController<TemplateFile03> {

    public void index() {
        TemplateFile file = getAttr( "file" );
        TemplateFile03 model = getDao().findFirstByColumn( "R_TMPFILE", file.getId() );
        if ( model != null ) {
            setAttrs( ModelKit.toMap( model ) );
            String content = TemplateText.getContent( file, model.getId() );
            setAttr( "C_CONTENT", content );
        }
    }

    @Override
    protected void afterSave( TemplateFile03 model ) {
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
    protected TemplateFile03 getDao() {
        return TemplateFile03.dao;
    }

    @Override
    public void rec() {
        SqlBuilder builder = SQL.SELECT( "*" ).FROM( "sys_stdtmp_file_03" ).WHERE( "R_TMPFILE = ?", getParaToInt( "R_SID" ) );
        SqlStatement statement = builder.toSqlStatement();
        TemplateFile03 model = getDao().findFirst( statement.getSql(), statement.getParams() );
        String sid = getPara( "sid" );
        if ( !StrKit.isEmpty( sid ) ) super.rec();
        if ( model != null ) {
            setAttrs( ModelKit.toMap( model ) );
        } else {
            setAttr( "R_TMPFILE", getParaToInt( "R_SID" ) );
        }

    }
}
