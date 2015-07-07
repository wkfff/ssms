/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PoiRender.java
 * 创建时间：2015-07-06
 * 创建用户：张铮彬
 */

package com.lanstar.render.aspose;

import com.aspose.words.DocumentBuilder;
import com.lanstar.common.log.Logger;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class AsposeRender extends Render {

    protected final Logger LOG = Logger.getLogger( getClass() );
    private final String data;
    private final OutputFormat OutputFormat;
    private final String fileName;

    public AsposeRender( String data, String fileName, OutputFormat OutputFormat ) {
        this.data = data;
        this.fileName = fileName;
        this.OutputFormat = OutputFormat;
    }

    public static AsposeRender me( String data, String fileName, OutputFormat OutputFormat ) {
        return new AsposeRender( data, fileName, OutputFormat );
    }

    @Override
    public void render() {
        response.reset();
        response.setHeader( "Content-disposition", "attachment; filename=" + encodeFileName( fileName ) );
        response.setContentType( OutputFormat.contentType( getEncoding() ) );
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            DocumentBuilder builder = new DocumentBuilder();
            builder.insertHtml( data );
            builder.getDocument().save( os, OutputFormat.format() );
        } catch ( Exception e ) {
            throw new RenderException( e );
        } finally {
            try {
                if ( os != null ) {
                    os.flush();
                    os.close();
                }
            } catch ( IOException e ) {
                LOG.error( e.getMessage(), e );
            }
        }
    }

    private String encodeFileName( String fileName ) {
        String s = fileName + OutputFormat.extension();
        try {
            return new String( s.getBytes( "GBK" ), "ISO8859-1" );
        } catch ( UnsupportedEncodingException e ) {
            return s;
        }
    }
}
