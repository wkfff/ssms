/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Render.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.Const;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Render {

    protected String view;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    private static String encoding = Const.DEFAULT_ENCODING;
    private static boolean devMode = Const.DEFAULT_DEV_MODE;

    static void init( String encoding, boolean devMode ) {
        Render.encoding = encoding;
        Render.devMode = devMode;
    }

    public static String getEncoding() {
        return encoding;
    }

    public static boolean getDevMode() {
        return devMode;
    }

    public final Render setContext( HttpServletRequest request, HttpServletResponse response ) {
        this.request = request;
        this.response = response;
        return this;
    }

    public final Render setContext( HttpServletRequest request, HttpServletResponse response, String viewPath ) {
        this.request = request;
        this.response = response;
        if ( view != null && !view.startsWith( "/" ) )
            view = viewPath + view;
        return this;
    }

    public String getView() {
        return view;
    }

    public void setView( String view ) {
        this.view = view;
    }

    /**
     * Render to client
     */
    public abstract void render();
}
