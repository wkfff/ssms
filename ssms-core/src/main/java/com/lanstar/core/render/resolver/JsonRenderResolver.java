/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JsonRenderResolver.java
 * 创建时间：2015-04-12
 * 创建用户：张铮彬
 */

package com.lanstar.core.render.resolver;

import com.lanstar.core.MapModelBean;
import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderFactory;

public class JsonRenderResolver implements RenderResolver {
    @Override
    public Render getRender( ViewAndModel vam, RequestContext requestContext ) {
        return RenderFactory.me().getJsonRender( vam.getModel(), requestContext );
    }

    @Override
    public Render getErrorRender( HandleException e, RequestContext requestContext ) {
        MapModelBean bean = new MapModelBean();
        bean.put( "errorCode", e.getErrorCode() );
        if ( e.getAttachObject() != null ) bean.put( "errors", e.getAttachObject() );
        Render render = RenderFactory.me().getJsonRender( bean, requestContext );
        return RenderFactory.me().wrapErrorRender( render, e.getErrorCode(), requestContext );
    }
}
