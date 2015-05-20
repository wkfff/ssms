/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TxByActionMethods.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord.tx;

import com.lanstar.core.ActionInvocation;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.IAtom;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * TxByActionMethods
 */
public class TxByActionMethods implements Interceptor {

    private Set<String> actionMethodSet = new HashSet<>();

    public TxByActionMethods( String... actionMethods ) {
        if ( actionMethods == null || actionMethods.length == 0 )
            throw new IllegalArgumentException( "actionMethods can not be blank." );

        for ( String actionMethod : actionMethods )
            actionMethodSet.add( actionMethod.trim() );
    }

    public void intercept( final ActionInvocation ai ) {
        Config config = Tx.getConfigWithTxConfig( ai );
        if ( config == null )
            config = DbKit.getConfig();

        if ( actionMethodSet.contains( ai.getMethodName() ) ) {
            DbPro.use( config.getName() ).tx( new IAtom() {
                public boolean run() throws SQLException {
                    ai.invoke();
                    return true;
                }
            } );
        } else {
            ai.invoke();
        }
    }
}