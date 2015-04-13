/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandleException.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.common.exception.WebException;

public class HandleException extends WebException {
    private static final long serialVersionUID = -1278507682263851953L;

    public HandleException() {
    }

    public HandleException( String message ) {
        super( message );
    }

    public HandleException( String message, Throwable cause ) {
        super( message, cause );
    }

    public HandleException( Throwable cause ) {
        super( cause );
    }

    public HandleException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }

    private int errorCode = -1;
    private Object attachObject;

    public int getErrorCode() {
        return errorCode;
    }

    public HandleException errorCode( int errorCode ) {
        this.errorCode = errorCode;
        return this;
    }

    public Object getAttachObject() {
        return attachObject;
    }

    public HandleException attachObject( Object attachObject ) {
        this.attachObject = attachObject;
        return this;
    }
}
