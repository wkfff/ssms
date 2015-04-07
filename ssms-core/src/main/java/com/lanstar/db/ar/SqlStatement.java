/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlStatement.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db.ar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL指令
 */
public class SqlStatement {
    /**
     * SQL空参数
     */
    public final static Object[] NONE_PARAM = new Object[] {};
    private final static Pattern REGEX_NAMED_PARAMS = Pattern.compile( ":[\\w]+", Pattern.MULTILINE );

    String sql;
    Object[] params = NONE_PARAM;

    public SqlStatement() {}

    public SqlStatement( String sql, Object[] params ) {
        this.sql = sql;
        this.params = params == null ? NONE_PARAM : params;
    }

    public SqlStatement( String sql, Collection<?> paramList ) {
        this.sql = sql;
        this.params = paramList.toArray( new Object[paramList.size()] );
    }

    public SqlStatement( String sql, Map<String, Object> namedParams ) {
        this.sql = sql;
        List<String> nps = prepareSql();
        if ( nps.size() > 0 ) {
            this.params = new Object[nps.size()];
            for ( int i = 0; i < nps.size(); i++ ) {
                this.params[i] = namedParams.get( nps.get( i ) );
            }
        }
    }

    public String getSql() {
        return sql;
    }

    public Object[] getParams() {
        return params;
    }

    private List<String> prepareSql() {
        Matcher m = REGEX_NAMED_PARAMS.matcher( this.sql );
        List<String> nparams = new ArrayList<>();
        while ( m.find() ) {
            nparams.add( m.group() );
        }
        this.sql = m.replaceAll( "?" );
        return nparams;
    }

}

