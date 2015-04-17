/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileInfo.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.common.io;

import org.springframework.util.StringUtils;

public class LocationBuilder {
    private final StringBuilder buffer = new StringBuilder();

    public static LocationBuilder newInstance() {
        return new LocationBuilder();
    }

    public LocationBuilder folder( String folder ) {
        folder = StringUtils.cleanPath( folder );
        buffer.append( '/' ).append( folder );
        return this;
    }

    public LocationBuilder filename( String filename ) {
        filename = StringUtils.stripFilenameExtension( filename );
        if ( buffer.length() > 0 ) buffer.append( '/' );
        buffer.append( filename );
        return this;
    }

    public LocationBuilder extension( String ext ) {
        String ext2 = StringUtils.getFilenameExtension( ext );
        if ( ext2 != null ) ext = ext2;
        buffer.append( '.' ).append( ext );
        return this;
    }

    public String build() {
        return this.buffer.toString();
    }

    public LocationBuilder clear() {
        buffer.delete( 0, buffer.length() - 1 );
        return this;
    }
}
