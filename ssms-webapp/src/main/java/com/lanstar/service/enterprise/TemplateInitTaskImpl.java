/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateInitializerImpl.java
 * 创建时间：2015-06-30
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantContext;
import com.lanstar.identity.TenantKit;
import com.lanstar.model.kit.folder.FileBean;
import com.lanstar.model.kit.folder.FolderBean;
import com.lanstar.model.system.Profession;
import com.lanstar.model.tenant.Template;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.model.tenant.TemplateText;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.IAtom;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.template.TemplatePropPlugin;
import com.lanstar.service.CycleType;
import com.lanstar.service.Parameter;

import java.sql.SQLException;
import java.util.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
class TemplateInitTaskImpl implements TemplateInitTask {
    private final Tenant tenant;
    private final Profession profession;
    private final com.lanstar.model.system.Template systemTemplate;
    private final Identity opertaor;
    private final FolderBean cacheContent;
    private final TenantContext tenantContext;
    private TemplateInitializerState status = TemplateInitializerState.NONE;
    private final List<Parameter> cycle = CycleType.parameters();

    private List<String> logs = new ArrayList<>();
    private Map<Class<?>, List<Model>> batchs = new LinkedHashMap<>();

    public TemplateInitTaskImpl( Tenant tenant, Profession profession, com.lanstar.model.system.Template systemTemplate, Identity opertaor ) {
        this.tenant = tenant;
        this.profession = profession;
        this.systemTemplate = systemTemplate;
        this.opertaor = opertaor;
        tenantContext = TenantKit.getContext( tenant );
        cacheContent = systemTemplate.getCacheContent();

        batchs.put( TemplateFolder.class, new ArrayList<Model>() );
        batchs.put( TemplateFile.class, new ArrayList<Model>() );
        batchs.put( TemplateText.class, new ArrayList<Model>() );
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
                try {
                    // 记录模板版本
                    initTemplateVersion();
                    // 拷贝目录
                    cloneFolders();
                    //cloneFileContetns();

                    for ( List<Model> batch : batchs.values() ) {
                        ModelKit.batchSave( DbPro.use(), batch );
                    }
                } catch ( Throwable e ) {
                    status = TemplateInitializerState.ERROR;
                    throw e;
                }
                return true;
            }
        } );

        Log( "模板初始化完成..." );

        status = TemplateInitializerState.FINISH;
        System.out.println( Joiner.on( "\n" ).join( logs ) );
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
        cloneFolder( null, cacheContent );
    }

    private void cloneFolder( FolderBean parent, FolderBean bean ) {
        if ( bean == null ) return;
        Log( "✔ ♦拷贝目录【%s】...", bean.getName() );
        TemplateFolder folder = new TemplateFolder();
        // 基础信息设置
        folder.setId( bean.getId() );
        folder.setName( bean.getName() );
        folder.setDescript( bean.getDescript() );
        folder.setIndex( bean.getIndex() );
        folder.setFileCount( bean.getFileCount() );
        // 设置关键的信息
        folder.setParentId( parent == null ? 0 : parent.getId() );
        folder.setTenant( tenant );
        folder.setTemplateId( systemTemplate.getId() );
        folder.setProfessionId( profession.getId() );
        folder.setVersion( systemTemplate.getVersion() );
        // 设置操作人员
        ModelInjector.injectOpreator( folder, opertaor, true );
        batchs.get( TemplateFolder.class ).add( folder );

        for ( FolderBean folderBean : bean.getChildren() ) {
            cloneFolder( bean, folderBean );
        }

        for ( FileBean fileBean : bean.getFiles() ) {
            cloneFile( bean, fileBean );
        }
    }

    private void cloneFile( FolderBean parent, FileBean bean ) {
        if ( bean == null ) return;
        Log( "✔ ♢拷贝文件【%s】...", bean.getName() );
        TemplateFile file = new TemplateFile();

        file.setId( bean.getId() );
        file.setName( bean.getName() );
        file.setDescript( bean.getDesc() );
        file.setIndex( bean.getIndex() );
        file.setTemplateProp( TemplatePropPlugin.me().get( bean.getTemplateFileCode() ) );
        file.setCycleUnitCode( bean.getCycleUnitCode() );
        file.setCycleUnitName( getCycleName( bean.getCycleUnitCode() ) );
        file.setCycleValue( bean.getCycleValue() );
        file.setExplain( bean.getExplain() );
        file.setRemind( bean.getRemind() );

        file.setParentId( parent.getId() );
        file.setParentName( parent.getName() );
        file.setTenant( tenant );
        file.setTemplateId( systemTemplate.getId() );
        file.setProfessionId( profession.getId() );
        file.setVersion( systemTemplate.getVersion() );
        ModelInjector.injectOpreator( file, opertaor, true );
        batchs.get( TemplateFile.class ).add( file );
    }

    private String getCycleName( final String code ) {
        Parameter parameter = Iterables.find( cycle, new Predicate<Parameter>() {
            @Override
            public boolean apply( Parameter input ) {
                return Objects.equals( input.getCode(), code );
            }
        }, null );
        if ( parameter == null ) return null;
        else return parameter.getName();
    }

    /*private void cloneFileContetns() {
        TemplatePropPlugin plugin = TemplatePropPlugin.me();

        // 文本列表
        List<com.lanstar.model.system.archive.TemplateText> textList = getAllTemplateText();

        for ( Parameter parameter : plugin.listParameter() ) {
            final TemplateProp prop = plugin.get( parameter.getCode() );
            ModelWrap archiveModelWrap = prop.getModel( ModelType.SYSTEM_ARCHIVE );
            List<com.lanstar.model.system.archive.ArchiveModel> list = archiveModelWrap.getDao().findByColumns(
                    ListKit.newArrayList( "R_TEMPLATE", "N_VERSION" ),
                    ListKit.newObjectArrayList( systemTemplate.getId(), systemTemplate.getVersion() ) );
            for ( final com.lanstar.model.system.archive.ArchiveModel model : list ) {
                final int id = model.getSid();
                model.remove( "UID", "SID" );
                ModelExt newModel = prop.getModel( ModelType.TENANT ).getModel();
                ModelKit.clone( model, newModel );
                TemplateFileModel tenantModel = (TemplateFileModel) newModel;
                tenantModel.setTenant( tenant );
                tenantModel.setOperator( opertaor );
                tenantModel.setTemplateId( systemTemplate.getId() );
                tenantModel.setProfessionId( profession.getId() );
                tenantModel.save();

                com.lanstar.model.system.archive.TemplateText text = Iterables.find( textList, new Predicate<com.lanstar.model.system.archive.TemplateText>() {
                    @Override
                    public boolean apply( com.lanstar.model.system.archive.TemplateText input ) {
                        return input.getParentId() == id
                                && Objects.equals( input.getTemplateFileCode(), prop.getCode() );
                    }
                }, null );
                if ( text != null ) {
                    TemplateText newText = new TemplateText();
                    text.remove( "UID", "SID" );
                    ModelKit.clone( text, newText );
                    newText.setParentId( tenantModel.getId() );
                    newText.setTenant( tenant );
                    newText.setOperator( opertaor );
                    newText.setTemplateId( systemTemplate.getId() );
                    newText.setProfessionId( profession.getId() );
                    batchs.get( TemplateText.class ).add( newText );
                }
            }
        }
    }

    private List<com.lanstar.model.system.archive.TemplateText> getAllTemplateText() {
        return com.lanstar.model.system.archive.TemplateText.dao.findByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "N_VERSION" ),
                ListKit.newObjectArrayList( systemTemplate.getId(), systemTemplate.getVersion() ) );
    }*/
}
