/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Asserts.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */
package com.lanstar.common.helper;

import java.util.Collection;

public class Asserts{
    /**
     * 检查字符串是否为空，为空则抛出IllegalArgumentException异常
     *
     * @param text    字符串
     * @param message 提示信息，只支持%s来替换参数
     * @param params  提示信息的参数
     */
    public static void notBlank( String text, String message, Object... params ){
        if( StringHelper.isBlank( text ) ) throw new IllegalArgumentException( StringHelper.format( message, params ) );
    }

    /**
     * 字符串不允许为NULL或长度0
     *
     * @param text    目标字符串
     * @param message 提示信息，只支持%s来替换参数
     * @param params  提示信息的参数
     */
    public static void notEmpty( String text, String message, Object... params ){
        if( text == null || text.length() == 0 ) throw new IllegalArgumentException( StringHelper.format( message, params ) );
    }

    public static void notEmpty( Collection<?> collection, String message, Object... params ){
        if( collection == null || collection.isEmpty() ) throw new IllegalArgumentException( StringHelper.format( message, params ) );
    }

    /**
     * 对象不允许为空
     *
     * @param object  目标对象
     * @param message 提示信息，只支持%s来替换参数
     * @param params  提示信息的参数
     */
    public static void notNull( Object object, String message, Object... params ){
        if( object == null ) throw new IllegalArgumentException( StringHelper.format( message, params ) );
    }

    /**
     * 条件为TRUE时候触发异常
     *
     * @param flag    TRUE = 触发,FALSE = 不触发
     * @param message 提示信息，只支持%s来替换参数
     * @param params  提示信息的参数
     */
    public static void check( boolean flag, String message, Object... params ){
        if( flag ) throw new IllegalArgumentException( StringHelper.format( message, params ) );
    }

    /**
     * 判断数组下标是否超出了边界
     *
     * @param array   目标数组
     * @param index   目标下标
     * @param message 提示信息
     * @param params  提示信息的参数
     */
    public static void outOfBounds( Object[] array, Integer index, String message, Object... params ){
        if( index == null || index <= 0 || index >= array.length ){
            throw new ArrayIndexOutOfBoundsException( StringHelper.format( message, params ) );
        }
    }

    /**
     * 抛出错误的BO对象的命令错误异常
     */
    public static void illegalCommand( String boId, String cmdId ){
        throw new IllegalArgumentException( "错误的业务对象[" + boId + "]命令名[" + cmdId + "]" );
    }
}
