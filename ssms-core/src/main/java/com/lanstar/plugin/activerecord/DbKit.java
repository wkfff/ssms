/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DbKit.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public final class DbKit {

    /**
     * The main Config object for system
     */
    static Config config = null;

    /**
     * For Model.getAttrsMap()/getModifyFlag() and Record.getColumnsMap()
     * while the ActiveRecordPlugin not start or the Exception throws of HashSessionManager.restorSession(..) by Jetty
     */
    static Config brokenConfig = new Config();

    private static Map<Class<? extends Model>, Config> modelToConfig = new HashMap<>();
    private static Map<String, Config> configNameToConfig = new HashMap<>();

    static final Object[] NULL_PARA_ARRAY = new Object[0];
    public static final String MAIN_CONFIG_NAME = "main";

    private DbKit() {}

    /**
     * Add Config object
     *
     * @param config the Config contains DataSource, Dialect and so on
     */
    public static void addConfig( Config config ) {
        if ( config == null )
            throw new IllegalArgumentException( "Config can not be null" );
        if ( configNameToConfig.containsKey( config.getName() ) )
            throw new IllegalArgumentException( "Config already exists: " + config.getName() );

        configNameToConfig.put( config.getName(), config );

        /**
         * Replace the main config if current config name is MAIN_CONFIG_NAME
         */
        if ( MAIN_CONFIG_NAME.equals( config.getName() ) )
            DbKit.config = config;

        /**
         * The configName may not be MAIN_CONFIG_NAME,
         * the main config have to set the first comming Config if it is null
         */
        if ( DbKit.config == null )
            DbKit.config = config;
    }

    static void addModelToConfigMapping( Class<? extends Model> modelClass, Config config ) {
        modelToConfig.put( modelClass, config );
    }

    public static Config getConfig() {
        return config;
    }

    public static Config getConfig( String configName ) {
        return configNameToConfig.get( configName );
    }

    public static Config getConfig( Class<? extends Model> modelClass ) {
        return modelToConfig.get( modelClass );
    }

    static void closeQuietly( ResultSet rs, Statement st ) {
        if ( rs != null ) {try {rs.close();} catch ( SQLException ignored ) {}}
        if ( st != null ) {try {st.close();} catch ( SQLException ignored ) {}}
    }

    static void closeQuietly( Statement st ) {
        if ( st != null ) {try {st.close();} catch ( SQLException ignored ) {}}
    }

    public static String replaceFormatSqlOrderBy( String sql ) {
        sql = sql.replaceAll( "(\\s)+", " " );
        int index = sql.toLowerCase().lastIndexOf( "order by" );
        if ( index > sql.toLowerCase().lastIndexOf( ")" ) ) {
            String sql1 = sql.substring( 0, index );
            String sql2 = sql.substring( index );
            sql2 = sql2.replaceAll( "[oO][rR][dD][eE][rR] [bB][yY] [\u4e00-\u9fa5a-zA-Z0-9_.]+((\\s)+(([dD][eE][sS][cC])|([aA][sS][cC])))?(( )*,( )*[\u4e00-\u9fa5a-zA-Z0-9_.]+(( )+(([dD][eE][sS][cC])|([aA][sS][cC])))?)*", "" );
            return sql1 + sql2;
        }
        return sql;
    }
}