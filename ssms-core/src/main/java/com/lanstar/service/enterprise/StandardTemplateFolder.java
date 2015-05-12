/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateFolder.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.service.OperateContext;

class StandardTemplateFolder implements IClonable<OperateContext> {
    private final ProfessionTemplateService service;
    private final JdbcRecord folderRecord;

    public StandardTemplateFolder( ProfessionTemplateService service, JdbcRecord folderRecord ) {
        this.service = service;
        this.folderRecord = folderRecord;
    }

    @Override
    public void cloneTo( OperateContext target ) {
        ARTable table = target.withTable( "SSM_STDTMP_FOLDER" )
                              .values( folderRecord )
                              .value( "R_TENANT", target.getTenantId() )
                              .value( "S_TENANT", target.getTenantName() )
                              .value( "P_TENANT", target.getTenantType().getName() );
        table.getValues().remove( "SID" );
        table.insert();

        int sid = target.getDbContext().getSID();

        // 目录下的文件
        JdbcRecordSet files = service.getOperateContext().withTable( "SYS_STDTMP_FILE" )
                                     .where( "R_SID=?", folderRecord.get( "SID" ) )
                                     .queryList();
        ClonableList<OperateContext> fileList = new ClonableList<>();
        for ( JdbcRecord file : files ) {
            fileList.add( new StandardTemplateFile( service, file, sid ) );
        }
        fileList.cloneTo( target );
    }
}
