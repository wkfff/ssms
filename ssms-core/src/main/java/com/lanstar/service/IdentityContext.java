/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：OperateContext.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.db.impl.TenantDbContext;
import com.lanstar.core.handle.identity.Identity;
import com.lanstar.core.handle.identity.TenantType;

public class IdentityContext extends OperateContext {
    private final Identity identity;

    /**
     * @param identity 身份标识
     */
    public IdentityContext( Identity identity ) {this( identity, new TenantDbContext( identity ) );}

    /**
     * @param identity 身份标识
     */
    public IdentityContext( Identity identity, HandlerDbContext dbContext ) {
        super( dbContext );
        this.identity = identity;
    }

    public Identity getIdentity() {
        return identity;
    }

    /**
     * 获取用户ID
     */
    public int getId() {return identity.getId();}

    /**
     * 获取租户ID
     */
    public int getTenantId() {return identity.getTenantId();}

    /**
     * 获取租户类型
     */
    public TenantType getTenantType() {return identity.getTenantType();}

    /**
     * 获取租户名称
     */
    public String getTenantName() {return identity.getTenantName();}

    /**
     * 获取用户名
     */
    public String getName() {return identity.getName();}
}

