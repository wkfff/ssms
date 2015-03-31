/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Logger.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.common.log;

import org.slf4j.LoggerFactory;

/**
 * 日志组件
 */
public class Logger {
    /** 抽取自SLF4J的日志 */
    private final org.slf4j.Logger logger;

    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }

    public Logger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public String getName() {
        return logger.getName();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String s, Object... params) {
        logger.debug((params == null || params.length == 0) ? s : String.format(s, params));
    }

    public void debug(Throwable throwable, String s, Object... params) {
        logger.debug((params == null || params.length == 0) ? s : String.format(s, params), throwable);
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public void info(String s, Object... params) {
        logger.info((params == null || params.length == 0) ? s : String.format(s, params));
    }

    public void info(Throwable throwable, String s, Object... params) {
        logger.info((params == null || params.length == 0) ? s : String.format(s, params), throwable);
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public void warn(String s, Object... aobj) {
        logger.warn(String.format(s, aobj));
    }

    public void warn(Throwable throwable, String s, Object... params) {
        logger.warn((params == null || params.length == 0) ? s : String.format(s, params), throwable);
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public void error(String s, Object... params) {
        logger.error((params == null || params.length == 0) ? s : String.format(s, params));
    }

    public void error(Throwable throwable, String s, Object... params) {
        logger.error(String.format(s, params), throwable);
    }
}
