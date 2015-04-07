/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RowMapperResultSetExtractor.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import com.lanstar.common.helper.Asserts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {
    private final RowMapper<T> rowMapper;
    private final int rowsExpected;

    public RowMapperResultSetExtractor( RowMapper<T> rowMapper ) {
        this(rowMapper, 0);
    }

    public RowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected){
        Asserts.notNull( rowMapper, "RowMapper是必须的" );
        this.rowMapper = rowMapper;
        this.rowsExpected = rowsExpected;
    }

    @Override
    public List<T> extractData( ResultSet rs ) throws SQLException {
        List<T> results = (this.rowsExpected > 0 ? new ArrayList<T>(this.rowsExpected) : new ArrayList<T>());
        int rowNum = 0;
        while (rs.next()) {
            results.add(this.rowMapper.mapRow(rs, rowNum++));
        }
        return results;
    }
}

