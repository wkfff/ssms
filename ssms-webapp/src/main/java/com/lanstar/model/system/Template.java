/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Template.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.alibaba.fastjson.JSON;
import com.lanstar.model.kit.folder.FolderBean;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

public class Template extends ModelExt<Template> {
    public static final Template dao = new Template();

    public Template() {
        deleteColumnLabel = "B_DELETE";
    }

    public static Template getByProfession( int professionId ) {
        return dao.findFirst( SqlKit.sql( "system.template.list" ), professionId );
    }

    @Override
    public boolean pseudoDelete() {
        return true;
    }

    public List<TemplateFolder> listFolder() {
        return TemplateFolder.listByTemplate( getId() );
    }

    public List<TemplateFolder> listFolder( int parentId ) {
        return TemplateFolder.listByTemplateAndParent( getId(), parentId );
    }

    public int getVersion() {
        Integer version = getInt( "N_VERSION" );
        if ( version == null ) version = 0;
        return version;
    }

    public void setVersion( int version ) {
        set( "N_VERSION", version );
    }

    public FolderBean getCacheContent() {
        return JSON.parseObject( getStr( "C_CONTENT" ), FolderBean.class );
    }

    public void setCacheContent( FolderBean folderBeans ) {
        set( "C_CONTENT", JSON.toJSONString( folderBeans ) );
    }

    public int getId() {
        return getInt( "SID" );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }
}
