/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Tenant.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

/**
 * 租户信息
 */
public interface Tenant {
    Tenant SYSTEM_TENANT = new Tenant() {
        @Override
        public int getTenantId() {
            return 0;
        }

        @Override
        public String getTenantName() {
            return "系统用户";
        }

        @Override
        public String getTenantCode() {
            return "SYSTEM";
        }

        @Override
        public TenantType getTenantType() {
            return TenantType.SYSTEM;
        }

        @Override
        public String getTenantDbCode() {
            return null;
        }
    };

    /**
     * 获取租户ID
     */
    int getTenantId();

    /**
     * 获取租户名称
     */
    String getTenantName();

    /**
     * 获取租户编码
     */
    String getTenantCode();

    /**
     * 获取租户类型
     */
    TenantType getTenantType();

    /**
     * 获取租户的数据库编码
     */
    String getTenantDbCode();
}