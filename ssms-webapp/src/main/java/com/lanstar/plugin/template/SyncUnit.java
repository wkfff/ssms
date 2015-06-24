/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SyncUnit.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import com.lanstar.app.Const;
import com.lanstar.common.ModelInjector;
import com.lanstar.common.log.Logger;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantContext;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.tenant.FileContentState;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.service.AttachTextService;

import java.util.List;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SyncUnit {
    private final Logger log = Logger.getLogger( SyncUnit.class );
    com.lanstar.model.system.TemplateFile sourceFile;
    com.lanstar.model.tenant.TemplateFile targetFile;
    TemplateFolder tenantFolder;
    TemplateProp templateProp;
    Tenant targetTenant;
    Identity operator;

    public void execute() {
        // 同步文件信息
        ModelKit.copyColumnsSkipEquals( sourceFile, targetFile,
                "C_NAME", "C_DESC", "B_REMIND", "N_CYCLE", "P_CYCLE", "S_CYCLE", "C_EXPLAIN", "P_TMPFILE", "S_TMPFILE", "N_INDEX" );
        if ( targetFile.isModified() ) {
            // tenantFile.setTenant(target.getIdentity() );
            ModelInjector.injectOpreator( targetFile, operator, true );
        }
        if ( targetFile.getId() == null ) initFile();
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
        targetFile.save();

        // 复制文件内容
        copyFileContent();
        // 同步来的文件，一开始一定为数量一定为0！！！
        targetFile.setFileCount( 0 );
        targetFile.update();

        log.debug( "========>创建文件完成..." );
    }

    protected void copyFileContent() {
        log.debug( "================>拷贝文件内容->表单数据..." );
        // 获取源文件ID
        Integer sourceFileId = sourceFile.getId();
        // 获取源文件DAO，并通过DAO获取文件内容。
        ModelExt sfcDao = templateProp.getSystemModelWrap().getDao();
        List<ModelExt> sourceContents = sfcDao.findByColumn( Const.TEMPLATE_FILE_PARENT_FIELD, sourceFileId );
        if ( sourceContents.size() == 0 ) return;
        for ( ModelExt sourceContent : sourceContents ) {
            Model targetContent = copyFileContent( sourceContent );
            copyAttachText( sourceContent, targetContent );
        }
    }

    protected Model copyFileContent( ModelExt systemFileContent ) {
        // 拷贝到租户文件中
        Model tenantFileContent = templateProp.getTenantModelWrap().getModel();
        ModelKit.clone( systemFileContent, tenantFileContent );
        tenantFileContent.remove( "SID", Const.TEMPLATE_FILE_PARENT_FIELD, "R_TENANT", "S_TENANT", "P_TENANT" );
        tenantFileContent.set( Const.TEMPLATE_FILE_PARENT_FIELD, targetFile.getId() );
        tenantFileContent.set( "R_TENANT", targetFile.get( "R_TENANT" ) );
        tenantFileContent.set( "S_TENANT", targetFile.get( "S_TENANT" ) );
        tenantFileContent.set( "P_TENANT", targetFile.get( "P_TENANT" ) );
        tenantFileContent.set( "R_TEMPLATE", targetFile.getTemplateId() );
        tenantFileContent.set( "R_TMPFLODER", this.tenantFolder.getId() );
        tenantFileContent.set( "P_PROFESSION", targetFile.getProfessionId() );
        tenantFileContent.set( "N_STATE", FileContentState.CLONED.getValue() );
        tenantFileContent.save();
        return tenantFileContent;
    }

    protected void copyAttachText( ModelExt systemFileContent, Model tenantFileContent ) {
        String src = templateProp.getSystemModelWrap().getTable().getName();
        Integer srcId = systemFileContent.getInt( "SID" );
        if ( srcId == null ) return;

        String dest = templateProp.getTenantModelWrap().getTable().getName();
        Integer destId = tenantFileContent.getInt( "SID" );
        if ( destId == null ) return;

        copyAttachText( src, srcId, dest, destId );
    }

    protected final void copyAttachText( String src, int srcId, String dest, int destId ) {
        log.debug( "================>拷贝文件内容->富文本数据..." );
        String content = AttachTextService.SYSTEM.getContent( src, "C_CONTENT", srcId );

        TenantContext tenantContext = TenantContext.with( targetTenant );
        AttachTextService service = tenantContext.getAttachTextService();
        service.save( dest, "C_CONTENT", destId, content, operator );
    }
}
