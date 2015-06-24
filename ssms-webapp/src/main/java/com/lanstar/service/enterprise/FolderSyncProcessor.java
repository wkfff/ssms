/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FolderSyncProcessor.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.common.ModelInjector;
import com.lanstar.common.log.Logger;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFolder;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

class FolderSyncProcessor implements SyncProcessor {
    private final Logger log = Logger.getLogger( FolderSyncProcessor.class );

    private final TemplateFolder systemFolder;
    private final int professionId;
    private com.lanstar.model.tenant.TemplateFolder parentFolder;

    public FolderSyncProcessor( TemplateFolder systemFolder, Profession profession ) {
        this.systemFolder = systemFolder;
        this.professionId = profession.getId();
    }

    private FolderSyncProcessor( TemplateFolder systemFolder, com.lanstar.model.tenant.TemplateFolder parentFolder ) {
        this.systemFolder = systemFolder;
        this.parentFolder = parentFolder;
        this.professionId = parentFolder.getProfessionId();
    }

    @Override
    public void sync( Tenant target, Identity operator ) {
        log.debug( "====>开始同步目录[%s]...", systemFolder.getName() );
        // 同步文件夹信息
        // “租户+专业+模板”确定数据的归属
        com.lanstar.model.tenant.TemplateFolder tenantFolder =
                com.lanstar.model.tenant.TemplateFolder.dao.findFirst(
                        SqlKit.sql( "tenant.templateFolder.getFolderByFolderTmp" ),
                        systemFolder.getId(),
                        target.getTenantId(),
                        target.getTenantType().getName(),
                        this.professionId,
                        this.systemFolder.getTemplateId() );
        if ( tenantFolder == null ) {
            tenantFolder = new com.lanstar.model.tenant.TemplateFolder();
            tenantFolder.setProfessionId( this.professionId );
            tenantFolder.setTemplateId( this.systemFolder.getTemplateId() );
            tenantFolder.setTenant( target );
        }
        ModelKit.copyColumnsSkipEquals( systemFolder, tenantFolder, "C_NAME", "C_DESC", "C_LOGO", "N_STATE", "B_DELETE", "N_INDEX", "N_VERSION" );
        if ( tenantFolder.isModified() ) {
            // tenantFolder.setTenant(target.getIdentity() );
            ModelInjector.injectOpreator( tenantFolder, operator, true );
        }
        if ( tenantFolder.getInt( "SID" ) == null ) {
            if ( parentFolder != null ) tenantFolder.setParent( parentFolder );
            tenantFolder.setSourceFolder( systemFolder );
            tenantFolder.save();
            log.debug( "====>租户不存在目录[%s]，因此创建目录[%s]...", systemFolder.getName(), tenantFolder.getName() );
        } else tenantFolder.update();

        // 同步子目录
        List<TemplateFolder> folders = systemFolder.listSubFolder();
        for ( TemplateFolder templateFolder : folders ) {
            new FolderSyncProcessor( templateFolder, tenantFolder ).sync( target, operator );
        }

        // 获取目录下的文件，并同步文件。
        List<TemplateFile> files = systemFolder.listFile();
        for ( TemplateFile file : files ) {
            new FileSyncProcessor( tenantFolder, file ).sync( target, operator );
        }
    }
}
