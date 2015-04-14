package com.lanstar.service.parameter;

import com.lanstar.db.DS;
import com.lanstar.db.DbContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.statement.SQL;
import com.lanstar.db.statement.SqlBuilder;
import com.lanstar.service.bean.Parameter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
