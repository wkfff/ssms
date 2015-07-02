/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PublishTaskFactory.java
 * 创建时间：2015-07-01
 * 创建用户：张铮彬
 */

package com.lanstar.service.system.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PublishTaskFactory {
    private static PublishTaskFactory me = new PublishTaskFactory();
    private ExecutorService pools = Executors.newFixedThreadPool( 20 );
    private Map<Integer, PublishTask> tasks = new ConcurrentHashMap<>();

    public static PublishTaskFactory me() {
        return me;
    }

    public synchronized PublishTask startTask( int templateId ) {
        PublishTask task = tasks.get( templateId );
        if ( task == null ) {
            task = new PublishTaskImpl( templateId );
            tasks.put( templateId, task );
            pools.execute( task );
        }
        return task;
    }

    public synchronized void removeTask( int templateId ) {
        tasks.remove( templateId );
    }

    public PublishTask getTask( int templateId ) {
        return this.tasks.get( templateId );
    }
}
