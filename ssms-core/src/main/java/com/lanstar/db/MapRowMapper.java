/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MapRowMapper.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

public abstract class MapRowMapper<T extends Map<String, Object>> implements RowMapper<T> {
    protected abstract T createColumnMap( int columnCount );

    protected String getColumnKey( String columnName ) {
        return columnName;
    }

    protected Object getColumnValue( ResultSet rs, int index ) throws SQLException {
        return JdbcHelper.getResultSetValue( rs, index );
    }

    @Override
    public T mapRow( ResultSet rs, int rowNum ) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        T mapOfColValues = createColumnMap( columnCount );
        for ( int i = 1; i <= columnCount; i++ ) {
            String key = getColumnKey( JdbcHelper.lookupColumnName( rsmd, i ) );
            Object obj = getColumnValue( rs, i );
            mapOfColValues.put( key, obj );
        }
        return mapOfColValues;
    }
}
