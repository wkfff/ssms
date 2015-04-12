/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderFactory.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.ModelBean;
import com.lanstar.core.RequestContext;
import com.lanstar.core.handle.HandleException;

public class RenderFactory {
    private static RenderFactory instance = new RenderFactory();

    public static RenderFactory me() {
        return instance;
    }

    public FreemarkerRender getViewRender( String viewName, ModelBean model, RequestContext requestContext ) {
        return new FreemarkerRender( new ViewAndModelContext( viewName, model, requestContext ) );
    }

    public Render getJsonRender( ModelBean model, RequestContext context ) {
        return new JsonRender( new ModelRenderContext( model, context ) );
    }

    public Render getErrorRender( HandleException exception, RequestContext requestContext ) {
        return new ErrorRender( new ErrorRenderContext( exception, requestContext ) );
    }

    public Render getErrorRender( int errorCode, RequestContext requestContext ) {
        return new ErrorRender( new ErrorRenderContext( errorCode, requestContext ) );
    }
}
