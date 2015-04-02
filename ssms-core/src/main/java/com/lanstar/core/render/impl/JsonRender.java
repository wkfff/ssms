/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JsonRender.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.render.impl;

import com.lanstar.core.HandlerContext;
import com.lanstar.core.RequestContext;
import com.lanstar.core.render.IRender;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderHelper;

public class JsonRender extends Render implements IRender {
    @Override
    protected void setHeader( RequestContext context ) {
        RenderHelper.setJsonHeader(context);
    }

    @Override
    protected void innerRender( HandlerContext context ) {
        // TODO: 解析数据对象，返回为json格式。
    }
}
