/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Render.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;

public abstract class AbstractRender implements Render {
    protected final RenderContext context;

    public AbstractRender( RenderContext context ) {
        this.context = context;
    }

    @Override
    public void render() throws RenderException {
        renderHeader( context.getRequestContext() );
        try {
            innerRender();
        } catch ( Exception e ) {
            throw new RenderException( e );
        }
    }

    protected abstract void renderHeader( RequestContext requestContext );

    protected abstract void innerRender() throws Exception;
}
