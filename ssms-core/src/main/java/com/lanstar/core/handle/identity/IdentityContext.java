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
    /**
     * 从上下文中获取用户标识
     *
     * @param type 用户标识的类型，如类型不匹配将报错。
     * @param <T>  用户标识类型
     *
     * @return 指定类型的用户标识
     */
    <T extends Identity> T getIdentity( Class<T> type );

    /**
     * 从上下文中获取用户标识。使用{@code Identity}表示。
     */
    Identity getIdentity();

    /**
     * 检测当前上下文中的${@code Identity}是否为给定的类型。
     *
     * @param type 用于检测的类型
     * @param <T>  类型
     *
     * @return 如果类型一致则返回true，否则返回false。
     */
    <T extends Identity> boolean is( Class<T> type );

    /**
     * 获取用户上下文所对应的数据库。
     *
     * @return {@code DbContext}实例
     *
     * @throws SQLException
     */
    DbContext getDbContext() throws SQLException;

    /**
     * 获取用户ID。
     */
    int getIdentityId();

    /**
     * 获取用户名
     */
    String getIdentityName();
}
