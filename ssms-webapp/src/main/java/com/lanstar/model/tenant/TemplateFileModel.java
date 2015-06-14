/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileModel.java
 * 创建时间：2015-06-09
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.app.Const;
import com.lanstar.plugin.activerecord.ModelExt;

public class TemplateFileModel<T extends TemplateFileModel<T>> extends ModelExt<T> {
    public int getTemplateFileId() {
        return getInt( Const.TEMPLATE_FILE_PARENT_FIELD );
    }

    public TemplateFile getTemplateFile() {
        return TemplateFile.dao.findById( getTemplateFileId() );
    }

    public FileContentState getStatus() {
        return FileContentState.valueOf( getInt( "N_STATE" ) );
    }

    public void setStatus( FileContentState status ) {
        set( "N_STATE", status.getValue() );
    }

    public int getVersion() {
        Integer version = getInt( "N_VERSION" );
        return version == null ? 0 : version;
    }

    public void setVersion( int version ) {
        set( "N_VERSION", version );
    }
}
