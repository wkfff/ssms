/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SaveFormat.java
 * 创建时间：2015-07-06
 * 创建用户：张铮彬
 */

package com.lanstar.render.aspose;

import com.aspose.words.SaveFormat;

public enum OutputFormat {
    DOC {
        @Override
        public String extension() {
            return ".doc";
        }

        @Override
        public String contentType( String encoding ) {
            return "application/msword;charset=" + encoding;
        }

        @Override
        public int format() {
            return SaveFormat.DOC;
        }
    },
    DOCX {
        @Override
        public String extension() {
            return ".docx";
        }

        @Override
        public String contentType( String encoding ) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=" + encoding;
        }

        @Override
        public int format() {
            return SaveFormat.DOCX;
        }
    },
    PDF {
        @Override
        public String extension() {
            return ".pdf";
        }

        @Override
        public String contentType( String encoding ) {
            return "application/pdf;charset=" + encoding;
        }

        @Override
        public int format() {
            return SaveFormat.PDF;
        }
    };

    public abstract String extension();

    public abstract String contentType( String encoding );

    public abstract int format();
}
