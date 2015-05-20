/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：C3p0Plugin.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.c3p0;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.IPlugin;
import com.lanstar.plugin.activerecord.IDataSourceProvider;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class C3p0Plugin implements IPlugin, IDataSourceProvider {

    private String jdbcUrl;
    private String user;
    private String password;
    private String driverClass = "com.mysql.jdbc.Driver";
    private int maxPoolSize = 100;
    private int minPoolSize = 10;
    private int initialPoolSize = 10;
    private int maxIdleTime = 20;
    private int acquireIncrement = 2;

    private ComboPooledDataSource dataSource;

    public C3p0Plugin setDriverClass( String driverClass ) {
        if ( StrKit.isBlank( driverClass ) )
            throw new IllegalArgumentException( "driverClass can not be blank." );
        this.driverClass = driverClass;
        return this;
    }

    public C3p0Plugin setMaxPoolSize( int maxPoolSize ) {
        if ( maxPoolSize < 1 )
            throw new IllegalArgumentException( "maxPoolSize must more than 0." );
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public C3p0Plugin setMinPoolSize( int minPoolSize ) {
        if ( minPoolSize < 1 )
            throw new IllegalArgumentException( "minPoolSize must more than 0." );
        this.minPoolSize = minPoolSize;
        return this;
    }

    public C3p0Plugin setInitialPoolSize( int initialPoolSize ) {
        if ( initialPoolSize < 1 )
            throw new IllegalArgumentException( "initialPoolSize must more than 0." );
        this.initialPoolSize = initialPoolSize;
        return this;
    }

    public C3p0Plugin setMaxIdleTime( int maxIdleTime ) {
        if ( maxIdleTime < 1 )
            throw new IllegalArgumentException( "maxIdleTime must more than 0." );
        this.maxIdleTime = maxIdleTime;
        return this;
    }

    public C3p0Plugin setAcquireIncrement( int acquireIncrement ) {
        if ( acquireIncrement < 1 )
            throw new IllegalArgumentException( "acquireIncrement must more than 0." );
        this.acquireIncrement = acquireIncrement;
        return this;
    }

    public C3p0Plugin( String jdbcUrl, String user, String password ) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
    }

    public C3p0Plugin( String jdbcUrl, String user, String password, String driverClass ) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
        this.driverClass = driverClass != null ? driverClass : this.driverClass;
    }

    public C3p0Plugin( String jdbcUrl, String user, String password, String driverClass, Integer maxPoolSize, Integer minPoolSize, Integer initialPoolSize, Integer maxIdleTime, Integer acquireIncrement ) {
        initC3p0Properties( jdbcUrl, user, password, driverClass, maxPoolSize, minPoolSize, initialPoolSize, maxIdleTime, acquireIncrement );
    }

    private void initC3p0Properties( String jdbcUrl, String user, String password, String driverClass, Integer maxPoolSize, Integer minPoolSize, Integer initialPoolSize, Integer maxIdleTime, Integer acquireIncrement ) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
        this.driverClass = driverClass != null ? driverClass : this.driverClass;
        this.maxPoolSize = maxPoolSize != null ? maxPoolSize : this.maxPoolSize;
        this.minPoolSize = minPoolSize != null ? minPoolSize : this.minPoolSize;
        this.initialPoolSize = initialPoolSize != null ? initialPoolSize : this.initialPoolSize;
        this.maxIdleTime = maxIdleTime != null ? maxIdleTime : this.maxIdleTime;
        this.acquireIncrement = acquireIncrement != null ? acquireIncrement : this.acquireIncrement;
    }

    public C3p0Plugin( File propertyfile ) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream( propertyfile );
            Properties ps = new Properties();
            ps.load( fis );

            initC3p0Properties( ps.getProperty( "jdbcUrl" ), ps.getProperty( "user" ), ps.getProperty( "password" ), ps.getProperty( "driverClass" ),
                    toInt( ps.getProperty( "maxPoolSize" ) ), toInt( ps.getProperty( "minPoolSize" ) ), toInt( ps.getProperty( "initialPoolSize" ) ),
                    toInt( ps.getProperty( "maxIdleTime" ) ), toInt( ps.getProperty( "acquireIncrement" ) ) );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            if ( fis != null )
                try {fis.close();} catch ( IOException e ) {e.printStackTrace();}
        }
    }

    public C3p0Plugin( Properties properties ) {
        Properties ps = properties;
        initC3p0Properties( ps.getProperty( "jdbcUrl" ), ps.getProperty( "user" ), ps.getProperty( "password" ), ps.getProperty( "driverClass" ),
                toInt( ps.getProperty( "maxPoolSize" ) ), toInt( ps.getProperty( "minPoolSize" ) ), toInt( ps.getProperty( "initialPoolSize" ) ),
                toInt( ps.getProperty( "maxIdleTime" ) ), toInt( ps.getProperty( "acquireIncrement" ) ) );
    }

    public boolean start() {
        dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl( jdbcUrl );
        dataSource.setUser( user );
        dataSource.setPassword( password );
        try {dataSource.setDriverClass( driverClass );} catch ( PropertyVetoException e ) {
            dataSource = null; System.err.println( "C3p0Plugin start error" ); throw new RuntimeException( e );
        }
        dataSource.setMaxPoolSize( maxPoolSize );
        dataSource.setMinPoolSize( minPoolSize );
        dataSource.setInitialPoolSize( initialPoolSize );
        dataSource.setMaxIdleTime( maxIdleTime );
        dataSource.setAcquireIncrement( acquireIncrement );

        return true;
    }

    private Integer toInt( String str ) {
        return Integer.parseInt( str );
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public boolean stop() {
        if ( dataSource != null )
            dataSource.close();
        return true;
    }
}