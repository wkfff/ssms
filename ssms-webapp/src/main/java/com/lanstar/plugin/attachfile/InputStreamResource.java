/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：InputStreamResource.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamResource extends AbstractResource {

    private final InputStream inputStream;

    private final String description;

    private boolean read = false;

    public InputStreamResource( InputStream inputStream ) {
        this( inputStream, "resource loaded through InputStream" );
    }

    public InputStreamResource( InputStream inputStream, String description ) {
        if ( inputStream == null ) {
            throw new IllegalArgumentException( "InputStream must not be null" );
        }
        this.inputStream = inputStream;
        this.description = (description != null ? description : "");
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    public InputStream getInputStream() throws IOException, IllegalStateException {
        if ( this.read ) {
            throw new IllegalStateException( "InputStream has already been read - " +
                    "do not use InputStreamResource if a stream needs to be read multiple times" );
        }
        this.read = true;
        return this.inputStream;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals( Object obj ) {
        return (obj == this ||
                (obj instanceof InputStreamResource && ((InputStreamResource) obj).inputStream
                        .equals( this.inputStream )));
    }

    @Override
    public int hashCode() {
        return this.inputStream.hashCode();
    }

}
