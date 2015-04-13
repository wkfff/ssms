/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：d.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.scheduler;

import com.lanstar.common.log.LogHelper;
import org.quartz.*;
import org.w3c.dom.Element;

public abstract class ScheduleTaskAbstr<T> implements Job {

    @SuppressWarnings("unchecked")
    final public void execute( JobExecutionContext context ) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        LogHelper.info( this.getClass(), "开始执行自动任务[" + jobDetail.getKey() + "]......" );
        JobDataMap dataMap = jobDetail.getJobDataMap();
        final T params = (T) dataMap.get( "parameter" );
        
        try {
            doExecute( params );
            LogHelper.info( this.getClass(), "自动任务执行成功。" );
        } catch ( Exception e ) {
            LogHelper.error( this.getClass(), "自动任务执行失败：" + e.getMessage() );
            e.printStackTrace();
        }
    }

    /**
     * 真正执行的任务调度类
     */
    protected abstract void doExecute( T params ) throws Exception;

    public abstract T getParameter( Element node ) throws Exception;

    public abstract T getParameter( String para ) throws Exception;
}
