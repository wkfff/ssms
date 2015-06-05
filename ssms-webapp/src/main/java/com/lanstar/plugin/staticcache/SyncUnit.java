/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SyncUnit.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.lanstar.common.ModelInjector;
import com.lanstar.common.log.Logger;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.service.AttachTextService;

@SuppressWarnings("rawtypes")
public class SyncUnit {
    private final Logger log = Logger.getLogger( SyncUnit.class );
    com.lanstar.model.system.TemplateFile sourceFile;
    com.lanstar.model.tenant.TemplateFile targetFile;
    TemplateFolder tenantFolder;
    IdentityContext targetContext;
    TemplateProp templateProp;

    public void execute() {
        // 同步文件信息
        ModelKit.copyColumnsSkipEquals( sourceFile, targetFile,
                "C_NAME", "C_DESC", "B_REMIND", "N_CYCLE", "P_CYCLE", "S_CYCLE", "C_EXPLAIN", "P_TMPFILE", "S_TMPFILE", "N_STATE", "B_DELETE", "N_INDEX", "N_VERSION" );
        if ( targetFile.isModified() ) {
            // tenantFile.setTenant(target.getIdentity() );
            ModelInjector.injectOpreator( targetFile, targetContext );
        }
        if ( targetFile.getInt( "SID" ) == null ) initFile();
        else targetFile.update();
    }

    public TemplateFile getSourceFile() {
        return sourceFile;
    }

    public com.lanstar.model.tenant.TemplateFile getTargetFile() {
        return targetFile;
    }

    public TemplateFolder getTenantFolder() {
        return tenantFolder;
    }

    public IdentityContext getTargetContext() {
        return targetContext;
    }

    public TemplateProp getTemplateProp() {
        return templateProp;
    }

    public Logger getLog() {
        return log;
    }

    protected void initFile() {
        log.debug( "================>文件不存在，创建文件中..." );
        // 新建模板文件，要同步拷贝文件内容。
        targetFile.setSourceFile( sourceFile );
        targetFile.setFolder( tenantFolder );

        // 复制文件内容
        copyFileContent();
        // 复制富文本
        copyAttachText();
        // 保存模板文件
        targetFile.save();
        log.debug( "========>创建文件完成..." );
    }

    protected void copyFileContent() {
        log.debug( "================>拷贝文件内容->表单数据..." );
        // 获取系统文件内容
        Model systemFileContent = templateProp.getSystemModelWrap().getDao().findById( sourceFile.getTemplateFileId() );
        if ( systemFileContent == null ) return;

        // 拷贝到租户文件中
        Model tenantFileContent = templateProp.getTenantModelWrap().getModel();
        ModelKit.clone( systemFileContent, tenantFileContent );
        tenantFileContent.remove( "SID", "R_TENANT", "S_TENANT", "P_TENANT" );
        tenantFileContent.set( "R_TENANT", targetFile.get( "R_TENANT" ) );
        tenantFileContent.set( "S_TENANT", targetFile.get( "S_TENANT" ) );
        tenantFileContent.set( "P_TENANT", targetFile.get( "P_TENANT" ) );
        tenantFileContent.save();
        Integer id = tenantFileContent.getInt( templateProp.getTenantModelWrap().getTable().getPrimaryKey() );
        targetFile.setTemplateFileId( id );
    }

    protected void copyAttachText() {
        String src = templateProp.getSystemModelWrap().getTable().getName();
        Integer srcId = sourceFile.getTemplateFileId();
        if ( srcId == null ) return;

        String dest = templateProp.getTenantModelWrap().getTable().getName();
        Integer destId = targetFile.getTemplateFileId();
        if ( destId == null ) return;

        copyAttachText( src, srcId, dest, destId );
    }

    protected final void copyAttachText( String src, int srcId, String dest, int destId ) {
        log.debug( "================>拷贝文件内容->富文本数据..." );
        String content = AttachTextService.SYSTEM.getContent( src, "C_CONTENT", srcId );

        AttachTextService service = targetContext.getAttachTextService();
        service.save( dest, "C_CONTENT", destId, content, targetContext );
    }
}
