/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DBSession.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import com.lanstar.common.helper.Asserts;
import com.lanstar.common.log.LogHelper;
import com.lanstar.common.log.Logger;
import com.lanstar.db.dialect.IDialect;
import com.lanstar.db.statement.SqlStatement;

import java.sql.*;
import java.util.List;

public class DBSession implements JdbcOperations {
    protected final Connection conn;
    private final IDialect dialect;
    private static final Logger log = new Logger( DBSession.class );

    DBSession( Connection conn, IDialect dialect ) {
        this.conn = conn;
        this.dialect = dialect;
    }

    public IDialect getDialect() {
        return dialect;
    }

    /**
     * 开始事务处理,启动的事务会引起以前有的事务的自动提交
     */
    public void beginTransaction() {
        try {
            JdbcHelper.beginTransaction( conn );
        } catch ( SQLException e ) {
            throw new DbException( "开启事务时发生异常", e );
        }
    }

    /**
     * 关闭事务。（先提交，如果失败就回滚，然后关闭事务）
     */
    public void endTransaction() {
        try {
            JdbcHelper.endTransaction( conn );
        } catch ( SQLException e ) {
            throw new DbException( "关闭事务时发生异常", e );
        }
    }

    /**
     * 提交事务
     */
    public void commitTransaction() {
        try {
            JdbcHelper.commit( conn );
        } catch ( SQLException e ) {
            throw new DbException( "提交事务时发生异常", e );
        }
    }

    /**
     * 当发现有失败的事务时候自动进行回滚
     */
    public void rollbackTransaction() {
        try {
            JdbcHelper.rollback( conn );
        } catch ( SQLException e ) {
            throw new DbException( "回滚事务时发生异常", e );
        }
    }

    @Override
    public <T> T execute( SqlStatement sqlStatement, PreparedStatementCallback<T> callback ) throws SQLException {
        Asserts.notNull( sqlStatement, "必须提供SQL片段" );
        Asserts.notNull( callback, "回调必须提供" );
        log.debug( "执行SQL:%s", sqlStatement );
        PreparedStatement preparedStatement = conn.prepareStatement( sqlStatement.getSql() );
        fillStatement( preparedStatement, sqlStatement.getParams() );
        try {
            return callback.doInPreparedStatement( preparedStatement );
        } catch ( SQLException e ) {
            rollbackTransaction();
            JdbcHelper.close( preparedStatement );
            throw e;
        }
    }

    @Override
    public int execute( SqlStatement sqlStatement ) {
        try {
            return execute( sqlStatement, new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement( PreparedStatement preparedStatement ) throws SQLException {
                    return preparedStatement.executeUpdate();
                }
            } );
        } catch ( SQLException e ) {
            throw new DbException( "执行SQL语句的时候发生了异常", e ).setSqlStatement( sqlStatement );
        }
    }

    @Override
    public <T> T query( SqlStatement sqlStatement, final ResultSetExtractor<T> extractor, final int maxRows ) {
        try {
            return execute( sqlStatement, new PreparedStatementCallback<T>() {
                @Override
                public T doInPreparedStatement( PreparedStatement preparedStatement ) throws SQLException {
                    if ( maxRows > 0 ) preparedStatement.setMaxRows( maxRows );
                    ResultSet resultSet = preparedStatement.executeQuery();
                    return extractor.extractData( resultSet );
                }
            } );
        } catch ( SQLException e ) {
            throw new DbException( "执行SQL查询的时候发生了异常", e ).setSqlStatement( sqlStatement );
        }
    }

    @Override
    public <T> T query( SqlStatement sqlStatement, final ResultSetExtractor<T> extractor ) {
        return query( sqlStatement, extractor, -1 );
    }

    @Override
    public <T> T first( SqlStatement sqlStatement, final ResultSetExtractor<T> extractor ) {
        return query( sqlStatement, extractor, 1 );
    }

    @Override
    public <T> List<T> query( SqlStatement sqlStatement, final RowMapper<T> rowMapper ) {
        return query( sqlStatement, new RowMapperResultSetExtractor<>( rowMapper ) );
    }

    @Override
    public <T> T first( SqlStatement sqlStatement, final RowMapper<T> rowMapper ) {
        return first( sqlStatement, new ResultSetExtractor<T>() {
            @Override
            public T extractData( ResultSet resultSet ) throws SQLException {
                if ( resultSet.next() ) return rowMapper.mapRow( resultSet, 1 );
                return null;
            }
        } );
    }

    @Override
    public void query( SqlStatement sqlStatement, final IRowAction rowAction ) {
        query( sqlStatement, new ResultSetExtractor<Object>() {
            @Override
            public Object extractData( ResultSet resultSet ) throws SQLException {
                int rowNum = 0;
                while ( resultSet.next() ) {
                    rowAction.process( resultSet, rowNum++ );
                }
                return null;
            }
        } );
    }

    @Override
    public JdbcRecordSet query( SqlStatement sqlStatement ) {
        final JdbcRecordRowMapper rowMapper = new JdbcRecordRowMapper();
        return query( sqlStatement, new ResultSetExtractor<JdbcRecordSet>() {
            @Override
            public JdbcRecordSet extractData( ResultSet resultSet ) throws SQLException {
                JdbcRecordSet jdbcRecords = new JdbcRecordSet();
                int rowNum = 0;
                while ( resultSet.next() ) {
                    jdbcRecords.add( rowMapper.mapRow( resultSet, rowNum++ ) );
                }
                return jdbcRecords;
            }
        } );
    }

    @Override
    public JdbcRecord first( SqlStatement sqlStatement ) {
        return first( sqlStatement, new JdbcRecordRowMapper() );
    }

    @Override
    public JdbcRecordSet query( String sql, Object[] params ) {
        return query( new SqlStatement( sql, params ) );
    }

    @Override
    public JdbcRecord first( String sql, Object[] params ) {
        return first( new SqlStatement( sql, params ), new JdbcRecordRowMapper() );
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
    @Override
    public int execute( String sql, Object[] params ) {
        return execute( new SqlStatement( sql, params ) );
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
    @Override
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
     * 自动计算当前SQL的记录数
     *
     * @param sql 执行的SQL
     *
     * @return 结果集数量
     *
     * @throws DbException
     */
    @Override
    public int getRecordsetSize( String sql, Object[] params ) {
        return first( new SqlStatement( "select count(*) from (" + sql + ") T", params ), new RowMapper<Integer>() {
            @Override
            public Integer mapRow( ResultSet rs, int rowNum ) throws SQLException {
                return rs.getInt( 1 );
            }
        } );
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

    public boolean isValid() {
        return JdbcHelper.isConnectionValid( conn );
    }
}

