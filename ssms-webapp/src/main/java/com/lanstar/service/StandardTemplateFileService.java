/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateFileService.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.identity.IdentityContext;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.staticcache.CacheManager;
import com.lanstar.plugin.staticcache.StandardTemplateCache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StandardTemplateFileService {
    private final IdentityContext context;

    public StandardTemplateFileService( IdentityContext context ) {
        this.context = context;
    }

    public static List<Parameter> listStandardTemplate() {
        List<Parameter> list = new ArrayList<>();
        StandardTemplateCache templateCache = CacheManager.me().getCache( StandardTemplateCache.class );
        for ( String key : templateCache.getKeys() ) {
            list.add( new Parameter( key, templateCache.getValue( key ) ) );
        }
        return list;
    }

    /**
     * 根据给定的文件类型编码，获取对应的数据库表名。
     *
     * @param code 文件类型编码
     */
    public static String getTableName( String code ) {
        return "SYS_STDTMP_FILE_" + code;
    }

    public int newFile( String code ) {
        Record record = new Record();
        record.set( "R_CREATE", context.getId() )
              .set( "S_CREATE", context.getName() )
              .set( "T_CREATE", new Date() )
              .set( "R_UPDATE", context.getId() )
              .set( "S_UPDATE", context.getName() )
              .set( "T_UPDATE", new Date() )
              .set( "R_TENANT", context.getTenantId() )
              .set( "S_TENANT", context.getTenantName() )
              .set( "P_TENANT", context.getTenantType().getName() );
        context.getTenantDb().save( getTableName( code ), "SID", record );

        return record.getLong( "SID" ).intValue();
    }

    public boolean delFile( String code, int tmpfileId ) {
        context.getAttachTextService().del( "STDTMP_FILE_" + code, "C_CONTENT", tmpfileId );
        return context.getTenantDb().deleteById( getTableName( code ), "SID", tmpfileId );
    }
}
