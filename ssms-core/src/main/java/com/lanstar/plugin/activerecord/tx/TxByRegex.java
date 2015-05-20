/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TxByRegex.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord.tx;

import com.lanstar.common.kit.StrKit;
import com.lanstar.core.ActionInvocation;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.IAtom;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * TxByRegex.
 */
public class TxByRegex implements Interceptor {

    private Pattern pattern;

    public TxByRegex( String regex ) {
        this( regex, true );
    }

    public TxByRegex( String regex, boolean caseSensitive ) {
        if ( StrKit.isBlank( regex ) )
            throw new IllegalArgumentException( "regex can not be blank." );

        pattern = caseSensitive ? Pattern.compile( regex ) : Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
    }

    public void intercept( final ActionInvocation ai ) {
        Config config = Tx.getConfigWithTxConfig( ai );
        if ( config == null )
            config = DbKit.getConfig();

        if ( pattern.matcher( ai.getActionKey() ).matches() ) {
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