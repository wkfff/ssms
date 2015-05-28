/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileSystemResource.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

import com.lanstar.common.Asserts;

import java.io.*;

public class FileSystemResource extends AbstractResource implements WritableResource {

    private final File file;

    private final String path;

    /**
     * Create a new {@code FileSystemResource} from a {@link File} handle.
     * <p>Note: When building relative resources via {@link #createRelative},
     * the relative path will apply <i>at the same directory level</i>:
     * e.g. new File("C:/dir1"), relative path "dir2" -> "C:/dir2"!
     * If you prefer to have relative paths built underneath the given root
     * directory, use the {@link #FileSystemResource(String) constructor with a file path}
     * to append a trailing slash to the root path: "C:/dir1/", which
     * indicates this directory as root for all relative paths.
     *
     * @param file a File handle
     */
    public FileSystemResource( File file ) {
        Asserts.notNull( file, "File must not be null" );
        this.file = file;
        this.path = StringUtils.cleanPath( file.getPath() );
    }

    /**
     * Create a new {@code FileSystemResource} from a file path.
     * <p>Note: When building relative resources via {@link #createRelative},
     * it makes a difference whether the specified resource base path here
     * ends with a slash or not. In the case of "C:/dir1/", relative paths
     * will be built underneath that root: e.g. relative path "dir2" ->
     * "C:/dir1/dir2". In the case of "C:/dir1", relative paths will apply
     * at the same directory level: relative path "dir2" -> "C:/dir2".
     *
     * @param path a file path
     */
    public FileSystemResource( String path ) {
        Asserts.notNull( path, "Path must not be null" );
        this.file = new File( path );
        this.path = StringUtils.cleanPath( path );
    }

    /**
     * Return the file path for this resource.
     */
    public final String getPath() {
        return this.path;
    }

    /**
     * This implementation returns whether the underlying file exists.
     *
     * @see java.io.File#exists()
     */
    @Override
    public boolean exists() {
        return this.file.exists();
    }

    /**
     * This implementation checks whether the underlying file is marked as readable
     * (and corresponds to an actual file with content, not to a directory).
     *
     * @see java.io.File#canRead()
     * @see java.io.File#isDirectory()
     */
    @Override
    public boolean isReadable() {
        return (this.file.canRead() && !this.file.isDirectory());
    }

    /**
     * This implementation opens a FileInputStream for the underlying file.
     *
     * @see java.io.FileInputStream
     */
    public InputStream getInputStream() throws IOException {
        return new FileInputStream( this.file );
    }

    /**
     * This implementation returns the underlying File reference.
     */
    @Override
    public File getFile() {
        return this.file;
    }

    /**
     * This implementation returns the underlying File's length.
     */
    @Override
    public long contentLength() throws IOException {
        return this.file.length();
    }

    /**
     * This implementation creates a FileSystemResource, applying the given path
     * relative to the path of the underlying file of this resource descriptor.
     */
    @Override
    public Resource createRelative( String relativePath ) {
        String pathToUse = StringUtils.applyRelativePath( this.path, relativePath );
        return new FileSystemResource( pathToUse );
    }

    /**
     * This implementation returns the name of the file.
     *
     * @see java.io.File#getName()
     */
    @Override
    public String getFilename() {
        return this.file.getName();
    }

    /**
     * This implementation returns a description that includes the absolute
     * path of the file.
     *
     * @see java.io.File#getAbsolutePath()
     */
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }

    // implementation of WritableResource

    /**
     * This implementation checks whether the underlying file is marked as writable
     * (and corresponds to an actual file with content, not to a directory).
     *
     * @see java.io.File#canWrite()
     * @see java.io.File#isDirectory()
     */
    public boolean isWritable() {
        return (this.file.canWrite() && !this.file.isDirectory());
    }

    /**
     * This implementation opens a FileOutputStream for the underlying file.
     *
     * @see java.io.FileOutputStream
     */
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream( this.file );
    }

    /**
     * This implementation compares the underlying File references.
     */
    @Override
    public boolean equals( Object obj ) {
        return (obj == this ||
                (obj instanceof FileSystemResource && this.path.equals( ((FileSystemResource) obj).path )));
    }

    /**
     * This implementation returns the hash code of the underlying File reference.
     */
    @Override
    public int hashCode() {
        return this.path.hashCode();
    }

}