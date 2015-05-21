/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RapidwareConfig.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.config;

import com.lanstar.common.kit.Prop;
import com.lanstar.common.kit.PropKit;
import com.lanstar.core.Const;

import java.io.File;
import java.util.Properties;

public abstract class RapidwareConfig {
    public abstract void configConstant( Constants me );

    public abstract void configRoute( Routes me );

    public abstract void configPlugin( Plugins me );

    public abstract void configInterceptor( Interceptors me );

    public abstract void configHandler( Handlers me );

    protected Prop prop = null;

    public Properties loadPropertyFile( String fileName ) {
        return loadPropertyFile( fileName, Const.DEFAULT_ENCODING );
    }

    /**
     * Load property file.
     * Example:<br>
     * loadPropertyFile("db_username_pass.txt", "UTF-8");
     *
     * @param fileName the file in CLASSPATH or the sub directory of the CLASSPATH
     * @param encoding the encoding
     */
    public Properties loadPropertyFile( String fileName, String encoding ) {
        prop = PropKit.use( fileName, encoding );
        return prop.getProperties();
    }

    /**
     * Load property file.
     *
     * @see #loadPropertyFile(File, String)
     */
    public Properties loadPropertyFile( File file ) {
        return loadPropertyFile( file, Const.DEFAULT_ENCODING );
    }

    /**
     * Load property file
     * Example:<br>
     * loadPropertyFile(new File("/var/config/my_config.txt"), "UTF-8");
     *
     * @param file     the properties File object
     * @param encoding the encoding
     */
    public Properties loadPropertyFile( File file, String encoding ) {
        prop = PropKit.use( file, encoding );
        return prop.getProperties();
    }

    public void unloadPropertyFile( String fileName ) {
        Prop uselessProp = PropKit.useless( fileName );
        if ( this.prop == uselessProp )
            this.prop = null;
    }

    public void unloadAllPropertyFiles() {
        PropKit.clear();
        prop = null;
    }

    private Prop getProp() {
        if ( prop == null )
            throw new IllegalStateException( "Load propties file by invoking loadPropertyFile(String fileName) method first." );
        return prop;
    }

    public String getProperty( String key ) {
        return getProp().get( key );
    }

    public String getProperty( String key, String defaultValue ) {
        return getProp().get( key, defaultValue );
    }

    public Integer getPropertyToInt( String key ) {
        return getProp().getInt( key );
    }

    public Integer getPropertyToInt( String key, Integer defaultValue ) {
        return getProp().getInt( key, defaultValue );
    }

    public Long getPropertyToLong( String key ) {
        return getProp().getLong( key );
    }

    public Long getPropertyToLong( String key, Long defaultValue ) {
        return getProp().getLong( key, defaultValue );
    }

    public Boolean getPropertyToBoolean( String key ) {
        return getProp().getBoolean( key );
    }

    public Boolean getPropertyToBoolean( String key, Boolean defaultValue ) {
        return getProp().getBoolean( key, defaultValue );
    }
}
