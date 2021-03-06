/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController01.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.controller.system.attachtext.AttachTokenGenerator;
import com.lanstar.core.aop.Before;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFile01;
import com.lanstar.model.system.TemplateText;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.plugin.activerecord.ModelKit;

public class TemplateFile01Controller extends SimplateController<TemplateFile01> {
    @Override
    protected TemplateFile01 getDao() {
        return TemplateFile01.dao;
    }

    public void index() {
        // 先判断sid 是否有值，如果有值根据模板获R_TMPFILE取到对应的模板，如果没值根据SID获取到模板。。。
        TemplateFile file = getAttr( "file" );
        TemplateFile01 model = getDao().findFirstByColumn( "R_TMPFILE", file.getId() );

        if ( model != null ) {
            setAttrs( ModelKit.toMap( model ) );
            String content = TemplateText.getContent( file, model.getId() );
            setAttr( "C_CONTENT", content );
        }
    }

    @Before(AttachTokenGenerator.class)
    public void view() {
        com.lanstar.model.system.archive.TemplateFile file = getAttr( "file" );
        ArchiveModel<?> model = file.getTemplateModel();
        setAttr( "title",model.getName());
        String content = com.lanstar.model.system.archive.TemplateText.getContent( file, model.getSid() );
        setAttr( "C_CONTENT", content );
        setAttrs( ModelKit.toMap( model ) );
        render( "view.ftl" );
    }

    @Override
    protected void afterSave( TemplateFile01 model ) {
        String content = getPara( "htmlContent" );
        TemplateText.saveContent( model, content, identityContext );
    }
    
}
