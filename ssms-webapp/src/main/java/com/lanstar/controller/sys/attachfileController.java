/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：attachfileController.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.NotMultipartException;
import com.lanstar.service.file.FileBean;
import com.lanstar.service.file.FileService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

public class attachfileController {
    // TODO: 先用spring的实现，以后再考虑换自己的实现
    private final CommonsMultipartResolver resolver = new CommonsMultipartResolver();

    // TODO: 示例页面，正式上线的时候以后移除掉
    public void index( HandlerContext context ) {}

    public void upload( HandlerContext context ) {
        HttpServletRequest request = context.getRequestContext().getRequest();
        // 判断是否是文件上传的请求，如果不是就直接报错返回。
        if ( !resolver.isMultipart( request ) ) throw new NotMultipartException();
        // 获取租户专用的文件服务
        FileService service = context.getFileService();
        // 转换请求
        MultipartHttpServletRequest multipart = resolver.resolveMultipart( request );
        // 使用文件服务保存文件
        String module = multipart.getParameter( "module" );
        int recordSid = Integer.parseInt( multipart.getParameter( "recordSid" ) );
        Iterator<String> fileMap = multipart.getFileNames();
        while ( fileMap.hasNext() ) {
            String next = fileMap.next();
            MultipartFile file = multipart.getFile( next );
            try {
                service.save( module, recordSid, new FileBean( file.getOriginalFilename(), file.getInputStream() ) );
            } catch ( SQLException | IOException e ) {
                throw new HandleException( e );
            }
        }
    }

    public ViewAndModel list( HandlerContext context ) {
        String module = context.getValue( "module" );
        int sid = Integer.parseInt( (String) context.getValue( "recordSid" ) );
        try {
            return context.returnWith().set( context.getFileService().list( module, sid ) );
        } catch ( SQLException e ) {
            throw new HandleException( e );
        }
    }
}
