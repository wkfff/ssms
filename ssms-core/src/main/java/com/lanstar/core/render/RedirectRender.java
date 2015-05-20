/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RedirectRender.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.Rapidware;

import java.io.IOException;

public class RedirectRender extends Render {

    private String url;
    private boolean withQueryString;
    private static final String contextPath = getContxtPath();

    static String getContxtPath() {
        String cp = Rapidware.me().getContextPath();
        return ("".equals( cp ) || "/".equals( cp )) ? null : cp;
    }

    public RedirectRender( String url ) {
        this.url = url;
        this.withQueryString = false;
    }

    public RedirectRender( String url, boolean withQueryString ) {
        this.url = url;
        this.withQueryString = withQueryString;
    }

    public void render() {
        if ( contextPath != null && !url.contains( "://" ) )
            url = contextPath + url;

        if ( withQueryString ) {
            String queryString = request.getQueryString();
            if ( queryString != null )
                if ( !url.contains( "?" ) )
                    url = url + "?" + queryString;
                else
                    url = url + "&" + queryString;
        }

        try {
            response.sendRedirect( url );    // always 302
        } catch ( IOException e ) {
            throw new RenderException( e );
        }
    }
}