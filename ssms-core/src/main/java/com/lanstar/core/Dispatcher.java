/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Diapatcher.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;

import java.util.LinkedList;
import java.util.List;

final class Dispatcher {
    private final List<Handler> handlers = new LinkedList<>();

    public Dispatcher( List<Handler> list ) {
        handlers.addAll( list );
    }

    public void add( Handler handler ) {
        handlers.add( handler );
    }

    public void add( List<Handler> list ) {
        handlers.addAll( list );
    }

    public void dispatch( DispatchContext context ) {
        new VirtualHandleChain( handlers ).doHandle( context );
    }

    private static class VirtualHandleChain implements HandleChain {
        private final List<Handler> additionalHandlers = new LinkedList<>();
        private int currentPosition = 0;

        public VirtualHandleChain( List<Handler> additionalHandlers ) {
            this.additionalHandlers.addAll( additionalHandlers );
        }

        @Override
        public void doHandle( DispatchContext context ) {
            if ( this.currentPosition == this.additionalHandlers.size() ) return;
            this.currentPosition++;
            Handler next = this.additionalHandlers.get( this.currentPosition - 1 );
            next.handle( context, this );
        }
    }
}
