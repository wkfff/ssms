/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：test.java
 * 创建时间：2015年6月1日 上午11:55:14
 * 创建用户：林峰
 */
package com.lanstar.plugin.quartz;

import org.quartz.JobDataMap;

import com.lanstar.common.log.Logger;
import com.lanstar.plugin.quartz.AbstractTask;

/**
 * 任务示例
 *
 */
public class demo extends AbstractTask {
    @Override
    protected void doExecute( JobDataMap dataMap ) throws Exception {
        int n = 0;
        if (dataMap.containsKey( "n" )) n = dataMap.getIntValue( "n" );
        n++;
        dataMap.put( "n", n );
        Logger.getLogger( getClass() ).info( "任务调度测试"+n);
    }
}
