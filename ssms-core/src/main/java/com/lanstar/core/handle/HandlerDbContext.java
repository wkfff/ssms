/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DB.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.db.DBSession;
import com.lanstar.db.ar.ARTable;

/**
 * 数据库操作上下文
 */
public class HandlerDbContext {
    private final DBSession session;

    public HandlerDbContext( DBSession session ) {
        this.session = session;
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
    public ARTable withTable( String tableName ) {
        return new ARTable( session ).table( tableName );
    }
}
