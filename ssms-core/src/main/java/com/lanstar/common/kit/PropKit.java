/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PropKit.java
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

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PropKit. PropKit can load properties file from CLASSPATH or File object.
 */
public class PropKit {

    private static final Map<String, Prop> map = new ConcurrentHashMap<>();

    private PropKit() {}

    /**
     * Using the properties file. It will loading the properties file if not loading.
     *
     * @see #use(String, String)
     */
    public static Prop use( String fileName ) {
        return use( fileName, Const.DEFAULT_ENCODING );
    }

    /**
     * Using the properties file. It will loading the properties file if not loading.
     * <p>
     * Example:<br>
     * PropKit.use("config.txt", "UTF-8");<br>
     * PropKit.use("other_config.txt", "UTF-8");<br><br>
     * String userName = PropKit.get("userName");<br>
     * String password = PropKit.get("password");<br><br>
     *
     * userName = PropKit.use("other_config.txt").get("userName");<br>
     * password = PropKit.use("other_config.txt").get("password");<br><br>
     *
     * PropKit.use("com/jfinal/config_in_sub_directory_of_classpath.txt");
     *
     * @param fileName the properties file's name in classpath or the sub directory of classpath
     * @param encoding the encoding
     */
    public static Prop use( String fileName, String encoding ) {
        Prop result = map.get( fileName );
        if ( result == null ) {
            result = new Prop( fileName, encoding );
            map.put( fileName, result );
        }
        return result;
    }

    /**
     * Using the properties file bye File object. It will loading the properties file if not loading.
     *
     * @see #use(File, String)
     */
    public static Prop use( File file ) {
        return use( file, Const.DEFAULT_ENCODING );
    }

    /**
     * Using the properties file bye File object. It will loading the properties file if not loading.
     * <p>
     * Example:<br>
     * PropKit.use(new File("/var/config/my_config.txt"), "UTF-8");<br>
     * Strig userName = PropKit.use("my_config.txt").get("userName");
     *
     * @param file     the properties File object
     * @param encoding the encoding
     */
    public static Prop use( File file, String encoding ) {
        Prop result = map.get( file.getName() );
        if ( result == null ) {
            result = new Prop( file, encoding );
            map.put( file.getName(), result );
        }
        return result;
    }

    public static Prop useless( String fileName ) {
        return map.remove( fileName );
    }

    public static void clear() {
        map.clear();
    }

    public static Prop getProp( String fileName ) {
        return map.get( fileName );
    }
}


