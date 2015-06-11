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
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile04;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class TemplateFile04Controller extends SimplateController<TemplateFile04> {

    @Override
    public void rec() {
        super.rec();
        Integer pid = getAttrForInt( Const.TEMPLATE_FILE_PARENT_FIELD );
        if (pid == null) pid = getParaToInt("pid");
        TemplateFile file = TemplateFile.dao.findById( pid );
        com.lanstar.model.system.TemplateFile sourceFile = file.getSourceFile();

        com.lanstar.model.system.TemplateFile04 sysFile = com.lanstar.model.system.TemplateFile04.dao.findFirstByColumn( Const.TEMPLATE_FILE_PARENT_FIELD, sourceFile
                .getId() );
        if (sysFile == null) return;
        setAttr( "TEMPLATE_ID", sysFile.getId() );
    }
    
    @Override
    protected TemplateFile04 getDao() {
        return TemplateFile04.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE("R_TMPFILE=?", getParaToInt("R_SID"));
    }
    
    public void view(){
        super.index();
        setAttr( "@READONLY", "true" );
        render( "index.ftl" );
    }
}
