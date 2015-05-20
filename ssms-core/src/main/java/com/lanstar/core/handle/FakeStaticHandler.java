/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FakeStaticHandler.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.common.kit.HandlerKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.DispatchContext;

/**
 * FakeStaticHandler.
 */
public class FakeStaticHandler implements Handler {

    private String viewPostfix;

    public FakeStaticHandler() {
        viewPostfix = ".html";
    }

    public FakeStaticHandler( String viewPostfix ) {
        if ( StrKit.isBlank( viewPostfix ) )
            throw new IllegalArgumentException( "viewPostfix can not be blank." );
        this.viewPostfix = viewPostfix;
    }

    @Override
    public void handle( DispatchContext context, HandleChain next ) {
        String target = context.getTarget();
        if ( "/".equals( target ) ) {
            next.doHandle( context );
            return;
        }

        if ( target.indexOf( '.' ) == -1 ) {
            HandlerKit.renderError404( context );
            return;
        }

        int index = target.lastIndexOf( viewPostfix );
        if ( index != -1 )
            target = target.substring( 0, index );
        next.doHandle( context );
    }
}