/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Tx.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord.tx;

import com.lanstar.core.ActionInvocation;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.plugin.activerecord.ActiveRecordException;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.NestedTransactionHelpException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ActiveRecord declare transaction.
 * Example: @Before(Tx.class)
 */
public class Tx implements Interceptor {

    static Config getConfigWithTxConfig( ActionInvocation ai ) {
        TxConfig txConfig = ai.getMethod().getAnnotation( TxConfig.class );
        if ( txConfig == null )
            txConfig = ai.getController().getClass().getAnnotation( TxConfig.class );

        if ( txConfig != null ) {
            Config config = DbKit.getConfig( txConfig.value() );
            if ( config == null )
                throw new RuntimeException( "Config not found with TxConfig" );
            return config;
        }
        return null;
    }

    protected int getTransactionLevel( Config config ) {
        return config.getTransactionLevel();
    }

    public void intercept( ActionInvocation ai ) {
        Config config = getConfigWithTxConfig( ai );
        if ( config == null )
            config = DbKit.getConfig();

        Connection conn = config.getThreadLocalConnection();
        if ( conn != null ) {    // Nested transaction support
            try {
                if ( conn.getTransactionIsolation() < getTransactionLevel( config ) )
                    conn.setTransactionIsolation( getTransactionLevel( config ) );
                ai.invoke();
                return;
            } catch ( SQLException e ) {
                throw new ActiveRecordException( e );
            }
        }

        Boolean autoCommit = null;
        try {
            conn = config.getConnection();
            autoCommit = conn.getAutoCommit();
            config.setThreadLocalConnection( conn );
            conn.setTransactionIsolation( getTransactionLevel( config ) );    // conn.setTransactionIsolation(transactionLevel);
            conn.setAutoCommit( false );
            ai.invoke();
            conn.commit();
        } catch ( NestedTransactionHelpException e ) {
            if ( conn != null ) try {conn.rollback();} catch ( Exception e1 ) {e1.printStackTrace();}
        } catch ( Throwable t ) {
            if ( conn != null ) try {conn.rollback();} catch ( Exception e1 ) {e1.printStackTrace();}
            throw new ActiveRecordException( t );
        } finally {
            try {
                if ( conn != null ) {
                    if ( autoCommit != null )
                        conn.setAutoCommit( autoCommit );
                    conn.close();
                }
            } catch ( Throwable t ) {
                t.printStackTrace();    // can not throw exception here, otherwise the more important exception in previous catch block can not be thrown
            } finally {
                config.removeThreadLocalConnection();    // prevent memory leak
            }
        }
    }
}