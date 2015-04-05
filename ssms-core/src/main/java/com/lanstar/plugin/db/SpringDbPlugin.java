/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SpringDbPlugin.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.db;

import com.lanstar.db.DbContext;
import com.lanstar.db.datasource.DataSourceFactory;

public class SpringDbPlugin implements IDbPlugin {
    private final DataSourceFactory dataSourceFactory;
    private DbContext dbContext;

    public SpringDbPlugin() {
        dataSourceFactory = DataSourceFactory.me();
    }

    /** 启动插件运行 */
    @Override
    public void startup() {
        // TODO:获取主数据源
        //        dbContext = dataSourceFactory.create(App.config().getProperties());
        //        dbContext.startup();
    }

    /** 关闭插件运行 */
    @Override
    public void shutdown() {
        dbContext.shutdown();
    }

    @Override
    public DbContext getDbContext() {
        return dbContext;
    }
}
