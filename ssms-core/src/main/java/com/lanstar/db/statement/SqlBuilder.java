/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Statement.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.db.statement;

public class SqlBuilder extends StatementBuilder {
    public SqlBuilder() {
        super();
    }

    public SqlBuilder _( String body ) {
        appendToCurrentClause( body );
        return this;
    }

    public SqlBuilder _( String format, Object... args ) {
        appendToCurrentClause( format, args );
        return this;
    }

    public SqlBuilder _If( boolean condition, String body ) {
        return _If( condition, body, NONE_PARA );
    }

    public SqlBuilder _If( boolean condition, int body ) {
        return _If( condition, String.valueOf( body ), NONE_PARA );
    }

    public SqlBuilder _If( boolean condition, long body ) {
        return _If( condition, String.valueOf( body ), NONE_PARA );
    }

    public SqlBuilder _If( boolean condition, String format, Object... args ) {
        return (condition) ? _( format, args ) : this;
    }

    public SqlBuilder SELECT() {
        setNextClause( "SELECT", ", " );
        return this;
    }

    public SqlBuilder SELECT( String body ) {
        return SELECT( body, NONE_PARA );
    }

    public SqlBuilder SELECT( String format, Object... args ) {
        appendClause( "SELECT", ", ", format, args );
        return this;
    }

    public SqlBuilder FROM( String body ) {
        return FROM( body, NONE_PARA );
    }

    public SqlBuilder FROM( String format, Object... args ) {
        appendClause( "FROM", ", ", format, args );
        return this;
    }

    public SqlBuilder FROM( StatementBuilder subQuery, String alias ) {
        return FROM( "({0}) " + alias, subQuery );
    }

    public SqlBuilder WITH( String body ) {
        return WITH( body, NONE_PARA );
    }

    public SqlBuilder WITH( String format, Object... args ) {
        appendClause( "WITH", null, format, args );
        return this;
    }

    public SqlBuilder WITH( StatementBuilder subQuery, String alias ) {
        return WITH( alias + " AS ({0})", subQuery );
    }

    public SqlBuilder JOIN() {
        setNextClause( "JOIN", null );
        return this;
    }

    public SqlBuilder JOIN( String body ) {
        return JOIN( body, NONE_PARA );
    }

    public SqlBuilder JOIN( String format, Object... args ) {
        appendClause( "JOIN", null, format, args );
        return this;
    }

    public SqlBuilder LEFT_JOIN( String body ) {
        return LEFT_JOIN( body, NONE_PARA );
    }

    public SqlBuilder LEFT_JOIN( String format, Object... args ) {
        appendClause( "LEFT JOIN", null, format, args );
        return this;
    }

    public SqlBuilder RIGHT_JOIN( String body ) {
        return RIGHT_JOIN( body, NONE_PARA );
    }

    public SqlBuilder RIGHT_JOIN( String format, Object... args ) {
        appendClause( "RIGHT JOIN", null, format, args );
        return this;
    }

    public SqlBuilder INNER_JOIN( String body ) {
        return INNER_JOIN( body, NONE_PARA );
    }

    public SqlBuilder INNER_JOIN( String format, Object... args ) {
        appendClause( "INNER JOIN", null, format, args );
        return this;
    }

    public SqlBuilder WHERE() {
        setNextClause( "WHERE", " AND " );
        return this;
    }

    public SqlBuilder WHERE( String body ) {
        return WHERE( body, NONE_PARA );
    }

    public SqlBuilder WHERE( String format, Object... args ) {
        appendClause( "WHERE", " AND ", format, args );
        return this;
    }

    public SqlBuilder GROUP_BY() {
        setNextClause( "GROUP BY", ", " );
        return this;
    }

    public SqlBuilder GROUP_BY( String body ) {
        return GROUP_BY( body, NONE_PARA );
    }

    public SqlBuilder GROUP_BY( String format, Object... args ) {
        appendClause( "GROUP BY", ", ", format, args );
        return this;
    }

    public SqlBuilder HAVING() {
        setNextClause( "HAVING", " AND " );
        return this;
    }

    public SqlBuilder HAVING( String body ) {
        return HAVING( body, NONE_PARA );
    }

    public SqlBuilder HAVING( String format, Object... args ) {
        appendClause( "HAVING", " AND ", format, args );
        return this;
    }

    public SqlBuilder ORDER_BY() {
        setNextClause( "ORDER BY", ", " );
        return this;
    }

    public SqlBuilder ORDER_BY( String body ) {
        return ORDER_BY( body, NONE_PARA );
    }

    public SqlBuilder ORDER_BY( String format, Object... args ) {
        appendClause( "ORDER BY", ", ", format, args );
        return this;
    }

    public SqlBuilder UNION() {
        appendClause( "UNION", null, null, NONE_PARA );
        return this;
    }

    public SqlBuilder INSERT_INTO( String body ) {
        return INSERT_INTO( body, NONE_PARA );
    }

    public SqlBuilder INSERT_INTO( String format, Object... args ) {
        appendClause( "INSERT INTO", null, format, args );
        return this;
    }

    public SqlBuilder DELETE_FROM( String body ) {
        return DELETE_FROM( body, NONE_PARA );
    }

    public SqlBuilder DELETE_FROM( String format, Object... args ) {
        appendClause( "DELETE FROM", null, format, args );
        return this;
    }

    public SqlBuilder UPDATE( String body ) {
        return UPDATE( body, NONE_PARA );
    }

    public SqlBuilder UPDATE( String format, Object... args ) {
        appendClause( "UPDATE", null, format, args );
        return this;
    }

    public SqlBuilder SET( String body ) {
        return SET( body, NONE_PARA );
    }

    public SqlBuilder SET( String format, Object... args ) {
        appendClause( "SET", ", ", format, args );
        return this;
    }

    public SqlBuilder VALUES( Object... args ) {
        appendClause( "VALUES", null, "({0})", new Object[] { args } );
        return this;
    }

    public SqlBuilder LIMIT() {
        setNextClause( "LIMIT", null );
        return this;
    }

    public SqlBuilder LIMIT( String body ) {
        return LIMIT( body, NONE_PARA );
    }

    public SqlBuilder LIMIT( String format, Object... args ) {
        appendClause( "LIMIT", null, format, args );
        return this;
    }

    public SqlBuilder LIMIT( int maxRecords ) {
        return LIMIT( "{0}", maxRecords );
    }

    public SqlBuilder OFFSET() {
        setNextClause( "OFFSET", null );
        return this;
    }

    public SqlBuilder OFFSET( String body ) {
        return OFFSET( body, NONE_PARA );
    }

    public SqlBuilder OFFSET( String format, Object... args ) {
        appendClause( "OFFSET", null, format, args );
        return this;
    }

    public SqlBuilder OFFSET( int startIndex ) {
        return OFFSET( "{0}", startIndex );
    }
}

