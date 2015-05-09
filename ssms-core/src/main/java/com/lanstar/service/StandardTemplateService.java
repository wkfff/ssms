/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateService.java
 * 创建时间：2015-05-09
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.ar.ARTable;
import com.lanstar.plugin.staticcache.CacheManager;
import com.lanstar.plugin.staticcache.impl.StandardTemplateCache;
import com.lanstar.service.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;

public class StandardTemplateService extends TenantService {
    /**
     * 根据身份标识获取租户服务
     *
     * @param identity 身份标识
     */
    public StandardTemplateService( Identity identity, HandlerDbContext dbContext ) {
        super( identity, dbContext );
    }

    public static List<Parameter> listStandardTemplate() {
        List<Parameter> list = new ArrayList<>();
        StandardTemplateCache templateCache = CacheManager.me().getCache( StandardTemplateCache.class );
        for ( String key : templateCache.getKeys() ) {
            list.add( new Parameter( key, templateCache.getValue( key ) ) );
        }
        return list;
    }

    public String getTableName( String code ) {
        return "SYS_STDTMP_FILE_" + code;
    }

    public int newFile( String code ) {
        ARTable table = getTable( getTableName( code ) )
                .value( "R_CREATE", identity.getId() )
                .value( "S_CREATE", identity.getName() )
                .value( "T_CREATE", "@now()" )
                .value( "R_CREATE", identity.getId() )
                .value( "S_CREATE", identity.getName() )
                .value( "S_CREATE", identity.getName() )
                .value( "R_TENANT", identity.getTenantId() )
                .value( "S_TENANT", identity.getTenantName() )
                .value( "P_TENANT", identity.getTenantType().getName() );

        table.insert();
        return dbContext.getSID();
    }

    private ARTable getTable( String tableName ) {
        return dbContext.withTable( tableName );
    }

    public int delFile( String code, String sid ) {
        return getTable( getTableName( code ) ).where( "SID=?", sid ).delete();
    }
}
