/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：UserBase.java
 * 创建时间：2015-07-22
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.tenant;

import com.lanstar.common.kit.StrKit;
import com.lanstar.identity.Tenant;
import com.lanstar.model.TenantModel;

public abstract class UserModel<T extends UserModel<T>> extends TenantModel<T> {
    public boolean ChangePassword( String oldPassword, String newPassword ) {
        if ( getStr( "C_PASSWD" ).equalsIgnoreCase( oldPassword ) == false ) return false;
        set( "C_PASSWD", newPassword.toUpperCase() );
        // 永远让密码变为已修改，否则没办法保存数据。            by 张铮彬#2015-7-22
        modifyFlag.add( "C_PASSWD" );
        return update();
    }

    protected abstract T findUser( String tenantCode, String name, String password );

    public void initPassword() {
        // 创建企业租户的时候同时创建一个admin用户,  默认密码为123456。
        // TODO: 创建用户的时候使用随机密码
        set( "C_PASSWD", StrKit.toMD5( "123456" ) );
    }

    public int getId() {
        return getInt( "SID" );
    }

    public int getOwnerId() {
        return getInt( "R_SID" );
    }

    public String getOwnerName() {
        return get( "S_NAME" );
    }

    public abstract Tenant getOwner();

    public void setOwner( Tenant owner ) {
        set( "R_SID", owner.getTenantId() );
        set( "S_NAME", owner.getTenantName() );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }

    public String getUsername() {
        return getStr( "C_USER" );
    }

    public void setUsername( String username ) {
        set( "C_USER", username );
    }

    public void setPassword( String newPwd ) {
        set( "C_PASSWD", newPwd.toUpperCase() );
    }
}
