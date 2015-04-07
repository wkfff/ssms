/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RecordColumn.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */
package com.lanstar.db.dialect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class RecordColumn {
    /**
     * 字段名称
     */
    private final String name;
    /**
     * 数据类型，参见java.sql.Types
     */
    private final int type;
    /**
     * 类型名称
     */
    private final String typeName;

    private RecordColumn( ResultSetMetaData rs, int index ) throws SQLException {
        this.name = rs.getColumnName( index );
        this.type = rs.getColumnType( index );
        this.typeName = rs.getColumnTypeName( index );
    }

    /**
     * 获得列定义
     *
     * @throws SQLException
     */
    public static RecordColumn[] build( ResultSet rs ) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int colsize = rsmd.getColumnCount();

        RecordColumn[] cols = new RecordColumn[colsize];
        for ( int i = 0; i < colsize; i++ ) {
            cols[i] = new RecordColumn( rsmd, i + 1 );
        }

        return cols;
    }

    /**
     * 获得列名称
     *
     * @return 列名称（物理名）
     */
    public String getName() {
        return name;
    }

    /**
     * @return 列类型，参见java.sql.Types
     */
    public int getType() {
        return type;
    }

    /**
     * @return 列类型名称，对应数据库定义
     */
    public String getTypeName() {
        return typeName;
    }
}
