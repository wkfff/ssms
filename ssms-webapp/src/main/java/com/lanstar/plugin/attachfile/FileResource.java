/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileResource.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

import com.lanstar.core.upload.UploadFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileResource {
    private final String filenameWithoutExtension;
    private final String extension;
    private final InputStreamResource resource;

    public FileResource( UploadFile file ) {
        this.filenameWithoutExtension = StringUtils.stripFilenameExtension( file.getOriginalFileName() );
        this.extension = StringUtils.getFilenameExtension( file.getOriginalFileName() );
        try {
            this.resource = new InputStreamResource( new FileInputStream( file.getFile() ) );
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException( e );
        }
    }

    public String getFilenameWithoutExtension() {
        return filenameWithoutExtension;
    }

    public String getExtension() {
        return extension;
    }

    public InputStreamResource getResource() {
        return resource;
    }
}
