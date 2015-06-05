/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.template.TemplatePropCache;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.staticcache.CacheManager;
import com.lanstar.plugin.staticcache.TemplateProp;

public class TemplateFile extends Model<TemplateFile> {
    public static TemplateFile dao = new TemplateFile();

    /** 获取模板属性 */
    public TemplateProp getTemplateProp() {
        TemplatePropCache cache = CacheManager.me().getCache( TemplatePropCache.class );
        return cache.getValue( getTemplateFileCode() );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getTemplateFileCode() {
        return getStr( "P_TMPFILE" );
    }

    public Integer getTemplateFileId() {
        return getInt( "R_TMPFILE" );
    }

    public void setTemplateFileId( Integer id ) {
        set( "R_TMPFILE", id );
    }

    public Integer getSourceFileId() {
        return getInt( "R_SOURCE" );
    }

    public void setFolder( TemplateFolder tenantFolder ) {
        set( "R_SID", tenantFolder.getId() );
        set( "S_NAME", tenantFolder.getName() );
    }

    public void setSourceFile( com.lanstar.model.system.TemplateFile file ) {
        set( "R_SOURCE", file.getId() );
    }
}

