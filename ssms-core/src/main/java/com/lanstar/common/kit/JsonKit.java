/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JsonKit.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import com.lanstar.plugin.activerecord.CPI;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.Record;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonKit {

    private static int convertDepth = 8;
    private static String timestampPattern = "yyyy-MM-dd HH:mm:ss";
    private static String datePattern = "yyyy-MM-dd";

    public static void setConvertDepth( int convertDepth ) {
        if ( convertDepth < 2 )
            throw new IllegalArgumentException( "convert depth can not less than 2." );
        JsonKit.convertDepth = convertDepth;
    }

    public static void setTimestampPattern( String timestampPattern ) {
        if ( timestampPattern == null || "".equals( timestampPattern.trim() ) )
            throw new IllegalArgumentException( "timestampPattern can not be blank." );
        JsonKit.timestampPattern = timestampPattern;
    }

    public static void setDatePattern( String datePattern ) {
        if ( datePattern == null || "".equals( datePattern.trim() ) )
            throw new IllegalArgumentException( "datePattern can not be blank." );
        JsonKit.datePattern = datePattern;
    }

    private static String mapToJson( Map map, int depth ) {
        if ( map == null )
            return "null";

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        Iterator iter = map.entrySet().iterator();

        sb.append( '{' );
        while ( iter.hasNext() ) {
            if ( first )
                first = false;
            else
                sb.append( ',' );

            Map.Entry entry = (Map.Entry) iter.next();
            toKeyValue( String.valueOf( entry.getKey() ), entry.getValue(), sb, depth );
        }
        sb.append( '}' );
        return sb.toString();
    }

    private static String toKeyValue( String key, Object value, StringBuilder sb, int depth ) {
        sb.append( '\"' );
        if ( key == null )
            sb.append( "null" );
        else
            escape( key, sb );
        sb.append( '\"' ).append( ':' );

        sb.append( toJson( value, depth ) );

        return sb.toString();
    }

    private static String listToJson( List list, int depth ) {
        if ( list == null )
            return "null";

        boolean first = true;
        StringBuilder sb = new StringBuilder();
        Iterator iter = list.iterator();

        sb.append( '[' );
        while ( iter.hasNext() ) {
            if ( first )
                first = false;
            else
                sb.append( ',' );

            Object value = iter.next();
            if ( value == null ) {
                sb.append( "null" );
                continue;
            }
            sb.append( toJson( value, depth ) );
        }
        sb.append( ']' );
        return sb.toString();
    }

    /**
     * Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters (U+0000 through U+001F).
     */
    private static String escape( String s ) {
        if ( s == null )
            return null;
        StringBuilder sb = new StringBuilder();
        escape( s, sb );
        return sb.toString();
    }

    private static void escape( String s, StringBuilder sb ) {
        for ( int i = 0; i < s.length(); i++ ) {
            char ch = s.charAt( i );
            switch ( ch ) {
                case '"':
                    sb.append( "\\\"" );
                    break;
                case '\\':
                    sb.append( "\\\\" );
                    break;
                case '\b':
                    sb.append( "\\b" );
                    break;
                case '\f':
                    sb.append( "\\f" );
                    break;
                case '\n':
                    sb.append( "\\n" );
                    break;
                case '\r':
                    sb.append( "\\r" );
                    break;
                case '\t':
                    sb.append( "\\t" );
                    break;
                case '/':
                    sb.append( "\\/" );
                    break;
                default:
                    if ( (ch >= '\u0000' && ch <= '\u001F') || (ch >= '\u007F' && ch <= '\u009F') || (ch >= '\u2000'
                            && ch <= '\u20FF') ) {
                        String str = Integer.toHexString( ch );
                        sb.append( "\\u" );
                        for ( int k = 0; k < 4 - str.length(); k++ ) {
                            sb.append( '0' );
                        }
                        sb.append( str.toUpperCase() );
                    } else {
                        sb.append( ch );
                    }
            }
        }
    }

    public static String toJson( Object value ) {
        return toJson( value, convertDepth );
    }

    public static String toJson( Object value, int depth ) {
        if ( value == null || (depth--) < 0 )
            return "null";

        if ( value instanceof String )
            return "\"" + escape( (String) value ) + "\"";

        if ( value instanceof Double ) {
            if ( ((Double) value).isInfinite() || ((Double) value).isNaN() )
                return "null";
            else
                return value.toString();
        }

        if ( value instanceof Float ) {
            if ( ((Float) value).isInfinite() || ((Float) value).isNaN() )
                return "null";
            else
                return value.toString();
        }

        if ( value instanceof Number )
            return value.toString();

        if ( value instanceof Boolean )
            return value.toString();

        if ( value instanceof java.util.Date ) {
            if ( value instanceof java.sql.Timestamp )
                return "\"" + new SimpleDateFormat( timestampPattern ).format( value ) + "\"";
            if ( value instanceof java.sql.Time )
                return "\"" + value.toString() + "\"";
            return "\"" + new SimpleDateFormat( datePattern ).format( value ) + "\"";
        }

        if ( value instanceof Map ) {
            return mapToJson( (Map) value, depth );
        }

        if ( value instanceof List ) {
            return listToJson( (List) value, depth );
        }

        String result = otherToJson( value, depth );
        if ( result != null )
            return result;

        // 类型无法处理时当作字符串处理,否则ajax调用返回时js无法解析
        // return value.toString();
        return "\"" + escape( value.toString() ) + "\"";
    }

    private static String otherToJson( Object value, int depth ) {
        if ( value instanceof Character ) {
            return "\"" + escape( value.toString() ) + "\"";
        }

        if ( value instanceof Model ) {
            Map map = CPI.getAttrs( (Model) value );
            return mapToJson( map, depth );
        }
        if ( value instanceof Record ) {
            Map map = ((Record) value).getColumns();
            return mapToJson( map, depth );
        }
        if ( value instanceof Object[] ) {
            Object[] arr = (Object[]) value;
            List list = new ArrayList( arr.length );
            for ( int i = 0; i < arr.length; i++ )
                list.add( arr[i] );
            return listToJson( list, depth );
        }
        if ( value instanceof Enum ) {
            return "\"" + ((Enum) value).toString() + "\"";
        }

        return beanToJson( value, depth );
    }

    private static String beanToJson( Object model, int depth ) {
        Map map = new HashMap();
        Method[] methods = model.getClass().getMethods();
        for ( Method m : methods ) {
            String methodName = m.getName();
            int indexOfGet = methodName.indexOf( "get" );
            if ( indexOfGet == 0 && methodName.length() > 3 ) {    // Only getter
                String attrName = methodName.substring( 3 );
                if ( !attrName.equals( "Class" ) ) {                // Ignore Object.getClass()
                    Class<?>[] types = m.getParameterTypes();
                    if ( types.length == 0 ) {
                        try {
                            Object value = m.invoke( model );
                            map.put( StrKit.firstCharToLowerCase( attrName ), value );
                        } catch ( Exception e ) {
                            throw new RuntimeException( e.getMessage(), e );
                        }
                    }
                }
            } else {
                int indexOfIs = methodName.indexOf( "is" );
                if ( indexOfIs == 0 && methodName.length() > 2 ) {
                    String attrName = methodName.substring( 2 );
                    Class<?>[] types = m.getParameterTypes();
                    if ( types.length == 0 ) {
                        try {
                            Object value = m.invoke( model );
                            map.put( StrKit.firstCharToLowerCase( attrName ), value );
                        } catch ( Exception e ) {
                            throw new RuntimeException( e.getMessage(), e );
                        }
                    }
                }
            }
        }
        return mapToJson( map, depth );
    }
}