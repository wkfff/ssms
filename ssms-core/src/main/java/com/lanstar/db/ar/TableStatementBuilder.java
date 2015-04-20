/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TableStatementBuilder.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db.ar;

import com.lanstar.common.helper.Asserts;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.common.log.Logger;
import com.lanstar.db.statement.SQL;
import com.lanstar.db.statement.SqlStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class TableStatementBuilder {
    private static final Logger log = Logger.getLogger( TableStatementBuilder.class );

    /**
     * 获得基于表的查询指令
     *
     * @param ar AR实体
     *
     * @return 指令s
     */
    static SqlStatement query( ARTable ar ) {
        Asserts.notEmpty( ar.table, "需要指定表/视图或SELECT子句" );
        ar.columns = StringHelper.isBlank( ar.columns ) ? "*" : ar.columns;

        return SQL.SELECT( ar.columns )
                  .FROM( ar.table )
                  .WHERE()._If( !StringHelper.isBlank( ar.where ), ar.where, ar.whereParams.toArray() )
                  .ORDER_BY()._If( !StringHelper.isBlank( ar.orderby ), ar.orderby )
                  .toSqlStatement();
    }

    /**
     * 根据表操作定义实现基于表的INERT INTO操作
     */
    static SqlStatement insert( ARTable ar ) {
        Asserts.check( StringHelper.isBlank( ar.table ) || ar.table.startsWith( "(" ), "必须指定表或视图名字，并且不支持SELECT集合" );

        StringBuilder sb = new StringBuilder( "INSERT INTO " );
        sb.append( ar.table ).append( "(" );

        StringBuilder vs = new StringBuilder();
        List<Object> params = new ArrayList<>();
        boolean first = true;
        //根据VALUES构造相关的指令
        //TODO： 识别字段是否在表内存在，识别BLOB类型字段的处理，识别关键字等
        for ( Map.Entry<String, Object> entry : ar.values.entrySet() ) {
            if ( first ) first = false;
            else {
                sb.append( "," );
                vs.append( "," );
            }
            Object v = entry.getValue();
            if ( v != null && v instanceof String && ((String) v).startsWith( "@" ) ) {
                sb.append( entry.getKey() );
                vs.append( ((String) v).substring( 1 ) );
            } else {
                sb.append( entry.getKey() );
                vs.append( "?" );
                params.add( v );
            }
        }

        sb.append( ") VALUES(" ).append( vs.toString() ).append( ")" );

        return new SqlStatement( sb.toString(), params );
    }

    /**
     * 根据表操作定义实现基于表的UPDATE操作
     */
    static SqlStatement update( ARTable ar ) {
        Asserts.check( StringHelper.isBlank( ar.table ) || ar.table.startsWith( "(" ), "必须指定表或视图名字，并且不支持SELECT集合" );
        StringBuilder sb = new StringBuilder( "UPDATE " );
        sb.append( ar.table ).append( " SET " );

        int whereParas = ar.whereParams == null ? 0 : ar.whereParams.size();
        int psize = ar.values.size();
        List<Object> params = new ArrayList<>( psize + whereParas );
        int i = 0;
        //根据VALUES构造相关的指令
        //TODO： 识别字段是否在表内存在，识别BLOB类型字段的处理，识别关键字等
        for ( Map.Entry<String, Object> entry : ar.values.entrySet() ) {
            params.add( entry.getValue() );
            if ( i++ > 0 ) sb.append( "," );
            sb.append( entry.getKey() ).append( "=?" );
        }
        // 处理WHERE
        if ( ar.where == null || ar.where.length() == 0 ) log.warn( "发现不带条件的UPDATE操作:" + sb.toString() );
        else {
            sb.append( " WHERE " ).append( ar.where );
            if ( ar.whereParams != null ) params.addAll( ar.whereParams );
        }

        return new SqlStatement( sb.toString(), params );
    }

    /**
     * 根据表操作定义实现基于表的UPDATE操作
     */
    static SqlStatement delete( ARTable ar ) {
        Asserts.check( StringHelper.isBlank( ar.table ) || ar.table.startsWith( "(" ), "必须指定表或视图名字，并且不支持SELECT集合" );
        StringBuilder sb = new StringBuilder( "DELETE FROM " );
        sb.append( ar.table );

        // 处理WHERE
        if ( ar.where == null || ar.where.length() == 0 ) log.warn( "发现不带条件的DELETE操作:" + sb.toString() );
        else {
            sb.append( " WHERE " ).append( ar.where );
        }

        return new SqlStatement( sb.toString(), ar.whereParams );
    }

}
