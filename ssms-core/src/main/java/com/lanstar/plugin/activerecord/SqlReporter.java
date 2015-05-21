/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlReporter.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import com.lanstar.common.log.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class SqlReporter implements InvocationHandler {

    private Connection conn;
    private static boolean loggerOn = false;
    private static final Logger log = Logger.getLogger( SqlReporter.class );

    SqlReporter( Connection conn ) {
        this.conn = conn;
    }

    public static void setLogger( boolean on ) {
        SqlReporter.loggerOn = on;
    }

    @SuppressWarnings("rawtypes")
    Connection getConnection() {
        Class clazz = conn.getClass();
        return (Connection) Proxy.newProxyInstance( clazz.getClassLoader(), new Class[] { Connection.class }, this );
    }

    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
        try {
            String name = method.getName();
            if ( name.equals( "prepareStatement" ) || name.equals( "prepareCall" ) ) {
                String info = "Sql: " + args[0];
                if ( loggerOn )
                    log.info( info );
                else
                    System.out.println( info );
            }
            return method.invoke( conn, args );
        } catch ( InvocationTargetException e ) {
            throw e.getTargetException();
        }
    }
}