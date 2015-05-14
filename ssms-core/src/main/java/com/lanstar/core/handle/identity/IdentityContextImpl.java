/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityContextImpl.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity;

import com.lanstar.common.helper.Asserts;
import com.lanstar.db.DbContext;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class IdentityContextImpl implements IdentityContext {
    private Identity identity;

    public IdentityContextImpl( Identity identity ) {
        Asserts.notNull( identity, "IDENTITY NOT NULL" );
        this.identity = identity;
    }

    @Override
    public Identity getIdentity() {
        return identity;
    }

    @Override
    public boolean is( TenantType type ) {
        Asserts.notNull( type, "TYPE NOT NULL" );
        return identity.getTenantType().equals( type );
    }

    @Override
    public int getIdentityId() {
        return identity.getId();
    }

    @Override
    public String getIdentityName() {
        return identity.getName();
    }

    @Override
    public int getTenantId() {return identity.getTenantId();}

    @Override
    public String getTenantName() {return identity.getTenantName();}

    @Override
    public TenantType getTenantType() {return identity.getTenantType();}

    @Override
    public DbContext getDbContext() throws SQLException {
        return identity.getDbContext();
    }

    private Map<Class<?>, Object> valueMap = new ConcurrentHashMap<>();

    /** 将指定值与上下文绑定 */
    @Override
    public void set( Object value ) {
        if ( value == null ) return;
        valueMap.put( value.getClass(), value );
    }

    /** 根据值的类型从上下文中取出值 */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get( Class<T> clazz ) {
        return (T) valueMap.get( clazz );
    }

    /**
     * 判断当前上下文中是否有指定类型的值
     */
    @Override
    public <T> boolean has( Class<T> clazz ) {
        return valueMap.containsKey( clazz );
    }
}
