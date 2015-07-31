/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewUser.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.model.system.tenant;

import com.lanstar.common.staticcache.TenantCache;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantType;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.plugin.staticcache.CacheManager;

public class ReviewUser extends UserModel<ReviewUser> {
    public static final ReviewUser dao = new ReviewUser();

    public static ReviewUser getUser( String tenantCode, String name, String password ) {
        return dao.findUser( tenantCode, name, password );
    }

    public static ReviewUser findUserByEmail( String email ) {
        return dao.findFirstByColumn( "C_EMAIL", email );
    }

    @Override
    protected ReviewUser findUser( String tenantCode, String name, String password ) {
        return dao.findFirst( SqlKit.sql( "system.reviewUser.getUserByNameAndPwd" ),
                tenantCode.toUpperCase(), name.toUpperCase(), password.toUpperCase() );
    }

    @Override
    public Tenant getOwner() {
        return CacheManager.me().getCache( TenantCache.class ).getValue( getOwnerId(), TenantType.REVIEW );
    }
}
