/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TaskMap.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.google.common.collect.Lists;
import com.lanstar.common.kit.BeanKit;

import java.util.List;
import java.util.NoSuchElementException;

public class TaskMap {
    private static TaskMap me = new TaskMap();

    public static TaskMap me() {
        return me;
    }

    private List<Class<? extends Task>> list = Lists.newArrayList();

    public List<Class<? extends Task>> tasks() {
        return list;
    }

    public TaskMap add( Class<? extends Task> task ) {
        list.add( task );
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Task> T getTask( Class<T> task ) {
        for ( Class<? extends Task> taskClass : list ) {
            if ( task.isAssignableFrom( taskClass ) ) return (T) BeanKit.newInstance( taskClass );
        }
        throw new NoSuchElementException( "无法找到匹配的任务元素" );
    }
}
