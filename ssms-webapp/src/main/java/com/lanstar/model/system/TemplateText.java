/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateText.java
 * 创建时间：2015-07-02
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.ModelExt;

import java.util.Objects;

public class TemplateText extends ModelExt<TemplateText> {
    public static final TemplateText dao = new TemplateText();

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

    public Integer getId() {
        return getInt( "SID" );
    }

    public void setTenant( Tenant tenant ) {
        set( "R_TENANT", tenant.getTenantId() );
        set( "S_TENANT", tenant.getTenantName() );
        set( "P_TENANT", tenant.getTenantType().getName() );
    }
}
