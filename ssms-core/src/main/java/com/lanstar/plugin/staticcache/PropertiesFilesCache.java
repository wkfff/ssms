/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PropertiesCache.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public abstract class PropertiesFilesCache<T> extends FilesCache<T> {
    @Override
    protected String getFileExtensions() {
        return ".properties";
    }

    @Override
    protected void parseFile( Map<String, T> pools, File file ) {
        Properties properties = new Properties();
        FileReader reader = null;
        try {
            reader = new FileReader( file );
            properties.load( reader );
            parseProperties( properties, pools, file );
        } catch ( IOException ignored ) {
        } finally {
            if ( reader != null )
                try {
                    reader.close();
                } catch ( IOException ignored ) {
                }
        }
    }

    protected abstract void parseProperties( Properties properties, Map<String, T> pools, File file );
}
