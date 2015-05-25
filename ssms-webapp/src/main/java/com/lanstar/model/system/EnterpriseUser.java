/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseUser.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class EnterpriseUser extends Model<EnterpriseUser> {
    public static final EnterpriseUser dao = new EnterpriseUser();

    public void initPassword() {
        // 创建企业租户的时候同时创建一个admin用户,  默认密码为123456。
        // TODO: 创建用户的时候使用随机密码
        set( "C_PASSWD", StrKit.toMD5( "123456" ) );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setUsername( String username ) {
        set( "C_USER", username );
    }

    public String getUsername() {
        return getStr( "C_USER" );
    }

    public void setEnterpriseId( Integer id ) {
        set( "R_SID", id );
    }

    public int getEnterpriseId() {
        return getInt( "R_SID" );
    }

    public void setEnterpriseName( String name ) {
        set( "S_NAME", name );
    }

    public String getEnterpriseName() {
        return get( "S_NAME" );
    }

    public static EnterpriseUser getUser( String sid, String pwd ) {
        return dao.findFirst( SqlKit.sql( "system.enterpriseUser.getUserBySidAndPwd" ), sid, pwd );
    }

    public void setPassword( String newPwd ) {
        set("C_PASSWD", newPwd.toUpperCase());
    }
}

