/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Logger.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.common.log;

/**
 * The five logging levels used by Log are (in order):
 * 1. DEBUG (the least serious)
 * 2. INFO
 * 3. WARN
 * 4. ERROR
 * 5. FATAL (the most serious)
 */
public abstract class Logger {

    private static ILoggerFactory factory;

    static {
        init();
    }

    public static void setLoggerFactory( ILoggerFactory loggerFactory ) {
        if ( loggerFactory != null )
            Logger.factory = loggerFactory;
    }

    public static Logger getLogger( Class<?> clazz ) {
        return factory.getLogger( clazz );
    }

    public static Logger getLogger( String name ) {
        return factory.getLogger( name );
    }

    public static void init() {
        if ( factory != null )
            return;
        try {
            Class.forName( "org.slf4j.Logger" );
            Class<?> factoryClass = Class.forName( "com.lanstar.common.log.LogbackLoggerFactory" );
            factory = (ILoggerFactory) factoryClass.newInstance();    // return new Log4jLoggerFactory();
        } catch ( Exception e ) {
            factory = new JdkLoggerFactory();
        }
    }

    public abstract void info( String format, Object... arguments );

    public abstract void debug( String message );

    public abstract void debug( String message, Throwable t );

    public abstract void info( String message );

    public abstract void info( String message, Throwable t );

    public abstract void debug( String format, Object... arguments );

    public abstract void warn( String message );

    public abstract void warn( String message, Throwable t );

    public abstract void warn( String format, Object... arguments );

    public abstract void error( String message );

    public abstract void error( String message, Throwable t );

    public abstract void error( String format, Object... arguments );

    public abstract void fatal( String message );

    public abstract void fatal( String message, Throwable t );

    public abstract void fatal( String format, Object... arguments );

    public abstract boolean isDebugEnabled();

    public abstract boolean isInfoEnabled();

    public abstract boolean isWarnEnabled();

    public abstract boolean isErrorEnabled();

    public abstract boolean isFatalEnabled();
}

