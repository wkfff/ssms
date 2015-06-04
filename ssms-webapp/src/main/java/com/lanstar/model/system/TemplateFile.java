/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.common.StandardTemplateFileKit;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.AttachTextService;

import java.util.Date;
import java.util.List;

public class TemplateFile extends Model<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    public static List<TemplateFile> list( Integer folderId ) {
        return TemplateFile.dao.find( SqlKit.sql( "system.templateFile.getFiles" ), folderId );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public Integer getTemplateFileId() {
        return getInt( "R_TMPFILE" );
    }

    public void setTemplateFileId( Integer id ) {
        set( "R_TMPFILE", id );
    }

    public Record getFileContent() {
        String tableName = getTableName();
        return Db.findById( tableName, "SID", getTemplateFileId() );
    }

    @Override
    public boolean delete() {
        // TODO:
        //context.getAttachTextService().del( "STDTMP_FILE_" + code, "C_CONTENT", tmpfileId );
        String tableName = getTableName();
        Db.deleteById( tableName, "SID", getTemplateFileId() );

        return super.delete();
    }

    @Override
    public boolean deleteById( Object id ) {
        // TODO:
        //context.getAttachTextService().del( "STDTMP_FILE_" + code, "C_CONTENT", tmpfileId );
        String tableName = getTableName();
        Db.deleteById( tableName, "SID", getTemplateFileId() );

        return super.deleteById( id );
    }

    @Override
    public boolean save() {
        initFileContent();
        return super.save();
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public Object getName() {
        return getStr( "C_NAME" );
    }

    public String getAttachText() {
        return AttachTextService.SYSTEM.getContent(
                "STDTMP_FILE_" + getTemplateFileCode(), "C_CONTENT", getTemplateFileId() );
    }

    private boolean initFileContent() {
        String tableName = getTableName();

        Record record = new Record()
                .set( "R_CREATE", get( "R_CREATE" ) )
                .set( "S_CREATE", get( "S_CREATE" ) )
                .set( "T_CREATE", new Date() )
                .set( "R_UPDATE", get( "R_UPDATE" ) )
                .set( "S_UPDATE", get( "S_UPDATE" ) )
                .set( "T_UPDATE", new Date() )
                .set( "R_TENANT", get( "R_TENANT" ) )
                .set( "S_TENANT", get( "S_TENANT" ) )
                .set( "P_TENANT", get( "P_TENANT" ) );
        boolean success = Db.save( tableName, "SID", record );
        setTemplateFileId( record.getLong( "SID" ).intValue() );

        return success;
    }

    private String getTableName() {return StandardTemplateFileKit.getSystemTableName( getTemplateFileCode() );}

    public static TemplateFile findByFileContent(String fileCode, int fileId){
        dao.findFirst( "SELECT * FROM " )
    }
}
