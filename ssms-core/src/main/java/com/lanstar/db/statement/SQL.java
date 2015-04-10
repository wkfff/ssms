/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SQL.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.db.statement;

public class SQL {
    public static SqlBuilder WITH( String body ) {
        return new SqlBuilder().WITH( body );
    }

    public static SqlBuilder WITH( String format, Object... args ) {
        return new SqlBuilder().WITH( format, args );
    }

    public static SqlBuilder WITH( SqlBuilder subQuery, String alias ) {
        return new SqlBuilder().WITH( subQuery, alias );
    }

    public static SqlBuilder SELECT( String body ) {
        return new SqlBuilder().SELECT( body );
    }

    public static SqlBuilder SELECT( String format, Object... args ) {
        return new SqlBuilder().SELECT( format, args );
    }

    public static SqlBuilder INSERT_INTO( String body ) {
        return new SqlBuilder().INSERT_INTO( body );
    }

    public static SqlBuilder INSERT_INTO( String format, Object... args ) {
        return new SqlBuilder().INSERT_INTO( format, args );
    }

    public static SqlBuilder UPDATE( String body ) {
        return new SqlBuilder().UPDATE( body );
    }

    public static SqlBuilder UPDATE( String format, Object... args ) {
        return new SqlBuilder().UPDATE( format, args );
    }

    public static SqlBuilder DELETE_FROM( String body ) {
        return new SqlBuilder().DELETE_FROM( body );
    }

    public static SqlBuilder DELETE_FROM( String format, Object... args ) {
        return new SqlBuilder().DELETE_FROM( format, args );
    }
}
