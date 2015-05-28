/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachFile.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.attachfile.LocationBuilder;

public class AttachFile extends Model<AttachFile> {
    public static final AttachFile dao = new AttachFile();

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getModule() {
        return getStr( "R_TABLE" );
    }

    public void setModule( String module ) {
        set( "R_TABLE", module );
    }

    public int getRecordSid() {
        return getInt( "R_SID" );
    }

    public void setRecordSid( int sid ) {
        set( "R_SID", sid );
    }

    public String getOuterFilename() {
        return LocationBuilder.newInstance()
                              .filename( getStr( "C_OUTER_NAME" ) )
                              .extension( getStr( "C_EXT" ) )
                              .build();
    }

    public void setOuterFilename( String filenameWithoutExtension ) {
        set( "C_OUTER_NAME", filenameWithoutExtension );
    }

    public String getInnerFilename() {
        return LocationBuilder.newInstance()
                              .filename( getStr( "C_INNER_NAME" ) )
                              .extension( getStr( "C_EXT" ) )
                              .build();
    }

    public void setInnerFilename( String filenameWithoutExtension ) {
        set( "C_INNER_NAME", filenameWithoutExtension );
    }

    public String getPath() {
        return getStr( "C_PATH" );
    }

    public void setPath( String fullPath ) {
        set( "C_PATH", fullPath );
    }

    public long getLength() {
        return getInt( "N_LENGTH" );
    }

    public void setLength( long length ) {
        set( "N_LENGTH", length );
    }

    public void setServiceCode( String serverCode ) {
        set( "P_SERVER", serverCode );
    }

    public void setExtension( String extension ) {
        set( "C_EXT", extension );
    }
}
