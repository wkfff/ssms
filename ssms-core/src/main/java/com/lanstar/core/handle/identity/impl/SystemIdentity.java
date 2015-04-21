/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemIdentity.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity.impl;

import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.DS;
import com.lanstar.db.DbContext;

import java.sql.SQLException;

/**
 * 标识系统默认用户 TODO:系统用户数据加载
 */
public class SystemIdentity implements Identity {
    public static Identity me() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return "admin";
    }

    @Override
    public int getTanendId() {
        return 0;
    }

    /**
     * 获取租户名称
     */
    @Override
    public String getTanentName() {
        return "system";
    }

    /**
     * 获取租户类型
     */
    @Override
    public String getTanentType() {
        return "S";
    }

    @Override
    public DbContext getDbContext() throws SQLException {
        return DS.getDbContext();
    }
}
