/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

public class TemplateFile extends ModelExt<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    public int getProfessionId() {
        return getInt( "P_PROFESSION" );
    }

    public void setProfessionId( int professionId ) {
        set( "P_PROFESSION", professionId );
    }

    public int getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    public void setTemplateId( int templateId ) {
        set( "R_TEMPLATE", templateId );
    }

    public TemplateFolder getFolder() {
        return TemplateFolder.dao.findById( getFolderId() );
    }

    public void setFolder( TemplateFolder tenantFolder ) {
        set( "R_SID", tenantFolder.getId() );
        set( "S_NAME", tenantFolder.getName() );
    }

    public int getFileCount() {
        return get( "N_COUNT" );
    }

    public void setFileCount( int count ) {
        set( "N_COUNT", count );
    }

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

    public com.lanstar.model.system.TemplateFile getSourceFile() {
        return com.lanstar.model.system.TemplateFile.dao.findById( getSourceFileId() );
    }

    public void setSourceFile( com.lanstar.model.system.TemplateFile file ) {
        set( "R_SOURCE", file.getId() );
    }

    public int getFolderId() {
        return getInt( "R_SID" );
    }

    public int getVersion() {
        Integer version = getInt( "N_VERSION" );
        return version == null ? 0 : version;
    }

    public void setVersion( int version ) {
        set( "N_VERSION", version );
    }

    public void setTenant( Tenant target ) {
        set( "R_TENANT", target.getTenantId() );
        set( "S_TENANT", target.getTenantName() );
        set( "P_TENANT", target.getTenantType().getName() );
    }
}

