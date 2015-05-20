/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RequestKey.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import javax.servlet.http.HttpServletRequest;

public class RequestKit {
    public static Object getValue( HttpServletRequest request, String key ) {
        Object value = request.getAttribute( key );
        if ( value == null ) value = request.getParameter( key );
        if ( value == null ) value = request.getHeader( key );
        if ( value == null ) value = request.getSession().getAttribute( key );
        if ( value == null ) value = request.getSession().getServletContext().getAttribute( key );
        return value;
    }

    public static boolean hasValue( HttpServletRequest request, String key, Object[] result ) {
        Object value = getValue( request, key );
        if ( value != null ) {
            if ( result.length > 1 ) result[0] = value;
            return true;
        }
        return false;
    }

    public static boolean hasValue( HttpServletRequest request, String key ) {
        return getValue( request, key ) != null;
    }
}
