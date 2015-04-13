/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HtmlRender.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;

import java.io.Writer;

class HtmlRender extends AbstractRender {
    private final String text;

    public HtmlRender( String text, RenderContext context ) {
        super( context );
        this.text = text;
    }

    @Override
    protected void renderHeader( RequestContext requestContext ) {
        RenderHelper.setHtmlHeader( requestContext );
    }

    @Override
    protected void innerRender() throws Exception {
        Writer writer = context.getOutput();
        writer.write( text );
        writer.flush();
    }
}
