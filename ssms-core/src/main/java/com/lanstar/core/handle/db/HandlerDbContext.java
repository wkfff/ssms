/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DB.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.db;

import com.lanstar.core.handle.identity.Identity;
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
        return new ARTable( getDBSession() ).table( tableName );
    }

    /**
     * 在事务上下文中执行。
     *
     * @param trans 事务上下文
     */
    public final boolean transaction( IAtom trans ) {
        DBSession session;
        session = getDBSession();
        try {
            session.beginTransaction();
            boolean result = trans.execute( this );
            if ( result ) session.commitTransaction();
            else session.rollbackTransaction();
            return result;
        } finally {
            session.endTransaction();
        }
    }

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public final int execute( SqlBuilder sqlBuilder ) {
        return getDBSession().execute( sqlBuilder.toSqlStatement() );
    }

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public final JdbcRecord first( SqlBuilder sqlBuilder ) {
        return getDBSession().first( sqlBuilder.toSqlStatement() );
    }

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public final JdbcRecordSet query( SqlBuilder sqlBuilder ) {
        return getDBSession().query( sqlBuilder.toSqlStatement() );
    }

    public final Object[] call( String spname, Object[] params ) {
        return getDBSession().callProcedure( spname, params );
    }

    public interface IAtom {
        boolean execute( HandlerDbContext dbContext );
    }

    /**
     * 获取新增记录的SID值
     */
    public int getSID() {
        return getDBSession().getSID();
    }

    public static void injection( ARTable table, Identity identity, boolean withUpdate ) {
        table.value( "R_UPDATE", identity.getId() );
        table.value( "S_UPDATE", identity.getName() );
        if ( !withUpdate ) {
            table.value( "R_CREATE", identity.getId() );
            table.value( "S_CREATE", identity.getName() );
            table.value( "T_CREATE", "@now()" );
            table.value( "R_TENANT", identity.getTenantId() );
            table.value( "S_TENANT", identity.getName() );
            table.value( "P_TENANT", identity.getTenantType().getName() );
        }
    }
}
