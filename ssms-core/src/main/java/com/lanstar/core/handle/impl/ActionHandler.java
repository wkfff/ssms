/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionHandler.java
 * 创建时间：2015年4月1日 下午12:06:01
 * 创建用户：林峰
 */
package com.lanstar.core.handle.impl;

import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.HandlerHelper;
import com.lanstar.core.render.RenderFactory;

/**
 * Action处理器
 */
public class ActionHandler extends Handler {
    @Override
    protected void innerHandle( HandlerContext handlerContext ) {
        // 获取Action
        Action action = ActionMapping.getAction( handlerContext.getMeta() );
        // 调度执行Action
        ViewAndModel viewAndModel = action.invoke( handlerContext );
        // 设置HandlerContext中的View和Model
        HandlerHelper.setViewAndModel( handlerContext, viewAndModel );
        // 输出View
        RenderFactory.me().render( handlerContext);
    }
}
