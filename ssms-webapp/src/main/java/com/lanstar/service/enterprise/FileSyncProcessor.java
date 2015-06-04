/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileSyncProcessor.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.common.ModelInjector;
import com.lanstar.common.log.Logger;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.sqlinxml.SqlKit;

class FileSyncProcessor implements SyncProcessor {
    private final Logger log = Logger.getLogger( FileSyncProcessor.class );

    private final TemplateFolder tenantFolder;
    private final TemplateFile systemFile;

    public FileSyncProcessor( TemplateFolder tenantFolder, TemplateFile systemFile ) {
        this.tenantFolder = tenantFolder;
        this.systemFile = systemFile;
    }

    @Override
    public void sync( IdentityContext target ) {
        log.debug( "========>开始同步文件[%s->%s]...", tenantFolder.getName(), systemFile.getName() );
        // 同步文件信息
        com.lanstar.model.tenant.TemplateFile tenantFile =
                com.lanstar.model.tenant.TemplateFile.dao.findFirst(
                        SqlKit.sql( "tenant.templateFile.getFileByFileTmp" ),
                        this.systemFile.getId(),
                        this.tenantFolder.getId(),
                        target.getTenantId(),
                        target.getTenantType().getName() );

        if ( tenantFile == null ) tenantFile = new com.lanstar.model.tenant.TemplateFile();
        ModelKit.copyColumnsSkipEquals( systemFile, tenantFile,
                "C_NAME", "C_DESC", "B_REMIND", "N_CYCLE", "P_CYCLE", "S_CYCLE", "C_EXPLAIN", "P_TMPFILE", "S_TMPFILE", "N_STATE", "B_DELETE", "N_INDEX", "N_VERSION" );
        if ( tenantFile.isModified() ) {
            // tenantFile.setTenant(target.getIdentity() );
            ModelInjector.injectOpreator( tenantFile, target );
        }
        if ( tenantFile.getInt( "SID" ) == null ) {
            log.debug( "================>文件不存在，创建文件中..." );
            // 新建模板文件，要同步拷贝文件内容。
            tenantFile.setSourceFile( systemFile );
            tenantFile.setFolder( tenantFolder );
            log.debug( "================>拷贝文件内容->表单数据..." );
            tenantFile.setFileContent( systemFile.getFileContent() );
            log.debug( "================>拷贝文件内容->富文本数据..." );
            tenantFile.setAttachText( systemFile.getAttachText(), target );
            tenantFile.save();
            log.debug( "========>创建文件完成..." );
        } else tenantFile.update();
    }
}
