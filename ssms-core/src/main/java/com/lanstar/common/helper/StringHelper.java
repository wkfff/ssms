/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StringHelper.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.common.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * 字符串工具类
 */
public final class StringHelper {
    /**
     * 将首字母设置成大写
     */
    public static String capitalize( String str ) {
        if ( str == null || (str.length()) == 0 ) return str;
        return String.valueOf( Character.toTitleCase( str.charAt( 0 ) ) ) + str.substring( 1 );
    }

    /**
     * 是否包含子串
     */
    public static boolean contains( String str, char searchChar ) {
        return !isEmpty( str ) && str.indexOf( searchChar ) >= 0;
    }

    /**
     * 如果字符串为空或者没有内容，则替换成默认的字符串
     *
     * @param str        要处理的字符串
     * @param defaultStr 默认字符串
     *
     * @return 结果字符串
     */
    public static String empty( String str, String defaultStr ) {
        return str == null || str.length() < 1 ? defaultStr : str;
    }

    /**
     * MD5加密字符串
     */
    public static String toMD5( String src ) {
        MessageDigest messageDigest;
        StringBuilder md5StrBuff = new StringBuilder();

        try {
            messageDigest = MessageDigest.getInstance( "MD5" );
            messageDigest.reset();
            messageDigest.update( src.getBytes( "UTF-8" ) );

            byte[] byteArray = messageDigest.digest();
            for ( byte aByteArray : byteArray ) {
                if ( Integer.toHexString( 0xFF & aByteArray ).length() == 1 )
                    md5StrBuff.append( "0" ).append( Integer.toHexString( 0xFF & aByteArray ) );
                else
                    md5StrBuff.append( Integer.toHexString( 0xFF & aByteArray ) );
            }
        } catch ( NoSuchAlgorithmException | UnsupportedEncodingException e ) {
            e.printStackTrace();
        }

        return md5StrBuff.toString();
    }

    /**
     * 检查是否为空白字符串，NULL，长度为0，空格等
     */
    public static boolean isBlank( String str ) {
        if ( str == null ) return true;
        int strLen = str.length();
        if ( strLen < 1 ) return true;
        for ( int i = 0; i < strLen; i++ )
            if ( (!Character.isWhitespace( str.charAt( i ) )) ) return false;
        return true;
    }

    /**
     * 任何一个地方存在空串都返回true;
     */
    public static boolean isBlank( String... args ) {
        for ( String o : args )
            if ( isBlank( o ) ) return true;
        return false;
    }

    /**
     * 检查字符串是否为空
     */
    public static boolean isEmpty( String str ) {
        return str == null || str.length() == 0;
    }

    /*
     * 是否是数字类型
     */
    public static boolean isNumeric( String str ) {
        if ( str == null ) return false;

        int sz = str.length();
        for ( int i = 0; i < sz; i++ )
            if ( !Character.isDigit( str.charAt( i ) ) ) return false;
        return true;
    }

    /**
     * 删除字符串中的空白字符
     */
    public static String removeBlank( String src ) {
        if ( src == null ) return null;
        StringBuilder s = new StringBuilder();
        char[] text = src.toCharArray();
        for ( char aText : text ) if ( !Character.isWhitespace( aText ) ) s.append( aText );
        return s.toString();
    }

    /**
     * 用指定的字符来替换空白字符
     */
    public static String replaceBlank( String src, char ch ) {
        if ( src == null ) return null;
        char[] text = src.toCharArray();
        for ( int i = 0; i < text.length; i++ )
            if ( Character.isWhitespace( text[i] ) ) text[i] = ch;
        return String.copyValueOf( text );
    }

    /**
     * 根据分隔字符来分隔字符串到字符串数组
     */
    public static String[] split( String text, String split ) {
        if ( isBlank( text ) ) return new String[] {};
        return text.split( split );
    }

    /**
     * 将字符串转化为SQL的in( ... )中的使用的字符串
     *
     * @param quote 是否需要用'包含起来声明为字符串
     */
    public static String toSqlInCase( Collection<String> ids, boolean quote ) {
        StringBuilder sb = new StringBuilder();

        for ( String o : ids ) {
            if ( o == null ) continue;
            sb.append( "," ).append( quote ? "'" : "" ).append( o ).append( quote ? "'" : "" );
        }
        if ( sb.length() > 1 ) sb.deleteCharAt( 0 );

        return sb.toString();
    }

    /**
     * 清楚空白字符，加了空串的判断
     */
    public static String trim( String str ) {
        return str == null ? null : str.trim();
    }

    /**
     * 清除头尾空白字符，如果字符串为null则自动用指定的默认字符串替代
     *
     * @param str 要处理的字符串
     * @param def 默认的字符串
     */
    public static String trim( String str, String def ) {
        return str == null ? def : str.trim();
    }

    /**
     * 将字符串转化为特定类型的值，除了日期以外
     */
    public static Object stringToType( String s, Class<?> type ) {
        if ( type == Integer.class ) {
            return Integer.valueOf( s );
        } else if ( type == int.class ) {
            return Integer.parseInt( s );
        } else if ( type == Long.class ) {
            return Long.valueOf( s );
        } else if ( type == long.class ) {
            return Long.parseLong( s );
        } else if ( type == Double.class ) {
            return Double.valueOf( s );
        } else if ( type == double.class ) {
            return Double.parseDouble( s );
        } else if ( type == Float.class ) {
            return Float.valueOf( s );
        } else if ( type == float.class ) {
            return Float.parseFloat( s );
        } else if ( type == Character.class ) {
            return s.charAt( 0 );
        } else if ( type == char.class ) {
            return s.charAt( 0 );
        } else {
            return s;
        }
    }

