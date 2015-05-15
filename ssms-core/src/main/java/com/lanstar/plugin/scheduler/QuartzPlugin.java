/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：QuartzPlugin.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.scheduler;

import com.lanstar.app.App;
import com.lanstar.app.container.ContainerHelper;
import com.lanstar.common.helper.BeanHelper;
import com.lanstar.common.helper.XmlHelper;
import com.lanstar.common.log.LogHelper;
import com.lanstar.plugin.IAppPlugin;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

/**
 * 任务调度器
 */
public class QuartzPlugin implements IAppPlugin {
    private Scheduler scheduler;
    private String confpath = "/WEB-INF/scheduler.xml";

    @Override
    public void startup() {
        try {
            doStartup();
        } catch ( Throwable e ) {
            throw new RuntimeException( "自动任务处理后台执行错误", e );
        }
    }

    @Override
    public void shutdown() {
        try {
            if ( scheduler != null && scheduler.isStarted() ) scheduler.shutdown();
            LogHelper.info( getClass(), "停止定时任务成功..." );
        } catch ( SchedulerException e ) {
            LogHelper.error( getClass(), e, "停止定时任务失败..." );
        }
    }

    private void doStartup() throws SchedulerException, ParserConfigurationException, IOException, SAXException {
        if ( scheduler == null ) {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } else {
            if ( scheduler.isStarted() ) scheduler.shutdown();
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        }

        // 读出配置文件的定义
        Element root = XmlHelper.getDocumentElement( App.getServletContext().getResourceAsStream( this.confpath ) );
        XmlHelper.selectNodes( root, "job", new XmlHelper.INodeParser(){
            private int index = 0;

            @Override
            public void parse( Element item ){
                String taskid = XmlHelper.getAttribute( item, "id", "__T_" + ( ++index ) );
                String caption = XmlHelper.getAttribute( item, "caption", "未命名服务" );
                String cron = XmlHelper.getAttribute( item, "cron", "" );

                Class<? extends ScheduleTaskAbstr<?>> task = null;
                try {
                    task = SchTaskFactory.getClass( XmlHelper.getAttribute( item, "type", "java" ), XmlHelper.getAttribute( item, "value", "" ) );
                } catch ( Exception e2 ) {
                   
                    e2.printStackTrace();
                }

                if( task == null ) return;

                // 获得配置的参数
                ScheduleTaskAbstr<?> t = BeanHelper.newInstance( task );
                Object params = null;
                try{
                    params = t.getParameter( item );
                } catch(Exception e1){
                    e1.printStackTrace();
//                    throw new Exception( "读取自动任务配置项目[" + caption + "]的参数错误" );
                }

                try{
                    // 置换成对应的JOB类
                    JobDetail jobdetail = new JobDetailImpl( taskid, Scheduler.DEFAULT_GROUP, task );
                    jobdetail.getJobDataMap().put( "parameter", params );
                    CronTrigger trigger = new CronTriggerImpl( taskid + "__Trigger", null, cron );
                    scheduler.scheduleJob( jobdetail, trigger );
                    LogHelper.debug( this.getClass(), "--->载入自动调度任务：" + caption );
                } catch(SchedulerException e){
                    LogHelper.warn( this.getClass(), "--->无法载入自动调度任务：" + caption + "==>" + e.getMessage() );
                    e.printStackTrace();
                } catch ( ParseException e ) {
                    e.printStackTrace();
                }
            }
        } );

        scheduler.start();
    }

    public static QuartzPlugin me() {
        return ContainerHelper.getBean( QuartzPlugin.class );
    }

    /**
     * 添加调度任务
     */
    public static void addTask( String taskid, Class<? extends Job> task, Object params, String cron ) {
        Scheduler scheduler = me().scheduler;
        try {
            // 置换成对应的JOB类
            JobDetail jobdetail = JobBuilder.newJob( task )
                                            .withIdentity( taskid, Scheduler.DEFAULT_GROUP )
                                            .build();
            jobdetail.getJobDataMap().put( "parameter", params );
            Trigger trigger = TriggerBuilder.newTrigger()
                                            .withIdentity( taskid + "__Trigger" )
                                            .withSchedule( CronScheduleBuilder.cronSchedule( cron ) )
                                            .build();
            scheduler.scheduleJob( jobdetail, trigger );
            if ( !scheduler.isShutdown() ) scheduler.start();
            LogHelper.debug( QuartzPlugin.class, "--->载入自动调度任务：" + taskid );
        } catch ( SchedulerException e ) {
            LogHelper.warn( QuartzPlugin.class, e, "--->无法载入自动调度任务：%s:%s==>%s", taskid, cron, e.getMessage() );
        }
    }

    /**
     * 添加调度任务
     */
    public static void addTask( Class<? extends Job> task, Object params, String cron ) {
        addTask( task.getName(), task, params, cron );
    }

    /**
     * 删除调度任务
     */
    public static void deleteTask( String taskid ) {
        Scheduler scheduler = me().scheduler;
        try {
            scheduler.deleteJob( JobKey.jobKey( taskid, Scheduler.DEFAULT_GROUP ) );//删除任务
            scheduler.pauseTrigger( TriggerKey.triggerKey( taskid, Scheduler.DEFAULT_GROUP ) );//停止触发器
            scheduler.unscheduleJob( TriggerKey.triggerKey( taskid, Scheduler.DEFAULT_GROUP ) );//移除触发器
        } catch ( SchedulerException e ) {
            LogHelper.warn( QuartzPlugin.class, "--->无法停止自动调度任务：" + taskid + "==>" + e.getMessage() );
            e.printStackTrace();
        }
    }

    /**
     * 删除调度任务
     */
    public static void deleteTask( Class<? extends Job> task ) {
        deleteTask( task.getName() );
    }

    public static boolean hasTask( String taskid ) {
        Scheduler scheduler = me().scheduler;
        try {
            Set<JobKey> names = scheduler.getJobKeys( GroupMatcher.anyJobGroup() );
            for ( JobKey name : names ) {
                if ( taskid.equals( name.getName() ) ) return true;
            }
            return false;
        } catch ( SchedulerException ignored ) {
            return false;
        }
    }

    public static boolean hasTask( Class<? extends Job> task ) {
        return hasTask( task.getName() );
    }
}
