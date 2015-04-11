/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NotSupportedRenderException.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

public class NotSupportedRenderException extends RenderException {
    private static final long serialVersionUID = 1974484946887532962L;

    public NotSupportedRenderException() {
    }

    public NotSupportedRenderException( String message ) {
        super( message );
    }

    public NotSupportedRenderException( String message, Throwable cause ) {
        super( message, cause );
    }

    public NotSupportedRenderException( Throwable cause ) {
        super( cause );
    }

    public NotSupportedRenderException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
