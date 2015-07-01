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
        return TemplateFile.dao.find( SqlKit.sql( "system.templateFile.getFiles" ),
                                      folderId );
    }

    public static List<TemplateFile> listByTemplate( int template ) {
        return TemplateFile.dao.find( SqlKit.sql( "system.templateFile.getFilesByTemplate" ),
                                      template );
    }

    public String getRemind() {

        return getStr( "B_REMIND" );
    }

    @Override
    public boolean delete() {
        // TODO:
        // context.getAttachTextService().del( "STDTMP_FILE_" + code,
        // "C_CONTENT", tmpfileId );
        getTemplateProp().getSystemModelWrap().getDao()
                         .deleteById( getId(), "R_TMPFILE" );
        return super.delete();
    }

    @Override
    public boolean deleteById( Object id ) {
        // TODO:
        // context.getAttachTextService().del( "STDTMP_FILE_" + code,
        // "C_CONTENT", tmpfileId );
        getTemplateProp().getSystemModelWrap().getDao()
                         .deleteById( getId(), "R_TMPFILE" );
        return super.deleteById( id );
    }

    public Integer getIndex() {
        return getInt( "N_INDEX" );
    }

    public void setIndex( Integer index ) {
        set( "N_INDEX", index );
    }

    /** 获取模板属性 */
    public TemplateProp getTemplateProp() {
        return TemplatePropPlugin.me().get( getTemplateFileCode() );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public String getTemplateFileName() {
        return getStr( "S_TMPFILE" );
    }

    public void setTemplateProp( TemplateProp prop ) {
        set( "P_TMPFILE", prop.getCode() );
        set( "S_TMPFILE", prop.getName() );
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

    public Integer getCycleValue() {
        return getInt( "N_CYCLE" );
    }

    public void setCycleValue( Integer value ) {
        set( "N_CYCLE", value );
    }

    public String getCycleUnitCode() {
        return getStr( "P_CYCLE" );
    }

    public void setCycleUnitCode( String value ) {
        set( "P_CYCLE", value );
    }

    public String getCycleUnitName() {
        return getStr( "S_CYCLE" );
    }

    public void setCycleUnitName( String name ) {
        set( "S_CYCLE", name );
    }

    public String getExplain() {
        return getStr( "C_EXPLAIN" );
    }

    public void setExplain( String explain ) {
        set( "C_EXPLAIN", explain );
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

    public void setParentId( Integer parentId ) {
        set( "R_SID", parentId );
    }

    public String getParentName() {
        return getStr( "S_NAME" );
    }

    public void setParentName( String parentName ) {
        set( "S_NAME", parentName );
    }

    public int getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    public void setTemplateId( Integer template ) {
        set( "R_TEMPLATE", template );
    }

    public void setRemind( String remind ) {
        set( "B_REMIND", remind );
    }
}
