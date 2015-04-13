/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionException.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.lanstar.core.handle.HandleException;

public class ActionException extends HandleException {
    private static final long serialVersionUID = -8375579557379901010L;

    public ActionException() {
    }

    public ActionException( String message ) {
        super( message );
    }

    public ActionException( String message, Throwable cause ) {
        super( message, cause );
    }

    public ActionException( Throwable cause ) {
        super( cause );
    }

    public ActionException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
