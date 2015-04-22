/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileCache.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.lanstar.app.ServletHelper;
import com.lanstar.common.log.LogHelper;
import com.lanstar.common.utils.DirectorySteper;

import java.io.File;
import java.util.Map;

public abstract class FilesCache<T> extends Cache<T> {
    @Override
    protected void load( final Map<String, T> pools ) {
        DirectorySteper.IFilePicker filePicker = new DirectorySteper.IFilePicker() {
            public void pick( File file ) {
                LogHelper.debug( FilesCache.class, "---->解析文件:" + file.getAbsolutePath() );
                if ( file.getAbsolutePath().toLowerCase().endsWith( getFileExtensions() ) ) {
                    parseFile( pools, file );
                }
            }
        };
        DirectorySteper steper = new DirectorySteper( filePicker );

        String path = ServletHelper.getRealPath( getPath() );
        File file = new File( path );
        if ( !file.exists() ) LogHelper.warn( XmlFilesCache.class, "文件或目录[%s]不存在", file );
        if ( file.isFile() ) filePicker.pick( file );
        if ( file.isDirectory() ) steper.step( file );
    }

    protected abstract String getFileExtensions();

    protected abstract String getPath();

    protected abstract void parseFile( Map<String, T> pools, File file );
}
