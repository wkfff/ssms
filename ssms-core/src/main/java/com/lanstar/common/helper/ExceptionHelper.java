/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ExceptionHelper.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.common.helper;

/**
 * Zgray创建于2015/2/15.
 */
public class ExceptionHelper {
    /**
     * 将异常转化为UNCHECKED类型的RuntimeException
     */
    public static RuntimeException runtimeException(Exception e) {
        return new RuntimeException(e);
    }

    /**
     * 将异常转换RuntimeException
     */
    public static RuntimeException runtimeException(Exception e, String msg, Object... params) {
        return new RuntimeException(String.format(msg, params), e);
    }

    /**
     * 将异常转换RuntimeException
     */
    public static RuntimeException runtimeException(String msg, Object... params) {
        return new RuntimeException(String.format(msg, params));
    }
}
