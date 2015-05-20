/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionException.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.common.kit.StrKit;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderFactory;

public class ActionException extends RuntimeException {

    private static final long serialVersionUID = 1998063243843477017L;
    private int errorCode;
    private Render errorRender;

    public ActionException( int errorCode, Render errorRender ) {
        if ( errorRender == null )
            throw new IllegalArgumentException( "The parameter errorRender can not be null." );

        this.errorCode = errorCode;
        this.errorRender = errorRender;
    }

    public ActionException( int errorCode, String errorView ) {
        if ( StrKit.isBlank( errorView ) )
            throw new IllegalArgumentException( "The parameter errorView can not be blank." );

        this.errorCode = errorCode;
        this.errorRender = RenderFactory.me().getErrorRender( errorCode, errorView );
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Render getErrorRender() {
        return errorRender;
    }
}
