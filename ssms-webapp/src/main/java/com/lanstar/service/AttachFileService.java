/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachFileService.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.TenantContext;
import com.lanstar.model.system.AttachFile;
import com.lanstar.plugin.attachfile.*;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AttachFileService {
    private final TenantContext tenantContext;
    private final ResourceService resourceService;

    public AttachFileService( TenantContext tenantContext ) {
        this.tenantContext = tenantContext;
        resourceService = ResourceKit.getResourceService();
    }

    public List<AttachFile> list( String module, Integer sid ) {
        return AttachFile.dao.find(
                SqlKit.sql( "system.attachFile.list" ),
                module,
                sid,
                tenantContext.getTenantId(),
                tenantContext.getTenantType().getName() );
    }

    public void save( String module, int recordSid, FileResource file, Identity taget ) throws IOException {
        String innerName = UUID.randomUUID().toString();
        // 保存文件后再插入数据库
        String fullPath = getFileLocation( module, innerName, file.getExtension() );
        long length = resourceService.saveResource( file.getResource(), fullPath );
        AttachFile attachFile = new AttachFile();
        attachFile.setModule( module );
        attachFile.setRecordSid( recordSid );
        attachFile.setInnerFilename( innerName );
        attachFile.setOuterFilename( file.getFilenameWithoutExtension() );
        attachFile.setExtension( file.getExtension() );
        attachFile.setLength( length );
        attachFile.setPath( fullPath );
        attachFile.setServiceCode( resourceService.getServerCode() );
        ModelInjector.injectOpreator( attachFile, taget );
        attachFile.save();
    }

    public boolean remove( String module, Integer sid ) {
        AttachFile attachFile = AttachFile.dao.findFirst(
                SqlKit.sql( "system.attachFile.list" ),
                module,
                sid,
                tenantContext.getTenantId(),
                tenantContext.getTenantType().getName() );
        resourceService.removeResouce( attachFile.getPath() );
        attachFile.delete();
        return true;
    }

    public Resource getFile( Integer id ) {
        AttachFile attachFile = AttachFile.dao.findById( id );
        Resource resource = resourceService.getResource( attachFile.getPath() );
        return new ResourceNameWrap(resource, attachFile.getOuterFilename());
    }

    private String getFileLocation( String module, String filename, String extension ) {
        // 规则：tenant/module/date[mounth]/file.ext
        SimpleDateFormat format = new SimpleDateFormat( "yyyyMM" );
        return LocationBuilder.newInstance()
                              .folder( tenantContext.getTenantCode() )
                              .folder( module )
                              .folder( format.format( new Date() ) )
                              .filename( filename ).extension( extension )
                              .build();
    }

    private class ResourceNameWrap implements Resource {
        private final Resource resource;
        private final String name;

        public ResourceNameWrap( Resource resource, String name ) {
            this.resource = resource;
            this.name = name;
        }

        @Override
        public boolean exists() {return resource.exists();}

        @Override
        public long contentLength() throws IOException {return resource.contentLength();}

        @Override
        public long lastModified() throws IOException {return resource.lastModified();}

        @Override
        public Resource createRelative( String relativePath ) throws IOException {return resource.createRelative( relativePath );}

        @Override
        public boolean isReadable() {return resource.isReadable();}

        @Override
        public boolean isOpen() {return resource.isOpen();}

        @Override
        public File getFile() throws IOException {return resource.getFile();}

        @Override
        public String getFilename() {return this.name;}

        @Override
        public String getDescription() {return resource.getDescription();}

        @Override
        public InputStream getInputStream() throws IOException {return resource.getInputStream();}
    }
}
