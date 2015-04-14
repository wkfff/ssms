/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ARTable.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db.ar;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.db.DBPaging;
import com.lanstar.db.DBSession;
import com.lanstar.db.IRowAction;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.statement.SqlStatement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ARTable extends ActiveRecordAbstr {
    String table;
    String columns;
    String where;
    Object[] whereParams;
    String orderby;
    final Map<String, Object> values = new HashMap<>();

    public ARTable( DBSession session ) {
        super( session );
    }

    /**
     * 设置表名
     *
     * @param table 表/视图名称，如果是SQL，则要求自动增加()，例如：(SELECT * FROM SYS_USER)
     *
     * @return 当前实例
     */
    public ARTable table( String table ) {
        this.table = trimString( table );
        if ( this.table.toUpperCase().startsWith( "SELECT " ) ) this.table = "(" + this.table + ")";
        return this;
    }

    /**
     * 设置INSERT的值
     *
     * @param values 值，MAP.Entry[字段名,值]
     *
     * @return 当前实例
     */
    public ARTable values( Map<String, Object> values ) {
        this.values.putAll( values );
        return this;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    /**
     * 增加INSERT中的字段-值
     *
     * @param fld   字段名
     * @param value 值
     *
     * @return 当前实例
     */
    public ARTable value( String fld, Object value ) {
        this.values.put( fld, value );
        return this;
    }

    /**
     * 设置WHERE条件，必须构造好的字符串，符合SQL语法（不包含WHERE文字）
     *
     * @param where WHERE 子句，不包含“WHERE”
     *
     * @return 当前对象
     */
    public ARTable where( String where ) {
        this.where = trimString( where );
        this.whereParams = null;
        return this;
    }

    /**
     * 设置WHERE条件，必须构造好的字符串，符合SQL语法（不包含WHERE文字）
     *
     * @param where WHERE 子句，不包含“WHERE”
     * @param ps    WHERE子句中的参数（？对应参数值）
     *
     * @return 当前对象
     */
    public ARTable where( String where, Object... ps ) {
        this.where = trimString( where );
        this.whereParams = ps;
        return this;
    }

    /**
     * 根据condition计算的结果设置WHERE条件，必须构造好的字符串，符合SQL语法（不包含WHERE文字）
     *
     * @param where WHERE 子句，不包含“WHERE”
     * @param ps    WHERE子句中的参数（？对应参数值）
     *
     * @return 当前对象
     */
    public ARTable where( boolean condition, String where, Object... ps ) {
        if ( condition ) where( where, ps );
        return this;
    }

    /**
     * 设置ORDER BY子句
     *
     * @param orderby ORDER BY 子句，不包含“ORDER BY”
     *
     * @return 当前对象
     */
    public ARTable orderby( String orderby ) {
        this.orderby = trimString( orderby );
        return this;
    }

    /**
     * 设置选中列子句，如果不设置或为空则默认为“*”
     *
     * @param columns 被选列，要求严格按照SQL语法
     *
     * @return 当前对象
     */
    public ARTable columns( String columns ) {
        this.columns = trimString( columns );
        return this;
    }

    /**
     * 清除指令内容，便于再次利用
     *
     * @return 当前实例
     */
    public ARTable clear() {
        columns = null;
        table = null;
        orderby = null;
        where = null;
        whereParams = null;
        values.clear();

        return this;
    }

    /**
     * 查询单条记录
     *
     * @return 记录集合
     */
    public JdbcRecord query() {
        SqlStatement st = TableStatementBuilder.query( this );
        try {
            return dialect.query( session, st );
        } catch ( SQLException e ) {
            throw new ActiveRecordException( e );
        }
    }

    /**
     * 查询多条记录
     *
     * @return 集合集合
     */
    public JdbcRecordSet queryList() {
        SqlStatement st = TableStatementBuilder.query( this );
        try {
            return dialect.queryList( session, st );
        } catch ( SQLException e ) {
            throw new ActiveRecordException( e );
        }
    }

    /**
     * 查询多条记录
     *
     * @param row 行处理回调函数
     */
    public void queryList( IRowAction row ) {
        SqlStatement st = TableStatementBuilder.query( this );
        try {
            dialect.queryList( session, st, row );
        } catch ( SQLException e ) {
            throw new ActiveRecordException( e );
        }
    }

    /**
     * 分页查询
     * @param paging
     * @return
     */
    public JdbcRecordSet queryPaging(DBPaging paging) {
        SqlStatement st = TableStatementBuilder.query( this );
        try {           
            return dialect.queryPaging( session, st ,paging);
        } catch ( SQLException e ) {
            throw new ActiveRecordException( e );
        }
    }
    
    /**
     * 插入到表中
     */
    public int insert() {
        SqlStatement st = TableStatementBuilder.insert( this );
        try {
            return dialect.executeUpdate( session, st );
        } catch ( SQLException e ) {
            throw new ActiveRecordException( e );
        }
    }

    /**
     * 更新表数据
     */
    public int update() {
        SqlStatement st = TableStatementBuilder.update( this );
        try {
            return dialect.executeUpdate( session, st );
        } catch ( SQLException e ) {
            throw new ActiveRecordException( e );
        }
    }

    public int save() {
        return StringHelper.isBlank( where ) ? insert() : update();
    }

    /**
     * 删除数据
     */
    public int delete() {
        SqlStatement st = TableStatementBuilder.delete( this );
        try {
            return dialect.executeUpdate( session, st );
        } catch ( SQLException e ) {
            throw new ActiveRecordException( e );
        }
    }
}
