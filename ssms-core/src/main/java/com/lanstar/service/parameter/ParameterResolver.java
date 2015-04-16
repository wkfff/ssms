/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ParameterResolver.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.service.parameter;

import com.lanstar.db.DS;
import com.lanstar.db.DbContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.statement.SQL;
import com.lanstar.db.statement.SqlBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数值获取器
 */
public class ParameterResolver {
    private static final ParameterResolver resolver = new ParameterResolver();
    private final DbContext dbContext;

    public static ParameterResolver me() {
        return resolver;
    }

    public ParameterResolver() {
        dbContext = DS.getDbContext();
    }

    public List<Parameter> getMultiParameter( String parameterName )
            throws SQLException {
        SqlBuilder sql = SQL.SELECT( "C_CODE, C_VALUE" )
                            .FROM( "SYS_PARA_MULTI" )
                            .WHERE( "C_NAME=?", parameterName );
        JdbcRecordSet records = dbContext.createDbSession().query( sql.toSqlStatement() );
        List<Parameter> list = new ArrayList<>();
        for ( JdbcRecord record : records ) {
            String code = record.getString( "C_CODE" );
            String value = record.getString( "C_VALUE" );
            list.add( new Parameter( code, value ) );
        }
        return list;
    }
}
