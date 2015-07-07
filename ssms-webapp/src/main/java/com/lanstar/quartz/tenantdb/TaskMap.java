/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TaskMap.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.google.common.collect.Lists;

import java.util.List;

public class TaskMap {
    private static TaskMap me = new TaskMap();

    public static TaskMap me() {
        return me;
    }

    private List<Class<Task>> list = Lists.newArrayList();

    public List<Class<Task>> tasks() {
        return list;
    }

    public TaskMap add( Class<Task> task ) {
        list.add( task );
        return this;
    }
}
