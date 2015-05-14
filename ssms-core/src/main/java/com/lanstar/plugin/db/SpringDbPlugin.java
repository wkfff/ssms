/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SpringDbPlugin.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.db;

import com.lanstar.app.App;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.db.DbContext;
import com.lanstar.db.datasource.DataSourceConfig;
import com.lanstar.db.datasource.DataSourceFactory;
import com.lanstar.plugin.staticcache.CacheManager;
import com.lanstar.plugin.staticcache.impl.TenantDbCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpringDbPlugin implements IDbPlugin {
    private final DataSourceFactory dataSourceFactory;
    private DbContext dbContext;
    private Map<String, DbContext> dbContextMap = new ConcurrentHashMap<>();

    public SpringDbPlugin() {
        dataSourceFactory = DataSourceFactory.me();
    }

    /**
     * 启动插件运行
     */
    @Override
    public void startup() {
        dbContext = dataSourceFactory.create( App.config().getProperties() );
        dbContext.startup();

        for ( DataSourceConfig config : CacheManager.me().getCache( TenantDbCache.class ).getValues() ) {
            String id = config.getId();
            if ( StringHelper.isBlank( id ) ) continue;
            DbContext dbContext = dataSourceFactory.create( config );
            dbContextMap.put( id, dbContext );
        }
    }

    /**
     * 关闭插件运行
     */
    @Override
    public void shutdown() {
        dbContext.shutdown();
    }

    @Override
    public DbContext getDbContext() {
        return dbContext;
    }

    /**
     * 获取指定的数据库上下文
     *
     * @return 数据库上下文实例
     *
     * @see DbContext
     */
    @Override
    public DbContext getDbContext( String dbName ) {
        return dbContextMap.get( dbName );
    }

    /** 获取所有的数据库上下文，不包括主数据库上下文。 */
    @Override
    public Iterable<DbContext> getDbContexts(){
        return dbContextMap.values();
    }
}
