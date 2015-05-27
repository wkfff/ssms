/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AbstractResource.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

import com.lanstar.common.Asserts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractResource implements Resource {
    public boolean exists() {
        // Try file existence: can we find the file in the file system?
        try {
            return getFile().exists();
        } catch ( IOException ex ) {
            // Fall back to stream existence: can we open the stream?
            try {
                InputStream is = getInputStream();
                is.close();
                return true;
            } catch ( Throwable isEx ) {
                return false;
            }
        }
    }

    public boolean isReadable() {
        return true;
    }

    public boolean isOpen() {
        return false;
    }

    public File getFile() throws IOException {
        throw new FileNotFoundException( getDescription() + " cannot be resolved to absolute file path" );
    }

    public long contentLength() throws IOException {
        InputStream is = this.getInputStream();
        Asserts.notNull( is, "resource input stream must not be null" );
        try {
            long size = 0;
            byte[] buf = new byte[255];
            int read;
            while ( (read = is.read( buf )) != -1 ) {
                size += read;
            }
            return size;
        } finally {
            try {
                is.close();
            } catch ( IOException ignored ) {
            }
        }
    }

    public long lastModified() throws IOException {
        long lastModified = getFileForLastModifiedCheck().lastModified();
        if ( lastModified == 0L ) {
            throw new FileNotFoundException( getDescription() +
                    " cannot be resolved in the file system for resolving its last-modified timestamp" );
        }
        return lastModified;
    }

    public Resource createRelative( String relativePath ) throws IOException {
        throw new FileNotFoundException( "Cannot create a relative resource for " + getDescription() );
    }

    public String getFilename() {
        return null;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public boolean equals( Object obj ) {
        return (obj == this ||
                (obj instanceof Resource && ((Resource) obj)
                        .getDescription().equals( getDescription() )));
    }

    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }

    protected File getFileForLastModifiedCheck() throws IOException {
        return getFile();
    }

}