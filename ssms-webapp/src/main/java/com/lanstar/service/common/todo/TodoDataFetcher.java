/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoDataList.java
 * 创建时间：2015-07-15
 * 创建用户：张铮彬
 */

package com.lanstar.service.common.todo;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.lanstar.identity.Tenant;
import com.lanstar.model.Todo;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import java.util.ArrayList;
import java.util.List;

public class TodoDataFetcher {
    private static final Function<Todo, TodoData> FUNCTION = new Function<Todo, TodoData>() {
        @Override
        public TodoData apply( Todo input ) {
            return TodoData.with( input );
        }
    };
    private Tenant tenant;
    private TodoType signature;
    private Integer professionId;
    private Integer templateId;

    private TodoDataFetcher() {
    }

    public static TodoDataFetcher with( Tenant tenant, TodoType signature ) {
        TodoDataFetcher list = new TodoDataFetcher();

        list.tenant = tenant;
        list.signature = signature;

        return list;
    }

    public TodoDataFetcher withProfessionId( int professionId ) {
        this.professionId = professionId;
        return this;
    }

    public TodoDataFetcher withTemplateId( int templateId ) {
        this.templateId = templateId;
        return this;
    }

    public TodoData[] fetch() {return fetch( null );}

    public TodoData[] fetch( Integer size ) {
        SqlStatement sql = buildSql( SQL.SELECT( "*" ) )
                .LIMIT()._If( size > 0, size )
                .toSqlStatement();
        List<Todo> list = Todo.dao.find( sql.getSql(), sql.getParams() );
        return Iterables.toArray( Lists.transform( list, FUNCTION ), TodoData.class );
    }

    public Page<TodoData> paginate( int pageNumber, int pageSize ) {
        SqlStatement sql = buildSql( new SqlBuilder() ).toSqlStatement();

        Page<Todo> page = Todo.dao.paginate( pageNumber, pageSize, "SELECT *", sql.getSql(), sql.getParams() );
        ArrayList<TodoData> list = Lists.newArrayList( Lists.transform( page.getList(), FUNCTION ) );

        return new Page<>( list, page.getPageNumber(), page.getPageSize(), page.getTotalPage(), page.getTotalRow() );
    }

    private SqlBuilder buildSql( SqlBuilder sqlBuilder ) {
        return sqlBuilder.FROM( "SYS_TODO" )
                         .WHERE( "C_CONTROL=?", signature )
                         ._( "R_TENANT=? AND P_TENANT=?", tenant.getTenantId(), tenant.getTenantType().getName() )
                         ._If( professionId != null, "P_PROFESSION=?", professionId )
                         ._If( templateId != null, "R_TEMPLATE=?", templateId )
                         .ORDER_BY( "T_CREATE DESC" );
    }
}
