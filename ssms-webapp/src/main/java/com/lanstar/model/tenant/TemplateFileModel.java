/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileModel.java
 * 创建时间：2015-06-09
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.app.Const;
import com.lanstar.identity.Tenant;
import com.lanstar.model.TenantModel;
import com.lanstar.plugin.template.ModelType;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

public class TemplateFileModel<T extends TemplateFileModel<T>> extends TenantModel<T> {
    @Override
    public boolean save() {
        boolean result = super.save();
        TemplateFile file = getTemplateFile();
        file.setFileCount( file.getFileCount() + 1 );
        file.update();
        return result;
    }

    @Override
    public boolean update() {
        boolean result = super.update();
        TemplateFile file = getTemplateFile();
        if ( file.getFileCount() == 0 ) {
            file.setFileCount( 1 );
            file.update();
        }
        return result;
    }

    @Override
    public boolean delete() {
        boolean result = super.delete();
        TemplateFile file = getTemplateFile();
        if ( file.getFileCount() > 0 ) {
            file.setFileCount( file.getFileCount() - 1 );
            file.update();
        }
        return result;
    }

    @Override
    public boolean deleteById( Object id ) {
        boolean result = super.deleteById( id );
        TemplateFile file = getTemplateFile();
        if ( file.getFileCount() > 0 ) {
            file.setFileCount( file.getFileCount() - 1 );
            file.update();
        }
        return result;
    }

    /**
     * 获取文件ID
     */
    public int getId() {
        return getInt( "SID" );
    }

    /**
     * 获取模板文件ID
     */
    public int getTemplateFileId() {
        return getInt( Const.TEMPLATE_FILE_PARENT_FIELD );
    }

    /**
     * 获取关联的文件
     */
    public TemplateFile getTemplateFile() {
        Tenant tenant = getTenant();
        return TemplateFile.findFirst( getTemplateId(), tenant.getTenantId(), getProfessionId(), getTemplateFileId() );
    }

    /**
     * 获取状态
     */
    public FileContentState getStatus() {
        return FileContentState.valueOf( getInt( "N_STATE" ) );
    }

    /**
     * 设置状态
     */
    public void setStatus( FileContentState status ) {
        set( "N_STATE", status.getValue() );
    }

    /**
     * 获取版本号
     */
    public int getVersion() {
        Integer version = getInt( "N_VERSION" );
        return version == null ? 0 : version;
    }

    /**
     * 设置版本号
     */
    public void setVersion( int version ) {
        set( "N_VERSION", version );
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
     * 获取关联的文本内容
     */
    public String getContentText() {
        TemplateProp templateProp = TemplatePropPlugin.me().get( ModelType.TENANT, getClass() );
        return TemplateText.getContent( getTemplateId(), getTenant(), getProfessionId(), templateProp.getCode(), getId() );
    }

    /**
     * 设置关联的文本内容
     */
    public void setContentText( String text ) {
        TemplateProp templateProp = TemplatePropPlugin.me().get( ModelType.TENANT, getClass() );
        TemplateText.saveContent( getTemplateId(), getTenant(), getProfessionId(), templateProp.getCode(), getId(), text, getOperator() );
    }
}
