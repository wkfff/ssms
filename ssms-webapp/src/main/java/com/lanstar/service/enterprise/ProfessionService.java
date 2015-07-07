/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ProfessionService.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.google.common.collect.Lists;
import com.lanstar.beans.FolderBean;
import com.lanstar.beans.FolderTreeBuilder;
import com.lanstar.common.ListKit;
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantContext;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.model.tenant.TemplateVersion;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.ModelKit;

import java.util.List;

public class ProfessionService {
    private final Profession profession;
    private final TenantContext tenantContext;
    private Template template;
    private TemplateInitTask templateInitTask;

    public ProfessionService( Profession profession, TenantContext tenantContext ) {
        this.profession = profession;
        this.tenantContext = tenantContext;
    }

    /**
     * 判断模板是否已经初始化
     *
     * @return 如果已经初始化则返回true，否则返回false。
     */
    public boolean templateInitialized() {
        List<String> columns = ListKit.newArrayList( "R_TENANT", "P_TENANT", "P_PROFESSION" );
        List<Object> values = ListKit.newObjectArrayList(
                tenantContext.getTenantId(),
                tenantContext.getTenantType().getName(),
                getId() );
        return com.lanstar.model.tenant.Template.dao.findFirstByColumns( columns, values ) != null;
    }

    /**
     * 初始化模板
     *
     * @param operator 操作人员
     */
    public void initTemplate( Identity operator ) {
        TemplateInitTaskFactory factory = TemplateInitTaskFactory.me();
        Tenant tenant = tenantContext.getTenant();
        Profession profession = getProfession();
        Template systemTemplate = getSystemTemplate();
        templateInitTask = factory.startTask( tenant, profession, systemTemplate, operator );
    }

    /**
     * 获取模板初始化任务，该任务对象可以获取到当前执行的状态和任务日志。
     */
    public TemplateInitTask getTemplateInitTask() {
        return templateInitTask;
    }

    /**
     * 完成模板初始化工作
     */
    public synchronized void finishInitTemplate() {
        // 这里的完成主要是指清理
        if ( templateInitTask.status() == TemplateInitializerState.FINISH ) {
            TemplateInitTaskFactory factory = TemplateInitTaskFactory.me();
            Tenant tenant = tenantContext.getTenant();
            Profession profession = getProfession();
            Template systemTemplate = getSystemTemplate();
            factory.removeTask( tenant, profession, systemTemplate );
            templateInitTask = null;
        }
    }

    public List<TemplateFolder> listTenantTemplateFolder() {
        Integer id = getSystemTemplate().getId();
        return TemplateFolder.dao.findByColumn( "R_TEMPLATE", id );
    }

    /** 归档当前专业的达标体系数据 */
    public void archive( IdentityContext identityContext ) {
        Template systemTemplate = getSystemTemplate();
        // 获取所有的目录和文件，不包括文件内容
        List<TemplateFolder> folders = listTenantTemplateFolder();
        List<TemplateFile> files = TemplateFile.dao.find( "select * from ssm_stdtmp_file\n"
                + "where r_sid in (select sid from ssm_stdtmp_folder where r_template = ? and r_tenant=? and p_tenant='E')", systemTemplate
                .getId(), identityContext.getTenantId() );
        // 根据目录信息构造树
        FolderTreeBuilder treeBuilder = new FolderTreeBuilder( ModelKit.toMap( folders ), ModelKit.toMap( files ), "R_SID" );
        FolderBean root = treeBuilder.tree();
        // 创建归档版本信息
        TemplateVersion templateVersion = new TemplateVersion();
        ModelInjector.injectOpreator( templateVersion, identityContext );
        templateVersion.setTenant( identityContext.getTenant() );
        templateVersion.setProfession( profession );
        templateVersion.setTemplate( systemTemplate );
        templateVersion.setArchiveData( root );

        // 生成版本号
        // 版本号特征码：Ver.租户CODE+'-'+'P'+专业ID+'T'+模板ID
        // Ver.E350102-P1-T1
        String sign = "Ver." + identityContext.getTenantCode() + "-P" + getId() + "-T" + systemTemplate.getId();
        Object[] params = { sign, 0, "@" };
        Object[] objects = Db.callProcedure( "SP_GETSEQVALUE", params );
        int version = Integer.parseInt( (String) objects[2] );
        templateVersion.setVersion( version );

        templateVersion.save();

        /*for ( TemplateFile file : files ) {
            file.getTemplateProp().getTenantModelWrap().getDao().
        }*/
    }

    public Template getSystemTemplate() {
        if ( this.template == null ) {
            this.template = Template.getByProfession( profession.getId() );
            return template;
        }
        return Template.getByProfession( profession.getId() );
    }

    public TemplateFolder getTenantTemplateFolder() {
        Integer id = getSystemTemplate().getId();
        return TemplateFolder.dao.findFirstByColumns( Lists.newArrayList( "R_TEMPLATE", "R_TENANT", "P_TENANT", "P_PROFESSION", "R_SID" ), Lists
                .newArrayList( id, tenantContext.getTenantId(), tenantContext.getTenantType()
                                                                             .getName(), profession.getId(), (Object) 0 ) );
    }

    public String getName() {return profession.getName();}

    public Integer getId() {return profession.getId();}

    public Profession getProfession() {
        return profession;
    }
}

