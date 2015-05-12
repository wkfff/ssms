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
import com.lanstar.core.handle.identity.TenantType;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.statement.SqlBuilder;

public class OperateContext implements AutoCloseable {
    private final Identity identity;
    private final HandlerDbContext dbContext;

    /**
     * @param identity 身份标识
     */
    public OperateContext( Identity identity, HandlerDbContext dbContext ) {
        this.identity = identity;
        this.dbContext = dbContext;
    }

    public Identity getIdentity() {
        return identity;
    }

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
    public void transaction( HandlerDbContext.TransactionContext trans ) {dbContext.transaction( trans );}

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     * @param sqlBuilder
     */
    public int execute( SqlBuilder sqlBuilder ) {return dbContext.execute( sqlBuilder );}

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     * @param sqlBuilder
     */
    public JdbcRecord first( SqlBuilder sqlBuilder ) {return dbContext.first( sqlBuilder );}

    /**
     * SqlBuilder具体使用参看https://github.com/maxtoroq/DbExtensions/blob/master/docs/SqlBuilder.md
     * @param sqlBuilder
     */
    public JdbcRecordSet query( SqlBuilder sqlBuilder ) {return dbContext.query( sqlBuilder );}

    public Object[] call( String spname, Object[] params ) {return dbContext.call( spname, params );}

    /**
     * 获取用户ID
     */
    public int getId() {return identity.getId();}

    /**
     * 获取租户ID
     */
    public int getTenantId() {return identity.getTenantId();}

    /**
     * 获取租户类型
     */
    public TenantType getTenantType() {return identity.getTenantType();}

    /**
     * 获取租户名称
     */
    public String getTenantName() {return identity.getTenantName();}

    /**
     * 获取用户名
     */
    public String getName() {return identity.getName();}

    @Override
    public void close() throws Exception {
        dbContext.close();
    }
}
