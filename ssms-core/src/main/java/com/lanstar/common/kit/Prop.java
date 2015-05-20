/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Prop.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

/**
 * Copyright (c) 2011-2015, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lanstar.common.kit;

import com.lanstar.core.Const;

import java.io.*;
import java.util.Properties;

/**
 * Prop. Prop can load properties file from CLASSPATH or File object.
 */
public class Prop {

    private Properties properties = null;

    /**
     * Prop constructor.
     * @see #Prop(String, String)
     */
    public Prop( String fileName ) {
        this( fileName, Const.DEFAULT_ENCODING );
    }

    /**
     * Prop constructor
     * <p>
     * Example:<br>
     * Prop prop = new Prop("my_config.txt", "UTF-8");<br>
     * String userName = prop.get("userName");<br><br>
     *
     * prop = new Prop("com/jfinal/file_in_sub_path_of_classpath.txt", "UTF-8");<br>
     * String value = prop.get("key");
     *
     * @param fileName the properties file's name in classpath or the sub directory of classpath
     * @param encoding the encoding
     */
    public Prop( String fileName, String encoding ) {
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream( fileName );        // properties.load(Prop.class.getResourceAsStream(fileName));
            if ( inputStream == null )
                throw new IllegalArgumentException( "Properties file not found in classpath: " + fileName );
            properties = new Properties();
            properties.load( new InputStreamReader( inputStream, encoding ) );
        } catch ( IOException e ) {
            throw new RuntimeException( "Error loading properties file.", e );
        } finally {
            if ( inputStream != null ) try {inputStream.close();} catch ( IOException e ) {e.printStackTrace();}
        }
    }

    /**
     * Prop constructor.
     * @see #Prop(File, String)
     */
    public Prop( File file ) {
        this( file, Const.DEFAULT_ENCODING );
    }

    /**
     * Prop constructor
     * <p>
     * Example:<br>
     * Prop prop = new Prop(new File("/var/config/my_config.txt"), "UTF-8");<br>
     * String userName = prop.get("userName");
     *
     * @param file the properties File object
     * @param encoding the encoding
     */
    public Prop( File file, String encoding ) {
        if ( file == null )
            throw new IllegalArgumentException( "File can not be null." );
        if ( file.isFile() == false )
            throw new IllegalArgumentException( "Not a file : " + file.getName() );

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream( file );
            properties = new Properties();
            properties.load( new InputStreamReader( inputStream, encoding ) );
        } catch ( IOException e ) {
            throw new RuntimeException( "Error loading properties file.", e );
        } finally {
            if ( inputStream != null ) try {inputStream.close();} catch ( IOException e ) {e.printStackTrace();}
        }
    }

    public String get( String key ) {
        return properties.getProperty( key );
    }

    public String get( String key, String defaultValue ) {
        String value = get( key );
        return (value != null) ? value : defaultValue;
    }

    public Integer getInt( String key ) {
        String value = get( key );
        return (value != null) ? Integer.parseInt( value ) : null;
    }

    public Integer getInt( String key, Integer defaultValue ) {
        String value = get( key );
        return (value != null) ? Integer.parseInt( value ) : defaultValue;
    }

    public Long getLong( String key ) {
        String value = get( key );
        return (value != null) ? Long.parseLong( value ) : null;
    }

    public Long getLong( String key, Long defaultValue ) {
        String value = get( key );
        return (value != null) ? Long.parseLong( value ) : defaultValue;
    }

    public Boolean getBoolean( String key ) {
        String value = get( key );
        return (value != null) ? Boolean.parseBoolean( value ) : null;
    }

    public Boolean getBoolean( String key, Boolean defaultValue ) {
        String value = get( key );
        return (value != null) ? Boolean.parseBoolean( value ) : defaultValue;
    }

    public boolean containsKey( String key ) {
        return properties.containsKey( key );
    }

    public Properties getProperties() {
        return properties;
    }
}
