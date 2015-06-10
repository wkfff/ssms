/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile03Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile03;
import com.lanstar.plugin.activerecord.ModelKit;

public class TemplateFile03Controller extends SimplateController<TemplateFile03> {
    @Override
    public void index() {
        String sid = getPara( "sid" );
        TemplateFile03 templateFile = getDao().findFirstByColumn( "R_TMPFILE", sid );
        if (templateFile == null) return;
        setAttrs( ModelKit.toMap( templateFile ) );
        TemplateFile file = templateFile.getTemplateFile();
        com.lanstar.model.system.TemplateFile sourceFile = file.getSourceFile();
        if ( sourceFile == null ) return;
        int sourceId = sourceFile.getId();
        com.lanstar.model.system.TemplateFile03 m = com.lanstar.model.system.TemplateFile03.dao.findFirstByColumn( "R_TMPFILE", sourceId );
        if ( m == null ) return;
        int id = m.getId();
        setAttr( "TEMPLATE_ID", id );
    }

    @Override
    protected TemplateFile03 getDao() {
        return TemplateFile03.dao;
    }
    
    public void view(){
        super.index();
        setAttr( "@READONLY", "true" );
        render( "index.ftl" );
    }
}
