/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：WritableResource.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.common.io;

import java.io.IOException;
import java.io.OutputStream;

public interface WritableResource extends Resource {

    /**
     * Return whether the contents of this resource can be modified,
     * e.g. via {@link #getOutputStream()} or {@link #getFile()}.
     * <p>Will be {@code true} for typical resource descriptors;
     * note that actual content writing may still fail when attempted.
     * However, a value of {@code false} is a definitive indication
     * that the resource content cannot be modified.
     * @see #getOutputStream()
     * @see #isReadable()
     */
    boolean isWritable();

    /**
     * Return an {@link OutputStream} for the underlying resource,
     * allowing to (over-)write its content.
     * @throws IOException if the stream could not be opened
     * @see #getInputStream()
     */
    OutputStream getOutputStream() throws IOException;

}
