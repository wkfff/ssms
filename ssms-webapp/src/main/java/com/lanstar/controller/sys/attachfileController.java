/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：attachfileController.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
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

public class attachfileController extends DefaultController {
    // TODO: 先用spring的实现，以后再考虑换自己的实现
    private final CommonsMultipartResolver resolver = new CommonsMultipartResolver();

    public attachfileController() {
        super( "SYS_ATTACH_FILE" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return null;
    }

    public void upload( HandlerContext context ) {
        HttpServletRequest request = context.getRequestContext().getRequest();
        if ( !resolver.isMultipart( request ) ) throw new NotMultipartException();

        FileService service = new FileService( context.getRequestContext().getIdentityContxt().getIdentity(  ) );
        MultipartHttpServletRequest multipart = resolver.resolveMultipart( request );
        String module = multipart.getParameter( "module" );
        int recordSid = Integer.parseInt( multipart.getParameter( "recordSid" ) );
        Iterator<String> fileMap = multipart.getFileNames();
        while ( fileMap.hasNext() ) {
            String next = fileMap.next();
            MultipartFile file = multipart.getFile( next );
            try {
                service.save( module, recordSid, new FileBean(file.getOriginalFilename(), file.getInputStream() ) );
            } catch ( SQLException | IOException e ) {
                throw new HandleException( e );
            }
        }
    }
}
