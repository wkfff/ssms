/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DateKit.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import java.text.ParsePosition;
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
    /**
     * 字符串转换为java.util.Date<br>
     * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
     * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
     * @param time String 字符串<br>
     * @return Date 日期<br>
     */
    public static Date toDate( String time ) {
        SimpleDateFormat formatter;
        int tempPos=time.indexOf("AD") ;
        time=time.trim() ;
        formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z");
        if(tempPos>-1){
          time=time.substring(0,tempPos)+
               "公元"+time.substring(tempPos+"AD".length());//china
          formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z");
        }
        tempPos=time.indexOf("-");
        if(tempPos>-1&&(time.indexOf(" ")<0)){
          formatter = new SimpleDateFormat ("yyyyMMddHHmmssZ");
        }
        else if((time.indexOf("/")>-1) &&(time.indexOf(" ")>-1)){
          formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
        }
        else if((time.indexOf("-")>-1) &&(time.indexOf(" ")>-1)){
          formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        }
        else if((time.indexOf("/")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
          formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a");
        }
        else if((time.indexOf("-")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
          formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a");
        }
        ParsePosition pos = new ParsePosition(0);
        java.util.Date ctime = formatter.parse(time, pos);

        return ctime;
    }

    public static String toStr( Date date ) {
        return toStr( date, DateKit.dateFormat );
    }

    public static String toStr( Date date, String format ) {
        return new SimpleDateFormat( format ).format( date );
    }
}