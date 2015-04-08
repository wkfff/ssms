/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MultiRender.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.render.impl;

import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.render.IRender;

import java.util.List;

public class MultiRender implements IRender {
    private final List<IRender> renders;

    public MultiRender( List<IRender> renders ) {
        this.renders = renders;
    }

    /**
     * 呈现页面。
     *
     * @param context 上下文
     */
    @Override
    public void render( HandlerContext context ) throws Exception {
        Exception lastException = null;
        for ( IRender render : renders ) {
            try {
                render.render( context );
                break;
            } catch ( Exception e ) {
                lastException = e;
            }
        }
        if ( lastException != null ) throw lastException;
    }
}
