/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MySqlDialect.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db.dialect.impl;

import com.lanstar.db.dialect.IDialect;

/**
 * MySQL方言
 */
public class MySqlDialect implements IDialect {
    /**
     * 获取JdbcDriver
     */
    @Override
    public String getJdbcDriver() {
        return "com.mysql.jdbc.Driver";
    }

    /**
     * 获取心跳检测SQL
     */
    @Override
    public String getHeartbeatSql() {
        return "SELECT 1 FROM DUAL";
    }
}
