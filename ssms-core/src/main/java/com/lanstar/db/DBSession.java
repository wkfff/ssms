/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DBSession.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import com.lanstar.common.log.LogHelper;
import com.lanstar.db.dialect.IDialect;

import java.sql.*;
import java.util.List;

public class DBSession {
    protected final Connection conn;
    private final IDialect dialect;

    DBSession( Connection conn, IDialect dialect ) {
        this.conn = conn;
        this.dialect = dialect;
    }

    public IDialect getDialect() {
        return dialect;
    }

    /**
     * 开始事务处理,启动的事务会引起以前有的事务的自动提交
     *
     * @throws DbException
     */
    public void beginTransaction() {
        JdbcHelper.beginTransaction( conn );
    }

    /**
     * 提交事务
     *
     * @throws DbException
     */
    public void commitTransaction() {
        JdbcHelper.commit( conn );
    }

    /**
     * 当发现有失败的事务时候自动进行回滚
     *
     * @throws DbException
     */
    public void rollbackTransaction() {
        JdbcHelper.rollback( conn );
    }

    /**
     * 执行UPDATE/DELETE/INSERT命令，并返回被影响的行数
     *
     * @param sql    要执行的SQL指令
     * @param params SQL指令参数，如果没有参数则直接使用null
     *
     * @return 被影响的记录行数目
     *
     * @throws DbException
     */
    public int execute( String sql, Object[] params ) {
        int rows = 0;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement( sql );
            fillStatement( stmt, params );
            rows = stmt.executeUpdate();
        } catch ( SQLException e ) {
            String msg = "[";
            if ( params != null ) for ( Object o : params ) {
                msg += o.toString() + ",";
            }
            msg += "]";
            throw new DbException( "执行SQL指令" + sql + ":参数=" + msg + "发生错误。\n" + e.getMessage(), e );
        } finally {
            if ( stmt != null ) try { stmt.close(); } catch ( SQLException ignored ) {}
        }

        return rows;
    }

    /**
     * 支持批处理
     *
     * @param sqls sql 批处理执行的SQL
     *
     * @return 受影响行数
     *
     * @throws DbException
     */
    public int executeBatch( List<String> sqls ) {
        try {
            final Statement ps = this.conn.createStatement();
            long count = 0L;
            for ( String sql : sqls ) {
                LogHelper.debug( DBSession.class, sql );
                ps.addBatch( sql );
                if ( count % 20000 == 0 ) {
                    ps.executeBatch();
                }
                count++;
            }
            ps.executeBatch();
            ps.close();
        } catch ( SQLException e ) {
            throw new DbException( "执行批处理错误:\n" + e.getMessage(), e );
        }
        return 1;
    }

    /**
     * 查询记录，带入行的处理类
     *
     * @param sql     SQL指令
     * @param params  参数
     * @param rower   记录行处理器
     * @param maxRows 返回最大行数，如果<1则是不设置
     *
     * @return 返回处理的行数
     *
     * @throws DbException
     */
    public int query( String sql, Object[] params, IRowAction rower, int maxRows ) throws DbException {

        ResultSet rs = null;
        PreparedStatement stmt = null;
        int i = 0;
        try {
            stmt = conn.prepareStatement( sql );
            fillStatement( stmt, params );
            if ( maxRows > 0 ) stmt.setMaxRows( maxRows ); // 设置返回的最多记录
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                try {
                    rower.process( rs, i );
                    i++;
                } catch ( Exception e ) {
                    throw new DbException( "记录行转换发生异常:" + e.getMessage(), e );
                }
            }
        } catch ( SQLException e ) {
            throw new DbException( e );
        } finally {
            try {
                if ( rs != null ) {
                    rs.close();
                }
            } catch ( Exception ignored ) {}
            try {
                if ( stmt != null ) {
                    stmt.close();
                }
            } catch ( Exception ignored ) {}
        }

        return i;
    }

    private void logSQL( String sql, Object[] params ) {
        LogHelper.debug( getClass(), "执行SQL指令:%s\n", sql );

        String msg = "[";
        if ( params != null )
            for ( Object o : params ) {
                msg += o.toString() + ",";
            }
        msg += "]";

        LogHelper.debug( getClass(), "参数: %s", msg );
    }

    public JdbcRecord query( String sql, Object[] params ) {
        JdbcRecordExtractor extractor = new JdbcRecordExtractor();
        query( sql, params, extractor, 1 );
        return extractor.record;
    }

    public JdbcRecordSet queryList( DBSession session, String sql, Object[] params ) {
        JdbcRecordSetExtractor extractor = new JdbcRecordSetExtractor();
        query( sql, params, extractor, 1 );
        return extractor.set;
    }

    /**
     * 自动计算当前SQL的记录数
     *
     * @param sql 执行的SQL
     *
     * @return 结果集数量
     *
     * @throws DbException
     */
    public int getRecordsetSize( String sql, Object[] params ) throws DbException {
        final int[] val = { 0 };
        query( "select count(*) from (" + sql + ")", params, new IRowAction() {
            public void process( ResultSet rs, int index ) throws Exception {
                val[0] = rs.getInt( 1 );
            }
        }, 1 );
        return val[0];
    }

    /**
     * 自动用参数数组填充SQL命令
     */
    private void fillStatement( PreparedStatement stmt, Object[] params ) throws SQLException {
        if ( params == null ) return;
        for ( int i = 0; i < params.length; i++ ) {
            if ( params[i] != null ) stmt.setObject( i + 1, params[i] );
            else stmt.setNull( i + 1, Types.VARCHAR );
        }
    }
}
