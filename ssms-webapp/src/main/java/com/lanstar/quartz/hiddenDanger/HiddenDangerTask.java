/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HiddenDangerTask.java
 * 创建时间：上午10:14:25
 * 创建用户：苏志亮
 */
package com.lanstar.quartz.hiddenDanger;

import com.lanstar.app.Const;
import com.lanstar.model.system.HiddenDangerModel;
import com.lanstar.plugin.activerecord.*;
import com.lanstar.plugin.quartz.AbstractTask;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.plugin.tlds.DsKit;
import com.lanstar.plugin.tlds.IDataSourceProviderContainer;
import org.quartz.JobDataMap;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 隐患排查定时任务
 *
 */
public class HiddenDangerTask extends AbstractTask {

    @Override
    protected void doExecute( JobDataMap dataMap ) throws Exception {
        Config config = DbKit.getConfig( Const.TENANT_DB_NAME );
        DataSource dataSource = config.getDataSource();
        if ( dataSource instanceof IDataSourceProviderContainer ) {
            Map<String, IDataSourceProvider> allProvider = ((IDataSourceProviderContainer) dataSource).getAllProvider();
            for ( String dsName : allProvider.keySet() ) {
                DsKit.switchDs( dataSource, dsName );
                List<Record> records = Db.use( config.getName() ).find( SqlKit.sql( "tenant.hiddenDanger.list" ) );
                List<HiddenDangerModel> batch = new ArrayList<>(  );

                for ( Record record : records ) {
                    HiddenDangerModel model = new HiddenDangerModel();
                    model.setTemplateId( record.getInt( "R_TEMPLATE" ) );
                    model.setProfessionId( record.getInt( "P_PROFESSION" ) );
                    model.setTenantId( record.getInt( "R_TENANT" ) );
                    model.setTenantName( record.getStr( "S_TENANT" ) );
                    model.setTenantType( record.getStr( "P_TENANT" ) );
                    model.setCreateTime( record.getDate( "T_CREATE" ) );
                    batch.add( model );
                }

                ModelKit.batchSave( Db.use( Const.TENANT_DB_NAME ), batch );
            }
        }
    }

}
