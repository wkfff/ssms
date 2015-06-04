/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController06.java
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFile06;
import com.lanstar.plugin.activerecord.ModelKit;

import java.util.List;

public class TemplateFileController06 extends SimplateController<TemplateFile06> {
    @Override
    public void rec() {
        TemplateFile templateFile = TemplateFile.findByFileContent( "06", getParaToInt( "sid" ) );

        List<TemplateFile06> list = getDao().findAll();
        setAttr( "list", ModelKit.toMap( list, "SID", "C_NAME", "C_EXAMINER", "T_EXAMINE", "P_LEVEL", "C_MEASURE", "C_RESPONSIBLE", "T_RECTIFICATION", "C_ACCEPTANCE" ) );
        setAttr( "template", templateFile );
    }

    @Override
    protected TemplateFile06 getDao() {
        return TemplateFile06.dao;
    }
}
