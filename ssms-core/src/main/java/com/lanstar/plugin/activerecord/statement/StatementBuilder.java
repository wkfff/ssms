/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StatementBuilder.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord.statement;

import com.google.common.base.Joiner;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

class StatementBuilder {
    public static final Object[] NONE_PARA = null;
    protected StringBuilder buffer;
    protected List<Object> parameterValues;
    private String currentClause;
    private String currentSeparator;
    private String nextClause;
    private String nextSeparator;

    public StatementBuilder() {
        this.buffer = new StringBuilder();
        this.parameterValues = new ArrayList<>();
    }

    private static boolean equalsIgnoreCase( String x, String y ) {
        if ( x == null && y == null ) return true;
        if ( x != null ) return x.equalsIgnoreCase( y );
        return y.equalsIgnoreCase( null );
    }

    private static String placeholder( int index ) {
        return String.format( "{%d}", index );
    }

    private static String[] placeholder( int start, int count, int offset ) {
        String[] temp = new String[count];
        for ( int i = start; i < count; i++ ) {
            temp[i] = placeholder( offset + i );
        }
        return temp;
    }

    protected StatementBuilder setCurrentClause( String clauseName, String separator ) {
        this.currentClause = clauseName;
        this.currentSeparator = separator;
        return this;
    }

    protected StatementBuilder setNextClause( String clauseName, String separator ) {
        this.nextClause = clauseName;
        this.nextSeparator = separator;
        return this;
    }

    protected StatementBuilder appendToCurrentClause( String body ) {
        return appendToCurrentClause( body, NONE_PARA );
    }

    protected StatementBuilder appendToCurrentClause( String format, Object... args ) {
        String clause = this.currentClause;
        String separator = this.currentSeparator;

        if ( this.nextClause != null ) {
            clause = this.nextClause;
            separator = this.nextSeparator;
        }

        appendClause( clause, separator, format, args );

        return this;
    }

    protected StatementBuilder appendClause( String clauseName, String separator, String format, Object... args ) {
        if ( separator == null || !equalsIgnoreCase( currentClause, clauseName ) ) {
            if ( !this.isEmpty() ) {
                this.buffer.append( '\n' );
            }

            if ( clauseName != null ) {
                this.buffer.append( clauseName );
                this.buffer.append( " " );
            }
        } else if ( separator != null ) {
            this.buffer.append( separator );
        }

        append( format, args );

        this.currentClause = clauseName;
        this.currentSeparator = separator;

        this.nextClause = null;
        this.nextSeparator = null;

        return this;
    }

    protected StatementBuilder append( String format, Object... args ) {
        if ( args == null || args.length == 0 ) {
            this.buffer.append( format );
            return this;
        }

        List<String> fargs = new ArrayList<>();
        for ( Object obj : args ) {
            if ( obj != null ) {
                if ( obj.getClass().isArray() ) {
                    int length = Array.getLength( obj );
                    fargs.add( Joiner.on( ", " ).join( placeholder( 0, length, this.parameterValues.size() ) ) );
                    for ( int i = 0; i < length; i++ ) {
                        this.parameterValues.add( Array.get( obj, i ) );
                    }
                    continue;
                } else if ( StatementBuilder.class.isAssignableFrom( obj.getClass() ) ) {
                    StatementBuilder sqlb = (StatementBuilder) obj;
                    fargs.add( ("\n" + MakeAbsolutePlaceholders( sqlb )).replaceAll( "\n", "\n\t" ) );
                    this.parameterValues.addAll( sqlb.parameterValues );

                    continue;
                }
            }

            fargs.add( placeholder( this.parameterValues.size() ) );
            this.parameterValues.add( obj );
        }

        if ( format == null ) {
            format = Joiner.on( ' ' ).join( placeholder( 0, fargs.size(), 0 ) );
        }

        this.buffer.append( MessageFormat.format( format, fargs.toArray() ) );

        return this;
    }

    public StatementBuilder append( StatementBuilder builder ) {
        return append( " "+ builder.buffer, builder.parameterValues.toArray( new Object[builder.parameterValues.size()] ) );
    }

    public boolean isEmpty() {
        return this.buffer.length() == 0;
    }

    private String MakeAbsolutePlaceholders( StatementBuilder sql ) {
        int count = sql.parameterValues.size();
        String[] args = new String[count];
        for ( int i = 0; i < count; i++ ) {
            args[i] = placeholder( this.parameterValues.size() + i );
        }
        return MessageFormat.format( sql.toString(), args );
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public SqlStatement toSqlStatement() {
        int count = this.parameterValues.size();
        Object[] temp = new Object[count];
        for ( int i = 0; i < count; i++ ) {
            temp[i] = "?";
        }
        String sql = MessageFormat.format( toString(), temp );
        sql = sql.trim().replaceAll( "\n", " " );
        return new SqlStatement( sql, this.parameterValues );
    }
}
