/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：WebException.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.common.exception;

/**
 * Web异常基类。
 * NOTE: 项目中所有自定义异常均继承自该类
 */
public class WebException extends RuntimeException {
    private static final long serialVersionUID = 8242841032365446427L;

    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
