/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DsKit.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.tlds;

import com.lanstar.common.Asserts;
import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.activerecord.Config;

import javax.sql.DataSource;

public class DsKit {
    public static void switchDs( DataSource dataSource, String dsName ) {
        Asserts.notNull( dataSource, "dataSource can not be null" );
        Asserts.notEmpty( dsName, "dsName can not be empty" );

        if ( dataSource instanceof ThreadLocalDataSource ) {
            ThreadLocalDataSource ds = (ThreadLocalDataSource) dataSource;
            if ( StrKit.isEmpty( dsName ) ) ds.setDataSource();
            else ds.setDataSource( dsName );
        }
    }

    public static void switchDs( Config config, String dsName ) {
        Asserts.notNull( config, "config can not be null" );
        switchDs( config.getDataSource(), dsName );
    }
}
