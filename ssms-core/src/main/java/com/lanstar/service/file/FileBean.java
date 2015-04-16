/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileBean.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.service.file;

import com.lanstar.common.io.InputStreamResource;
import com.lanstar.common.io.Resource;
import org.springframework.util.StringUtils;

import java.io.InputStream;

public class FileBean {
    private String filenameWithoutExtension;
    private String extension;
    private Resource resource;

    public FileBean( String file, InputStream inputStream ) {
        this.filenameWithoutExtension = StringUtils.stripFilenameExtension( file );
        this.extension = StringUtils.getFilenameExtension( file );
        this.resource = new InputStreamResource( inputStream );
    }

    public String getFilenameWithoutExtension() {
        return filenameWithoutExtension;
    }

    public String getExtension() {
        return extension;
    }

    public Resource getResource() {
        return resource;
    }
}