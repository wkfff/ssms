package com.lanstar.core.render;

import com.lanstar.core.RequestContext;

import java.io.IOException;
import java.io.Writer;

public interface RenderContext {
    /**
     * 获取请求上下文
     */
    RequestContext getRequestContext();

    /**
     * 获取输出流
     */
    Writer getOutput() throws IOException;
}
