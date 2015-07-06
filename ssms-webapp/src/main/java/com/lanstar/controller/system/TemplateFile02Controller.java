/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController02.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.controller.system.attachtext.AttachTokenGenerator;
import com.lanstar.core.aop.Before;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFile02;
import com.lanstar.model.system.TemplateText;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class TemplateFile02Controller extends SimplateController<TemplateFile02> {
    public void index() {
        TemplateFile file = getAttr( "file" );
        TemplateFile02 model = getDao().findFirstByColumn( "R_TMPFILE", file.getId() );
        if ( model != null ) {
            setAttrs( ModelKit.toMap( model ) );
            String content = TemplateText.getContent( file, model.getId() );
            setAttr( "C_CONTENT", content );
        }
    }

    @Override
    protected void afterSave( TemplateFile02 model ) {
        String content = getPara( "htmlContent" );
        TemplateText.saveContent( model, content, identityContext );
    }

    @Before(AttachTokenGenerator.class)
    public void view() {
        com.lanstar.model.system.archive.TemplateFile file = getAttr( "file" );
        ArchiveModel<?> model = file.getTemplateModel();

        // setAttr("file", file);
        // setAttr("model", ModelKit.toMap(model));
        render( "view.ftl" );
    }

    @Override
    protected TemplateFile02 getDao() {
        return TemplateFile02.dao;
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
