/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFolder.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.model.TenantModel;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

public class TemplateFolder extends TenantModel<TemplateFolder> {
    public static final TemplateFolder dao = new TemplateFolder();

    public static List<TemplateFolder> listByTemplate( int template ) {
        return dao.find( SqlKit.sql( "system.templateFolder.getFolders" ), template );
    }

    public static List<TemplateFolder> listByTemplateAndParent( int template, int parentId ) {
        return dao.find( SqlKit.sql( "system.templateFolder.getFoldersByParentId" ), template, parentId );
    }

    public List<TemplateFolder> listSubFolder() {
        return listByTemplateAndParent( getTemplateId(), getId() );
    }

    public List<TemplateFile> listFile() {
        return TemplateFile.list( getId() );
    }

    public Integer getIndex() {
        return getInt( "N_INDEX" );
    }

    public void setIndex( Integer index ) {
        set( "N_INDEX", index );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }

    public Integer getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    public void setTemplateId( int id ) {
        set( "R_TEMPLATE", id );
    }

    public String getDescript() {
        return getStr( "C_DESC" );
    }

    public void setDescript( String desc ) {
        set( "C_DESC", desc );
    }

    public int getParentId() {
        return getInt( "R_SID" );
    }

    public void setParentId( int id ) {
        set( "R_SID", id );
    }
}
