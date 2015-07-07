/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateText.java
 * 创建时间：2015-07-02
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.common.ListKit;
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.model.TenantModel;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateText extends TenantModel<TemplateText> {
    public static final TemplateText dao = new TemplateText();

    public static String getContent( UniqueTag uniqueTag, String templateFileCode, int recoredId ) {
        return getContent( uniqueTag.getTemplateId(), uniqueTag.getTenant(), uniqueTag.getProfessionId(), templateFileCode, recoredId );
    }
    
    public static String getContent( int templateId, Tenant tenant, int professionId, String templateFileCode, int recoredId ) {
        TemplateText text = dao.findFirstByColumns(
                ListKit.newArrayList( "R_TEMPLATE", "P_PROFESSION", "R_TENANT", "P_TENANT", "P_TMPFILE", "R_SID" ),
                ListKit.newObjectArrayList(
                        templateId,
                        professionId,
                        tenant.getTenantId(),
                        tenant.getTenantType().getName(),
                        templateFileCode,
                        recoredId ) );
        if ( text == null ) return null;
        return text.getContent();
    }

    public static int saveContent( UniqueTag uniqueTag, String templateFileCode, int recoredId, String content, Identity operator ) {
        return saveContent( uniqueTag.getTemplateId(), uniqueTag.getTenant(), uniqueTag.getProfessionId(), templateFileCode, recoredId, content, operator );
    }
    
    public static int saveContent( int templateId, Tenant tenant, int professionId, String templateFileCode, int recoredId, String content, Identity operator ) {
        TemplateText text = dao.findFirstByColumns(
            ListKit.newArrayList( "R_TEMPLATE", "P_PROFESSION", "R_TENANT", "P_TENANT", "P_TMPFILE", "R_SID" ),
            ListKit.newObjectArrayList(
                templateId,
                professionId,
                tenant.getTenantId(),
                tenant.getTenantType().getName(),
                templateFileCode,
                recoredId ) );
        boolean exists = text != null;
        if ( exists == false ) text = new TemplateText();

        text.setTemplateId( templateId );
        text.setProfessionId( professionId );
        text.setTenant( tenant );
        text.setTemplateFileCode( templateFileCode );
        text.setParentId( recoredId );
        text.setContent( content );

        ModelInjector.injectOpreator( text, operator, true );

        if ( exists ) text.update();
        else text.save();

        return text.getId();
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public int getParentId() {
        return getInt( "R_SID" );
    }

    public void setParentId( int id ) {
        set( "R_SID", id );
    }

    /**
     * 获取模板文件所属专业的ID
     */
    public int getProfessionId() {
        return getInt( "P_PROFESSION" );
    }

    /**
     * 设置模板文件所属专业的ID
     */
    public void setProfessionId( int professionId ) {
        set( "P_PROFESSION", professionId );
    }

    /**
     * 获取所属模板的ID
     */
    public int getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    /**
     * 设置所属模板的ID
     */
    public void setTemplateId( int templateId ) {
        set( "R_TEMPLATE", templateId );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public void setTemplateFileCode( String code ) {
        set( "P_TMPFILE", code );
    }

    public String getContent() {
        return getStr( "C_CONTENT" );
    }

    public void setContent( String content ) {
        set( "C_CONTENT", content );
    }
}
