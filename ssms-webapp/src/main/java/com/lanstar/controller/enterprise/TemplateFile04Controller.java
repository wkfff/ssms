/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile04Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.app.Const;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.Identity;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile02;
import com.lanstar.model.tenant.TemplateFile04;
import com.lanstar.model.tenant.TemplateText;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile04Controller extends TemplateFileController<TemplateFile04> {

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

    @Override
    protected void afterSave( TemplateFile04 model ) {
        String content = getPara( "htmlContent" );
        model.setContentText( content );
    }

    @Override
    protected TemplateFile04 getDao() {
        return TemplateFile04.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE( "R_TMPFILE=?", getPara( "fileId" ) ).ORDER_BY( "T_TIME DESC" );
    }

    public void view() {
    }

    public void detail() {
        super.rec();
    }
}
