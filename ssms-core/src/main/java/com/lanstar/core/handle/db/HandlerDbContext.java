/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DB.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.db;

import com.lanstar.db.DBSession;
import com.lanstar.db.DbException;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.statement.SqlBuilder;

import java.sql.SQLException;

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
        try {
            return new ARTable( getDBSession() ).table( tableName );
        } catch ( SQLException e ) {
            throw new DbException( e );
        }
    }

    /**
     * 在事务上下文中执行。
     *
     * @param trans 事务上下文
     */
    public final void transaction( TransactionContext trans ) {
        DBSession session;
        try {
            session = getDBSession();
        } catch ( SQLException e ) {
            throw new DbException( e );
        }
        try {
            session.beginTransaction();
            trans.execute( this );
        } finally {
            session.endTransaction();
        }
    }

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public final int execute( SqlBuilder sqlBuilder ) {
        try {
            return getDBSession().execute( sqlBuilder.toSqlStatement() );
        } catch ( SQLException e ) {
            throw new DbException( e );
        }
    }

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public final JdbcRecord first( SqlBuilder sqlBuilder ) {
        try {
            return getDBSession().first( sqlBuilder.toSqlStatement() );
        } catch ( SQLException e ) {
            throw new DbException( e );
        }
    }

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public final JdbcRecordSet query( SqlBuilder sqlBuilder ) {
        try {
            return getDBSession().query( sqlBuilder.toSqlStatement() );
        } catch ( SQLException e ) {
            throw new DbException( e );
        }
    }

    public interface TransactionContext {
        void execute( HandlerDbContext dbContext );
    }
    
    /**
     * 获取新增记录的SID值
     */
    public int getSID() {
        try {
            return getDBSession().getSID();
        } catch ( SQLException e ) {
            throw new DbException( e );
        }
    }
}
