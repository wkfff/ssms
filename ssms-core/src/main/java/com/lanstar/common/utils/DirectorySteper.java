/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DirectorySteper.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */
package com.lanstar.common.utils;

import com.lanstar.common.exception.WebException;
import com.lanstar.common.log.LogHelper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * 遍历目录的处理器
 *
 * @author CHI
 */
public class DirectorySteper {
    private final IFilePicker filePicker;

    public DirectorySteper( IFilePicker picker ) {
        this.filePicker = picker;
    }

    public void step( String path ) throws IOException {
        File p = new File( path );
        if ( !p.exists() || !p.isDirectory() ) throw new IOException( "目录不存在" );
        step( p );
    }

    public void step( Collection<File> paths ) throws IOException {
        for ( File p : paths ) step( p );
    }

    public void step( File dir ) {
        if ( dir == null ) return;
        if ( !dir.exists() ) return;
        if ( !dir.isDirectory() ) return;

        File[] files = dir.listFiles();
        if ( files == null ) return;
        for ( File f : files ) {
            if ( f.isFile() ) try {
                filePicker.pick( f );
            } catch ( WebException e ) {
                LogHelper.warn( DirectorySteper.class, e, "处理%s文件错误！", f.getName() );
            }
            if ( f.isDirectory() ) step( f );
        }
    }

    /* 文件处理者，处理遍历到的文件 */
    public interface IFilePicker {
        void pick( File file );
    }
}
