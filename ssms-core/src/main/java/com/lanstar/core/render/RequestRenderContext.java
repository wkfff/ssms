/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RequestRenderContext.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;

import java.io.IOException;
import java.io.Writer;

public class RequestRenderContext implements RenderContext {
    protected final RequestContext context;

    public RequestRenderContext(RequestContext context) {
        this.context = context;
    }

    @Override
    public RequestContext getRequestContext() {
        return context;
    }

    @Override
    public Writer getOutput() throws IOException {
        return context.getResponse().getWriter();
    }
}
