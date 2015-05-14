/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateFile.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.db.JdbcRecord;
import com.lanstar.db.ar.ARTable;
import com.lanstar.service.TenantContext;

class StandardTemplateFile implements IClonable<TenantContext> {
    private final ProfessionTemplateService service;
    private final JdbcRecord sourceFile;
    private final int folderId;
    private final int sourceSid;
    private final String sourceFileTable;
    private final String targetFileTable;
    private final String sourceFileSid;

    public StandardTemplateFile( ProfessionTemplateService service, JdbcRecord sourceFile, int folderId ) {
        this.service = service;
        this.sourceFile = sourceFile;
        this.sourceSid = (int) sourceFile.get( "SID" );
        String tempFileCode = sourceFile.getString( "P_TMPFILE" );
        this.sourceFileSid = sourceFile.getString( "R_TMPFILE" );
        this.sourceFileTable = "SYS_STDTMP_FILE_" + tempFileCode;
        this.targetFileTable = "SSM_STDTMP_FILE_" + tempFileCode;
        this.folderId = folderId;
    }

    @Override
    public void cloneTo( TenantContext target ) {
        // skip clone if exists
        if ( exists( target ) ) return;

        // clone template file info
        int templdateSid = cloneTemplate( target );

        // clone file info
        cloneFile( target, templdateSid );

        // clone html editor content
        cloneTemplateEditorContent( templdateSid );
    }

    private int cloneFile( TenantContext target, int templdateSid ) {
        ARTable fileTable = target.withTable( "SSM_STDTMP_FILE" )
                                  .values( sourceFile )
                                  .value( "R_SID", folderId )
                                  .value( "R_TMPFILE", templdateSid )
                                  .value( "R_TENANT", target.getTenantId() )
                                  .value( "S_TENANT", target.getTenantName() )
                                  .value( "P_TENANT", target.getTenantType().getName() );
        fileTable.getValues().remove( "SID" );
        fileTable.value( "R_SOURCE", sourceSid );
        fileTable.insert();
        return target.getDbContext().getSID();
    }

    private int cloneTemplate( TenantContext target ) {
        JdbcRecord tmpFile = service.source.withTable( sourceFileTable ).where( "SID=?", sourceFileSid ).query();
        ARTable table = target.withTable( targetFileTable ).values( tmpFile )
                              .value( "R_TENANT", target.getTenantId() )
                              .value( "S_TENANT", target.getTenantName() )
                              .value( "P_TENANT", target.getTenantType().getName() );
        table.getValues().remove( "SID" );
        table.insert();
        return target.getDbContext().getSID();
    }

    private void cloneTemplateEditorContent( int templdateSid ) {
        // TODO: 这个表是个大表，可能会爆！！！
        String sql = String.format(
                "INSERT INTO SYS_ATTACH_TEXT(R_TABLE, R_SID, R_FIELD, C_CONTENT, R_CREATE, S_CREATE, T_CREATE, R_UPDATE, S_UPDATE, T_UPDATE, R_TENANT, S_TENANT, P_TENANT)\n"
                        + "SELECT '%s', %s, R_FIELD, C_CONTENT, R_CREATE, S_CREATE, T_CREATE, R_UPDATE, S_UPDATE, T_UPDATE, R_TENANT, S_TENANT, P_TENANT\n"
                        + "FROM SYS_ATTACH_TEXT\n"
                        + "WHERE R_TABLE = ? AND R_SID = ?", targetFileTable, templdateSid );
        service.source.execute( sql, new Object[] { sourceFileTable, sourceSid } );
    }

    private boolean exists( TenantContext target ) {
        JdbcRecord folder = target.withTable( "SSM_STDTMP_FILE" )
                                  .where( "R_SOURCE=? and R_SID=?", sourceSid, folderId )
                                  .query();
        return folder != null;
    }
}
