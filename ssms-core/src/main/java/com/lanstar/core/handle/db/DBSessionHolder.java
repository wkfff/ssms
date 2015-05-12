/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DBSessionHolder.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.db;

import com.lanstar.db.DBSession;

public abstract class DBSessionHolder implements AutoCloseable {
    private DBSession session;

    public final DBSession getDBSession() {
        if ( session == null || !session.isValid() ) {
            session = buildDbSession();
            // 打开事务
            session.beginTransaction();
        }
        return session;
    }

    protected abstract DBSession buildDbSession();

    @Override
    public void close() throws Exception {
        if (session!=null) {
            session.close();
        }
    }
}
