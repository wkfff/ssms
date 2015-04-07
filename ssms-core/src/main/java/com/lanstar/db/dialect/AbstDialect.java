/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AbstDialect.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db.dialect;

import com.lanstar.db.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstDialect implements IDialect {
    @Override
    public JdbcRecord query( DBSession session, String sql, Object[] params ) throws SQLException {
        return session.query( sql, params );
    }

    @Override
    public JdbcRecordSet queryList( DBSession session, String sql, Object[] params ) throws SQLException {
        return session.queryList( session, sql, params );
    }

    @Override
    public void queryList( DBSession session, String sql, Object[] params, final IRowCallback rowcb ) throws SQLException {
        session.query( sql, params, new IRowAction() {
            @Override
            public void process( ResultSet rs, int i ) throws Exception {
                rowcb.execute( rs, i );
            }
        }, JdbcHelper.MAX_ROW );
    }

    @Override
    public int executeUpdate( DBSession session, String sql, Object[] params ) throws SQLException {
         return session.execute( sql, params );
    }
}
