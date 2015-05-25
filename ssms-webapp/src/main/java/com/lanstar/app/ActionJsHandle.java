/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionJsHandle.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import com.lanstar.common.kit.ServletKit;
import com.lanstar.core.DispatchContext;
import com.lanstar.core.Rapidware;
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class ActionJsHandle implements Handler {
    @Override
    public void handle( DispatchContext context, HandleChain next ) {
        String target = context.getTarget();
        if ( target.endsWith( ".js" ) ) {
            target = "/WEB-INF/views" + target;
            String path = ServletKit.getRealPath( target );
            File file = new File( path );
            if ( file.isFile() && file.exists() ) {
                try {
                    context.setHandled( true );
                    Rapidware.me()
                             .getServletContext()
                             .getRequestDispatcher( target )
                             .forward( context.getRequest(), context.getResponse() );
                } catch ( ServletException | IOException ignored ) {
                    context.setHandled( false );
                }
            }
        }
        next.doHandle( context );
    }
}
