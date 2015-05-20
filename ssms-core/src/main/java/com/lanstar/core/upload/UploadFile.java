/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：UploadFile.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.upload;

import java.io.File;

public class UploadFile {

    private String parameterName;

    private String saveDirectory;
    private String fileName;
    private String originalFileName;
    private String contentType;

    public UploadFile( String parameterName, String saveDirectory, String filesystemName, String originalFileName, String contentType ) {
        this.parameterName = parameterName;
        this.saveDirectory = saveDirectory;
        this.fileName = filesystemName;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public String getSaveDirectory() {
        return saveDirectory;
    }

    public File getFile() {
        if ( saveDirectory == null || fileName == null ) {
            return null;
        } else {
            return new File( saveDirectory + File.separator + fileName );
        }
    }
}