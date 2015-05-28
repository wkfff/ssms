/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFolder.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

public class TemplateFolder extends Model<TemplateFolder> {
    public static final TemplateFolder dao = new TemplateFolder();

    public static List<TemplateFolder> list( int template ) {
        return dao.find( SqlKit.sql( "system.templateFolder.getFolders" ), template );
    }

    public static List<TemplateFolder> list( int template, int parentId ) {
        return dao.find( SqlKit.sql( "system.templateFolder.getFoldersByParentId" ), template, parentId );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getName(){
        return getStr( "C_NAME" );
    }

    public Integer getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    public List<TemplateFolder> listSubFolder() {
        return list( getTemplateId(), getId() );
    }

    public List<TemplateFile> listFile() {
        return TemplateFile.list( getId() );
    }
}
