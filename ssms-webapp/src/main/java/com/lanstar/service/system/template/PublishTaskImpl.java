/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PublishTaskImpl.java
 * 创建时间：2015-07-01
 * 创建用户：张铮彬
 */

package com.lanstar.service.system.template;

import com.google.common.collect.Lists;
import com.lanstar.beans.system.FolderBean;
import com.lanstar.beans.system.FolderTreeBuilder;
import com.lanstar.model.system.TemplateText;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.model.system.archive.Template;
import com.lanstar.model.system.archive.TemplateFile;
import com.lanstar.model.system.archive.TemplateFolder;
import com.lanstar.plugin.activerecord.*;
import com.lanstar.plugin.template.ModelType;
import com.lanstar.plugin.template.ModelWrap;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;
import com.lanstar.service.Parameter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
class PublishTaskImpl implements PublishTask {
    private final int templateId;
    private PublishState status = PublishState.NONE;
    private List<String> logs = new ArrayList<>();
    private List<com.lanstar.model.system.TemplateFolder> folders;
    private List<com.lanstar.model.system.TemplateFile> files;
    private com.lanstar.model.system.Template template;
    private Map<Class<?>, List<Model>> batchs = new LinkedHashMap<>();

    public PublishTaskImpl( int templateId ) {
        this.templateId = templateId;

        batchs.put( com.lanstar.model.system.TemplateFolder.class, new ArrayList<Model>() );
        batchs.put( com.lanstar.model.system.TemplateFile.class, new ArrayList<Model>() );
        batchs.put( com.lanstar.model.system.TemplateText.class, new ArrayList<Model>() );
        for ( Parameter parameter : TemplatePropPlugin.me().listParameter() ) {
            TemplateProp prop = TemplatePropPlugin.me().get( parameter.getCode() );
            batchs.put( prop.getModel( ModelType.SYSTEM ).getModelClass(), new ArrayList<Model>() );
        }
    }

    @Override
    public PublishState status() {
        return status;
    }

    @Override
    public void run() {
        status = PublishState.STARTUP;
        Db.tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                template = com.lanstar.model.system.Template.dao.findById( templateId );
                Log( "开始发布模板:%s...", template.getName() );
                if ( template == null ) Log( "无法找到模板，请确认模板有效。" );
                else {
                    folders = com.lanstar.model.system.TemplateFolder.list( templateId );
                    files = com.lanstar.model.system.TemplateFile.listByTemplate( templateId );

                    Log( "正在归档模板..." );
                    archiveTemplate();
                    Log( "正在归档目录..." );
                    archiveFolders();
                    Log( "正在归档文件..." );
                    archiveFiles();
                    Log( "正在归档文件内容..." );
                    archiveFileContents();
                    Log( "正在归档文件文本..." );
                    archiveFileText();

                    for ( List<Model> batch : batchs.values() ) {
                        ModelKit.batchSave( DbPro.use(), batch );
                    }
                }
                Log( "开始模板发布完成..." );
                return true;
            }
        } );
        status = PublishState.FINISH;
    }

    private FolderBean getTemplate() {
        // 根据目录信息构造树
        FolderTreeBuilder treeBuilder = new FolderTreeBuilder( folders, files, "R_SID" );
        return treeBuilder.tree();
    }

    @Override
    public List<String> getLogs() {
        return Lists.newArrayList( logs );
    }

    private void archiveTemplate() {
        FolderBean folderBeans = getTemplate();
        template.setCacheContent( folderBeans );
        template.setVersion( template.getVersion() + 1 );
        template.update();

        Template archive = new Template();
        ModelKit.clone( template, archive );
        archive.save();
    }

    private void archiveFolders() {
        for ( com.lanstar.model.system.TemplateFolder folder : folders ) {
            TemplateFolder archive = new TemplateFolder();
            ModelKit.clone( folder, archive );
            archive.setVersion( template.getVersion() );
            batchs.get( com.lanstar.model.system.TemplateFolder.class ).add( archive );
            Log( "✔ ☆正在归档目录:%s", folder.getName() );
        }
    }

    private void archiveFiles() {
        for ( com.lanstar.model.system.TemplateFile file : files ) {
            TemplateFile archive = new TemplateFile();
            ModelKit.clone( file, archive );
            archive.setVersion( template.getVersion() );
            batchs.get( com.lanstar.model.system.TemplateFile.class ).add( archive );
            Log( "✔ ★正在归档文件:%s", file.getName() );
        }
    }

    private void archiveFileContents() {
        TemplatePropPlugin plugin = TemplatePropPlugin.me();
        for ( Parameter parameter : plugin.listParameter() ) {
            TemplateProp prop = plugin.get( parameter.getCode() );
            ModelWrap srcModelWrap = prop.getModel( ModelType.SYSTEM );
            ModelWrap archiveModelWrap = prop.getModel( ModelType.SYSTEM_ARCHIVE );
            List<Model> contents = srcModelWrap.getDao().findByColumn( "R_TEMPLATE", templateId );
            for ( Model content : contents ) {
                ArchiveModel newModel = (ArchiveModel) archiveModelWrap.getModel();
                ModelKit.clone( content, newModel );
                newModel.setVersion( template.getVersion() );
                batchs.get( srcModelWrap.getModelClass() ).add( newModel );
            }
        }
    }

    private void archiveFileText() {
        List<TemplateText> list = TemplateText.dao.findByColumn( "R_TEMPLATE", templateId );
        for ( TemplateText templateText : list ) {
            com.lanstar.model.system.archive.TemplateText archive = new com.lanstar.model.system.archive.TemplateText();
            ModelKit.clone( templateText, archive );
            archive.setVersion( template.getVersion() );
            batchs.get( TemplateText.class ).add( archive );
        }
    }

    private void Log( String msg, Object... args ) {
        this.logs.add( args.length > 0 ? String.format( msg, args ) : msg );
    }
}
