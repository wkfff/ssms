/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateInitializerImpl.java
 * 创建时间：2015-06-30
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lanstar.beans.system.FileBean;
import com.lanstar.beans.system.FolderBean;
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantContext;
import com.lanstar.model.system.Profession;
import com.lanstar.model.tenant.Template;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.IAtom;
import com.lanstar.plugin.activerecord.ModelKit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class TemplateInitTaskImpl implements TemplateInitTask {
    private final Tenant tenant;
    private final Profession profession;
    private final com.lanstar.model.system.Template systemTemplate;
    private final Identity opertaor;
    private final FolderBean cacheContent;
    private final TenantContext tenantContext;
    private TemplateInitializerState status;
    private List<TemplateFolder> batchSaveFolders = Lists.newArrayList();
    private List<TemplateFile> batchSaveFiles = Lists.newArrayList();

    private List<String> logs = new ArrayList<>();

    public TemplateInitTaskImpl( Tenant tenant, Profession profession, com.lanstar.model.system.Template systemTemplate, Identity opertaor ) {
        this.tenant = tenant;
        this.profession = profession;
        this.systemTemplate = systemTemplate;
        this.opertaor = opertaor;
        tenantContext = TenantContext.with( tenant );
        cacheContent = systemTemplate.getCacheContent();
    }

    @Override
    public TemplateInitializerState status() {
        return status;
    }

    @Override
    public void run() {
        status = TemplateInitializerState.STARTUP;

        Log( "=======================================================" );
        Log( "当前专业:%s", profession.getName() );
        Log( "=======================================================" );
        Log( "开始初始化模板..." );

        tenantContext.getTenantDb().tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                // 记录模板版本
                initTemplateVersion();
                // 拷贝目录
                cloneFolders();

                if ( batchSaveFolders.size() > 0 )
                    ModelKit.batchSave( tenantContext.getTenantDb(), batchSaveFolders );
                if ( batchSaveFiles.size() > 0 )
                    ModelKit.batchSave( tenantContext.getTenantDb(), batchSaveFiles );

                return true;
            }
        } );

        Log( "模板初始化完成..." );

        status = TemplateInitializerState.FINISH;
        System.out.println(Joiner.on( "\n" ).join( logs ));
    }

    @Override
    public List<String> getLogs() {
        return Lists.newArrayList( logs );
    }

    private void Log( String msg, Object... args ) {
        this.logs.add( args.length > 0 ? String.format( msg, args ) : msg );

    }

    private void initTemplateVersion() {
        Template template = new Template();
        template.setTemplate( systemTemplate );
        template.setProfession( profession );
        template.setTenant( tenant );
        ModelInjector.injectOpreator( template, opertaor, true );
        template.save();
    }

    private void cloneFolders() {
        cloneFolder( cacheContent );
    }

    private void cloneFolder( FolderBean bean ) {
        if ( bean == null ) return;
        Log( "✔ ♦拷贝目录【%s】...", bean.getName() );
        TemplateFolder folder = new TemplateFolder();
        folder.setId( bean.getId() );
        folder.setName( bean.getName() );
        // ....

        folder.setTenant( tenant );
        folder.setTemplateId( systemTemplate.getId() );
        folder.setProfessionId( profession.getId() );
        ModelInjector.injectOpreator( folder, opertaor, true );
        batchSaveFolders.add( folder );

        for ( FolderBean folderBean : bean.getChildren() ) {
            cloneFolder( folderBean );
        }

        for ( FileBean fileBean : bean.getFiles() ) {
            cloneFile( fileBean );
        }
    }

    private void cloneFile( FileBean bean ) {
        if ( bean == null ) return;
        Log( "✔ ♢拷贝文件【%s】...", bean.getName() );
        TemplateFile file = new TemplateFile();

        file.setId( bean.getId() );
        file.setName( bean.getName() );
        // ....

        file.setTenant( tenant );
        file.setTemplateId( systemTemplate.getId() );
        file.setProfessionId( profession.getId() );
        ModelInjector.injectOpreator( file, opertaor, true );
        batchSaveFiles.add( file );
    }
}
