/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：OperateContext.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.statement.SqlBuilder;

public abstract class OperateContext implements AutoCloseable {
    protected final HandlerDbContext dbContext;

    public OperateContext( HandlerDbContext dbContext ) {this.dbContext = dbContext;}

    public HandlerDbContext getDbContext() {
        return dbContext;
    }

    /**
     * 根据表名返回一个{@link ARTable}对象，通过该对象可以快速的对表进行操作。
     *
     * @param tableName 表名
     *
     * @return {@link ARTable}对象
     *
     * @see ARTable
     */
    public ARTable withTable( String tableName ) {return dbContext.withTable( tableName );}

    /**
     * 在事务上下文中执行。
     *
     * @param trans 事务上下文
     */
    public boolean transaction( HandlerDbContext.IAtom trans ) { return dbContext.transaction( trans );}

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public int execute( SqlBuilder sqlBuilder ) {return dbContext.execute( sqlBuilder );}

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public JdbcRecord first( SqlBuilder sqlBuilder ) {return dbContext.first( sqlBuilder );}

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     */
    public JdbcRecordSet query( SqlBuilder sqlBuilder ) {return dbContext.query( sqlBuilder );}

    public Object[] call( String spname, Object[] params ) {return dbContext.call( spname, params );}

    public static void injection( ARTable table, Identity identity, boolean withUpdate ) {
        HandlerDbContext.injection( table, identity, withUpdate );
    }

    @Override
    public void close() throws Exception {
        dbContext.close();
    }
}