    /**
     * 将数组转化成字符串
     *
     * @param split 分隔符
     * @param quote 是否加单引号
     */
    public static String join( Object[] list, String split, boolean quote ) {
        if ( list == null ) return "";
        if ( split == null ) split = "";
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < list.length; i++ ) {
            if ( i > 0 ) sb.append( split );
            if ( quote ) sb.append( "'" );
            sb.append( list[i].toString() );
            if ( quote ) sb.append( "'" );
        }
        return sb.toString();
    }

    /**
     * 将对象集合转化成字符串
     *
     * @param split 分隔符
     * @param quote 是否加单引号
     */
    public static String join( Collection<?> list, String split, boolean quote ) {
        if ( list == null ) return "";
        if ( split == null ) split = "";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for ( Object o : list ) {
            if ( i > 0 ) sb.append( split );
            if ( quote ) sb.append( "'" );
            sb.append( o );
            if ( quote ) sb.append( "'" );
            i++;
        }

        return sb.toString();
    }

    /**
     * 相加字符串
     */
    public static String concat( String... args ) {
        StringBuilder result = new StringBuilder();
        if ( args != null ) for ( String s : args ) {
            if ( s != null ) result.append( s );
        }
        return result.length() < 1 ? null : result.toString();
    }

    private StringHelper() {
    }

    /**
     * 简单的格式化文本,从GOOGLE的GUAVA中复制出来，实现%s的简单替换
     */
    public static String format( String template, Object... args ) {
        template = String.valueOf( template );

        // start substituting the arguments into the '%s' placeholders
        StringBuilder builder = new StringBuilder( template.length() + 16 * args.length );
        int templateStart = 0;
        int i = 0;
        while ( i < args.length ) {
            int placeholderStart = template.indexOf( "%s", templateStart );
            if ( placeholderStart == -1 ) {
                break;
            }
            builder.append( template.substring( templateStart, placeholderStart ) );
            builder.append( args[i++] );
            templateStart = placeholderStart + 2;
        }
        builder.append( template.substring( templateStart ) );

        // if we run out of placeholders, append the extra args in square braces
        if ( i < args.length ) {
            builder.append( " [" );
            builder.append( args[i++] );
            while ( i < args.length ) {
                builder.append( ", " );
                builder.append( args[i++] );
            }
            builder.append( ']' );
        }

        return builder.toString();
    }

    /**
     * 判断字符串指定位置是否由特定字符串开始（忽略大小写）
     *
     * @param text   要检查的字符串
     * @param prefix 匹配头部的字符串
     * @param begin  起始比较位置
     *
     * @return TRUE = 符合，FALSE = 不符合
     */
    public static boolean startsWithIgnoreCase( String text, String prefix, int begin ) {
        int to = begin, po = 0;
        int pc = prefix.length();
        if ( (begin < 0) || (begin > text.length() - pc) ) return false;
        while ( --pc >= 0 ) {
            if ( Character.toUpperCase( text.charAt( to++ ) ) != Character.toUpperCase( prefix.charAt( po++ ) ) )
                return false;
        }
        return true;
    }

    /**
     * 判断字符串指定位置是否由特定字符串开始（忽略大小写）
     *
     * @param text   要检查的字符串
     * @param prefix 匹配头部的字符串
     *
     * @return TRUE = 符合，FALSE = 不符合
     */
    public static boolean startsWithIgnoreCase( String text, String prefix ) {
        return startsWithIgnoreCase( text, prefix, 0 );
    }

    /**
     * 字符串转换为整数
     */
    public static int toInteger( String text, int defvalue ) {
        if ( text == null || text.length() == 0 ) return defvalue;
        try {
            return Integer.parseInt( text );
        } catch ( Throwable e ) {
            return defvalue;
        }
    }

    /**
     * 日期转换函数，自动匹配格式，必须是年月日时分秒格式
     *
     * @param str    字符串
     * @param defval 默认值
     *
     * @return 值
     */
    public static Date toDate( String str, Date defval ) {
        try {
            if ( isBlank( str ) ) return defval;

            StringBuilder tmp = new StringBuilder();
            for ( int i = 0; i < str.length(); i++ ) {
                char c = str.charAt( i );
                if ( c >= '0' && c <= '9' ) tmp.append( c );
            }
            String val = tmp.toString();
            int ilen = val.length();
            String format;
            if ( ilen == 4 ) format = "MMdd";
            else if ( ilen == 8 ) format = "yyyyMMdd";
            else if ( ilen == 10 ) format = "yyyyMMddHH";
            else if ( ilen == 12 ) format = "yyyyMMddHHmm";
            else if ( ilen == 14 ) format = "yyyyMMddHHmmss";
            else format = "yyyyMMdd"; // 非正常长度分析到day

            return new SimpleDateFormat( format ).parse( str );
        } catch ( Throwable e ) {
            return defval;
        }
    }

    /**
     * 字符串转化为浮点数
     *
     * @param text 待转换字符串
     * @param v    转换失败的默认值
     *
     * @return 结果
     */
    public static double toDouble( String text, double v ) {
        try {
            return Double.parseDouble( text );
        } catch ( Throwable e ) {
            return v;
        }
    }
}
