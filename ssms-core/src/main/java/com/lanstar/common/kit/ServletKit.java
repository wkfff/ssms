/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ServletHelper.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import com.lanstar.core.Rapidware;

import javax.servlet.ServletContext;
import java.io.InputStream;

public class ServletKit {
    public static InputStream getResource( String... paths ) {
        return getResource( Rapidware.me().getServletContext(), paths );
    }

    public static InputStream getResource( ServletContext context, String... paths ) {
        if ( paths == null ) return null;
        for ( String path : paths ) {
            InputStream stream = context.getResourceAsStream( path );
            if ( stream == null ) continue;
            return stream;
        }
        return null;
    }

    public static String getRealPath( String path ) {
        return Rapidware.me().getServletContext().getRealPath( path );
    }

    public static String getContextPath() {
        return Rapidware.me().getServletContext().getContextPath();
    }
}