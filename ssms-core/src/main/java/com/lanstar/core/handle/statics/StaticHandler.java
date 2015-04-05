/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StaticHandler.java
 * 创建时间：2015-04-05
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.statics;

import com.lanstar.common.helper.Asserts;
import com.lanstar.core.RequestContext;
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.handle.HandlerContext;

import java.io.File;

public class StaticHandler implements Handler {
    @Override
    public void handle( HandlerContext context, HandleChain nextHandle ) {
        RequestContext requestContext = context.getRequestContext();
        // 获取资源目录
        String resourceFolder = requestContext.getResourceFolder( false );
        File folder = new File( requestContext.getRealPath( resourceFolder ) );
        Asserts.check( !folder.exists(), "资源目录不存在" );
        String[] children = folder.list();
        String uri = requestContext.getUri();
        // TODO:这里判断规则不够强壮
        for ( String f : children ) {
            if ( uri.startsWith( "/" + f ) ) {
                requestContext.forward( resourceFolder + uri );
                return;
            }
        }
        nextHandle.doHandle( context );
    }
}
