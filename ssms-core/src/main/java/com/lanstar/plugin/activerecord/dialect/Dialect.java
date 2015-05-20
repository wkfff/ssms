/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Dialect.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord.dialect;

import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Dialect {
    public abstract String forTableBuilderDoBuild( String tableName );

    public abstract void forModelSave( Table table, Map<String, Object> attrs, StringBuilder sql, List<Object> paras );

    public abstract String forModelDeleteById( Table table );

    public abstract void forModelUpdate( Table table, Map<String, Object> attrs, Set<String> modifyFlag, String pKey, Object id, StringBuilder sql, List<Object> paras );

    public abstract String forModelFindById( Table table, String columns );

    public abstract String forDbFindById( String tableName, String primaryKey, String columns );

    public abstract String forDbDeleteById( String tableName, String primaryKey );

    public abstract void forDbSave( StringBuilder sql, List<Object> paras, String tableName, Record record );

    public abstract void forDbUpdate( String tableName, String primaryKey, Object id, Record record, StringBuilder sql, List<Object> paras );

    public abstract void forPaginate( StringBuilder sql, int pageNumber, int pageSize, String select, String sqlExceptSelect );

    public boolean isOracle() {
        return false;
    }

    public boolean isTakeOverDbPaginate() {
        return false;
    }

    public Page<Record> takeOverDbPaginate( Connection conn, int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) throws SQLException {
        throw new RuntimeException( "You should implements this method in " + getClass().getName() );
    }

    public boolean isTakeOverModelPaginate() {
        return false;
    }

    @SuppressWarnings("rawtypes")
    public Page takeOverModelPaginate( Connection conn, Class<? extends Model> modelClass, int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) throws Exception {
        throw new RuntimeException( "You should implements this method in " + getClass().getName() );
    }

    public void fillStatement( PreparedStatement pst, List<Object> paras ) throws SQLException {
        for ( int i = 0, size = paras.size(); i < size; i++ ) {
            pst.setObject( i + 1, paras.get( i ) );
        }
    }

    public void fillStatement( PreparedStatement pst, Object... paras ) throws SQLException {
        for ( int i = 0; i < paras.length; i++ ) {
            pst.setObject( i + 1, paras[i] );
        }
    }

    public String getDefaultPrimaryKey() {
        return "id";
    }
}