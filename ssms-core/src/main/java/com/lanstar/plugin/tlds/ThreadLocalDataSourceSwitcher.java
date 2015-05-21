/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ThreadLocalDataSourceSwitcher.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.tlds;

import com.lanstar.common.kit.StrKit;
import com.lanstar.core.ActionInvocation;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.plugin.activerecord.Config;
import com.lanstar.plugin.activerecord.DbKit;

import javax.sql.DataSource;

public class ThreadLocalDataSourceSwitcher implements Interceptor {
    static DsConfig getConfigWithDsConfig( ActionInvocation ai ) {
        DsConfig dsConfig = ai.getMethod().getAnnotation( DsConfig.class );
        if ( dsConfig == null )
            dsConfig = ai.getController().getClass().getAnnotation( DsConfig.class );
        return dsConfig;
    }

    @Override
    public void intercept( ActionInvocation ai ) {
        DsConfig dsConfig = getConfigWithDsConfig( ai );
        Config config;
        String dsName = null;
        if ( dsConfig != null ) {
            config = DbKit.getConfig( dsConfig.value() );
            dsName = dsConfig.name();
            if ( config == null )
                throw new RuntimeException( "Config not found with DsConfig" );
        } else config = DbKit.getConfig();

        DataSource dataSource = config.getDataSource();

        switchDataSource( ai, dataSource, dsName );
    }

    public void switchDataSource( ActionInvocation ai, DataSource dataSource ) {
        switchDataSource( ai, dataSource, null );
    }

    public void switchDataSource( ActionInvocation ai, DataSource dataSource, String dsName ) {
        if ( dataSource instanceof ThreadLocalDataSource ) {
            ThreadLocalDataSource ds = (ThreadLocalDataSource) dataSource;
            if ( StrKit.isEmpty( dsName ) ) ds.setDataSource();
            else ds.setDataSource( dsName );
            return;
        }
        ai.invoke();
    }
}
