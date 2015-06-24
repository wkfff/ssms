/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachText.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.Model;

import java.util.Objects;

public class AttachText extends Model<AttachText> {
    public static final AttachText dao = new AttachText();

    public String getContent() {
        return getStr( "C_CONTENT" );
    }

    public void setContent( String content ) {
        if ( Objects.equals( getContent(), content ) == false ) set( "C_CONTENT", content );
    }

    public String getTable() {
        return getStr( "R_TABLE" );
    }

    public void setTable( String table ) {
        set( "R_TABLE", table );
    }

    public String getField() {
        return getStr( "R_FIELD" );
    }

    public void setField( String field ) {
        set( "R_FIELD", field );
    }

    public int getRSid() {
        return getInt( "R_SID" );
    }

    public void setRSid( int sid ) {
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
