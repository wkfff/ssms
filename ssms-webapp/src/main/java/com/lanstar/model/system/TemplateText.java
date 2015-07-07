/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateText.java
 * 创建时间：2015-07-02
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.common.ListKit;
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.ModelExt;

public class TemplateText extends ModelExt<TemplateText> {
    public static final TemplateText dao = new TemplateText();

    public static String getContent( TemplateFile file, int recoredId ) {
        return getContent( file.getTemplateId(), file.getTemplateFileCode(), recoredId );
    }

    public static String getContent( int templateId, String templateFileCode, int recoredId ) {
        TemplateText text = dao.findFirstByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "P_TMPFILE", "R_SID" ),
                ListKit.newObjectArrayList( templateId, templateFileCode, recoredId ) );
        if ( text == null ) return null;
        return text.getContent();
    }

    public static int saveContent( TemplateFileModel<?> model, String content, IdentityContext identityContext ) {
        TemplateFile file = model.getTemplateFile();
        return saveContent(
                file.getTemplateId(),
                file.getTemplateFileCode(),
                model.getId(),
                content,
                identityContext.getTenant(),
                identityContext.getIdentity() );
    }

    public static int saveContent( int templateId, String templateFileCode, int recoredId, String content, Tenant tenant, Identity operator ) {
        TemplateText text = dao.findFirstByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "P_TMPFILE", "R_SID" ),
                ListKit.newObjectArrayList( templateId, templateFileCode, recoredId ) );
        boolean exists = text != null;
        if ( exists == false ) text = new TemplateText();

        text.setTemplateId( templateId );
        text.setTemplateFileCode( templateFileCode );
        text.setParentId( recoredId );
        text.setContent( content );
        text.setTenant( tenant );
        ModelInjector.injectOpreator( text, operator, true );

        if ( exists ) text.update();
        else text.save();

        return text.getId();
    }

    public String getContent() {
        return getStr( "C_CONTENT" );
    }

    public void setContent( String content ) {
        set( "C_CONTENT", content );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public void setTemplateFileCode( String code ) {
        set( "P_TMPFILE", code );
    }

    public int getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    public void setTemplateId( int id ) {
        set( "R_TEMPLATE", id );
    }

    public int getParentId() {
        return getInt( "R_SID" );
    }

    public void setParentId( int sid ) {
        set( "R_SID", sid );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public void setTenant( Tenant tenant ) {
        set( "R_TENANT", tenant.getTenantId() );
        set( "S_TENANT", tenant.getTenantName() );
        set( "P_TENANT", tenant.getTenantType().getName() );
    }
}
