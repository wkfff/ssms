/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GovernmentUser.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class GovernmentUser extends Model<GovernmentUser> {
    public static final GovernmentUser dao = new GovernmentUser();

    public static GovernmentUser getUser( String sid, String pwd ) {
        return dao.findFirst( SqlKit.sql( "system.governmentUser.getUserBySidAndPwd" ), sid, pwd.toUpperCase() );
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

    public void setGovernment( Government model ) {
        set( "R_SID", model.getId() );
        set( "S_NAME", model.getName() );
    }

    public int getEnterpriseId() {
        return getInt( "R_SID" );
    }

    public String getEnterpriseName() {
        return get( "S_NAME" );
    }

    public void setPassword( String newPwd ) {
        set( "C_PASSWD", newPwd.toUpperCase() );
    }

    public void initPassword() {
        // 创建企业租户的时候同时创建一个admin用户,  默认密码为123456。
        // TODO: 创建用户的时候使用随机密码
        set( "C_PASSWD", StrKit.toMD5( "123456" ) );
    }
}
