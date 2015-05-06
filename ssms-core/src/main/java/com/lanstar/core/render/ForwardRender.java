/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ForwardRender.java
 * 创建时间：2015-05-05
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.app.App;
import com.lanstar.app.ServletHelper;
import com.lanstar.core.RequestContext;

import javax.servlet.ServletException;
import java.io.IOException;

class ForwardRender implements Render {
    private final String location;
    private final RequestContext context;

    public ForwardRender( String location, RequestContext requestContext ) {
        this.location = location;
        this.context = requestContext;
    }

    @Override
    public void render() throws RenderException {
        String location = this.location;
        String viewFolder = App.config().getViewFolder();
        if ( !viewFolder.startsWith( "/" ) ) viewFolder = "/" + viewFolder;
        String contextPath = ServletHelper.getContextPath();
        contextPath = contextPath + viewFolder;
        if ( location.startsWith( "/" ) && !location.startsWith( contextPath ) )
            location = contextPath + location;
        try {
            App.getServletContext().getRequestDispatcher( location ).forward( context.getRequest(), context.getResponse() );
        } catch ( ServletException | IOException e ) {
            throw new RenderException( e );
        }
    }
}
