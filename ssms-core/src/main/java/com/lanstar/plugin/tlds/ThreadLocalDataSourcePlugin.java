/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ThreadLocalDataSourcePlugin.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.tlds;

import com.lanstar.plugin.IPlugin;
import com.lanstar.plugin.activerecord.IDataSourceProvider;

import javax.sql.DataSource;
import java.util.Map;

public class ThreadLocalDataSourcePlugin implements IPlugin, IDataSourceProvider {

    private ThreadLocalDataSource dataSource = new ThreadLocalDataSource();

    public void setDataSource( String dsName ) {dataSource.setDataSource( dsName );}

    public Map<String, IDataSourceProvider> getAllProvider() {return dataSource.getAllProvider();}

    public ThreadLocalDataSource set( String dsName, IDataSourceProvider provider ) {return dataSource.set( dsName, provider );}

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public boolean start() {
        dataSource.init();
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }
}
