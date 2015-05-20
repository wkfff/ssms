/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ContextPathHandler.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.common.kit.StrKit;
import com.lanstar.core.DispatchContext;

/**
 * Provide a context path to view if you need.
 * <br>
 * Example:<br>
 * In JFinalFilter: handlers.add(new ContextPathHandler("CONTEXT_PATH"));<br>
 * in freemarker: <img src="${BASE_PATH}/images/logo.png" />
 */
public class ContextPathHandler implements Handler {

    private String contextPathName;

    public ContextPathHandler() {
        contextPathName = "CONTEXT_PATH";
    }

    public ContextPathHandler( String contextPathName ) {
        if ( StrKit.isBlank( contextPathName ) )
            throw new IllegalArgumentException( "contextPathName can not be blank." );
        this.contextPathName = contextPathName;
    }

    public void handle( DispatchContext context, HandleChain next ) {
        context.getRequest().setAttribute( contextPathName, context.getRequest().getContextPath() );
        next.doHandle( context );
    }
}