/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantUser.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.identity.Identity;
import com.lanstar.identity.TenantType;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class TenantUser extends Model<TenantUser> implements Identity {
    public static final TenantUser dao = new TenantUser();

    public static TenantUser getUser( String tenentCode, String username, String password ) {
        return TenantUser.dao.findFirst( SqlKit.sql( "system.tenantUser.login" ),
                tenentCode.toUpperCase(), username.toUpperCase(), password.toUpperCase() );
    }

    @Override
    public int getId() {
        return getInt( "USER_ID" );
    }

    @Override
    public String getName() {
        return getStr( "USER_NAME" );
    }

    @Override
    public String getCode() {
        return getStr( "USER_CODE" );
    }

    @Override
    public int getTenantId() {
        return getLong( "TENANT_ID" ).intValue();
    }

    @Override
    public String getTenantName() {
        return getStr( "TENANT_NAME" );
    }

    @Override
    public String getTenantCode() {
        return getStr( "TENANT_CODE" );
    }

    @Override
    public TenantType getTenantType() {
        return TenantType.getValue( getStr( "TENANT_TYPE" ) );
    }
}
