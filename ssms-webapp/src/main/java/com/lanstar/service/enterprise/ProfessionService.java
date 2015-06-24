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
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.model.tenant.TemplateVersion;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.IAtom;
import com.lanstar.plugin.activerecord.ModelKit;

import java.sql.SQLException;
import java.util.List;

public class ProfessionService {
    private final Profession profession;

    public ProfessionService( Profession profession ) {
        this.profession = profession;
    }

    /**
     * 同步专业模板到指定租户上下文中
     */
    public void sync( final IdentityContext identityContext ) {
        Db.tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                TemplateSyncProcessor.process( ProfessionService.this, identityContext );
                return true;
            }
        } );
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
        return Template.getByProfession( profession.getId() );
    }

    public TemplateFolder getTenantTemplateFolder() {
        // FIXME: 这里没有根据租户以及租户的专业进行过滤，因此取出来的数据可能有问题。
        Integer id = getSystemTemplate().getId();
        return TemplateFolder.dao.findFirstByColumns( Lists.newArrayList( "R_TEMPLATE", "R_SID" ), Lists.newArrayList( id, (Object) 0 ) );
    }

    public String getName() {return profession.getName();}

    public Integer getId() {return profession.getId();}

    public Profession getProfession() {
        return profession;
    }
}

