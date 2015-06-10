/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFolder.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.plugin.activerecord.ModelExt;

public class TemplateFolder extends ModelExt<TemplateFolder> {
    public static final TemplateFolder dao = new TemplateFolder();

    public Integer getFileCount() {
        return getInt( "N_COUNT" );
    }

    public void setFileCount( int fileCount ) {
        set( "N_COUNT", fileCount );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public TemplateFolder getParent() {
        return TemplateFolder.dao.findById( getParentId() );
    }

    public void setParent( TemplateFolder parentFolder ) {
        set( "R_SID", parentFolder.getId() );
    }

    public Integer getParentId() {
        return getInt( "R_SID" );
    }

    public void setSourceFolder( com.lanstar.model.system.TemplateFolder systemFolder ) {
        set( "R_SOURCE", systemFolder.getId() );
    }
}
