/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SchTaskFactory.java
 * 创建时间：2015年5月14日 下午3:36:22
 * 创建用户：林峰
 */
package com.lanstar.plugin.scheduler;

import com.lanstar.plugin.scheduler.impl.SPTask;

public final class SchTaskFactory {
    
    @SuppressWarnings("unchecked")
    public static Class<? extends ScheduleTaskAbstr<?>> getClass(String actionType, String actionValue) throws Exception {
        Class<? extends ScheduleTaskAbstr<?>> ta = null;

        if ("sp".equalsIgnoreCase(actionType)) ta = SPTask.class ; // SP为存储过程的任务类
        else if ("java".equalsIgnoreCase(actionType)) { 
            try {
                ta = (Class<? extends ScheduleTaskAbstr<?>>) Class.forName(actionValue);
            }
            catch (Throwable e) {
                throw new Exception("调度任务"+actionType+"对应的类不存在或错误，请联系员.");
            }
        }
        return ta;
    }
}