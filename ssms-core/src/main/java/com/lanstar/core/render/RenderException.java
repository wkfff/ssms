/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderException.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.common.exception.WebException;

public class RenderException extends WebException {
    private static final long serialVersionUID = -7166176701864167354L;

    public RenderException() {
    }

    public RenderException( String message ) {
        super( message );
    }

    public RenderException( String message, Throwable cause ) {
        super( message, cause );
    }

    public RenderException( Throwable cause ) {
        super( cause );
    }

    public RenderException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
