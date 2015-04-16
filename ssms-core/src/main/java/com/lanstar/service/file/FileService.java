/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileService.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.service.file;

import com.lanstar.common.io.LocationBuilder;
import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.DbContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.plugin.file.ResourcePlugin;
import com.lanstar.plugin.file.ResourceService;
import com.lanstar.service.TanentService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileService extends TanentService {
    public static final String TABLENAME = "SYS_ATTACH_FILE";
    private final DbContext context;
    private final ResourceService resourceService;

    /**
     * 使用租户身份来进行文件服务管理
     *
     * @param identity 租户用户身份标识
     */
    public FileService( Identity identity ) {
        super( identity );
        // 获取当前租户的数据库上下文
        context = identity.getDbContext();
        resourceService = ResourcePlugin.me().getResourceService();
    }

    /**
     * 列出指定模块指定记录的所有文件。
     *
     * @param module   模块名称
     * @param recordId 记录SID
     *
     * @return 文件列表
     *
     * @throws SQLException
     */
    public List<AttachFile> list( String module, String recordId ) throws SQLException {
        ARTable table = getTable().where( "R_TABLE=? and R_SID=?", module, recordId );

        JdbcRecordSet records = table.queryList();
        List<AttachFile> files = new ArrayList<>();
        for ( JdbcRecord record : records ) {
            files.add( new AttachFile( record ) );
        }

        return files;
    }

    /**
     * 保存文件到指定的模块指定的记录中
     *
     * @param module   模块名称
     * @param recordId 记录SID
     * @param file     资源文件
     */
    public void save( String module, int recordId, FileBean file ) throws SQLException, IOException {
        String innerName = UUID.randomUUID().toString();
        // 保存文件后再插入数据库
        String fullPath = getFileLocation( module, innerName, file.getExtension() );
        long length = resourceService.saveResource( file.getResource(), fullPath );
        // 数据库记录
        ARTable table = getTable()
                .value( "R_TABLE", module )
                .value( "R_SID", recordId )
                .value( "C_INNER_NAME", innerName )
                .value( "C_OUTER_NAME", file.getFilenameWithoutExtension() )
                .value( "C_EXT", file.getExtension() )
                .value( "N_LENGTH", length )
                .value( "C_PATH", fullPath )
                .value( "P_SERVER", resourceService.getServerCode() )
                .value( "R_CREATE", identity.getId() )
                .value( "S_CREATE", identity.getName() )
                .value( "T_CREATE", "@now()" )
                .value( "R_CREATE", identity.getId() )
                .value( "S_CREATE", identity.getName() )
                .value( "S_CREATE", identity.getName() )
                .value( "R_TANENT", identity.getTanendId() )
                .value( "P_TANENT", identity.getTanentType() );

        table.insert();
    }

    private String getFileLocation( String module, String filename, String extension ) {
        // 规则：tanent/module/date[mounth]/file.ext
        SimpleDateFormat format = new SimpleDateFormat( "yyyyMM" );
        return LocationBuilder.newInstance()
                              .folder( identity.getTanendId() )
                              .folder( module )
                              .folder( format.format( new Date() ) )
                              .filename( filename ).extension( extension )
                              .build();
    }

    private ARTable getTable() throws SQLException {return new ARTable( context.createDbSession() ).table( TABLENAME );}
}
