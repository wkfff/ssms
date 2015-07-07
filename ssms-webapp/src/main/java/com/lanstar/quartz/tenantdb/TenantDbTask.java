/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateTodoTask.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.lanstar.app.Const;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.IDataSourceProvider;
import com.lanstar.plugin.quartz.AbstractTask;
import com.lanstar.plugin.tlds.DsKit;
import com.lanstar.plugin.tlds.IDataSourceProviderContainer;
import org.quartz.JobDataMap;

import javax.sql.DataSource;
import java.util.Map;

public class TenantDbTask extends AbstractTask {
    @Override
    protected void doExecute( JobDataMap dataMap ) throws Exception {
        Config config = DbKit.getConfig( Const.TENANT_DB_NAME );
        DataSource dataSource = config.getDataSource();
        if ( dataSource instanceof IDataSourceProviderContainer ) {
            Map<String, IDataSourceProvider> allProvider = ((IDataSourceProviderContainer) dataSource).getAllProvider();
            for ( String dsName : allProvider.keySet() ) {
                DsKit.switchDs( dataSource, dsName );

                for ( Class<Task> task : TaskMap.me().tasks() ) {
                    Task instance = task.newInstance();
                    instance.execute( dataSource );
                }
            }
        }
    }
}
