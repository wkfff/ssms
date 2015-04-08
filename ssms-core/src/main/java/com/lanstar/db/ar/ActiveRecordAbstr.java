/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActiveRecordAbstr.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */
package com.lanstar.db.ar;

import com.lanstar.db.DBSession;
import com.lanstar.db.JdbcOperations;
import com.lanstar.db.dialect.IDialect;

import java.util.Collection;

abstract class ActiveRecordAbstr {
    /**
     * SQL的空参数
     */
    public static final Object[] EMPTY_PARA = new Object[] {};
    final JdbcOperations session;
    final IDialect dialect;

    ActiveRecordAbstr( DBSession session ) {
        this.session = session;
        this.dialect = session.getDialect();
    }

    String trimString( String text ) {
        return (text == null || text.length() == 0) ? null : text.trim();
    }

    protected Object[] toParams( Object[] ps ) {
        if ( ps == null || ps.length == 0 ) return EMPTY_PARA;
        if ( ps.length == 1 && ps[0] != null ) {
            if ( ps[0] instanceof Object[] ) return (Object[]) ps[0];
            else if ( ps[0] instanceof Collection ) {
                Collection cl = (Collection) ps[0];
                Object[] ts = new Object[cl.size()];
                int i = 0;
                for ( Object o : ts ) ts[i++] = o;
                return ts;
            }
        }
        return ps;
    }
}
