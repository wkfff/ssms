/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Handlers.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.core.RequestContext;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Handlers {
    private final VirtualHandleChain handleChain;

    public Handlers() {
        handleChain = new VirtualHandleChain();
    }

    public void add( Handler handler ) {
        handleChain.add( handler );
    }

    public void add( List<Handler> list ) {
        handleChain.add( list );
    }

    public void handle( RequestContext context ) {
        handleChain.doHandle( HandlerHelper.createHandlerContext( context ), true );
    }

    private static class VirtualHandleChain implements HandleChain {
        private final List<Handler> additionalHandlers = new LinkedList<>();
        private int currentPosition = 0;

        void add( Handler handler ) {
            additionalHandlers.add( handler );
        }

        void add( Collection<Handler> handlers ) {
            additionalHandlers.addAll( handlers );
        }

        @Override
        public void doHandle( HandlerContext context ) {
            if ( this.currentPosition == this.additionalHandlers.size() ) return;
            this.currentPosition++;
            Handler next = this.additionalHandlers.get( this.currentPosition - 1 );
            next.handle( context, this );
        }

        public void doHandle( HandlerContext handlerContext, boolean reset ) {
            if ( reset ) currentPosition = 0;
            doHandle( handlerContext );
        }
    }
}

