/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IQueryDialect.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db.dialect;

import com.lanstar.db.*;
import com.lanstar.db.statement.SqlStatement;

import java.sql.SQLException;

public interface IQueryDialect {
    JdbcRecord query( JdbcOperations operations, SqlStatement sqlStatement ) throws SQLException;

    JdbcRecordSet queryList( JdbcOperations operations, SqlStatement sqlStatement ) throws SQLException;

    void queryList( JdbcOperations operations, SqlStatement sqlStatement, IRowAction row ) throws SQLException;

    /**
     * 分页查询
     */
    JdbcPageRecordSet queryPaging( JdbcOperations operations, SqlStatement sqlStatement, DBPaging paging ) throws SQLException;
}
