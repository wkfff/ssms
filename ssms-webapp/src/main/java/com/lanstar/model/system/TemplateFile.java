/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

import java.util.List;

public class TemplateFile extends ModelExt<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    public static List<TemplateFile> list( Integer folderId ) {
        return TemplateFile.dao.find( SqlKit.sql( "system.templateFile.getFiles" ), folderId );
    }

    @Override
    public boolean delete() {
        // TODO:
        //context.getAttachTextService().del( "STDTMP_FILE_" + code, "C_CONTENT", tmpfileId );
        getTemplateProp().getSystemModelWrap().getDao().deleteById( getId(), "R_TMPFILE" );
        return super.delete();
    }

    @Override
    public boolean deleteById( Object id ) {
        // TODO:
        //context.getAttachTextService().del( "STDTMP_FILE_" + code, "C_CONTENT", tmpfileId );
        getTemplateProp().getSystemModelWrap().getDao().deleteById( getId(), "R_TMPFILE" );
        return super.deleteById( id );
    }

    /** 获取模板属性 */
    public TemplateProp getTemplateProp() {
        return TemplatePropPlugin.me().get( getTemplateFileCode() );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public Object getName() {
        return getStr( "C_NAME" );
    }
}
