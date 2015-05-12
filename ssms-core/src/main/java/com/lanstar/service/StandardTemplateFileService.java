/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateService.java
 * 创建时间：2015-05-09
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.db.ar.ARTable;
import com.lanstar.plugin.staticcache.CacheManager;
import com.lanstar.plugin.staticcache.impl.StandardTemplateCache;
import com.lanstar.service.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板文件服务
 */
public class StandardTemplateFileService extends TenantService {
    /**
     * 根据身份标识获取租户服务
     */
    public StandardTemplateFileService( IdentityContext context ) {
        super( context );
    }

    /**
     * 列举出当前系统支持的所有的标准模板文件
     */
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
    public String getTableName( String code ) {
        return "SYS_STDTMP_FILE_" + code;
    }

    /**
     * 根据指定的类型编码创建一个新文件模板
     *
     * @param code 文件类型编码
     */
    public int newFile( String code ) {
        ARTable table = getTable( getTableName( code ) )
                .value( "R_CREATE", getIdentityContext().getId() )
                .value( "S_CREATE", getIdentityContext().getName() )
                .value( "T_CREATE", "@now()" )
                .value( "R_CREATE", getIdentityContext().getId() )
                .value( "S_CREATE", getIdentityContext().getName() )
                .value( "S_CREATE", getIdentityContext().getName() )
                .value( "R_TENANT", getIdentityContext().getTenantId() )
                .value( "S_TENANT", getIdentityContext().getTenantName() )
                .value( "P_TENANT", getIdentityContext().getTenantType().getName() );

        table.insert();
        return getIdentityContext().getDbContext().getSID();
    }

    /**
     * 删除指定类型的文件
     *
     * @param code 文件类型编码
     * @param sid  要删除的文件的SID
     */
    public int delFile( String code, String sid ) {
        return getTable( getTableName( code ) ).where( "SID=?", sid ).delete();
    }

    private ARTable getTable( String tableName ) {
        return getIdentityContext().withTable( tableName );
    }
}
