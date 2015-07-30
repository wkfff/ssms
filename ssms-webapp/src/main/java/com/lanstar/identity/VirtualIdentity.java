/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：VirtualIdentity.java
 * 创建时间：2015-07-24
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

public class VirtualIdentity implements Identity {
    private final Identity identity;
    private final Tenant tenant;

    VirtualIdentity( Identity identity, Tenant tenant ) {
        this.identity = identity;
        this.tenant = tenant;
    }

    @Override
    public int getId() {
        return identity.getId();
    }

    @Override
    public String getName() {
        return identity.getName();
    }

    @Override
    public String getCode() {
        return identity.getCode();
    }

    @Override
    public Tenant getTenant() {
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

    @Override
    public String getTenantDbCode() {
        return tenant.getTenantDbCode();
    }
}
