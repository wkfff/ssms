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
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.controller.SimplateController;
import com.lanstar.core.render.JsonRender;
import com.lanstar.model.system.Template;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFolder;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class TemplateFolderController extends SimplateController<TemplateFolder> {
    public void tree() {
        List<TemplateFolder> list = TemplateFolder.list( getParaToInt( "template" ) );
        renderJson( EasyUIControllerHelper.toTree( "0", ModelKit.toMap( list ),
                                                   "SID", "R_SID", "C_NAME" ) );
    }

    public void manager() {
        final Integer template = getParaToInt( "template" );
        Asserts.notNull( template, "template 不能为空" );
        setAttr( "template", Template.dao.findById( template ) );
        setAttr( "items", listTemplateItems( template ) );
    }

    // 保存文件目录
    public void saveFolder() {
        Integer id = getParaToInt( "id" );
        String name = getPara( "name" );
        String desc = getPara( "desc" );
        Integer tempalte = getParaToInt( "template" );
        Integer index = getParaToInt( "index" );
        Integer parent = getParaToInt( "parent" );
        if ( id != null ) {
            TemplateFolder templatefolder = getDao().findById( id );
            templatefolder.set( "C_NAME", name );
            templatefolder.set( "C_DESC", desc );
            templatefolder.update();
            setAttr( "SID", id );
            renderJson();
        } else {
            TemplateFolder model = new TemplateFolder();
            model.set( "C_NAME", name );
            model.set( "C_DESC", desc );
            model.set( "R_TEMPLATE", tempalte );
            model.set( "R_SID", parent );
            model.set( "N_INDEX", index );
            afterSave( model );
            model.save();
            setAttr( "SID", model.getId() );
            renderJson();
        }
    }
    // 保存文件模板
    public void saveFile() {
        Integer id = getParaToInt( "id" );
        String name = getPara( "name" );
        String desc = getPara( "desc" );
        Integer tempalte = getParaToInt( "template" );
        Integer index = getParaToInt( "index" );
        Integer parent = getParaToInt( "parent" );
        String parentName = getPara( "parentName" );
        if ( id != null ) {
            TemplateFile templatefile = TemplateFile.dao.findById( id );
            templatefile.set( "C_NAME", name );
            templatefile.set( "C_DESC", desc );
            templatefile.update();
            setAttr( "SID", id );
            renderJson();
        } else {
            TemplateFile model = new TemplateFile();
            model.set( "C_NAME", name );
            model.set( "C_DESC", desc );
            model.set( "R_TEMPLATE", tempalte );
            model.set( "R_SID", parent );
            model.set( "N_INDEX", index );
            model.set( "S_NAME", parentName );
            model.save();
            setAttr( "SID", model.getId() );
            renderJson();
        }
    }

    public void removeFile() {
        TemplateFile model=TemplateFile.dao.findById(  getParaToInt( "id" ) );
        if(model!=null){
            model.delete();
            render( new JsonRender( true ).forIE() );
        }else{
            render( new JsonRender( false ).forIE() );
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

    @Override
    public void rec() {
        super.rec();
        renderJson();
    }

    @Override
    protected TemplateFolder getDao() {
        return TemplateFolder.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE()._If( isParaBlank( "R_SID" ) == false,
                                             "R_SID=?", getPara( "R_SID" ) );
    }

    @Override
    protected SqlBuilder buildOrder() {
        return new SqlBuilder().ORDER_BY( "N_INDEX, T_CREATE" );
    }

    @Override
    protected void afterSave( TemplateFolder model ) {
        if ( model.getIndex() == null ) model.setIndex( model.getId() );
    }

    private List<FolderBean> listTemplateItems( int template ) {
        final List<TemplateFolder> folders = TemplateFolder.list( template );
        List<TemplateFile> files = TemplateFile.listByTemplate( template );
        // 根据目录信息构造树
        FolderTreeBuilder treeBuilder = new FolderTreeBuilder( folders, files,
                                                               "R_SID" );
        FolderBean root = treeBuilder.tree();
        return root.getChildren();
    }
}
