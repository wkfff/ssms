/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActiveRecordPlugin.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.IPlugin;
import com.lanstar.plugin.activerecord.dialect.Dialect;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ActiveRecordPlugin implements IPlugin {

    private String configName = DbKit.MAIN_CONFIG_NAME;
    private Config config = null;

    private DataSource dataSource;
    private IDataSourceProvider dataSourceProvider;
    private Integer transactionLevel = null;
    private Boolean showSql = null;
    private Boolean devMode = null;
    private Dialect dialect = null;
    private IContainerFactory containerFactory = null;

    private boolean isStarted = false;
    private List<Table> tableList = new ArrayList<>();

    public ActiveRecordPlugin( Config config ) {
        if ( config == null )
            throw new IllegalArgumentException( "Config can not be null" );
        this.config = config;
    }

    public ActiveRecordPlugin( DataSource dataSource ) {
        this( DbKit.MAIN_CONFIG_NAME, dataSource );
    }

    public ActiveRecordPlugin( String configName, DataSource dataSource ) {
        this( configName, dataSource, Connection.TRANSACTION_READ_COMMITTED );
    }

    public ActiveRecordPlugin( DataSource dataSource, int transactionLevel ) {
        this( DbKit.MAIN_CONFIG_NAME, dataSource, transactionLevel );
    }

    public ActiveRecordPlugin( String configName, DataSource dataSource, int transactionLevel ) {
        if ( StrKit.isBlank( configName ) )
            throw new IllegalArgumentException( "configName can not be blank" );
        if ( dataSource == null )
            throw new IllegalArgumentException( "dataSource can not be null" );
        this.configName = configName.trim();
        this.dataSource = dataSource;
        this.setTransactionLevel( transactionLevel );
    }

    public ActiveRecordPlugin( IDataSourceProvider dataSourceProvider ) {
        this( DbKit.MAIN_CONFIG_NAME, dataSourceProvider );
    }

    public ActiveRecordPlugin( String configName, IDataSourceProvider dataSourceProvider ) {
        this( configName, dataSourceProvider, Connection.TRANSACTION_READ_COMMITTED );
    }

    public ActiveRecordPlugin( IDataSourceProvider dataSourceProvider, int transactionLevel ) {
        this( DbKit.MAIN_CONFIG_NAME, dataSourceProvider, transactionLevel );
    }

    public ActiveRecordPlugin( String configName, IDataSourceProvider dataSourceProvider, int transactionLevel ) {
        if ( StrKit.isBlank( configName ) )
            throw new IllegalArgumentException( "configName can not be blank" );
        if ( dataSourceProvider == null )
            throw new IllegalArgumentException( "dataSourceProvider can not be null" );
        this.configName = configName.trim();
        this.dataSourceProvider = dataSourceProvider;
        this.setTransactionLevel( transactionLevel );
    }

    public ActiveRecordPlugin addMapping( String tableName, String primaryKey, Class<? extends Model<?>> modelClass ) {
        tableList.add( new Table( tableName, primaryKey, modelClass ) );
        return this;
    }

    public ActiveRecordPlugin addMapping( String tableName, Class<? extends Model<?>> modelClass ) {
        tableList.add( new Table( tableName, modelClass ) );
        return this;
    }

    /**
     * Set transaction level define in java.sql.Connection
     *
     * @param transactionLevel only be 0, 1, 2, 4, 8
     */
    public ActiveRecordPlugin setTransactionLevel( int transactionLevel ) {
        int t = transactionLevel;
        if ( t != 0 && t != 1 && t != 2 && t != 4 && t != 8 )
            throw new IllegalArgumentException( "The transactionLevel only be 0, 1, 2, 4, 8" );
        this.transactionLevel = transactionLevel;
        return this;
    }

    public ActiveRecordPlugin setShowSql( boolean showSql ) {
        this.showSql = showSql;
        return this;
    }

    public ActiveRecordPlugin setDevMode( boolean devMode ) {
        this.devMode = devMode;
        return this;
    }

    public Boolean getDevMode() {
        return devMode;
    }

    public ActiveRecordPlugin setDialect( Dialect dialect ) {
        if ( dialect == null )
            throw new IllegalArgumentException( "dialect can not be null" );
        this.dialect = dialect;
        return this;
    }

    public ActiveRecordPlugin setContainerFactory( IContainerFactory containerFactory ) {
        if ( containerFactory == null )
            throw new IllegalArgumentException( "containerFactory can not be null" );
        this.containerFactory = containerFactory;
        return this;
    }

    public boolean start() {
        if ( isStarted )
            return true;

        if ( dataSourceProvider != null )
            dataSource = dataSourceProvider.getDataSource();
        if ( dataSource == null )
            throw new RuntimeException( "ActiveRecord start error: ActiveRecordPlugin need DataSource or DataSourceProvider" );

        if ( config == null )
            config = new Config( configName, dataSource, dialect, showSql, devMode, transactionLevel, containerFactory );
        DbKit.addConfig( config );

        boolean succeed = TableBuilder.build( tableList, config );
        if ( succeed ) {
            Db.init();
            isStarted = true;
        }
        return succeed;
    }

    public boolean stop() {
        isStarted = false;
        return true;
    }
}
