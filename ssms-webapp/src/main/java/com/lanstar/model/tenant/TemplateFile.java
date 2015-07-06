/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.common.ListKit;
import com.lanstar.identity.TenantType;
import com.lanstar.model.TenantModel;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.plugin.template.ModelType;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile extends TenantModel<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    public static TemplateFile findFirst( int templateId, int tenantId, int professionId, int srcId ) {
        return dao.findFirstByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "R_TENANT", "P_TENANT", "P_PROFESSION", "SID" ),
                ListKit.newObjectArrayList( templateId, tenantId, TenantType.ENTERPRISE.getName(), professionId, srcId ) );
    }

    public static TemplateFile findFirst( UniqueTag uniqueTag, Integer id ) {
        return dao.findFirstByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "R_TENANT", "P_TENANT", "P_PROFESSION", "SID" ),
                ListKit.newObjectArrayList(
                        uniqueTag.getTemplateId(),
                        uniqueTag.getTenantId(),
                        TenantType.ENTERPRISE.getName(),
                        uniqueTag.getProfessionId(),
                        id ) );
    }

    public String getParentName() {
        return getStr( "S_NAME" );
    }

    public void setParentName( String name ) {
        set( "S_NAME", name );
    }

    public Integer getParentId() {
        return getInt( "R_SID" );
    }

    public void setParentId( int id ) {
        set( "R_SID", id );
    }

    public Integer getIndex() {
        return getInt( "N_INDEX" );
    }

    public void setIndex( Integer index ) {
        set( "N_INDEX", index );
    }

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

    public void setTemplateProp( TemplateProp prop ) {
        set( "P_TMPFILE", prop.getCode() );
        set( "S_TMPFILE", prop.getName() );
    }

    /**
     * 获取文件ID
     */
    public int getId() {
        return getInt( "SID" );
    }

    public void setId( int id ) {
        set( "SID", id );
    }

    /**
     * 获取文件名称
     */
    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }

    /**
     * 获取模板文件代码
     */
    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    /**
     * 获取来源文件，即拷贝自的文件
     */
    public com.lanstar.model.system.archive.TemplateFile getSourceFile() {
        com.lanstar.model.system.archive.TemplateFile file = com.lanstar.model.system.archive.TemplateFile.dao.findFirstByColumns( 
            ListKit.newArrayList( "R_TEMPLATE", "SID", "N_VERSION" ),
            ListKit.newObjectArrayList( getTemplateId(), getId(), getVersion() ) );
        return file;
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

    public String getDesc() {
        return getStr( "C_DESC" );
    }

    public void setDesc( String desc ) {
        set( "C_DESC", desc );
    }

    public String getCycleUnitCode() {
        return getStr( "P_CYCLE" );
    }

    public void setCycleUnitCode( String code ) {
        set( "P_CYCLE", code );
    }

    public String getCycleUnitName() {
        return getStr( "S_CYCLE" );
    }

    public void setCycleUnitName( String name ) {
        set( "S_CYCLE", name );
    }

    public String getExplain() {
        return getStr( "C_EXPLAIN" );
    }

    public void setExplain( String value ) {
        set( "C_EXPLAIN", value );
    }

    public boolean getRemind() {
        return "1".equals( getStr( "B_REMIND" ) );
    }

    public void setRemind( boolean remind ) {
        set( "B_REMIND", remind ? "1" : "0" );
    }

    public Integer getCycleValue() {
        return getInt( "N_CYCLE" );
    }

    public void setCycleValue( Integer value ) {
        set( "N_CYCLE", value );
    }

    public ArchiveModel<?> getTemplateModel() {
        return (ArchiveModel<?>) getTemplateProp().getModel( ModelType.SYSTEM_ARCHIVE ).getDao().findFirstByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "R_TMPFILE", "N_VERSION" ),
                ListKit.newObjectArrayList( getTemplateId(), getId(), getVersion() ) );
    }
}
