/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantDbContext.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.db.impl;

import com.lanstar.core.RequestContext;
import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.identity.Tenant;
import com.lanstar.db.DBSession;
import com.lanstar.db.DbException;

import java.sql.SQLException;

public class TenantDbContext extends HandlerDbContext {
    private Tenant tenant;
    private RequestContext context;

    public TenantDbContext( RequestContext context ) {
        this.context = context;
    }

    public TenantDbContext( Tenant tenant ) {
        this.tenant = tenant;
    }

    @Override
    protected DBSession buildDbSession() {
        if ( tenant == null && !context.hasIdentityContext() ) {
            throw new DbException( "身份认证无法通过，无法创建租户数据库会话！" );
        }

        try {
            if ( tenant == null ) tenant = context.getIdentityContxt().getIdentity();
            return tenant.getDbContext().createDbSession();
        } catch ( SQLException e ) {
            throw new DbException( "无法创建数据库会话", e );
        }
    }
}
