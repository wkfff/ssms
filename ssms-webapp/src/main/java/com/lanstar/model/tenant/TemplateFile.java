/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.model.TenantModel;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

public class TemplateFile extends TenantModel<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    /**
     * 获取模板文件所属专业的ID
     */
    public int getProfessionId() {
        return getInt( "P_PROFESSION" );
    }

    /**
     * 设置模板文件所属专业的ID
     */
    public void setProfessionId( int professionId ) {
        set( "P_PROFESSION", professionId );
    }

    /**
     * 获取所属模板的ID
     */
    public int getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    /**
     * 设置所属模板的ID
     */
    public void setTemplateId( int templateId ) {
        set( "R_TEMPLATE", templateId );
    }

    /**
     * 获取所属目录
     */
    public TemplateFolder getFolder() {
        return TemplateFolder.dao.findById( getFolderId() );
    }

    /**
     * 设置所属目录
     */
    public void setFolder( TemplateFolder tenantFolder ) {
        set( "R_SID", tenantFolder.getId() );
        set( "S_NAME", tenantFolder.getName() );
    }

    /**
     * 获取文件中的文件记录数量
     */
    public int getFileCount() {
        return get( "N_COUNT" );
    }

    /**
     * 设置文件中的文件记录数量
     */
    public void setFileCount( int count ) {
        set( "N_COUNT", count );
    }

    /** 获取模板属性 */
    public TemplateProp getTemplateProp() {
        return TemplatePropPlugin.me().get( getTemplateFileCode() );
    }

    /**
     * 获取文件ID
     */
    public Integer getId() {
        return getInt( "SID" );
    }

    /**
     * 获取文件名称
     */
    public String getName() {
        return getStr( "C_NAME" );
    }

    /**
     * 获取模板文件代码
     */
    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    /**
     * 获取来源文件ID，即描述拷贝自哪个文件。
     */
    public Integer getSourceFileId() {
        return getInt( "R_SOURCE" );
    }

    /**
     * 获取来源文件，即拷贝自的文件
     */
    public com.lanstar.model.system.TemplateFile getSourceFile() {
        return com.lanstar.model.system.TemplateFile.dao.findById( getSourceFileId() );
    }

    /**
     * 设置来源文件
     */
    public void setSourceFile( com.lanstar.model.system.TemplateFile file ) {
        set( "R_SOURCE", file.getId() );
    }

    /**
     * 获取所属目录ID
     */
    public int getFolderId() {
        return getInt( "R_SID" );
    }

    /**
     * 获取文件版本号
     */
    public int getVersion() {
        Integer version = getInt( "N_VERSION" );
        return version == null ? 0 : version;
    }

    /**
     * 设置文件版本号
     */
    public void setVersion( int version ) {
        set( "N_VERSION", version );
    }
}

