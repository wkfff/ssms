/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantContext.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.db.impl.TenantDbContext;
import com.lanstar.core.handle.identity.Tenant;
import com.lanstar.core.handle.identity.TenantType;

public class TenantContext extends OperateContext {
    private final Tenant tenant;

    public TenantContext( Tenant tenant ) {this( tenant, new TenantDbContext( tenant ) );}

    public TenantContext( Tenant tenant, HandlerDbContext dbContext ) {
        super( dbContext );
        this.tenant = tenant;
    }

    /**
     * 获取租户ID
     */
    public int getTenantId() {return tenant.getTenantId();}

    /**
     * 获取租户名称
     */
    public String getTenantName() {return tenant.getTenantName();}

    /**
     * 获取租户类型
     */
    public TenantType getTenantType() {return tenant.getTenantType();}
}
