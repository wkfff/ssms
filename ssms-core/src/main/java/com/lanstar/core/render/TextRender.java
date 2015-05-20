/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TextRender.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import java.io.IOException;
import java.io.PrintWriter;

public class TextRender extends Render {

    private static final String DEFAULT_CONTENT_TYPE = "text/plain; charset=" + getEncoding();

    private String text;
    private String contentType;

    public TextRender( String text ) {
        this.text = text;
    }

    public TextRender( String text, String contentType ) {
        this.text = text;
        this.contentType = contentType;
    }

    public TextRender( String text, ContentType contentType ) {
        this.text = text;
        this.contentType = contentType.value();
    }

    public void render() {
        PrintWriter writer = null;
        try {
            response.setHeader( "Pragma", "no-cache" );    // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
            response.setHeader( "Cache-Control", "no-cache" );
            response.setDateHeader( "Expires", 0 );

            if ( contentType == null ) {
                response.setContentType( DEFAULT_CONTENT_TYPE );
            } else {
                response.setContentType( contentType );
                response.setCharacterEncoding( getEncoding() );
            }

            writer = response.getWriter();
            writer.write( text );
            writer.flush();
        } catch ( IOException e ) {
            throw new RenderException( e );
        } finally {
            if ( writer != null )
                writer.close();
        }
    }
}