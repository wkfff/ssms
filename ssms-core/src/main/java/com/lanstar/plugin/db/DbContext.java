/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DbContext.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.db;

import com.google.common.base.Strings;
import com.lanstar.common.log.LogHelper;
import com.lanstar.plugin.db.dialect.IDialect;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbContext {
    private final DataSource datasource;
    private final IDialect dialect;

    public DbContext(DataSource datasource, IDialect dialect) {
        this.datasource = datasource;
        this.dialect = dialect;
    }

    public DataSource getDataSource() {
        return datasource;
    }

    public IDialect getDialect() {
        return dialect;
    }

    public void startup() {
        while (true) {
            Exception e = test();
            if (e == null)
                break;
            LogHelper.error(this.getClass(), e, "数据源连接错误");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void shutdown() {
        if (this.datasource instanceof Closeable) {
            try {
                ((Closeable) this.datasource).close();
            } catch (Throwable ignored) {
            }
        }
    }

    /**
     * 测试该数据库源是否可连通
     */
    private Exception test() {
        String sql = dialect.getHeartbeatSql();
        if (Strings.isNullOrEmpty(sql))
            return new IllegalArgumentException("数据源没有配置HBSQL");

        Connection conn;
        try {
            conn = datasource.getConnection();
        } catch (SQLException e) {
            return e;
        }

        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            return e;
        } finally {
            JdbcHelper.close(st, conn);
        }

        return null;
    }
}
