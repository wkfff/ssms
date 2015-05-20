/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MultipartRequest.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.upload;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MultipartRequest extends HttpServletRequestWrapper {

    private static String saveDirectory;
    private static int maxPostSize;
    private static String encoding;
    private static boolean isMultipartSupported = false;
    private static final DefaultFileRenamePolicy fileRenamePolicy = new DefaultFileRenamePolicy();

    private List<UploadFile> uploadFiles;
    private com.oreilly.servlet.MultipartRequest multipartRequest;

    static void init( String saveDirectory, int maxPostSize, String encoding ) {
        MultipartRequest.saveDirectory = saveDirectory;
        MultipartRequest.maxPostSize = maxPostSize;
        MultipartRequest.encoding = encoding;
        MultipartRequest.isMultipartSupported = true;    // 在OreillyCos.java中保障了, 只要被初始化就一定为 true
    }

    public MultipartRequest( HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding ) {
        super( request );
        wrapMultipartRequest( request, saveDirectory, maxPostSize, encoding );
    }

    public MultipartRequest( HttpServletRequest request, String saveDirectory, int maxPostSize ) {
        super( request );
        wrapMultipartRequest( request, saveDirectory, maxPostSize, encoding );
    }

    public MultipartRequest( HttpServletRequest request, String saveDirectory ) {
        super( request );
        wrapMultipartRequest( request, saveDirectory, maxPostSize, encoding );
    }

    public MultipartRequest( HttpServletRequest request ) {
        super( request );
        wrapMultipartRequest( request, saveDirectory, maxPostSize, encoding );
    }

    /**
     * 添加对相对路径的支持
     * 1: 以 "/" 开头或者以 "x:开头的目录被认为是绝对路径
     * 2: 其它路径被认为是相对路径, 需要 RapidwareConfig.uploadedFileSaveDirectory 结合
     */
    private String handleSaveDirectory( String saveDirectory ) {
        if ( saveDirectory.startsWith( "/" ) || saveDirectory.indexOf( ":" ) == 1 )
            return saveDirectory;
        else
            return MultipartRequest.saveDirectory + saveDirectory;
    }

    private void wrapMultipartRequest( HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding ) {
        if ( !isMultipartSupported )
            throw new RuntimeException( "Oreilly cos.jar is not found, Multipart post can not be supported." );

        saveDirectory = handleSaveDirectory( saveDirectory );

        File dir = new File( saveDirectory );
        if ( !dir.exists() ) {
            if ( !dir.mkdirs() ) {
                throw new RuntimeException(
                        "Directory " + saveDirectory + " not exists and can not create directory." );
            }
        }

//		String content_type = request.getContentType();
//        if (content_type == null || content_type.indexOf("multipart/form-data") == -1) {
//        	throw new RuntimeException("Not multipart request, enctype=\"multipart/form-data\" is not found of form.");
//        }

        uploadFiles = new ArrayList<>();

        try {
            multipartRequest = new com.oreilly.servlet.MultipartRequest( request, saveDirectory, maxPostSize, encoding, fileRenamePolicy );
            Enumeration files = multipartRequest.getFileNames();
            while ( files.hasMoreElements() ) {
                String name = (String) files.nextElement();
                String filesystemName = multipartRequest.getFilesystemName( name );

                // 文件没有上传则不生成 UploadFile, 这与 cos的解决方案不一样
                if ( filesystemName != null ) {
                    String originalFileName = multipartRequest.getOriginalFileName( name );
                    String contentType = multipartRequest.getContentType( name );
                    UploadFile uploadFile = new UploadFile( name, saveDirectory, filesystemName, originalFileName, contentType );
                    if ( isSafeFile( uploadFile ) )
                        uploadFiles.add( uploadFile );
                }
            }
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    private boolean isSafeFile( UploadFile uploadFile ) {
        if ( uploadFile.getFileName().toLowerCase().endsWith( ".jsp" ) ) {
            uploadFile.getFile().delete();
            return false;
        }
        return true;
    }

    public List<UploadFile> getFiles() {
        return uploadFiles;
    }

    /**
     * Methods to replace HttpServletRequest methods
     */
    public Enumeration getParameterNames() {
        return multipartRequest.getParameterNames();
    }

    public String getParameter( String name ) {
        return multipartRequest.getParameter( name );
    }

    public String[] getParameterValues( String name ) {
        return multipartRequest.getParameterValues( name );
    }

    public Map getParameterMap() {
        Map map = new HashMap();
        Enumeration enumm = getParameterNames();
        while ( enumm.hasMoreElements() ) {
            String name = (String) enumm.nextElement();
            map.put( name, multipartRequest.getParameterValues( name ) );
        }
        return map;
    }
}
