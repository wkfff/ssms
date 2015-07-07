/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-07-01
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.archive;

import com.lanstar.common.ListKit;
import com.lanstar.plugin.template.ModelType;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

public class TemplateFile extends ArchiveModel<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }
    
    public int getSid() {
        return getInt( "SID" );
    }
    
    /** 获取模板属性 */
    public TemplateProp getTemplateProp() {
        return TemplatePropPlugin.me().get( getTemplateFileCode() );
    }

    public ArchiveModel<?> getTemplateModel() {
        ArchiveModel<?> file=(ArchiveModel<?>) getTemplateProp().getModel( ModelType.SYSTEM_ARCHIVE ).getDao().findFirstByColumns(
            ListKit.newArrayList( "R_TEMPLATE", "R_TMPFILE", "N_VERSION" ),
            ListKit.newObjectArrayList(getTemplateId(), getSid(),getVersion() ) );
        return file;
    }

    public String getContextText() {
        return TemplateText.getContent( getTemplateId(), getVersion(), getTemplateFileCode(), getSid() );
    }
}
