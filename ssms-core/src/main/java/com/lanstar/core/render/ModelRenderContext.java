/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ModelRenderContext.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.common.bean.IJsonable;
import com.lanstar.core.MapModelBean;
import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;

class ModelRenderContext extends RequestRenderContext{
    private final ModelBean model;

    public ModelRenderContext( ModelBean model, RequestContext context ) {
        super(context);
        this.model = model;
    }

    public IJsonable getModel() {
        // 如果模型为空白，则获取上下文中的所有本地变量作为输出
        if ( model == null ) return new MapModelBean( context.getValues() );
        return this.model;
    }
}
