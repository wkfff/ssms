/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：LogHelper.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 日志辅助类 */
public final class LogHelper {

    public static void debug(Class<?> type, String s, Object... params) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.debug((params == null || params.length == 0) ? s : String.format(s, params));
    }

    public static void debug(Class<?> type, Throwable throwable, String s, Object... params) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.debug((params == null || params.length == 0) ? s : String.format(s, params), throwable);
    }

    public static void info(Class<?> type, String s, Object... params) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.info((params == null || params.length == 0) ? s : String.format(s, params));
    }

    public static void info(Class<?> type, Throwable throwable, String s, Object... params) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.info((params == null || params.length == 0) ? s : String.format(s, params), throwable);
    }

    public static void warn(Class<?> type, String s, Object... aobj) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.warn(String.format(s, aobj));
    }

    public static void warn(Class<?> type, Throwable throwable, String s, Object... params) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.warn((params == null || params.length == 0) ? s : String.format(s, params), throwable);
    }

    public static void error(Class<?> type, String s, Object... params) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.error((params == null || params.length == 0) ? s : String.format(s, params));
    }

    public static void error(Class<?> type, Throwable throwable, String s, Object... params) {
        Logger logger = LoggerFactory.getLogger(type);
        logger.error(String.format(s, params), throwable);
    }
}
