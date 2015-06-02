/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ScheduleTaskAbstr.java
 * 创建时间：2015年5月29日 下午4:38:00
 * 创建用户：林峰
 */

package com.lanstar.plugin.quartz;

import com.lanstar.common.log.Logger;

import org.quartz.*;

@SuppressWarnings("deprecation")
public abstract class AbstractTask implements StatefulJob {
    final Logger log = Logger.getLogger( this.getClass() );
    final public void execute( JobExecutionContext context ) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        log.info("开始执行自动任务[" + jobDetail.getKey() + "]......" );
        JobDataMap dataMap = jobDetail.getJobDataMap();
        try {
            doExecute( dataMap );
            log.info( "自动任务执行成功。" );
        } catch ( Exception e ) {
            log.error( "自动任务执行失败：" + e.getMessage() );
        }
    }

    /**
     * 真正执行的任务调度类
     * @param dataMap 有状态任务的数据存储集合
     */
    protected abstract void doExecute( JobDataMap dataMap ) throws Exception;
}
