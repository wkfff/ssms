/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：QuartzPlugin.java
 * 创建时间：2015年5月29日 下午4:08:00
 * 创建用户：林峰
 */
package com.lanstar.plugin.quartz;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.lanstar.common.kit.BeanKit;
import com.lanstar.common.kit.ResourceKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.common.log.Logger;
import com.lanstar.plugin.IPlugin;

/**
 * 任务调度
 *
 */
public class QuartzPlugin implements IPlugin {
    private static final String JOB = "job";
    private final Logger logger = Logger.getLogger(getClass());
    private Map<Job, String> jobs = Maps.newLinkedHashMap();
    private SchedulerFactory sf;
    private Scheduler scheduler;
    private String jobConfig;
    private String confConfig;
    private Map<String, String> jobProp;

    public QuartzPlugin(String jobConfig, String confConfig) {
        this.jobConfig = jobConfig;
        this.confConfig = confConfig;
    }

    public QuartzPlugin(String jobConfig) {
        this.jobConfig = jobConfig;
    }

    public QuartzPlugin() {
    }

    public QuartzPlugin add(String jobCronExp, Job job) {
        jobs.put(job, jobCronExp);
        return this;
    }

    @Override
    public boolean start() {
        loadJobsFromProperties();
        startJobs();
        return true;
    }

    private void startJobs() {
        try {
            if (StrKit.notBlank(confConfig)) {
                sf = new StdSchedulerFactory(confConfig);
            } else {
                sf = new StdSchedulerFactory();
            }
            scheduler = sf.getScheduler();
        } catch (SchedulerException e) {
            Throwables.propagate(e);
        }
        Set<Map.Entry<Job, String>> set = jobs.entrySet();
        try {
            for (Map.Entry<Job, String> entry : set) {
                Job job = entry.getKey();
                String jobDesc = job.getClass().getSimpleName();
                String jobClassName = job.getClass().getName();
                String jobCronExp = entry.getValue();
//                JobDetail jobDetail;
//                CronTrigger trigger;
                //JobDetail and CornTrigger are classes in 1.x version,but are interfaces in 2.X version.
//                if (VERSION_1.equals(version)) {
//                    jobDetail = Reflect.on("org.quartz.JobDetail").create(jobClassName, jobClassName, job.getClass()).get();
//                    trigger = Reflect.on("org.quartz.CronTrigger").create(jobClassName, jobClassName, jobCronExp).get();
//                } else {
//                    jobDetail = Reflect.on("org.quartz.JobBuilder").call("newJob", job.getClass()).call("withIdentity", jobClassName, jobClassName)
//                            .call("build").get();
//                    Object temp = Reflect.on("org.quartz.TriggerBuilder").call("newTrigger").get();
//                    temp = Reflect.on(temp).call("withIdentity", jobClassName, jobClassName).get();
//                    temp = Reflect.on(temp).call("withSchedule",
//                            Reflect.on("org.quartz.CronScheduleBuilder").call("cronSchedule", jobCronExp).get())
//                            .get();
//                    trigger = Reflect.on(temp).call("build").get();
//                }
//                Date ft = Reflect.on(scheduler).call("scheduleJob", jobDetail, trigger).get();
//                logger.debug(Reflect.on(jobDetail).call("getKey") + " has been scheduled to run at: " + ft + " " +
//                        "and repeat based on expression: " + Reflect.on(trigger).call("getCronExpression"));
                
                JobDetail jobDetail = new JobDetailImpl( jobClassName, Scheduler.DEFAULT_GROUP, job.getClass() );
                CronTrigger trigger = new CronTriggerImpl( jobClassName + "__Trigger", null, jobCronExp );
                Date ft = scheduler.scheduleJob( jobDetail, trigger );
                logger.debug("载入调度任务"+jobDesc + "(" + ft.toLocaleString() + ");执行周期: " + trigger.getCronExpression());
            }
            scheduler.start();
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

    private void loadJobsFromProperties() {
        if (StrKit.isBlank(jobConfig)) {
            return;
        }
        jobProp = ResourceKit.readProperties(jobConfig);
        Set<Map.Entry<String, String>> entries = jobProp.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            if (!key.endsWith(JOB) || !isEnableJob(enable(key))) {
                continue;
            }
            String jobClassName = jobProp.get(key) + "";
            String jobCronExp = jobProp.get(cronKey(key)) + "";
            Class<Job> job = BeanKit.getClass( jobClassName );
            try {
                jobs.put(job.newInstance(), jobCronExp);
            } catch (Exception e) {
                Throwables.propagate(e);
            }
        }
    }

    private String enable(String key) {
        return key.substring(0, key.lastIndexOf(JOB)) + "enable";
    }

    private String cronKey(String key) {
        return key.substring(0, key.lastIndexOf(JOB)) + "cron";
    }

    private boolean isEnableJob(String enableKey) {
        Object enable = jobProp.get(enableKey);
        if (enable != null && "false".equalsIgnoreCase((enable + "").trim())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean stop() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            Throwables.propagate(e);
        }
        return true;
    }

    public QuartzPlugin confConfig(String confConfig) {
        this.confConfig = confConfig;
        return this;
    }

    public QuartzPlugin jobConfig(String jobConfig) {
        this.jobConfig = jobConfig;
        return this;
    }
}
