/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DateKit.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateKit {

    public static String dateFormat = "yyyy-MM-dd";
    public static String timeFormat = "yyyy-MM-dd HH:mm:ss";

    public static void setDateFromat( String dateFormat ) {
        if ( StrKit.isBlank( dateFormat ) )
            throw new IllegalArgumentException( "dateFormat can not be blank." );
        DateKit.dateFormat = dateFormat;
    }

    public static void setTimeFromat( String timeFormat ) {
        if ( StrKit.isBlank( timeFormat ) )
            throw new IllegalArgumentException( "timeFormat can not be blank." );
        DateKit.timeFormat = timeFormat;
    }

    public static Date toDate( String dateStr ) {
        throw new RuntimeException( "Not finish!!!" );
    }

    public static String toStr( Date date ) {
        return toStr( date, DateKit.dateFormat );
    }

    public static String toStr( Date date, String format ) {
        return new SimpleDateFormat( format ).format( date );
    }
}