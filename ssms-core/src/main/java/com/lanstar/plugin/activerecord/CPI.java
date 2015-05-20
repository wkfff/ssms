/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CPI.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Cross Package Invoking pattern for package activerecord.
 */
public abstract class CPI {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String, Object> getAttrs( Model model ) {
        return model.getAttrs();
    }

    public static <T> List<T> query( Connection conn, String sql, Object... paras ) throws SQLException {
        return Db.query( DbKit.config, conn, sql, paras );
    }

    public static <T> List<T> query( String configName, Connection conn, String sql, Object... paras ) throws SQLException {
        return Db.query( DbKit.getConfig( configName ), conn, sql, paras );
    }

    public static List<Record> find( Connection conn, String sql, Object... paras ) throws SQLException {
        return Db.find( DbKit.config, conn, sql, paras );
    }

    public static List<Record> find( String configName, Connection conn, String sql, Object... paras ) throws SQLException {
        return Db.find( DbKit.getConfig( configName ), conn, sql, paras );
    }

    public static Page<Record> paginate( Connection conn, int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) throws SQLException {
        return Db.paginate( DbKit.config, conn, pageNumber, pageSize, select, sqlExceptSelect, paras );
    }

    public static Page<Record> paginate( String configName, Connection conn, int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) throws SQLException {
        return Db.paginate( DbKit.getConfig( configName ), conn, pageNumber, pageSize, select, sqlExceptSelect, paras );
    }

    public static int update( Connection conn, String sql, Object... paras ) throws SQLException {
        return Db.update( DbKit.config, conn, sql, paras );
    }

    public static int update( String configName, Connection conn, String sql, Object... paras ) throws SQLException {
        return Db.update( DbKit.getConfig( configName ), conn, sql, paras );
    }
}