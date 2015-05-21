/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MultiDataSource.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.tlds;

import com.lanstar.common.Asserts;
import com.lanstar.plugin.activerecord.ActiveRecordException;
import com.lanstar.plugin.activerecord.IDataSourceProvider;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

class ThreadLocalDataSource implements DataSource {
    private ThreadLocal<DataSource> dataSource = new ThreadLocal<>();
    private ThreadLocal<String> dsName = new ThreadLocal<>();

    private Map<String, IDataSourceProvider> pools = new ConcurrentSkipListMap<>();
    private IDataSourceProvider first;
    private String firstDsName;

    public void init() {
        if ( first == null ) throw new ActiveRecordException( "请设置至少一个的数据源提供器！" );
        dataSource.set( first.getDataSource() );
        dsName.set( firstDsName );
    }

    public Map<String, IDataSourceProvider> getAllProvider() {
        return Collections.unmodifiableMap( pools );
    }

    public ThreadLocalDataSource set( String dsName, IDataSourceProvider provider ) {
        Asserts.notEmpty( dsName, "dsName can not be empty" );
        Asserts.notNull( provider, "provider can not be null" );

        pools.put( dsName, provider );
        if ( first == null ) first = provider;
        if ( firstDsName == null ) firstDsName = dsName;
        return this;
    }

    public DataSource getDataSource() {
        DataSource dataSource = this.dataSource.get();
        if ( dataSource == null ) {
            throw new ActiveRecordException( "无法获取当前线程的数据源，请先执行setDataSource方法。" );
        }
        return dataSource;
    }

    public void setDataSource() {
        dataSource.set( first.getDataSource() );
    }

    public void setDataSource( String dsName ) {
        Asserts.notEmpty( dsName, "dsName can not be empty" );
        if ( Objects.equals( dsName, this.dsName.get() ) ) return;

        IDataSourceProvider provider = pools.get( dsName );

        Asserts.notNull( provider, "unknown dsName" );
        dataSource.set( provider.getDataSource() );
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection( String username, String password ) throws SQLException {
        return getDataSource().getConnection( username, password );
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return getDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter( PrintWriter out ) throws SQLException {
        getDataSource().setLogWriter( out );
    }

    @Override
    public void setLoginTimeout( int seconds ) throws SQLException {
        getDataSource().setLoginTimeout( seconds );
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return getDataSource().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return getDataSource().getParentLogger();
    }

    @Override
    public <T> T unwrap( Class<T> iface ) throws SQLException {
        return getDataSource().unwrap( iface );
    }

    @Override
    public boolean isWrapperFor( Class<?> iface ) throws SQLException {
        return getDataSource().isWrapperFor( iface );
    }
}
