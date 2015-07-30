/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PasswordResetHistory.java
 * 创建时间：2015-07-29
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.model.TenantModel;
import com.lanstar.model.system.tenant.UserModel;

import java.util.Date;

public class PasswordResetHistory extends TenantModel<PasswordResetHistory> {
    public static final PasswordResetHistory dao = new PasswordResetHistory();

    public int getUserId() {
        return getInt( "R_USER" );
    }

    public void setUserId( int value ) {
        set( "R_USER", value );
    }

    public UserModel<?> getUser() {
        return getTenant().getTenantType().findUserById( getUserId() );
    }

    public void setUser( UserModel<?> user ) {
        set( "R_USER", user.getId() );
        set( "S_USER", user.getUsername() );
    }

    public String getEmail() {
        return getStr( "C_EMAIL" );
    }

    public void setEmail( String value ) {
        set( "C_EMAIL", value );
    }

    public String getToken() {
        return getStr( "C_TOKEN" );
    }

    public void setToken( String token ) {
        set( "C_TOKEN", token );
    }

    public Date getApplyTime() {
        return getDate( "T_APPLY" );
    }

    public void setApplyTime( Date value ) {
        set( "T_APPLY", value );
    }
}
