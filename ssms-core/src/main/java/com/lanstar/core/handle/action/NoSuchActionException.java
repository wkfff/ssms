/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NoSuchActionException.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.lanstar.common.helper.StringHelper;

public class NoSuchActionException extends ActionException {
    private static final long serialVersionUID = 8034981648106503768L;

    public NoSuchActionException() {
    }

    public NoSuchActionException( String message ) {
        super( message );
    }

    public NoSuchActionException( String message, Object... args ) {
        super( StringHelper.format( message, args ) );
    }

    public NoSuchActionException( String message, Throwable cause ) {
        super( message, cause );
    }

    public NoSuchActionException( Throwable cause ) {
        super( cause );
    }

    public NoSuchActionException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
