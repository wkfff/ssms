/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TxByActionMethods.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import com.lanstar.core.ActionInvocation;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityHolder;
import com.lanstar.identity.IdentityKit;
import com.lanstar.identity.TenantKit;
import com.lanstar.plugin.activerecord.IAtom;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TxByActionMethods implements Interceptor {
    private Set<String> actionMethodSet = new HashSet<>();

    public TxByActionMethods( String... actionMethods ) {
        if ( actionMethods == null || actionMethods.length == 0 )
            throw new IllegalArgumentException( "actionMethods can not be blank." );

        for ( String actionMethod : actionMethods )
            actionMethodSet.add( actionMethod.trim() );
    }

    @Override
    public void intercept( final ActionInvocation ai ) {
        if ( actionMethodSet.contains( ai.getMethodName() ) ) {
            IdentityKit.getIdentityHolder( ai.getController() ).runAs( new IdentityHolder.Action() {
                @Override
                public void invoke( Identity identity ) {
                    TenantKit.getTenantDb( identity.getTenant() ).tx( new IAtom() {
                        public boolean run() throws SQLException {
                            ai.invoke();
                            return true;
                        }
                    } );
                }
            } );
        } else {
            ai.invoke();
        }
    }
}
