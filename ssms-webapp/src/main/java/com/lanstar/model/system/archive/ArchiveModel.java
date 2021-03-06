/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ArchiveModel.java
 * 创建时间：2015-07-01
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.archive;

import com.lanstar.app.Const;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.template.ModelType;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

public class ArchiveModel<T extends ArchiveModel<T>> extends ModelExt<T> {

    public int getId() {
        return getInt( "UID" );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public int getSid() {
        return getInt( "SID" );
    }

    public int getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    public int getVersion() {
        return getInt( "N_VERSION" );
    }

    public void setVersion( int version ) {
        set( "N_VERSION", version );
    }

    public final int getTemplateFileId() {
        return getInt( Const.TEMPLATE_FILE_PARENT_FIELD );
    }

    /**
     * 获取关联的文本内容
     */
    public String getContentText() {
        TemplateProp templateProp = TemplatePropPlugin.me().get( ModelType.SYSTEM_ARCHIVE, getClass() );
        return TemplateText.getContent( getTemplateId(), getVersion(), templateProp.getCode(), getSid() );
    }
}
