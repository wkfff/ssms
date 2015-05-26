/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdkLogger.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.common.log;

import java.util.logging.Level;

/**
 * JdkLogger.
 */
public class JdkLogger extends Logger {

    private java.util.logging.Logger log;
    private String clazzName;

    JdkLogger( Class<?> clazz ) {
        log = java.util.logging.Logger.getLogger( clazz.getName() );
        clazzName = clazz.getName();
    }

    JdkLogger( String name ) {
        log = java.util.logging.Logger.getLogger( name );
        clazzName = name;
    }

    @Override
    public void info( String format, Object... arguments ) {
        log.logp( Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), format, arguments );
    }

    @Override
    public void info( Throwable t, String format, Object... arguments ) {
        log.logp( Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(),
                arguments != null && arguments.length > 0 ? String.format( format, arguments ) : format, t );
    }

    public void debug( String message ) {
        log.logp( Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message );
    }

    public void debug( String message, Throwable t ) {
        log.logp( Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t );
    }

    public void info( String message ) {
        log.logp( Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message );
    }

    public void info( String message, Throwable t ) {
        log.logp( Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t );
    }

    @Override
    public void debug( String format, Object... arguments ) {
        log.logp( Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), format, arguments );
    }

    @Override
    public void debug( Throwable t, String format, Object... arguments ) {
        log.logp( Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(),
                arguments != null && arguments.length > 0 ? String.format( format, arguments ) : format, t );
    }

    public void warn( String message ) {
        log.logp( Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message );
    }

    public void warn( String message, Throwable t ) {
        log.logp( Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t );
    }

    @Override
    public void warn( String format, Object... arguments ) {
        log.logp( Level.WARNING, clazzName, Thread.currentThread()
                                                  .getStackTrace()[1].getMethodName(), format, arguments );
    }

    @Override
    public void warn( Throwable t, String format, Object... arguments ) {
        log.logp( Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(),
                arguments != null && arguments.length > 0 ? String.format( format, arguments ) : format, t );
    }

    public void error( String message ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message );
    }

    public void error( String message, Throwable t ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t );
    }

    @Override
    public void error( String format, Object... arguments ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread()
                                                 .getStackTrace()[1].getMethodName(), format, arguments );
    }

    @Override
    public void error( Throwable t, String format, Object... arguments ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(),
                arguments != null && arguments.length > 0 ? String.format( format, arguments ) : format, t );
    }

    /**
     * JdkLogger fatal is the same as the error.
     */
    public void fatal( String message ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message );
    }

    /**
     * JdkLogger fatal is the same as the error.
     */
    public void fatal( String message, Throwable t ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t );
    }

    @Override
    public void fatal( String format, Object... arguments ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread()
                                                 .getStackTrace()[1].getMethodName(), format, arguments );
    }

    @Override
    public void fatal( Throwable t, String format, Object... arguments ) {
        log.logp( Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(),
                arguments != null && arguments.length > 0 ? String.format( format, arguments ) : format, t );
    }

    public boolean isDebugEnabled() {
        return log.isLoggable( Level.FINE );
    }

    public boolean isInfoEnabled() {
        return log.isLoggable( Level.INFO );
    }

    public boolean isWarnEnabled() {
        return log.isLoggable( Level.WARNING );
    }

    public boolean isErrorEnabled() {
        return log.isLoggable( Level.SEVERE );
    }

    public boolean isFatalEnabled() {
        return log.isLoggable( Level.SEVERE );
    }
}



