/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandlerHelper.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.google.common.base.Splitter;
import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;

import java.util.List;

public class HandlerHelper {
    public static void setViewAndModel( HandlerContext handlerContext, ViewAndModel viewAndModel ) {
        handlerContext.setViewName( viewAndModel.getViewName() );
        handlerContext.setModel( viewAndModel.getModel() );
    }

    /**
     * 根据请求上下文实例化{@link HandlerContext}
     */
    public static HandlerContext createHandlerContext( RequestContext requestContext ) {
        return new HandlerContext( requestContext );
    }

    /**
     * 将URL解析为元数据
     *
     * @param url URL
     *
     * @return 元数据
     *
     * @see HandlerMeta
     */
    public static HandlerMeta parseUrl( String url ) {
        HandlerMeta meta = new HandlerMeta();
        // 解析/e/a02/index.html为：
        //     e=>大模块
        //     a02=>a02Controller
        //     index=>Action
        //     .html=>Render
        url = url.replace( '.', '/' );
        List<String> result = Splitter.on( '/' ).omitEmptyStrings().trimResults().splitToList( url );
        meta.module = result.get( 0 );
        meta.controller = result.get( 1 );
        meta.action = result.get( 2 );
        meta.render = result.get( 3 );

        return meta;
    }
}
