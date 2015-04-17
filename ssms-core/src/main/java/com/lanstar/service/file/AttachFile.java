/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachFile.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.service.file;

import com.lanstar.common.io.LocationBuilder;
import com.lanstar.db.JdbcRecord;

public class AttachFile {
    private final String module;
    private final String recordSid;
    private final String outerFilename;
    private final String innerFilename;
    private final String path;
    private final long length;

    public AttachFile( JdbcRecord record ) {
        this.module = record.getString( "R_TABLE" );
        this.recordSid = record.getString( "R_SID" );
        this.outerFilename = LocationBuilder.newInstance()
                                            .filename( record.getString( "C_OUTER_NAME" ) )
                                            .extension( record.getString( "C_EXT" ) )
                                            .build();
        this.innerFilename = LocationBuilder.newInstance()
                                            .filename( record.getString( "C_INNER_NAME" ) )
                                            .extension( record.getString( "C_EXT" ) )
                                            .build();
        this.path = record.getString( "C_PATH" );
        this.length = ((Integer)record.get( "N_LENGTH" )).longValue();
        // TODO: 增加更多内容
    }

    public String getModule() {
        return module;
    }

    public String getRecordSid() {
        return recordSid;
    }

    public String getOuterFilename() {
        return outerFilename;
    }

    public String getInnerFilename() {
        return innerFilename;
    }

    public String getPath() {
        return path;
    }

    public long getLength() {
        return length;
    }
}
