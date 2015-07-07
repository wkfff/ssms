/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateText.java
 * 创建时间：2015-07-02
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.archive;

import com.lanstar.common.ListKit;
import com.lanstar.identity.Tenant;

import java.util.Objects;

public class TemplateText extends ArchiveModel<TemplateText> {
    public static final TemplateText dao = new TemplateText();

    public static String getContent( TemplateFile file, int recoredId ) {
        return getContent( file.getTemplateId(), file.getVersion(), file.getTemplateFileCode(), recoredId );
    }

    public static String getContent( int templateId, int version, String templateFileCode, int recoredId ) {
        TemplateText text = dao.findFirstByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "P_TMPFILE", "R_SID", "N_VERSION" ),
                ListKit.newObjectArrayList( templateId, templateFileCode, recoredId, version ) );
        if ( text == null ) return null;
        return text.getContent();
    }

    public String getContent() {
        return getStr( "C_CONTENT" );
    }

    public void setContent( String content ) {
        if ( Objects.equals( getContent(), content ) == false ) set( "C_CONTENT", content );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public void setTemplateFileCode( String code ) {
        set( "P_TMPFILE", code );
    }

    public int getParentId() {
        return getInt( "R_SID" );
    }

    public void setParentId( int sid ) {
        set( "R_SID", sid );
    }

    public void setTenant( Tenant tenant ) {
        set( "R_TENANT", tenant.getTenantId() );
        set( "S_TENANT", tenant.getTenantName() );
        set( "P_TENANT", tenant.getTenantType().getName() );
    }
}
