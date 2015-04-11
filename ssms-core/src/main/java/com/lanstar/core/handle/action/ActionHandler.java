/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionHandler.java
 * 创建时间：2015年4月1日 下午12:06:01
 * 创建用户：林峰
 */
package com.lanstar.core.handle.action;

import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.render.RenderFactory;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Action处理器
 */
public class ActionHandler implements Handler {
    private final ActionCache actionCache;

    public ActionHandler() {
        actionCache = new ActionCache();
    }

    @Override
    public void handle( HandlerContext context, HandleChain next ) throws ServletException, IOException {
        ActionMeta meta = ActionMeta.parseUrl( context.getRequestContext().getUri() );
        // 如果不是Action请求，则继续往下送，否则就开始做Action
        if ( meta == null ) {
            next.doHandle( context );
            return;
        }
        Action action = actionCache.getAction( meta );
        // 调度执行Action
        ViewAndModel vam = action.invoke( context );
        // 输出View
        RenderFactory.me().getActionRender( meta, vam, context ).render();
    }
}

