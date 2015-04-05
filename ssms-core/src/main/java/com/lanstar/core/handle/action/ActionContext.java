/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionContext.java
 * 创建时间：2015-04-05
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.google.common.base.Joiner;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.HandlerHelper;

public class ActionContext extends HandlerContext {
    private final ActionMeta meta;

    /**
     * 初始化实例。只能在包内初始化，因此请使用{@link HandlerHelper}来实例化。
     *
     * @param context 请求上下文
     *
     * @see HandlerHelper
     */
    ActionContext( HandlerContext context ) {
        super( context.getRequestContext() );
        this.meta = ActionMeta.parseUrl( context.getRequestContext().getUri() );
        setViewName( meta != null ? meta.getAction() : null );
    }

    /**
     * 获取请求的元数据信息
     *
     * @return 元数据
     */
    public ActionMeta getMeta() {
        return meta;
    }

    /**
     * 获取View路径
     */
    @Override
    public String getViewPath() {
        return Joiner.on( '/' ).join( meta.getModule(), meta.getController(), getViewName() + TEMPLATE_SUFFIX );
    }

    /**
     * 获取Render
     */
    @Override
    public String getRender() {
        return meta.getRender();
    }

    public static ActionContext newInstance(HandlerContext context) {
        return new ActionContext( context );
    }

    public boolean isActionRequest() {
        return meta != null;
    }
}
