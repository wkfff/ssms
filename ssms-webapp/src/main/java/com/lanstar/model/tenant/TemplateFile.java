/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

public class TemplateFile extends ModelExt<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    /** 获取模板属性 */
    public TemplateProp getTemplateProp() {
        return TemplatePropPlugin.me().get( getTemplateFileCode() );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public Integer getSourceFileId() {
        return getInt( "R_SOURCE" );
    }

    public void setFolder( TemplateFolder tenantFolder ) {
        set( "R_SID", tenantFolder.getId() );
        set( "S_NAME", tenantFolder.getName() );
    }

    public void setSourceFile( com.lanstar.model.system.TemplateFile file ) {
        set( "R_SOURCE", file.getId() );
    }
}

