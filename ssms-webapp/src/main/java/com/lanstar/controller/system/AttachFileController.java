/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachFileController.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lanstar.common.log.Logger;
import com.lanstar.core.Controller;
import com.lanstar.core.upload.UploadFile;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.AttachFile;
import com.lanstar.plugin.attachfile.FileResource;
import com.lanstar.plugin.attachfile.Resource;
import com.lanstar.plugin.attachfile.ResourceRender;
import com.lanstar.service.AttachFileService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AttachFileController extends Controller {
    private final Logger log = Logger.getLogger( AttachFileController.class );

    public void list() {
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );

        String module = getPara( "module" );
        Integer sid = getParaToInt( "recordSid" );

        AttachFileService service = identityContext.getAttachFileService();
        List<AttachFile> list = service.list( module, sid );
        List<Map<String, Object>> maps = Lists.transform( list, new Function<AttachFile, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply( AttachFile input ) {
                Map<String, Object> m = Maps.newLinkedHashMap();
                m.put( "id", input.getId() );
                m.put( "module", input.getModule() );
                m.put( "recordSid", input.getRecordSid() );
                m.put( "outerFilename", input.getOuterFilename() );
                m.put( "length", input.getLength() );
                return m;
            }
        } );

        renderJson( maps );
    }

    public void upload() {
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        AttachFileService service = identityContext.getAttachFileService();

        List<UploadFile> files = getFiles();
        String module = getPara( "module" );
        int recordSid = getParaToInt( "recordSid" );
        for ( UploadFile file : files ) {
            try {
                service.save( module, recordSid, new FileResource( file ), identityContext.getIdentity() );
            } catch ( IOException e ) {
                throw new RuntimeException( e );
            }
            if ( file.getFile().delete() == false ) log.warn( "删除文件%s失败", file.getFileName() );
        }
        renderNull();
    }

    public void del() {
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        AttachFileService service = identityContext.getAttachFileService();

        String module = getPara( "module" );
        Integer sid = getParaToInt( "recordSid" );
        renderJson( service.remove( module, sid ) );
    }

    public void down() {
        Integer id = getParaToInt( "id" );
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        AttachFileService service = identityContext.getAttachFileService();
        Resource file = service.getFile( id );
        render( new ResourceRender( file ) );
    }
}
