/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DBSession.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import java.sql.Connection;

public class DBSession {
    protected final Connection conn;

    public DBSession() {
        conn = DS.getConnection();
    }

    /**
     * 开始事务处理,启动的事务会引起以前有的事务的自动提交
     *
     * @throws DbException
     */
    public void beginTransaction() throws DbException {
        JdbcHelper.beginTransaction( conn );
    }

    /**
     * 提交事务
     *
     * @throws DbException
     */
    public void commitTransaction() throws DbException {
        JdbcHelper.commit( conn );
    }

    /**
     * 当发现有失败的事务时候自动进行回滚
     *
     * @throws DbException
     */
    public void rollbackTransaction() throws DbException {
        JdbcHelper.rollback( conn );
    }
}
