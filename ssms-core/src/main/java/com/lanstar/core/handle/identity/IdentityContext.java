/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityContext.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity;

import com.lanstar.db.DbContext;

import java.sql.SQLException;

public interface IdentityContext {
    /** 从上下文中获取用户标识。使用{@code Identity}表示。 */
    Identity getIdentity();

    /** 检测当前上下文中的${@code Identity}是否为给定的类型。 */
    boolean is( TenantType type );

    /** 获取用户ID。 */
    int getIdentityId();

    /** 获取用户名 */
    String getIdentityName();

    /** 获取租户ID */
    int getTenantId();

    /** 获取租户名称 */
    String getTenantName();

    /** 获取租户类型 */
    TenantType getTenantType();

    /** 获取用户上下文所对应的数据库。 */
    DbContext getDbContext() throws SQLException;

    /** 将指定值与上下文绑定 */
    void set( Object value );

    /** 根据值的类型从上下文中取出值 */
    <T> T get( Class<T> clazz );

    /** 判断当前上下文中是否有指定类型的值 */
    <T> boolean has( Class<T> clazz );
}
