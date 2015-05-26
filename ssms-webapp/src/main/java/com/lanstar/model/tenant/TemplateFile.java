/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.plugin.activerecord.*;
import com.lanstar.common.StandardTemplateFileKit;

public class TemplateFile extends Model<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    public void setFolder( TemplateFolder tenantFolder ) {
        set( "R_SID", tenantFolder.getId() );
        set( "S_NAME", tenantFolder.getName() );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public Integer getTemplateFileId() {
        return getInt( "R_TMPFILE" );
    }

    public void setTemplateId( int id ) {
        set( "R_TMPFILE", id );
    }

    public Record getFileContent() {
        Config config = DbKit.getConfig( TemplateFile.class );
        DbPro dbPro = DbPro.use( config.getName() );
        String tableName = getTableName();
        return dbPro.findById( tableName, "SID", getTemplateFileId() );
    }

    public boolean setFileContent( Record content ) {
        Config config = DbKit.getConfig( TemplateFile.class );
        DbPro dbPro = DbPro.use( config.getName() );
        String tableName = getTableName();
        content.remove( "SID", "R_TENANT", "S_TENANT", "P_TENANT" );
        content.set( "R_TENANT", get( "R_TENANT" ) );
        content.set( "S_TENANT", get( "S_TENANT" ) );
        content.set( "P_TENANT", get( "P_TENANT" ) );
        return dbPro.save( tableName, "SID", content );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public void setSourceFile( com.lanstar.model.system.TemplateFile tenantFile ) {
        set( "R_SOURCE", tenantFile.getId() );
    }

    private String getTableName() {return StandardTemplateFileKit.getTenantTableName( getTemplateFileCode() );}
}

