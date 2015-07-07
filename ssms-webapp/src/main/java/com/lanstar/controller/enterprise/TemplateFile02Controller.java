/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile02Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.app.Const;
import com.lanstar.common.Asserts;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile02;
import com.lanstar.model.tenant.TemplateText;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.render.aspose.AsposeRender;
import com.lanstar.render.aspose.OutputFormat;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile02Controller extends TemplateFileController<TemplateFile02> {
    @Override
    public void rec() {
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        super.rec();
        Integer pid = getAttrForInt( Const.TEMPLATE_FILE_PARENT_FIELD );
        boolean isNew = pid == null;
        if ( isNew ) pid = getParaToInt( "fileId" );
        TemplateFile file = TemplateFile.findFirst( uniqueTag, pid );

        if ( isNew == false ) {
            String content = TemplateText.getContent( uniqueTag, file.getTemplateFileCode(), getAttrForInt( "SID" ) );
            setAttr( "C_CONTENT", content );
        }
        setAttr( "file", file );
    }

    public void export() {
        Integer sid = getParaToInt();
        Asserts.notNull( sid, "非法的参数请求" );
        TemplateFile02 fileItem = TemplateFile02.dao.findById( sid );
        String content = fileItem.getContentText();
        render( AsposeRender.me( content, fileItem.getName(), OutputFormat.PDF ) );
    }

    @Override
    protected TemplateFile02 getDao() {
        return TemplateFile02.dao;
    }

    @Override
    protected void afterSave( TemplateFile02 model ) {
        String content = getPara( "htmlContent" );
        model.setContentText( content );
    }

    @Override
    protected SqlBuilder buildWhere() {
        return super.buildWhere().WHERE( "R_TMPFILE=?", getPara( "fileId" ) ).ORDER_BY( "T_DATE_01 DESC" );
    }

    public void view() {
    }

    public void detail() {
        super.rec();
    }
}
