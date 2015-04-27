/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantDbCache.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache.impl;

import com.lanstar.common.helper.PropertiesHelper;
import com.lanstar.db.datasource.DataSourceConfig;
import com.lanstar.plugin.staticcache.PropertiesFilesCache;

import java.io.File;
import java.util.Map;
import java.util.Properties;

public class TenantDbCache extends PropertiesFilesCache<DataSourceConfig> {
    @Override
    protected String getPath() {
        return "WEB-INF/db";
    }

    @Override
    public String getName() {
        return "Tenant DB";
    }

    @Override
    protected void parseProperties( Properties properties, Map<String, DataSourceConfig> pools, File file ) {
        DataSourceConfig config = new DataSourceConfig( properties );
        String id = PropertiesHelper.get( properties, "id", "demo" );
        pools.put( id, config );
    }
}
