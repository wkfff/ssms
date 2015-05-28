/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Resource.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

import java.io.File;
import java.io.IOException;

public interface Resource extends InputStreamSource {
    /**
     * 确定资源十分存在，如果存在则返回true，否则返回false。
     */
    boolean exists();

    /**
     * 确定资源是否可读取。
     */
    boolean isReadable();

    /**
     * 确定资源是否已经打开。
     */
    boolean isOpen();

    /**
     * 获取{@code FILE}对象
     */
    File getFile() throws IOException;

    /**
     * 获取资源内容长度。
     */
    long contentLength() throws IOException;

    /**
     * 获取资源最后修改时间
     */
    long lastModified() throws IOException;

    /**
     * 基于当前资源创建相对定位的资源
     */
    Resource createRelative( String relativePath ) throws IOException;

    /**
     * 获取资源文件名称
     */
    String getFilename();

    /**
     * 获取资源描述
     */
    String getDescription();
}
