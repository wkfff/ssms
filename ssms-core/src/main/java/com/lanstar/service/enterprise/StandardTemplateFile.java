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
    private final JdbcRecord file;
    private final int sid;

    public StandardTemplateFile( ProfessionTemplateService service, JdbcRecord file, int sid ) {
        this.service = service;
        this.file = file;
        this.sid = sid;
    }

    @Override
    public void cloneTo( TenantContext target ) {
        ARTable fileTable = target.withTable( "SSM_STDTMP_FILE" )
                                  .values( file )
                                  .value( "R_SID", sid )
                                  .value( "R_TENANT", target.getTenantId() )
                                  .value( "S_TENANT", target.getTenantName() )
                                  .value( "P_TENANT", target.getTenantType().getName() );
        fileTable.getValues().remove( "SID" );
        fileTable.insert();

        String code = file.getString( "P_TMPFILE" );
        String sid = file.getString( "R_TMPFILE" );
        JdbcRecord tmpFile = service.getIdentityContext().withTable( "SYS_STDTMP_FILE_" + code ).where( "SID=?", sid )
                                    .query();
        ARTable table = target.withTable( "SSM_STDTMP_FILE_" + code ).values( tmpFile );
        table.getValues().remove( "SID" );
        table.insert();
    }
}
