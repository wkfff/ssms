/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ServiceUser.java
 * 创建时间：2015-07-29
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantType;

public class ServiceUser implements Identity {
    public static final Identity INST = new ServiceUser();

    private ServiceUser() {
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return "系统用户";
    }

    @Override
    public String getCode() {
        return "SYSTEM_USER";
    }

    @Override
    public Tenant getTenant() {
        return Tenant.SYSTEM_TENANT;
    }

    @Override
    public int getTenantId() {
        return getTenant().getTenantId();
    }

    @Override
    public String getTenantName() {
        return getTenant().getTenantName();
    }

    @Override
    public String getTenantCode() {
        return getTenant().getTenantCode();
    }

    @Override
    public TenantType getTenantType() {
        return getTenant().getTenantType();
    }

    @Override
    public String getTenantDbCode() {
        return getTenant().getTenantDbCode();
    }
}