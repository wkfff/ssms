/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Log4jLogger.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.common.log;

import org.slf4j.LoggerFactory;

/**
 * slf4j Logger.
 */
public class LogbackLogger extends Logger {

    private org.slf4j.Logger log;

    LogbackLogger( Class<?> clazz ) {
        log = LoggerFactory.getLogger( clazz );
    }

    LogbackLogger( String name ) {
        log = LoggerFactory.getLogger( name );
    }

    @Override
    public void info( String message ) {
        log.info( message );
    }

    @Override
    public void info( String message, Throwable t ) {
        log.info( message, t );
    }

    @Override
    public void info( String format, Object... arguments ) {log.info( format, arguments );}

    @Override
    public void debug( String message ) {
        log.debug( message );
    }

    @Override
    public void debug( String message, Throwable t ) {
        log.debug( message, t );
    }

    @Override
    public void debug( String format, Object... arguments ) {log.debug( format, arguments );}

    @Override
    public void warn( String message ) {
        log.warn( message );
    }

    @Override
    public void warn( String message, Throwable t ) {
        log.warn( message, t );
    }

    @Override
    public void warn( String format, Object... arguments ) {log.warn( format, arguments );}

    @Override
    public void error( String message ) {
        log.error( message );
    }

    @Override
    public void error( String message, Throwable t ) {
        log.error( message, t );
    }

    @Override
    public void error( String format, Object... arguments ) {log.error( format, arguments );}

    @Override
    public void fatal( String message ) {
        log.error( message );
    }

    @Override
    public void fatal( String message, Throwable t ) {
        log.error( message, t );
    }

    @Override
    public void fatal( String format, Object... arguments ) {log.error( format, arguments );}

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return log.isErrorEnabled();
    }
}

