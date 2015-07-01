/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateItemController.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import java.util.List;

import com.lanstar.beans.system.FolderBean;
import com.lanstar.beans.system.FolderTreeBuilder;
import com.lanstar.common.Asserts;
import com.lanstar.common.ModelInjector;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Template;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFolder;
import com.lanstar.plugin.template.TemplatePropPlugin;
import com.lanstar.service.MultiParaType;

public class TemplateFolderController extends Controller {
    public void manager() {
        final Integer template = getParaToInt( "template" );
        Asserts.notNull( template, "template 不能为空" );
        setAttr( "template", Template.dao.findById( template ) );
        setAttr( "SYS_CYCLE", MultiParaType.SYS_CYCLE.parameters() );
        setAttr( "tmpfiles", TemplatePropPlugin.me().listParameter() );
        setAttr( "items", getTemplate( template ).getChildren() );
    }

    // 保存文件目录
    public void saveFolder() {
        Integer id = getParaToInt( "id" );
        String name = getPara( "name" );
        String desc = getPara( "desc" );
        Integer index = getParaToInt( "index" );
        int template = getParaToInt( "template" );
        int parent = getParaToInt( "parent" );
        TemplateFolder model;
        if ( id == null ) model = new TemplateFolder();
        else model = TemplateFolder.dao.findById( id );

        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        model.setName( name );
        model.setDescript( desc );
        model.setIndex( index );
        model.setTemplateId( template );
        model.setParentId( parent );
        ModelInjector.injectOpreator( model, identityContext );

        if ( id == null ) model.save();
        else model.update();

        renderJson( model.getId() );
    }

    // 保存文件模板
    public void saveFile() {
        Integer id = getParaToInt( "id" );
        String name = getPara( "name" );
        String desc = getPara( "desc" );
        Integer template = getParaToInt( "template" );
        Integer index = getParaToInt( "index" );
        Integer parentId = getParaToInt( "parentId" );
        String parentName = getPara( "parentName" );
        String templateFileCode = getPara( "templateFileCode" );
        String explain = getPara( "explain" );
        String cycleUnitCode = getPara( "cycleUnitCode" );
        String cycleUnitName = getPara( "cycleUnitName" );
        Integer cycleValue = getParaToInt( "cycleValue" );

        TemplateFile model;
        if ( id == null ) model = new TemplateFile();
        else model = TemplateFile.dao.findById( id );

        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        model.setTemplateId( template );
        model.setParentId( parentId );
        model.setParentName( parentName );
        model.setName( name );
        model.setDescript( desc );
        model.setIndex( index );
        model.setCycleValue( cycleValue );
        model.setCycleUnitCode( cycleUnitCode );
        model.setCycleUnitName( cycleUnitName );
        model.setTemplateProp( TemplatePropPlugin.me().get( templateFileCode ) );
        model.setExplain( explain );

        ModelInjector.injectOpreator( model, identityContext );

        if ( id == null ) model.save();
        else model.update();

        renderJson( model.getId() );
    }

    public void removeFile() {
        TemplateFile model = TemplateFile.dao.findById( getParaToInt( "id" ) );
        if ( model != null ) {
            renderJson( model.delete() );
        } else {
            renderJson( false );
        }
    }

    public void removeFolder() {
        TemplateFolder model = TemplateFolder.dao.findById( getParaToInt( "id" ) );
        if ( model != null ) {
            renderJson( model.delete() );
        } else {
            renderJson( false );
        }
    }

    public void publish() {
        final Integer templateId = getParaToInt();
        final List<FolderBean> folderBeans = listTemplateItems( templateId );
        final Template template = Template.dao.findById( templateId );
        template.setCacheContent( folderBeans );
        template.setVersion( template.getVersion() + 1 );
        renderJson( template.update() );
    }

    private FolderBean getTemplate( int template ) {
        final List<TemplateFolder> folders = TemplateFolder.list( template );
        List<TemplateFile> files = TemplateFile.listByTemplate( template );
        // 根据目录信息构造树
        FolderTreeBuilder treeBuilder = new FolderTreeBuilder( folders, files, "R_SID" );
        return treeBuilder.tree();
    }
}
