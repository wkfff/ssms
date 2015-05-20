/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Config.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.activerecord.dialect.Dialect;
import com.lanstar.plugin.activerecord.dialect.MysqlDialect;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Config {

    String name;

    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    DataSource dataSource;
    int transactionLevel = Connection.TRANSACTION_READ_COMMITTED;

    boolean showSql = false;
    boolean devMode = false;
    Dialect dialect = new MysqlDialect();

    IContainerFactory containerFactory = new IContainerFactory() {
        public Map<String, Object> getAttrsMap() {return new HashMap<>();}

        public Map<String, Object> getColumnsMap() {return new HashMap<>();}

        public Set<String> getModifyFlagSet() {return new HashSet<>();}
    };

    /**
     * For DbKit.brokenConfig = new Config();
     */
    Config() {

    }

    /**
     * Constructor with DataSource
     *
     * @param dataSource the dataSource, can not be null
     */
    public Config( String name, DataSource dataSource ) {
        if ( StrKit.isBlank( name ) )
            throw new IllegalArgumentException( "Config name can not be blank" );
        if ( dataSource == null )
            throw new IllegalArgumentException( "DataSource can not be null" );

        this.name = name.trim();
        this.dataSource = dataSource;
    }

    /**
     * Constructor with DataSource and Dialect
     *
     * @param dataSource the dataSource, can not be null
     * @param dialect    the dialect, can not be null
     */
    public Config( String name, DataSource dataSource, Dialect dialect ) {
        if ( StrKit.isBlank( name ) )
            throw new IllegalArgumentException( "Config name can not be blank" );
        if ( dataSource == null )
            throw new IllegalArgumentException( "DataSource can not be null" );
        if ( dialect == null )
            throw new IllegalArgumentException( "Dialect can not be null" );

        this.name = name.trim();
        this.dataSource = dataSource;
        this.dialect = dialect;
    }

    /**
     * Constructor with full parameters
     *
     * @param dataSource       the dataSource, can not be null
     * @param dialect          the dialect, set null with default value: new MysqlDialect()
     * @param showSql          the showSql,set null with default value: false
     * @param devMode          the devMode, set null with default value: false
     * @param transactionLevel the transaction level, set null with default value: Connection.TRANSACTION_READ_COMMITTED
     * @param containerFactory the containerFactory, set null with default value: new IContainerFactory(){......}
     */
    public Config( String name,
            DataSource dataSource,
            Dialect dialect,
            Boolean showSql,
            Boolean devMode,
            Integer transactionLevel,
            IContainerFactory containerFactory ) {
        if ( StrKit.isBlank( name ) )
            throw new IllegalArgumentException( "Config name can not be blank" );
        if ( dataSource == null )
            throw new IllegalArgumentException( "DataSource can not be null" );

        this.name = name.trim();
        this.dataSource = dataSource;

        if ( dialect != null )
            this.dialect = dialect;
        if ( showSql != null )
            this.showSql = showSql;
        if ( devMode != null )
            this.devMode = devMode;
        if ( transactionLevel != null )
            this.transactionLevel = transactionLevel;
        if ( containerFactory != null )
            this.containerFactory = containerFactory;
    }

    public String getName() {
        return name;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public int getTransactionLevel() {
        return transactionLevel;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public IContainerFactory getContainerFactory() {
        return containerFactory;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public boolean isDevMode() {
        return devMode;
    }

    // --------

    /**
     * Support transaction with Transaction interceptor
     */
    public final void setThreadLocalConnection( Connection connection ) {
        threadLocal.set( connection );
    }

    public final void removeThreadLocalConnection() {
        threadLocal.remove();
    }

    /**
     * Get Connection. Support transaction if Connection in ThreadLocal
     */
    public final Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if ( conn != null )
            return conn;
        return showSql ? new SqlReporter( dataSource.getConnection() ).getConnection() : dataSource.getConnection();
    }

    /**
     * Helps to implement nested transaction.
     * Tx.intercept(...) and Db.tx(...) need this method to detected if it in nested transaction.
     */
    public final Connection getThreadLocalConnection() {
        return threadLocal.get();
    }

    /**
     * Close ResultSet、Statement、Connection
     * ThreadLocal support declare transaction.
     */
    public final void close( ResultSet rs, Statement st, Connection conn ) {
        if ( rs != null ) {try {rs.close();} catch ( SQLException ignored ) {}}
        if ( st != null ) {try {st.close();} catch ( SQLException ignored ) {}}

        if ( threadLocal.get() == null ) {    // in transaction if conn in threadlocal
            if ( conn != null ) {
                try {conn.close();} catch ( SQLException e ) {throw new ActiveRecordException( e );}
            }
        }
    }

    public final void close( Statement st, Connection conn ) {
        if ( st != null ) {try {st.close();} catch ( SQLException ignored ) {}}

        if ( threadLocal.get() == null ) {    // in transaction if conn in threadlocal
            if ( conn != null ) {
                try {conn.close();} catch ( SQLException e ) {throw new ActiveRecordException( e );}
            }
        }
    }

    public final void close( Connection conn ) {
        if ( threadLocal.get() == null )        // in transaction if conn in threadlocal
            if ( conn != null )
                try {conn.close();} catch ( SQLException e ) {throw new ActiveRecordException( e );}
    }
}