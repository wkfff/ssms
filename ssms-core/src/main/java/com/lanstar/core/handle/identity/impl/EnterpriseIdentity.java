/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CompanyIdentity.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity.impl;

import com.lanstar.core.handle.identity.TenantType;
import com.lanstar.db.JdbcRecord;

/**
 * 企业人员
 */
public class EnterpriseIdentity extends AbstractIdentity {
    private final JdbcRecord record;

    public EnterpriseIdentity( JdbcRecord record ) {
        this.record = record;
    }

    @Override
    public int getId() {
        return (int) record.get( "SID" );
    }

    @Override
    public String getName() {
        return record.getString( "C_NAME" );
    }

    /**
     * 获取租户类型
     */
    @Override
    public TenantType getTenantType() {
        return TenantType.ENTERPRISE;
    }
}
