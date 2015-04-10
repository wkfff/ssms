/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DB.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.db;

import com.lanstar.db.DBSession;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.statement.SqlBuilder;

/**
 * 数据库操作上下文
 */
public abstract class HandlerDbContext extends DBSessionHolder {
    /**
     * 根据表名返回一个{@link ARTable}对象，通过该对象可以快速的对表进行操作。
     *
     * @param tableName 表名
     *
     * @return {@link ARTable}对象
     *
     * @see ARTable
     */
    public final ARTable withTable( String tableName ) {
        return new ARTable( getDbSession() ).table( tableName );
    }

    /**
     * 在事务上下文中执行。
     *
     * @param trans 事务上下文
     */
    public final void transaction( TransactionContext trans ) {
        DBSession session = getDbSession();
        try {
            session.beginTransaction();
            trans.execute( this );
        } finally {
            session.endTransaction();
        }
    }

    public final int execute( SqlBuilder sqlBuilder ) {
        return getDbSession().execute( sqlBuilder.toSqlStatement() );
    }

    public final JdbcRecord first( SqlBuilder sqlBuilder ) {
        return getDbSession().first( sqlBuilder.toSqlStatement() );
    }

    public final JdbcRecordSet query( SqlBuilder sqlBuilder ) {
        return getDbSession().query( sqlBuilder.toSqlStatement() );
    }

    public interface TransactionContext {
        void execute( HandlerDbContext dbContext );
    }
}
