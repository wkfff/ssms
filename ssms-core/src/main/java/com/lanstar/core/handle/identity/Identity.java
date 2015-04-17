/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Identity.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity;

import com.lanstar.db.DbContext;

public interface Identity {
    /**
     * 获取用户ID
     */
    String getId();

    /**
     * 获取用户名
     */
    String getName();

    /**
     * 获取租户ID
     */
    int getTanendId();

    /**
     * 获取租户名称
     */
    String getTanentName();

    /**
     * 获取租户类型
     */
    String getTanentType();

    /**
     * 获取租户对应的数据库上下文
     */
    DbContext getDbContext();
}