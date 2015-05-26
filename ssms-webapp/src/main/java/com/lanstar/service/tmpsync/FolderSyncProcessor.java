/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FolderSyncProcessor.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.tmpsync;

import com.lanstar.common.ModelInjector;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFolder;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

class FolderSyncProcessor implements SyncProcessor {
    private final TemplateFolder folder;
    private com.lanstar.model.tenant.TemplateFolder parentFolder;

    public FolderSyncProcessor( TemplateFolder folder ) {
        this.folder = folder;
    }

    private FolderSyncProcessor( TemplateFolder folder, com.lanstar.model.tenant.TemplateFolder parentFolder ) {
        this.folder = folder;
        this.parentFolder = parentFolder;
    }

    @Override
    public void sync( IdentityContext target ) {
        // 同步文件夹信息
        com.lanstar.model.tenant.TemplateFolder tenantFolder = com.lanstar.model.tenant.TemplateFolder.dao.findFirst(
                SqlKit.sql( "tenant.templateFolder.getFolderByFolderTmp" ), folder.getId() );
        if ( tenantFolder == null ) tenantFolder = new com.lanstar.model.tenant.TemplateFolder();
        ModelKit.copyColumnsSkipEquals( folder, tenantFolder, "C_NAME", "R_TEMPLATE", "C_DESC", "C_LOGO", "N_STATE", "B_DELETE", "N_INDEX", "N_VERSION" );
        if ( tenantFolder.isModified() ) {
            // tenantFolder.setTenant(target.getIdentity() );
            ModelInjector.injectOpreator( tenantFolder, target );
        }
        if ( tenantFolder.getInt( "SID" ) == null ) {
            if ( parentFolder != null ) tenantFolder.setParent( parentFolder );
            tenantFolder.save();
        } else tenantFolder.update();

        // 同步子目录
        List<TemplateFolder> folders = folder.listSubFolder();
        for ( TemplateFolder templateFolder : folders ) {
            new FolderSyncProcessor( templateFolder, tenantFolder ).sync( target );
        }

        // 获取目录下的文件，并同步文件。
        List<TemplateFile> files = folder.listFile();
        for ( TemplateFile file : files ) {
            new FileSyncProcessor( tenantFolder, file ).sync( target );
        }
    }
}
