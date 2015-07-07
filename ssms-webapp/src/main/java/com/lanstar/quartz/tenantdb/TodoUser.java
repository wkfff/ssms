/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoUser.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.lanstar.identity.Identity;
import com.lanstar.identity.TenantType;

public class TodoUser implements Identity {
    public static final Identity INST = new TodoUser();

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return "定时任务用户";
    }

    @Override
    public String getCode() {
        return "TASK_USER";
    }

    @Override
    public int getTenantId() {
        return SYSTEM_TENANT.getTenantId();
    }

    @Override
    public String getTenantName() {
        return SYSTEM_TENANT.getTenantName();
    }

    @Override
    public String getTenantCode() {
        return SYSTEM_TENANT.getTenantCode();
    }

    @Override
    public TenantType getTenantType() {
        return SYSTEM_TENANT.getTenantType();
    }
}
