/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DataSourceConfig.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db.datasource;

import com.lanstar.common.helper.PropertiesHelper;
import com.lanstar.db.dialect.DialectFactory;
import com.lanstar.db.dialect.IDialect;

import java.util.Properties;

/**
 * 数据源配置（在配置文件中）
 */
public class DataSourceConfig {
    /** JDBC驱动程序 */
//    public static final String JDBC_DRIVER = "jdbc_driver";
    /**
     * JDBC的URL地址
     */
    public static final String JDBC_URL = "jdbc_url";
    /**
     * JDBC的用户名
     */
    public static final String JDBC_USER = "jdbc_user";
    /**
     * JDBC的连接密码
     */
    public static final String JDBC_PASSWORD = "jdbc_password";
    /**
     * 连接池最小值
     */
    public static final String JDBC_POOL_MINSIZE = "jdbc_pool_minsize";
    /**
     * 连接池最大值
     */
    public static final String JDBC_POOL_MAXSIZE = "jdbc_pool_maxsize";
    /**
     * 过滤器定义，在DRUID中应用
     */
    public static final String JDBC_POOL_FILTERS = "jdbc_pool_filters";
    /**
     * 数据库连接池类型
     */
    public static final String JDBC_POOL_TYPE = "jdbc_pool_type";
    /**
     * 数据库方言类
     */
    public static final String JDBC_DIALECT = "jdbc_dialect";
    /**
     * 是否必须成功启动后才能做后续的操作
     */
    public static final String JDBC_START_REQUIRED = "jdbc_start_required";

    //    final String jdbcDriver;
    final String jdbcUrl;
    final String jdbcUser;
    final String jdbcPassword;
    final int poolMaxSize;
    final int poolMinSize;
    final String poolFilters;
    final boolean required;
    final String poolType;
    public IDialect dialect;

    public DataSourceConfig( Properties props ) {

//        this.jdbcDriver = PropertiesHelper.get(props, JDBC_DRIVER, null);
        this.jdbcUrl = PropertiesHelper.get( props, JDBC_URL, null );
        this.jdbcUser = PropertiesHelper.get( props, JDBC_USER, null );
        this.jdbcPassword = PropertiesHelper.get( props, JDBC_PASSWORD, null );
        this.poolMaxSize = PropertiesHelper.get( props, JDBC_POOL_MAXSIZE, 250 );
        this.poolMinSize = PropertiesHelper.get( props, JDBC_POOL_MINSIZE, 3 );
        this.poolFilters = PropertiesHelper.get( props, JDBC_POOL_FILTERS, "" );
        this.required = "true".equalsIgnoreCase( PropertiesHelper.get( props, JDBC_START_REQUIRED, "false" ) );
        this.dialect = DialectFactory.me().getDialect( PropertiesHelper.get( props, JDBC_DIALECT, "mysql" ) );
        this.poolType = PropertiesHelper.get( props, JDBC_POOL_TYPE, "druid" );
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public int getPoolMaxSize() {
        return poolMaxSize;
    }

    public int getPoolMinSize() {
        return poolMinSize;
    }

    public String getPoolFilters() {
        return poolFilters;
    }

    public boolean isRequired() {
        return required;
    }

    public String getPoolType() {
        return poolType;
    }

    public IDialect getDialect() {
        return dialect;
    }
}
