/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DruidDataSourceProvider.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db.datasource.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.lanstar.common.log.LogHelper;
import com.lanstar.db.datasource.DataSourceConfig;
import com.lanstar.db.datasource.DataSourceFactory;
import com.lanstar.db.datasource.IDataSourceProvider;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DruidDataSourceProvider implements IDataSourceProvider {
    @Override
    public DataSource createDatasource( DataSourceConfig config ) {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl( config.getJdbcUrl() );
        ds.setUsername( config.getJdbcUser() );
        ds.setPassword( config.getJdbcPassword() );
        ds.setInitialSize( config.getPoolMinSize() );
        ds.setMinIdle( config.getPoolMinSize() );
        ds.setMaxActive( config.getPoolMaxSize() );
        ds.setDriverClassName( config.dialect.getJdbcDriver() );
        // 配置获取连接等待超时的时间
        ds.setMaxWait( 6000L );
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        ds.setMinEvictableIdleTimeMillis( 300000L );
        // 心跳测试指令
        ds.setValidationQuery( config.dialect.getHeartbeatSql() );
        // 空闲测试
        ds.setTestWhileIdle( true );
        // 申请连接时执行validationQuery检测连接是否有效(会降低性能)
        ds.setTestOnBorrow( false );
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        ds.setTimeBetweenEvictionRunsMillis( 60000L );
        ds.setMinEvictableIdleTimeMillis( 300000L );
        // 归还连接检测是否有效（会降低性能）
        ds.setTestOnReturn( false );
        // 如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。分库分表较多的数据库，建议配置为false。
        ds.setPoolPreparedStatements( false );
        ds.setMaxPoolPreparedStatementPerConnectionSize( 20 );
        // 对于长时间不使用的连接强制关闭(超过30分钟没动静就删除)
        ds.setRemoveAbandoned( true );
        ds.setRemoveAbandonedTimeout( 1800 );
        ds.setLogAbandoned( true );
        //

        // 配置监控统计拦截的filters
        try {
//            ds.addFilters( "stat,log4j" );
            // 创建好数据库后先执行一次初始化操作
            ds.init();
        } catch ( SQLException e ) {
            LogHelper.error( DataSourceFactory.class, e, "数据源无法完成初始化" );
        }

        return ds;
    }
}
