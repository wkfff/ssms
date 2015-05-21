/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileRender.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.common.kit.PathKit;
import com.lanstar.common.kit.StrKit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileRender extends Render {

    private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";

    private File file;
    private static String fileDownloadPath;
    private static ServletContext servletContext;
    private static String webRootPath;

    public FileRender( File file ) {
        this.file = file;
    }

    public FileRender( String fileName ) {
        fileName = fileName.startsWith( "/" ) ? webRootPath + fileName : fileDownloadPath + fileName;
        this.file = new File( fileName );
    }

    static void init( String fileDownloadPath, ServletContext servletContext ) {
        FileRender.fileDownloadPath = fileDownloadPath;
        FileRender.servletContext = servletContext;
        webRootPath = PathKit.getWebRootPath();
    }

    public void render() {
        if ( file == null || !file.isFile() ) {
            RenderFactory.me().getErrorRender( 404 ).setContext( request, response ).render();
            return;
        }

        // ---------
        response.setHeader( "Accept-Ranges", "bytes" );
        response.setHeader( "Content-disposition", "attachment; filename=" + encodeFileName( file.getName() ) );
        String contentType = servletContext.getMimeType( file.getName() );
        response.setContentType( contentType != null ? contentType : DEFAULT_CONTENT_TYPE );

        // ---------
        if ( StrKit.isBlank( request.getHeader( "Range" ) ) )
            normalRender();
        else
            rangeRender();
    }

    private String encodeFileName( String fileName ) {
        try {
            return new String( fileName.getBytes( "GBK" ), "ISO8859-1" );
        } catch ( UnsupportedEncodingException e ) {
            return fileName;
        }
    }

    private void normalRender() {
        response.setHeader( "Content-Length", String.valueOf( file.length() ) );
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream( new FileInputStream( file ) );
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            for ( int len; (len = inputStream.read( buffer )) != -1; ) {
                outputStream.write( buffer, 0, len );
            }
            outputStream.flush();
        } catch ( IOException e ) {
            if ( getDevMode() ) throw new RenderException( e );
        } catch ( Exception e ) {
            throw new RenderException( e );
        } finally {
            if ( inputStream != null )
                try {inputStream.close();} catch ( IOException ignored ) {}
            if ( outputStream != null )
                try {outputStream.close();} catch ( IOException ignored ) {}
        }
    }

    private void rangeRender() {
        Long[] range = { null, null };
        processRange( range );

        String contentLength = String.valueOf( range[1] - range[0] + 1 );
        response.setHeader( "Content-Length", contentLength );
        response.setStatus( HttpServletResponse.SC_PARTIAL_CONTENT );    // status = 206

        // Content-Range: bytes 0-499/10000
        StringBuilder contentRange = new StringBuilder( "bytes " ).append( String.valueOf( range[0] ) )
                                                                  .append( "-" )
                                                                  .append( String.valueOf( range[1] ) )
                                                                  .append( "/" )
                                                                  .append( String.valueOf( file.length() ) );
        response.setHeader( "Content-Range", contentRange.toString() );

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            long start = range[0];
            long end = range[1];
            inputStream = new BufferedInputStream( new FileInputStream( file ) );
            if ( inputStream.skip( start ) != start )
                throw new RuntimeException( "File skip error" );
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            long position = start;
            for ( int len; position <= end && (len = inputStream.read( buffer )) != -1; ) {
                if ( position + len <= end ) {
                    outputStream.write( buffer, 0, len );
                    position += len;
                } else {
                    for ( int i = 0; i < len && position <= end; i++ ) {
                        outputStream.write( buffer[i] );
                        position++;
                    }
                }
            }
            outputStream.flush();
        } catch ( IOException e ) {
            if ( getDevMode() ) throw new RenderException( e );
        } catch ( Exception e ) {
            throw new RenderException( e );
        } finally {
            if ( inputStream != null )
                try {inputStream.close();} catch ( IOException ignored ) {}
            if ( outputStream != null )
                try {outputStream.close();} catch ( IOException ignored ) {}
        }
    }

    /**
     * Examples of byte-ranges-specifier values (assuming an entity-body of length 10000):
     * The first 500 bytes (byte offsets 0-499, inclusive): bytes=0-499
     * The second 500 bytes (byte offsets 500-999, inclusive): bytes=500-999
     * The final 500 bytes (byte offsets 9500-9999, inclusive): bytes=-500
     * Or bytes=9500-
     */
    private void processRange( Long[] range ) {
        String rangeStr = request.getHeader( "Range" );
        int index = rangeStr.indexOf( ',' );
        if ( index != -1 )
            rangeStr = rangeStr.substring( 0, index );
        rangeStr = rangeStr.replace( "bytes=", "" );

        String[] arr = rangeStr.split( "-", 2 );
        if ( arr.length < 2 )
            throw new RuntimeException( "Range error" );

        long fileLength = file.length();
        for ( int i = 0; i < range.length; i++ ) {
            if ( StrKit.notBlank( arr[i] ) ) {
                range[i] = Long.parseLong( arr[i].trim() );
                if ( range[i] >= fileLength )
                    range[i] = fileLength - 1;
            }
        }

        // Range format like: 9500-
        if ( range[0] != null && range[1] == null ) {
            range[1] = fileLength - 1;
        }
        // Range format like: -500
        else if ( range[0] == null && range[1] != null ) {
            range[0] = fileLength - range[1];
            range[1] = fileLength - 1;
        }

        // check final range
        if ( range[0] == null || range[0] > range[1] )
            throw new RuntimeException( "Range error" );
    }
}