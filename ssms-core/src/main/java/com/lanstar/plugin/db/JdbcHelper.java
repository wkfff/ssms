/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdbcHelper.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.db;

import com.lanstar.common.log.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/** JDBC的帮助类 */
final class JdbcHelper{
    private static Logger log = Logger.getLogger( JdbcHelper.class );

    /**
     * 安静的关闭所有可关闭对象
     *
     * @param objects 所有对象，可变参数
     */
    public static void close( AutoCloseable... objects ){
        if( objects == null || objects.length < 1 ) return;
        for( AutoCloseable object : objects ){
            if( object == null ) continue;
            if( object instanceof Connection ) {
                Connection conn = (Connection) object;
                if(!isConnectionValid(conn)) return;
                commit( conn );
            }
            try{
                object.close();
            } catch(Exception ignored){
            }
        }
    }

    /**
     * 判断链接是否能使用
     */
    public static boolean isConnectionValid( Connection conn ){
        if( conn == null ) return false;
        try{
            return !conn.isClosed();
        } catch(SQLException e){
            log.warn( "测试连接时候发现异常：" + e.getLocalizedMessage() );
            return false;
        }
    }

    /**
     * 回滚当前事务
     *
     * @param conn 数据库连接
     */
    public static void rollback( Connection conn ){
        if( isConnectionValid( conn ) ){
            try{
                if(!conn.getAutoCommit()) conn.rollback();
            } catch(SQLException e){
                log.warn( "回滚事务时候发现异常：", e.getLocalizedMessage() );
            }
        }
    }

    /**
     * 提交当前事务
     *
     * @param conn 数据库连接
     */
    public static void commit( Connection conn ){
        try{
            // 如果需要提交，则自动实现提交，出错了则回滚
            if(!conn.getAutoCommit()){
                try{
                    conn.commit();
                } catch(Throwable e){
                    conn.rollback();
                }
            }
        } catch(SQLException e){
            log.warn( "提交事务时候发现异常：", e.getLocalizedMessage() );
        }
    }
}

