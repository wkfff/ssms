/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionReporter.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.core.aop.Interceptor;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

final class ActionReporter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    /**
     * Report action before action invoking when the common request coming
     */
    static boolean reportCommonRequest( Controller controller, Action action ) {
        String content_type = controller.getRequest().getContentType();
        if ( content_type == null || !content_type.toLowerCase()
                                                  .contains( "multipart" ) ) {    // if (content_type == null || content_type.indexOf("multipart/form-data") == -1) {
            doReport( controller, action );
            return false;
        }
        return true;
    }

    /**
     * Report action after action invoking when the multipart request coming
     */
    static void reportMultipartRequest( Controller controller, Action action ) {
        doReport( controller, action );
    }

    private static void doReport( Controller controller, Action action ) {
        StringBuilder sb = new StringBuilder( "\nRapidware action report -------- " ).append( sdf.format( new Date() ) )
                                                                                  .append( " ---------------------------\n" );
        Class<? extends Controller> cc = action.getControllerClass();
        sb.append( "Controller  : " )
          .append( cc.getName() )
          .append( ".(" )
          .append( cc.getSimpleName() )
          .append( ".java:1)" );
        sb.append( "\nMethod      : " ).append( action.getMethodName() ).append( "\n" );

        String urlParas = controller.getPara();
        if ( urlParas != null ) {
            sb.append( "UrlPara     : " ).append( urlParas ).append( "\n" );
        }

        Interceptor[] inters = action.getInterceptors();
        if ( inters.length > 0 ) {
            sb.append( "Interceptor : " );
            for ( int i = 0; i < inters.length; i++ ) {
                if ( i > 0 )
                    sb.append( "\n              " );
                Interceptor inter = inters[i];
                Class<? extends Interceptor> ic = inter.getClass();
                sb.append( ic.getName() ).append( ".(" ).append( ic.getSimpleName() ).append( ".java:1)" );
            }
            sb.append( "\n" );
        }

        // print all parameters
        HttpServletRequest request = controller.getRequest();
        @SuppressWarnings("unchecked")
        Enumeration<String> e = request.getParameterNames();
        if ( e.hasMoreElements() ) {
            sb.append( "Parameter   : " );
            while ( e.hasMoreElements() ) {
                String name = e.nextElement();
                String[] values = request.getParameterValues( name );
                if ( values.length == 1 ) {
                    sb.append( name ).append( "=" ).append( values[0] );
                } else {
                    sb.append( name ).append( "[]={" );
                    for ( int i = 0; i < values.length; i++ ) {
                        if ( i > 0 )
                            sb.append( "," );
                        sb.append( values[i] );
                    }
                    sb.append( "}" );
                }
                sb.append( "  " );
            }
            sb.append( "\n" );
        }
        sb.append( "--------------------------------------------------------------------------------\n" );
        System.out.print( sb.toString() );
    }
}