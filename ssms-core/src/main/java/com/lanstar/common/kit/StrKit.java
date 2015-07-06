/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StrKit.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

/**
 * StrKit.
 */
public class StrKit {

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase( String str ) {
        char firstChar = str.charAt( 0 );
        if ( firstChar >= 'A' && firstChar <= 'Z' ) {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String( arr );
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase( String str ) {
        char firstChar = str.charAt( 0 );
        if ( firstChar >= 'a' && firstChar <= 'z' ) {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String( arr );
        }
        return str;
    }

    /**
     * 字符串为 null 或者为  "" 时返回 true
     */
    public static boolean isBlank( String str ) {
        return str == null || "".equals( str.trim() ) ? true : false;
    }

    public static boolean isBlank( String... args ) {
        for ( String o : args )
            if ( isBlank( o ) ) return true;
        return false;
    }

    /**
     * 字符串不为 null 而且不为  "" 时返回 true
     */
    public static boolean notBlank( String str ) {
        return str == null || "".equals( str.trim() ) ? false : true;
    }

    public static boolean notBlank( String... strings ) {
        if ( strings == null )
            return false;
        for ( String str : strings )
            if ( str == null || "".equals( str.trim() ) )
                return false;
        return true;
    }

    public static boolean notNull( Object... paras ) {
        if ( paras == null )
            return false;
        for ( Object obj : paras )
            if ( obj == null )
                return false;
        return true;
    }

    public static String format( String message, Object... params ) {
        return String.format( message, params );
    }

    /**
     * 根据分隔字符来分隔字符串到字符串数组
     */
    public static String[] split( String text, String split ) {
        if ( isBlank( text ) ) return new String[] {};
        return text.split( split );
    }

    /**
     * 检查字符串是否为空
     */
    public static boolean isEmpty( String str ) {
        return str == null || str.length() == 0;
    }

    public static boolean notEmpty( String str ) {
        return isEmpty( str ) == false;
    }

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
     * 将对象集合转化成字符串
     *
     * @param list
     * @param split 分隔符
     * @param quote 是否加单引号
     * @return
     */
    public static String join( Collection<?> list, String split, boolean quote ){
        if( list == null ) return "";
        if( split == null ) split = "";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for( Object o : list ){
            if( i > 0 ) sb.append( split );
            if( quote ) sb.append( "'" );
            sb.append( o );
            if( quote ) sb.append( "'" );
            i++;
        }

        return sb.toString();
    }
    
    /**
     * 如果字符串为空或者没有内容，则替换成默认的字符串
     *
     * @param str        要处理的字符串
     * @param defaultStr 默认字符串
     * @return 结果字符串
     */
    public static String empty( String str, String defaultStr ){
        return str == null || str.length() < 1 ? defaultStr : str;
    }
}