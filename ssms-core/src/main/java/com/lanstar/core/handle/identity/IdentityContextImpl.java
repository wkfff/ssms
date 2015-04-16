/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityContextImpl.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity;

import com.lanstar.common.helper.Asserts;
import com.lanstar.db.DS;
import com.lanstar.db.DbContext;

class IdentityContextImpl implements IdentityContext {
    private Identity identity;

    public IdentityContextImpl( Identity identity ) {
        Asserts.notNull( identity, "IDENTITY NOT NULL" );
        this.identity = identity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Identity> T getIdentity( Class<T> type ) throws ClassCastException {
        if ( !is( type ) ) throw new ClassCastException();
        return (T) identity;
    }

    @Override
    public Identity getIdentity() {
        return identity;
    }

    @Override
    public <T extends Identity> boolean is( Class<T> type ) {
        Asserts.notNull( type, "TYPE NOT NULL" );
        return type.isAssignableFrom( identity.getClass() );
    }

    @Override
    public DbContext getDbContext() {
        // TODO 根据Identity来获取DbContext
        return DS.getDbContext();
    }

    @Override
    public String getIdentityId() {
        return identity.getId();
    }

    @Override
    public String getIdentityName() {
        return identity.getName();
    }
}
