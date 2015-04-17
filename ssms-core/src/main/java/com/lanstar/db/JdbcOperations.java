/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdbcOperations.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import com.lanstar.db.dialect.JdbcPageRecordSet;
import com.lanstar.db.statement.SqlStatement;

import java.sql.SQLException;
import java.util.List;

public interface JdbcOperations {
    /**
     * 执行SQL语句
     *
     * @param sqlStatement SQL语句
     * @param callback     预处理回调
     * @param <T>          返回值类型
     *
     * @return 返回值
     *
     * @throws SQLException
     */
    <T> T execute( SqlStatement sqlStatement, PreparedStatementCallback<T> callback ) throws SQLException;

    /**
     * 执行SQL语句，适用于INSERT/UPDATE/DELETE。
     *
     * @param sqlStatement SQL语句
     *
     * @return 受影响的记录数
     */
    int execute( SqlStatement sqlStatement );

    /**
     * 执行SQL语句，适用于INSERT/UPDATE/DELETE。
     *
     * @param sql    SQL语句
     * @param params SQL参数
     *
     * @return 受影响的记录数
     */
    int execute( String sql, Object[] params );

    /**
     * 以查询方式执行SQL语句
     *
     * @param sqlStatement SQL语句
     * @param extractor    结果集提取器
     * @param maxRows      结果集最大数据行
     * @param <T>          结果集类型
     *
     * @return 结果集
     */
    <T> T query( SqlStatement sqlStatement, ResultSetExtractor<T> extractor, int maxRows );

    /**
     * 以查询方式执行SQL语句
     *
     * @param sqlStatement SQL语句
     * @param extractor    结果集提取器
     * @param <T>          结果集类型
     *
     * @return 结果集
     */
    <T> T query( SqlStatement sqlStatement, ResultSetExtractor<T> extractor );

    /**
     * 以查询方式执行SQL语句，返回结果列表
     *
     * @param sqlStatement SQL语句
     * @param rowMapper    行映射处理器
     * @param <T>          结果集类型
     *
     * @return 结果集
     */
    <T> List<T> query( SqlStatement sqlStatement, RowMapper<T> rowMapper );

    /**
     * 以查询方式执行SQL语句，返回结果列表
     *
     * @param sqlStatement SQL语句
     *
     * @return 结果集
     */
    JdbcRecordSet query( SqlStatement sqlStatement );

    /**
     * 以查询方式执行SQL语句，返回结果集
     *
     * @param sql    SQL语句
     * @param params SQL参数
     *
     * @return 结果集
     */
    JdbcRecordSet query( String sql, Object[] params );

    JdbcPageRecordSet query(SqlStatement sqlStatement, DBPaging paging);

    /**
     * 以查询方式执行SQL语句，返回结果列表
     *
     * @param sqlStatement SQL语句
     * @param rowAction    行处理器
     */
    void query( SqlStatement sqlStatement, IRowAction rowAction );

    /**
     * 执行查询语句，返回结果集第一行数据。
     *
     * @param sqlStatement SQL语句
     * @param extractor    结果集提取器
     * @param <T>          结果集类型
     *
     * @return 结果集
     */
    <T> T first( SqlStatement sqlStatement, ResultSetExtractor<T> extractor );

    /**
     * 执行查询语句，返回结果集第一行数据。
     *
     * @param sqlStatement SQL语句
     * @param rowMapper    行映射处理器
     * @param <T>          结果集类型
     *
     * @return 结果集
     */
    <T> T first( SqlStatement sqlStatement, RowMapper<T> rowMapper );

    /**
     * 执行查询语句，返回结果集第一行数据。
     *
     * @param sqlStatement SQL语句
     *
     * @return 结果集
     */
    JdbcRecord first( SqlStatement sqlStatement );

    /**
     * 执行查询语句，返回结果集第一行数据。
     *
     * @param sql    SQL语句
     * @param params SQL参数
     *
     * @return 结果集
     */
    JdbcRecord first( String sql, Object[] params );

    int executeBatch( List<String> sqls );

    /**
     * 获取结果集记录数
     *
     * @param sql    SQL语句
     * @param params SQL参数
     *
     * @return 记录数
     */
    int getRecordsetSize( String sql, Object[] params );

    /**
     * 获取新增记录的SID值
     * @return
     */
    int getSID();
}
