/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：XmlRender.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import freemarker.template.Template;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class XmlRender extends FreeMarkerRender {

    private static final String contentType = "text/xml; charset=" + getEncoding();

    public XmlRender( String view ) {
        super( view );
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void render() {
        response.setContentType( contentType );

        Map root = new HashMap();
        for ( Enumeration<String> attrs = request.getAttributeNames(); attrs.hasMoreElements(); ) {
            String attrName = attrs.nextElement();
            root.put( attrName, request.getAttribute( attrName ) );
        }

        PrintWriter writer = null;
        try {
            Template template = getConfiguration().getTemplate( view );
            writer = response.getWriter();
            template.process( root, writer );        // Merge the data-model and the template
        } catch ( Exception e ) {
            throw new RenderException( e );
        } finally {
            if ( writer != null )
                writer.close();
        }
    }
}