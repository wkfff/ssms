/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachText.java
 * 创建时间：2015-04-17
 * 创建用户：张铮彬
 */

package com.lanstar.service.attachtext;

import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.DBSession;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.ar.ARTable;
import com.lanstar.service.TenantService;

import java.sql.SQLException;

/**
 * 附加文本服务
 */
public class AttachTextService extends TenantService {
    private static final String TABLENAME = "SYS_ATTACH_TEXT";

    /**
     * 根据身份标识获取租户服务
     *
     * @param identity 身份标识
     */
    public AttachTextService( Identity identity ) {
        super( identity );
    }

    public String getContent( String tableName, String field, int sid ) throws SQLException {
        ARTable table = getTable()
                .columns( "C_CONTENT" )
                .where( "R_TABLE=? and R_FIELD=? and R_SID=? and R_TENANT=? and P_TENANT=?",
                        tableName, field, sid,
                        identity.getTenantId(),
                        identity.getTenantType().getName() );

        JdbcRecord record = table.query();
        if ( record == null ) return null;
        return record.getString( "C_CONTENT" );
    }

    public void save( String tableName, String field, int recordSId, String content ) throws SQLException {

        ARTable table = getTable()
                .value( "R_TABLE", tableName )
                .value( "R_FIELD", field )
                .value( "R_SID", recordSId )
                .value( "C_CONTENT", content )
                .value( "R_TENANT", identity.getTenantId() )
                .value( "S_TENANT", identity.getTenantName() )
                .value( "P_TENANT", identity.getTenantType().getName() )
                .value( "R_UPDATE", identity.getTenantId() )
                .value( "S_UPDATE", identity.getName() )
                .where( "R_TABLE=? and R_FIELD=? and R_SID=? and R_TENANT=? and P_TENANT=?",
                        tableName, field, recordSId,
                        identity.getTenantId(),
                        identity.getTenantType().getName() );
        if ( table.query() == null ) {
            table.value( "R_CREATE", identity.getId() )
                 .value( "S_CREATE", identity.getName() )
                 .value( "T_CREATE", "@now()" );
            table.insert();
        } else {
            table.update();
        }
    }

    private ARTable getTable() throws SQLException {
        return getTable( getDBSession() );
    }

    private ARTable getTable( DBSession session ) {
        return new ARTable( session ).table( TABLENAME );
    }
}
