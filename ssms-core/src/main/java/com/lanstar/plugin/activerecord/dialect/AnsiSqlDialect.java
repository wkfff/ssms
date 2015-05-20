/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AnsiSqlDialect.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord.dialect;

import com.lanstar.plugin.activerecord.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AnsiSqlDialect extends Dialect {

    public String forTableBuilderDoBuild( String tableName ) {
        return "select * from " + tableName + " where 1 = 2";
    }

    public void forModelSave( Table table, Map<String, Object> attrs, StringBuilder sql, List<Object> paras ) {
        sql.append( "insert into " ).append( table.getName() ).append( "(" );
        StringBuilder temp = new StringBuilder( ") values(" );
        for ( Entry<String, Object> e : attrs.entrySet() ) {
            String colName = e.getKey();
            if ( table.hasColumnLabel( colName ) ) {
                if ( paras.size() > 0 ) {
                    sql.append( ", " );
                    temp.append( ", " );
                }
                sql.append( colName );
                temp.append( "?" );
                paras.add( e.getValue() );
            }
        }
        sql.append( temp.toString() ).append( ")" );
    }

    public String forModelDeleteById( Table table ) {
        String pKey = table.getPrimaryKey();
        StringBuilder sql = new StringBuilder( 45 );
        sql.append( "delete from " );
        sql.append( table.getName() );
        sql.append( " where " ).append( pKey ).append( " = ?" );
        return sql.toString();
    }

    public void forModelUpdate( Table table, Map<String, Object> attrs, Set<String> modifyFlag, String pKey, Object id, StringBuilder sql, List<Object> paras ) {
        sql.append( "update " ).append( table.getName() ).append( " set " );
        for ( Entry<String, Object> e : attrs.entrySet() ) {
            String colName = e.getKey();
            if ( !pKey.equalsIgnoreCase( colName ) && modifyFlag.contains( colName )
                    && table.hasColumnLabel( colName ) ) {
                if ( paras.size() > 0 )
                    sql.append( ", " );
                sql.append( colName ).append( " = ? " );
                paras.add( e.getValue() );
            }
        }
        sql.append( " where " ).append( pKey ).append( " = ?" );
        paras.add( id );
    }

    public String forModelFindById( Table table, String columns ) {
        StringBuilder sql = new StringBuilder( "select " );
        if ( columns.trim().equals( "*" ) ) {
            sql.append( columns );
        } else {
            String[] columnsArray = columns.split( "," );
            for ( int i = 0; i < columnsArray.length; i++ ) {
                if ( i > 0 )
                    sql.append( ", " );
                sql.append( columnsArray[i].trim() );
            }
        }
        sql.append( " from " );
        sql.append( table.getName() );
        sql.append( " where " ).append( table.getPrimaryKey() ).append( " = ?" );
        return sql.toString();
    }

    public String forDbFindById( String tableName, String primaryKey, String columns ) {
        StringBuilder sql = new StringBuilder( "select " );
        if ( columns.trim().equals( "*" ) ) {
            sql.append( columns );
        } else {
            String[] columnsArray = columns.split( "," );
            for ( int i = 0; i < columnsArray.length; i++ ) {
                if ( i > 0 )
                    sql.append( ", " );
                sql.append( columnsArray[i].trim() );
            }
        }
        sql.append( " from " );
        sql.append( tableName.trim() );
        sql.append( " where " ).append( primaryKey ).append( " = ?" );
        return sql.toString();
    }

    public String forDbDeleteById( String tableName, String primaryKey ) {
        StringBuilder sql = new StringBuilder( "delete from " );
        sql.append( tableName.trim() );
        sql.append( " where " ).append( primaryKey ).append( " = ?" );
        return sql.toString();
    }

    public void forDbSave( StringBuilder sql, List<Object> paras, String tableName, Record record ) {
        sql.append( "insert into " );
        sql.append( tableName.trim() ).append( "(" );
        StringBuilder temp = new StringBuilder();
        temp.append( ") values(" );

        for ( Entry<String, Object> e : record.getColumns().entrySet() ) {
            if ( paras.size() > 0 ) {
                sql.append( ", " );
                temp.append( ", " );
            }
            sql.append( e.getKey() );
            temp.append( "?" );
            paras.add( e.getValue() );
        }
        sql.append( temp.toString() ).append( ")" );
    }

    public void forDbUpdate( String tableName, String primaryKey, Object id, Record record, StringBuilder sql, List<Object> paras ) {
        sql.append( "update " ).append( tableName.trim() ).append( " set " );
        for ( Entry<String, Object> e : record.getColumns().entrySet() ) {
            String colName = e.getKey();
            if ( !primaryKey.equalsIgnoreCase( colName ) ) {
                if ( paras.size() > 0 ) {
                    sql.append( ", " );
                }
                sql.append( colName ).append( " = ? " );
                paras.add( e.getValue() );
            }
        }
        sql.append( " where " ).append( primaryKey ).append( " = ?" );
        paras.add( id );
    }

    /**
     * SELECT * FROM subject t1 WHERE (SELECT count(*) FROM subject t2 WHERE t2.id < t1.id AND t2.key = '123') > = 10 AND (SELECT count(*) FROM subject t2 WHERE t2.id < t1.id AND t2.key = '123') < 20 AND t1.key = '123'
     */
    public void forPaginate( StringBuilder sql, int pageNumber, int pageSize, String select, String sqlExceptSelect ) {
        throw new ActiveRecordException( "Your should not invoke this method because takeOverDbPaginate(...) will take over it." );
    }

    public boolean isTakeOverDbPaginate() {
        return true;
    }

    @SuppressWarnings("rawtypes")
    public Page<Record> takeOverDbPaginate( Connection conn, int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) throws SQLException {
        long totalRow = 0;
        int totalPage = 0;
        List result = CPI.query( conn, "select count(*) " + DbKit.replaceFormatSqlOrderBy( sqlExceptSelect ), paras );
        int size = result.size();
        if ( size == 1 )
            totalRow = ((Number) result.get( 0 )).longValue();
        else if ( size > 1 )
            totalRow = result.size();
        else
            return new Page<Record>( new ArrayList<Record>( 0 ), pageNumber, pageSize, 0, 0 );

        totalPage = (int) (totalRow / pageSize);
        if ( totalRow % pageSize != 0 ) {
            totalPage++;
        }

        StringBuilder sql = new StringBuilder();
        sql.append( select ).append( " " ).append( sqlExceptSelect );
        PreparedStatement pst = conn.prepareStatement( sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY );
        for ( int i = 0; i < paras.length; i++ ) {
            pst.setObject( i + 1, paras[i] );
        }
        ResultSet rs = pst.executeQuery();

        // move the cursor to the start
        int offset = pageSize * (pageNumber - 1);
        for ( int i = 0; i < offset; i++ )
            if ( !rs.next() )
                break;

        List<Record> list = buildRecord( rs, pageSize );
        if ( rs != null ) rs.close();
        if ( pst != null ) pst.close();
        return new Page<Record>( list, pageNumber, pageSize, totalPage, (int) totalRow );
    }

    private List<Record> buildRecord( ResultSet rs, int pageSize ) throws SQLException {
        List<Record> result = new ArrayList<Record>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] labelNames = new String[columnCount + 1];
        int[] types = new int[columnCount + 1];
        buildLabelNamesAndTypes( rsmd, labelNames, types );
        for ( int k = 0; k < pageSize && rs.next(); k++ ) {
            Record record = new Record();
            Map<String, Object> columns = record.getColumns();
            for ( int i = 1; i <= columnCount; i++ ) {
                Object value;
                if ( types[i] < Types.BLOB )
                    value = rs.getObject( i );
                else if ( types[i] == Types.CLOB )
                    value = ModelBuilder.handleClob( rs.getClob( i ) );
                else if ( types[i] == Types.NCLOB )
                    value = ModelBuilder.handleClob( rs.getNClob( i ) );
                else if ( types[i] == Types.BLOB )
                    value = ModelBuilder.handleBlob( rs.getBlob( i ) );
                else
                    value = rs.getObject( i );

                columns.put( labelNames[i], value );
            }
            result.add( record );
        }
        return result;
    }

    private void buildLabelNamesAndTypes( ResultSetMetaData rsmd, String[] labelNames, int[] types ) throws SQLException {
        for ( int i = 1; i < labelNames.length; i++ ) {
            labelNames[i] = rsmd.getColumnLabel( i );
            types[i] = rsmd.getColumnType( i );
        }
    }

    public boolean isTakeOverModelPaginate() {
        return true;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Page<? extends Model> takeOverModelPaginate( Connection conn, Class<? extends Model> modelClass, int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras ) throws Exception {
        long totalRow = 0;
        int totalPage = 0;
        List result = CPI.query( conn, "select count(*) " + DbKit.replaceFormatSqlOrderBy( sqlExceptSelect ), paras );
        int size = result.size();
        if ( size == 1 )
            totalRow = ((Number) result.get( 0 )).longValue();        // totalRow = (Long)result.get(0);
        else if ( size > 1 )
            totalRow = result.size();
        else
            return new Page( new ArrayList( 0 ), pageNumber, pageSize, 0, 0 );    // totalRow = 0;

        totalPage = (int) (totalRow / pageSize);
        if ( totalRow % pageSize != 0 ) {
            totalPage++;
        }

        // --------
        StringBuilder sql = new StringBuilder();
        sql.append( select ).append( " " ).append( sqlExceptSelect );
        PreparedStatement pst = conn.prepareStatement( sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY );
        for ( int i = 0; i < paras.length; i++ ) {
            pst.setObject( i + 1, paras[i] );
        }
        ResultSet rs = pst.executeQuery();

        // move the cursor to the start
        int offset = pageSize * (pageNumber - 1);
        for ( int i = 0; i < offset; i++ )
            if ( !rs.next() )
                break;

        List list = buildModel( rs, modelClass, pageSize );
        if ( rs != null ) rs.close();
        if ( pst != null ) pst.close();
        return new Page( list, pageNumber, pageSize, totalPage, (int) totalRow );
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final <T> List<T> buildModel( ResultSet rs, Class<? extends Model> modelClass, int pageSize ) throws SQLException, InstantiationException, IllegalAccessException {
        List<T> result = new ArrayList<T>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] labelNames = new String[columnCount + 1];
        int[] types = new int[columnCount + 1];
        buildLabelNamesAndTypes( rsmd, labelNames, types );
        for ( int k = 0; k < pageSize && rs.next(); k++ ) {
            Model<?> ar = modelClass.newInstance();
            Map<String, Object> attrs = CPI.getAttrs( ar );
            for ( int i = 1; i <= columnCount; i++ ) {
                Object value;
                if ( types[i] < Types.BLOB )
                    value = rs.getObject( i );
                else if ( types[i] == Types.CLOB )
                    value = ModelBuilder.handleClob( rs.getClob( i ) );
                else if ( types[i] == Types.NCLOB )
                    value = ModelBuilder.handleClob( rs.getNClob( i ) );
                else if ( types[i] == Types.BLOB )
                    value = ModelBuilder.handleBlob( rs.getBlob( i ) );
                else
                    value = rs.getObject( i );

                attrs.put( labelNames[i], value );
            }
            result.add( (T) ar );
        }
        return result;
    }
}