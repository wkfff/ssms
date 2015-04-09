/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdbcHelper.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import com.lanstar.common.log.Logger;

import java.sql.*;

/**
 * JDBC的帮助类
 */
final class JdbcHelper {
    public static final int MAX_ROW = -1;
    private static final Logger log = Logger.getLogger( JdbcHelper.class );

    /**
     * 安静的关闭所有可关闭对象
     *
     * @param objects 所有对象，可变参数
     */
    public static void close( AutoCloseable... objects ) {
        if ( objects == null || objects.length < 1 ) return;
        for ( AutoCloseable object : objects ) {
            if ( object == null ) continue;
            if ( object instanceof Connection ) {
                Connection conn = (Connection) object;
                if ( !isConnectionValid( conn ) ) return;
                try {
                    commit( conn );
                } catch ( SQLException e ) {
                    throw new DbException( "关闭连接时发生了异常", e );
                }
            }
            try {
                object.close();
            } catch ( Exception ignored ) {
            }
        }
    }

    /**
     * 判断链接是否能使用
     */
    public static boolean isConnectionValid( Connection conn ) {
        if ( conn == null ) return false;
        try {
            return !conn.isClosed();
        } catch ( SQLException e ) {
            log.warn( "测试连接时候发现异常：" + e.getLocalizedMessage() );
            return false;
        }
    }

    /**
     * 开启事务支持
     *
     * @param conn 要开启事务支持的连接
     */
    public static void beginTransaction( Connection conn ) throws SQLException {
        if ( isConnectionValid( conn ) ) {
            if ( !conn.getAutoCommit() )
                commit( conn );
            conn.setAutoCommit( false );
        }
    }

    /**
     * 关闭事务。（先提交，如果失败就回滚，然后关闭事务）
     */
    public static void endTransaction(Connection conn) throws SQLException{
        if ( isConnectionValid( conn ) ) {
            if ( !conn.getAutoCommit() )
                commit( conn );
            conn.setAutoCommit( true );
        }
    }

    /**
     * 提交当前事务
     *
     * @param conn 数据库连接
     */
    public static void commit( Connection conn ) throws SQLException {
        if ( isConnectionValid( conn ) ) {
            if ( !conn.getAutoCommit() ) {
                try {
                    conn.commit();
                } catch ( SQLException e ) {
                    conn.rollback(); // 提交失败则自动回滚
                    throw e;
                }
            }
        }
    }

    /**
     * 回滚当前事务
     *
     * @param conn 数据库连接
     */
    public static void rollback( Connection conn ) throws SQLException {
        if ( isConnectionValid( conn ) ) {
            if ( !conn.getAutoCommit() ) conn.rollback();
        }
    }

    public static String lookupColumnName( ResultSetMetaData resultSetMetaData, int columnIndex ) throws SQLException {
        String name = resultSetMetaData.getColumnLabel( columnIndex );
        if ( name == null || name.length() < 1 ) {
            name = resultSetMetaData.getColumnName( columnIndex );
        }
        return name;
    }

    // 抽取自Spring JdbcTempate
    public static Object getResultSetValue( ResultSet rs, int index ) throws SQLException {
        Object obj = rs.getObject( index );
        String className = null;
        if ( obj != null ) {
            className = obj.getClass().getName();
        }
        if ( obj instanceof Blob ) {
            Blob blob = (Blob) obj;
            obj = blob.getBytes( 1, (int) blob.length() );
        } else if ( obj instanceof Clob ) {
            Clob clob = (Clob) obj;
            obj = clob.getSubString( 1, (int) clob.length() );
        } else if ( "oracle.sql.TIMESTAMP".equals( className ) || "oracle.sql.TIMESTAMPTZ".equals( className ) ) {
            obj = rs.getTimestamp( index );
        } else if ( className != null && className.startsWith( "oracle.sql.DATE" ) ) {
            String metaDataClassName = rs.getMetaData().getColumnClassName( index );
            if ( "java.sql.Timestamp".equals( metaDataClassName ) || "oracle.sql.TIMESTAMP"
                    .equals( metaDataClassName ) ) {
                obj = rs.getTimestamp( index );
            } else {
                obj = rs.getDate( index );
            }
        } else if ( obj != null && obj instanceof java.sql.Date ) {
            if ( "java.sql.Timestamp".equals( rs.getMetaData().getColumnClassName( index ) ) ) {
                obj = rs.getTimestamp( index );
            }
        }
        return obj;
    }
}

