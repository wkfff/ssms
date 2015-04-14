/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AbstDialect.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db.dialect;

import com.lanstar.db.*;
import com.lanstar.db.statement.SqlStatement;

import java.sql.SQLException;

public abstract class AbstDialect implements IDialect {
    @Override
    public JdbcRecord query( JdbcOperations operations, SqlStatement sqlStatement ) {
        return operations.first( sqlStatement );
    }

    @Override
    public JdbcRecordSet queryList( JdbcOperations operations, SqlStatement sqlStatement ) throws SQLException {
        return operations.query( sqlStatement );
    }

    @Override
    public void queryList( JdbcOperations operations, SqlStatement sqlStatement, final IRowAction rowcb ) throws SQLException {
        operations.query( sqlStatement, rowcb );
    }

    @Override
    public int executeUpdate( JdbcOperations operations, SqlStatement sqlStatement ) throws SQLException {
        return operations.execute( sqlStatement );
    }

    @Override
    public JdbcRecordSet queryPaging( JdbcOperations operations,SqlStatement sqlStatement,DBPaging paging ) throws SQLException {
        paging.setRecCount( operations.getRecordsetSize( sqlStatement.getSql(), sqlStatement.getParams()) );
        String sql = this.getPagingSql( sqlStatement.getSql(), paging.getStartIndex(), paging.getEndIndex() );
        SqlStatement stat = new SqlStatement(sql,sqlStatement.getParams());
        JdbcRecordSet rs = operations.query( stat );
        rs.setPaging( paging );
        return rs;
    }
}
