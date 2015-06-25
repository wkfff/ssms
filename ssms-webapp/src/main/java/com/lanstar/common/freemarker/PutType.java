/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PutType.java
 * 创建时间：2015-06-25
 * 创建用户：张铮彬
 */

package com.lanstar.common.freemarker;

import java.io.IOException;
import java.io.Writer;

public enum PutType {
    APPEND {
        @Override
        public void write( Writer out, String bodyResult, String putContents ) throws IOException {
            out.write( bodyResult );
            out.write( putContents );
        }
    },
    PREPEND {
        @Override
        public void write( Writer out, String bodyResult, String putContents ) throws IOException {
            out.write( putContents );
            out.write( bodyResult );
        }
    },
    REPLACE {
        @Override
        public void write( Writer out, String bodyResult, String putContents ) throws IOException {
            out.write( putContents );
        }
    };

    public abstract void write( Writer out, String bodyResult, String putContents ) throws IOException;
}
