/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Render.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;

public abstract class Render implements IRender {
    @Override
    public final void render( Renderable context ) throws Exception {
        setHeader( context.getRequestContext() );
        innerRender( context );
    }

    /**
     * 设置响应头
     *
     * @param requestContext 请求上下文。
     */
    protected abstract void setHeader( RequestContext requestContext );

    /**
     * 内部Render过程
     *
     * @param context 请求上下文
     */
    protected abstract void innerRender( Renderable context ) throws Exception;
}
