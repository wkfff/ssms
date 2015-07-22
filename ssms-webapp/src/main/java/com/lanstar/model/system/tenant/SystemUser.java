/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemUser.java
 * 创建时间：2015-07-22
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.tenant;

import com.lanstar.identity.Tenant;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class SystemUser extends UserModel<SystemUser> {
    public static final SystemUser dao = new SystemUser();

    public static SystemUser getUser( String name, String password ) {
        return dao.findUser( null, name, password );
    }

    @Override
    protected SystemUser findUser( String tenantCode, String name, String password ) {
        return dao.findFirst( SqlKit.sql( "system.systemUser.getUserByNameAndPwd" ), name.toUpperCase(), password.toUpperCase() );
    }

    @Override
    public Tenant getOwner() {
        return Tenant.SYSTEM_TENANT;
    }
}
