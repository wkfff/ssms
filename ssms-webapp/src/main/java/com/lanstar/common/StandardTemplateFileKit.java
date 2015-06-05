/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateFileService.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.common;

import com.lanstar.plugin.staticcache.CacheManager;
import com.lanstar.plugin.staticcache.StandardTemplateCache;
import com.lanstar.plugin.staticcache.TemplateProp;
import com.lanstar.service.Parameter;

import java.util.ArrayList;
import java.util.List;

public abstract class StandardTemplateFileKit {
    public static List<Parameter> listStandardTemplate() {
        List<Parameter> list = new ArrayList<>();
        StandardTemplateCache templateCache = CacheManager.me().getCache( StandardTemplateCache.class );
        for ( String key : templateCache.getKeys() ) {
            TemplateProp prop = templateCache.getValue( key );
            list.add( prop.getParameter() );
        }
        return list;
    }

    /**
     * 根据给定的文件类型编码，获取对应的数据库表名。
     *
     * @param code 文件类型编码
     */
    /*public static String getSystemTableName( String code ) {
        return "SYS_STDTMP_FILE_" + code;
    }

    public static String getTenantTableName( String code ) {
        return "SSM_STDTMP_FILE_" + code;
    }*/
}
