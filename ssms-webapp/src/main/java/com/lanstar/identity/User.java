/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：User.java
 * 创建时间：2015-07-22
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.model.system.tenant.UserModel;

public class User implements Identity {
    private final UserModel<?> user;
    private Tenant tenant;

    public User( UserModel<?> user ) {
        this.user = user;
    }

    @Override
    public int getId() {
        return user.getId();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public String getCode() {
        return user.getUsername();
    }

    @Override
    public Tenant getTenant() {
        if ( tenant == null ) {
            tenant = user.getOwner();
        }
        return tenant;
    }

    @Override
    public int getTenantId() {
        return tenant.getTenantId();
    }

    @Override
    public String getTenantName() {
        return tenant.getTenantName();
    }

    @Override
    public String getTenantCode() {
        return tenant.getTenantCode();
    }

    @Override
    public TenantType getTenantType() {
        return tenant.getTenantType();
    }

    public UserModel<?> getUser() {
        return user;
    }
}
