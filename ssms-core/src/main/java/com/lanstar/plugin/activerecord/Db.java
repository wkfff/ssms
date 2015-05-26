/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Db.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Db {

    private static DbPro dbPro = null;

    static void init() {
        dbPro = DbPro.use();
    }

    public static DbPro use( String configName ) {
        return DbPro.use( configName );
    }

    static <T> List<T> query( Config config, Connection conn, String sql, Object... paras ) throws SQLException {
        return dbPro.query( config, conn, sql, paras );
    }

    public static <T> List<T> query( String sql, Object... paras ) {
        return dbPro.query( sql, paras );
    }

    /**
     * @param sql an SQL statement
     *
     * @see #query(String, Object...)
     */
    public static <T> List<T> query( String sql ) {
        return dbPro.query( sql );
    }

    /**
     * Execute sql query and return the first result. I recommend add "limit 1" in your sql.
     *
     * @param sql   an SQL statement that may contain one or more '?' IN parameter placeholders
     * @param paras the parameters of sql
     *
     * @return Object[] if your sql has select more than one column,
     * and it return Object if your sql has select only one column.
     */
    public static <T> T queryFirst( String sql, Object... paras ) {
        return dbPro.queryFirst( sql, paras );
    }

    /**
     * @param sql an SQL statement
     *
     * @see #queryFirst(String, Object...)
     */
    public static <T> T queryFirst( String sql ) {
        return dbPro.queryFirst( sql );
    }

    // 26 queryXxx method below -----------------------------------------------

    /**
     * Execute sql query just return one column.
     *
     * @param <T>   the type of the column that in your sql's select statement
     * @param sql   an SQL statement that may contain one or more '?' IN parameter placeholders
     * @param paras the parameters of sql
     *
     * @return List<T>
     */
    public static <T> T queryColumn( String sql, Object... paras ) {
        return dbPro.queryColumn( sql, paras );
    }

    public static <T> T queryColumn( String sql ) {
        return dbPro.queryColumn( sql );
    }

    public static String queryStr( String sql, Object... paras ) {
        return dbPro.queryStr( sql, paras );
    }

    public static String queryStr( String sql ) {
        return dbPro.queryStr( sql );
    }

    public static Integer queryInt( String sql, Object... paras ) {
        return dbPro.queryInt( sql, paras );
    }

    public static Integer queryInt( String sql ) {
        return dbPro.queryInt( sql );
    }

    public static Long queryLong( String sql, Object... paras ) {
        return dbPro.queryLong( sql, paras );
    }

    public static Long queryLong( String sql ) {
        return dbPro.queryLong( sql );
    }

    public static Double queryDouble( String sql, Object... paras ) {
        return dbPro.queryDouble( sql, paras );
    }

    public static Double queryDouble( String sql ) {
        return dbPro.queryDouble( sql );
    }

    public static Float queryFloat( String sql, Object... paras ) {
        return dbPro.queryFloat( sql, paras );
    }

    public static Float queryFloat( String sql ) {
        return dbPro.queryFloat( sql );
    }

    public static java.math.BigDecimal queryBigDecimal( String sql, Object... paras ) {
        return dbPro.queryBigDecimal( sql, paras );
    }

    public static java.math.BigDecimal queryBigDecimal( String sql ) {
        return dbPro.queryBigDecimal( sql );
    }

    public static byte[] queryBytes( String sql, Object... paras ) {
        return dbPro.queryBytes( sql, paras );
    }

    public static byte[] queryBytes( String sql ) {
        return dbPro.queryBytes( sql );
    }

    public static java.util.Date queryDate( String sql, Object... paras ) {
        return dbPro.queryDate( sql, paras );
    }

    public static java.util.Date queryDate( String sql ) {
        return dbPro.queryDate( sql );
    }

    public static java.sql.Time queryTime( String sql, Object... paras ) {
        return dbPro.queryTime( sql, paras );
    }

    public static java.sql.Time queryTime( String sql ) {
        return dbPro.queryTime( sql );
    }

    public static java.sql.Timestamp queryTimestamp( String sql, Object... paras ) {
        return dbPro.queryTimestamp( sql, paras );
    }

    public static java.sql.Timestamp queryTimestamp( String sql ) {
        return dbPro.queryTimestamp( sql );
    }

    public static Boolean queryBoolean( String sql, Object... paras ) {
        return dbPro.queryBoolean( sql, paras );
    }

    public static Boolean queryBoolean( String sql ) {
        return dbPro.queryBoolean( sql );
    }

    public static Number queryNumber( String sql, Object... paras ) {
        return dbPro.queryNumber( sql, paras );
    }

    public static Number queryNumber( String sql ) {
        return dbPro.queryNumber( sql );
    }
    // 26 queryXxx method under -----------------------------------------------

    /**
     * Execute sql update
     */
    static int update( Config config, Connection conn, String sql, Object... paras ) throws SQLException {
        return dbPro.update( config, conn, sql, paras );
    }

    /**
     * Execute update, insert or delete sql statement.
     *
     * @param sql   an SQL statement that may contain one or more '?' IN parameter placeholders
     * @param paras the parameters of sql
     *
     * @return either the row count for <code>INSERT</code>, <code>UPDATE</code>,
     * or <code>DELETE</code> statements, or 0 for SQL statements
     * that return nothing
     */
    public static int update( String sql, Object... paras ) {
        return dbPro.update( sql, paras );
    }

    /**
     * @param sql an SQL statement
     *
     * @see #update(String, Object...)
     */
    public static int update( String sql ) {
        return dbPro.update( sql );
    }

    static List<Record> find( Config config, Connection conn, String sql, Object... paras ) throws SQLException {
        return dbPro.find( config, conn, sql, paras );
    }

    public static List<Record> find( String sql, Object... paras ) {
        return dbPro.find( sql, paras );
    }

    public static List<Record> find( String sql ) {
        return dbPro.find( sql );
    }

    /**
     * Find first record. I recommend add "limit 1" in your sql.
     *
     * @param sql   an SQL statement that may contain one or more '?' IN parameter placeholders
     * @param paras the parameters of sql
     *
     * @return the Record object
     */
    public static Record findFirst( String sql, Object... paras ) {
        return dbPro.findFirst( sql, paras );
    }

    /**
     * @param sql an SQL statement
     *
     * @see #findFirst(String, Object...)
     */
    public static Record findFirst( String sql ) {
        return dbPro.findFirst( sql );
    }

    /**
     * Find record by id.
     * Example: Record user = Db.findById("user", 15);
     *
     * @param tableName the table name of the table
     * @param idValue   the id value of the record
     */
    public static Record findById( String tableName, Object idValue ) {
        return dbPro.findById( tableName, idValue );
    }

    /**
     * Find record by id. Fetch the specific columns only.
     * Example: Record user = Db.findById("user", 15, "name, age");
     *
     * @param tableName the table name of the table
     * @param idValue   the id value of the record
     * @param columns   the specific columns separate with comma character ==> ","
     */
    public static Record findById( String tableName, Number idValue, String columns ) {
        return dbPro.findById( tableName, idValue, columns );
    }

    /**
     * Find record by id.
     * Example: Record user = Db.findById("user", "user_id", 15);
     *
     * @param tableName  the table name of the table
     * @param primaryKey the primary key of the table
     * @param idValue    the id value of the record
     */
    public static Record findById( String tableName, String primaryKey, Number idValue ) {
        return dbPro.findById( tableName, primaryKey, idValue );
    }

    /**
     * Find record by id. Fetch the specific columns only.
     * Example: Record user = Db.findById("user", "user_id", 15, "name, age");
     *
     * @param tableName  the table name of the table
     * @param primaryKey the primary key of the table
     * @param idValue    the id value of the record
     * @param columns    the specific columns separate with comma character ==> ","
     */
    public static Record findById( String tableName, String primaryKey, Object idValue, String columns ) {
        return dbPro.findById( tableName, primaryKey, idValue, columns );
    }

    /**
     * Delete record by id.
     * Example: boolean succeed = Db.deleteById("user", 15);
     *
     * @param tableName the table name of the table
     * @param id        the id value of the record
     *
     * @return true if delete succeed otherwise false
     */
    public static boolean deleteById( String tableName, Object id ) {
        return dbPro.deleteById( tableName, id );
    }

    /**
     * Delete record by id.
     * Example: boolean succeed = Db.deleteById("user", "user_id", 15);
     *
     * @param tableName  the table name of the table
     * @param primaryKey the primary key of the table
     * @param id         the id value of the record
     *
     * @return true if delete succeed otherwise false
     */
    public static boolean deleteById( String tableName, String primaryKey, Object id ) {
        return dbPro.deleteById( tableName, primaryKey, id );
    }

    /**
     * Delete record.
     * Example: boolean succeed = Db.delete("user", "id", user);
     *
     * @param tableName  the table name of the table
     * @param primaryKey the primary key of the table
     * @param record     the record
     *
     * @return true if delete succeed otherwise false
     */
    public static boolean delete( String tableName, String primaryKey, Record record ) {
        return dbPro.delete( tableName, primaryKey, record );
    }

    /**
     * Example: boolean succeed = Db.delete("user", user);
     *
     * @see #delete(String, String, Record)
     */
    public static boolean delete( String tableName, Record record ) {
        return dbPro.delete( tableName, record );
    }

    static Page<Record> paginate( Config config, Connection conn, int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) throws SQLException {
        return dbPro.paginate( config, conn, pageNumber, pageSize, select, sqlExceptSelect, paras );
    }

    public static Page<Record> paginate( int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) {
        return dbPro.paginate( pageNumber, pageSize, select, sqlExceptSelect, paras );
    }

    public static Page<Record> paginate( int pageNumber, int pageSize, String select, String sqlExceptSelect ) {
        return dbPro.paginate( pageNumber, pageSize, select, sqlExceptSelect );
    }

    static boolean save( Config config, Connection conn, String tableName, String primaryKey, Record record ) throws SQLException {
        return dbPro.save( config, conn, tableName, primaryKey, record );
    }

    /**
     * Save record.
     *
     * @param tableName  the table name of the table
     * @param primaryKey the primary key of the table
     * @param record     the record will be saved
     */
    public static boolean save( String tableName, String primaryKey, Record record ) {
        return dbPro.save( tableName, primaryKey, record );
    }

    /**
     * @see #save(String, String, Record)
     */
    public static boolean save( String tableName, Record record ) {
        return dbPro.save( tableName, record );
    }

    static boolean update( Config config, Connection conn, String tableName, String primaryKey, Record record ) throws SQLException {
        return dbPro.update( config, conn, tableName, primaryKey, record );
    }

    /**
     * Update Record.
     *
     * @param tableName  the table name of the Record save to
     * @param primaryKey the primary key of the table
     * @param record     the Record object
     */
    public static boolean update( String tableName, String primaryKey, Record record ) {
        return dbPro.update( tableName, primaryKey, record );
    }

    /**
     * Update Record. The primary key of the table is: "id".
     *
     * @see #update(String, String, Record)
     */
    public static boolean update( String tableName, Record record ) {
        return dbPro.update( tableName, record );
    }

    public static Object execute( ICallback callback ) {
        return dbPro.execute( callback );
    }

    /**
     * Execute callback. It is useful when all the API can not satisfy your requirement.
     *
     * @param config   the Config object
     * @param callback the ICallback interface
     */
    static Object execute( Config config, ICallback callback ) {
        return dbPro.execute( config, callback );
    }

    /**
     * Execute transaction.
     *
     * @param config           the Config object
     * @param transactionLevel the transaction level
     * @param atom             the atom operation
     *
     * @return true if transaction executing succeed otherwise false
     */
    static boolean tx( Config config, int transactionLevel, IAtom atom ) {
        return dbPro.tx( config, transactionLevel, atom );
    }

    public static boolean tx( int transactionLevel, IAtom atom ) {
        return dbPro.tx( transactionLevel, atom );
    }

    /**
     * Execute transaction with default transaction level.
     */
    public static boolean tx( IAtom atom ) {
        return dbPro.tx( atom );
    }

    public static int[] batch( String sql, Object[][] paras, int batchSize ) {
        return dbPro.batch( sql, paras, batchSize );
    }

    public static int[] batch( String sql, String columns, List modelOrRecordList, int batchSize ) {
        return dbPro.batch( sql, columns, modelOrRecordList, batchSize );
    }

    public static int[] batch( List<String> sqlList, int batchSize ) {
        return dbPro.batch( sqlList, batchSize );
    }

    public static Object[] callProcedure( String spName, Object... params ) {return dbPro.callProcedure( spName, params );}
}