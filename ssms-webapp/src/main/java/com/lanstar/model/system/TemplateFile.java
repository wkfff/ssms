/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.google.common.collect.Lists;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.plugin.template.ModelWrap;
import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplatePropPlugin;

import java.util.Date;
import java.util.List;

public class TemplateFile extends ModelExt<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    public static List<TemplateFile> list( Integer folderId ) {
        return TemplateFile.dao.find( SqlKit.sql( "system.templateFile.getFiles" ), folderId );
    }

    public static TemplateFile findByFileContent( String fileCode, int fileId ) {
        return dao.findFirstByColumns( Lists.newArrayList( "P_TMPFILE", "R_TMPFILE" ), Lists.newArrayList( fileCode, (Object) fileId ) );
    }

    @Override
    public boolean delete() {
        // TODO:
        //context.getAttachTextService().del( "STDTMP_FILE_" + code, "C_CONTENT", tmpfileId );

        getTemplateProp().getSystemModelWrap().getDao().deleteById( getTemplateFileId() );
        return super.delete();
    }

    @Override
    public boolean deleteById( Object id ) {
        // TODO:
        //context.getAttachTextService().del( "STDTMP_FILE_" + code, "C_CONTENT", tmpfileId );
        getTemplateProp().getSystemModelWrap().getDao().deleteById( getTemplateFileId() );

        return super.deleteById( id );
    }

    @Override
    public boolean save() {
        initFileContent();
        return super.save();
    }

    /** 获取模板属性 */
    public TemplateProp getTemplateProp() {
        return TemplatePropPlugin.me().get( getTemplateFileCode() );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public Integer getTemplateFileId() {
        return getInt( "R_TMPFILE" );
    }

    /** 设置模板文件ID */
    public void setTemplateFileId( Integer id ) {
        set( "R_TMPFILE", id );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public Object getName() {
        return getStr( "C_NAME" );
    }

    @SuppressWarnings("rawtypes")
    private boolean initFileContent() {
        ModelWrap modelWrap = getTemplateProp().getSystemModelWrap();
        Model model = modelWrap.getModel();
        model.set( "R_CREATE", get( "R_CREATE" ) )
             .set( "S_CREATE", get( "S_CREATE" ) )
             .set( "T_CREATE", new Date() )
             .set( "R_UPDATE", get( "R_UPDATE" ) )
             .set( "S_UPDATE", get( "S_UPDATE" ) )
             .set( "T_UPDATE", new Date() )
             .set( "R_TENANT", get( "R_TENANT" ) )
             .set( "S_TENANT", get( "S_TENANT" ) )
             .set( "P_TENANT", get( "P_TENANT" ) );

        boolean success = model.save();
        setTemplateFileId( model.getInt( modelWrap.getTable().getPrimaryKey() ) );

        return success;
    }
}
