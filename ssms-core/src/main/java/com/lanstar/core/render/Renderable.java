package com.lanstar.core.render;

import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;

import java.io.Writer;

public interface Renderable {

    /**
     * 获取请求上下文
     */
    RequestContext getRequestContext();

    /**
     * 获取输出流
     */
    Writer getOutput();

    /**
     * 获取Render
     */
    String getRender();

    /**
     * 获取View路径
     */
    String getViewPath();

    /**
     * 从上下文中取值
     *
     * @param key key
     *
     * @return value
     */
    Object getValue( String key );

    /**
     * 获取模型
     */
    ModelBean getModel();
}
