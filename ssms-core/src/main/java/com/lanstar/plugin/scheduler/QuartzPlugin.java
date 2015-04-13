/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：QuartzPlugin.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.scheduler;

import com.lanstar.app.container.ContainerHelper;
import com.lanstar.common.log.LogHelper;
import com.lanstar.plugin.IAppPlugin;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Set;

/**
 * 任务调度器
 */
public class QuartzPlugin implements IAppPlugin {
    private Scheduler scheduler;

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

    private void doStartup() throws SchedulerException {
        if ( scheduler == null ) {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } else {
            if ( scheduler.isStarted() ) scheduler.shutdown();
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        }

//        // 读出配置文件的定义
//        Element root = XmlHelper.getDocumentElement( App.getServletContext().getResourceAsStream( this.confpath ) );
//        XmlHelper.selectNodes( root, "job", new XmlHelper.INodeParser(){
//            private int index = 0;
//
//            @Override
//            public void parse( Element item ){
//                String taskid = XmlUtil.getAttribute( item, "id", "__T_" + ( ++index ) );
//                String caption = XmlUtil.getAttribute( item, "caption", "未命名服务" );
//                String cron = XmlUtil.getAttribute( item, "cron", "" );
//
//                Class<? extends ScheduleTaskAbstr<?>> task = SchTaskFactory.getClass( XmlUtil.getAttribute( item, "type", "java" ), XmlUtil.getAttribute( item, "value", "" ) );
//
//                if( task == null ) return;
//
//                // 获得配置的参数
//                ScheduleTaskAbstr<?> t = ClassUtil.newInstance( task );
//                Object params = null;
//                try{
//                    params = t.getParameter( item );
//                } catch(Exception e1){
//                    throw new WebException( "读取自动任务配置项目[" + caption + "]的参数错误" );
//                }
//
//                try{
//                    // 置换成对应的JOB类
//                    JobDetail jobdetail = new JobDetail( taskid, Scheduler.DEFAULT_GROUP, task );
//                    jobdetail.getJobDataMap().put( "parameter", params );
//                    jobdetail.setName( caption );
//                    CronTrigger trigger = new CronTrigger( taskid + "__Trigger", null, cron );
//                    scheduler.scheduleJob( jobdetail, trigger );
//                    LogHelper.debug( this.getClass(), "--->载入自动调度任务：" + caption );
//                } catch(ParseException e){
//                    LogHelper.warn( this.getClass(), "--->无法载入自动调度任务：" + caption + "==>" + e.getMessage() );
//                    e.printStackTrace();
//                } catch(SchedulerException e){
//                    LogHelper.warn( this.getClass(), "--->无法载入自动调度任务：" + caption + "==>" + e.getMessage() );
//                    e.printStackTrace();
//                }
//            }
//        } );
//
//        //工作提醒定时任务
//        String GET_ALL_WR_SQL = "SELECT SID,C_CAPTION,C_CRON FROM SYS_WR_MODEL WHERE B_VALID='1' AND B_TIMEER='1'";
//        DB.query( GET_ALL_WR_SQL, new IRowAction(){
//            @Override
//            public void process( ResultSet rs, int index ) throws Exception{
//                String sid = StringUtil.trim( rs.getString( 1 ) );
//                String name = StringUtil.trim( rs.getString( 2 ) );
//                String cron = StringUtil.trim( rs.getString( 3 ) );
//
//                if( !StringUtil.isBlank( cron ) ){
//                    String taskid = StringUtil.getUUID();
//                    try{
//                        scheduler.pauseTrigger( name, Scheduler.DEFAULT_GROUP );//停止触发器
//                        scheduler.unscheduleJob( name, Scheduler.DEFAULT_GROUP );//移除触发器
//                        scheduler.deleteJob( name, Scheduler.DEFAULT_GROUP );//删除任务
//                        // 置换成对应的JOB类
//                        JobDetail jobdetail = new JobDetail( taskid, Scheduler.DEFAULT_GROUP, WorkRemindTask.class );
//                        jobdetail.getJobDataMap().put( "parameter", sid );
//                        jobdetail.setName( name );
//                        CronTrigger trigger = new CronTrigger( taskid + "__Trigger", null, cron );
//                        scheduler.scheduleJob( jobdetail, trigger );
//                        LogHelper.debug( this.getClass(), "--->载入自动调度任务：" + name );
//                    } catch(ParseException e){
//                        LogHelper.warn( this.getClass(), "--->无法载入自动调度任务：" + name + "==>" + e.getMessage() );
//                        e.printStackTrace();
//                    } catch(SchedulerException e){
//                        LogHelper.warn( this.getClass(), "--->无法载入自动调度任务：" + name + "==>" + e.getMessage() );
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } );
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
