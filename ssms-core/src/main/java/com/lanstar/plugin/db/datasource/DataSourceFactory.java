/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DataSourceFactory.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */
package com.lanstar.plugin.db.datasource;

import com.lanstar.app.container.ContainerHelper;
import com.lanstar.common.helper.Asserts;
import com.lanstar.plugin.db.DbContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 数据源创建工厂，根据系统配置文件来定义 */
public final class DataSourceFactory {
    private Map<String, IDataSourceProvider> providerMap = new HashMap<>();

    public DataSourceFactory(Map<String, IDataSourceProvider> map) {
        for (String providerName : map.keySet()) {
            providerMap.put(providerName.toUpperCase(), map.get(providerName));
        }
        Asserts.notEmpty(providerMap.keySet(), "无法找到任何IDataSourceProvider实现！");
    }

    /**
     * 根据数据源名称从配置文件中获得指定的数据源定义
     */
    public DbContext create(Properties ps) {
        DataSourceConfig config = new DataSourceConfig(ps);
        DataSource datasource = providerMap.get(config.poolType.toUpperCase()).createDatasource(config);
        return new DbContext(datasource, config.dialect);
    }

    public static DataSourceFactory me(){
        return ContainerHelper.getBean(DataSourceFactory.class);
    }
}
