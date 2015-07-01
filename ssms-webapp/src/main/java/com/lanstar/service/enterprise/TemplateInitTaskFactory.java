/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateInitManager.java
 * 创建时间：2015-06-30
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TemplateInitTaskFactory {
    private static TemplateInitTaskFactory instance = new TemplateInitTaskFactory();
    // TODO 在配置文件中添加线程池配置
    private ExecutorService service = Executors.newFixedThreadPool( 20 );
    private Map<String, TemplateInitTask> pools = new ConcurrentHashMap<>();

    public static TemplateInitTaskFactory me() {
        return instance;
    }

    public synchronized TemplateInitTask startTask( Tenant tenant, Profession profession, Template systemTemplate, Identity operator ) {
        String key = buildKey( tenant, profession );
        TemplateInitTask task = pools.get( key );
        if ( task == null ) {
            task = new TemplateInitTaskImpl( tenant, profession, systemTemplate, operator );
            pools.put( key, task );
            service.execute( task );
        }
        return task;
    }

    public synchronized void removeTask( Tenant tenant, Profession profession, Template systemTemplate ) {
        String key = buildKey( tenant, profession );
        pools.remove( key );
    }

    private String buildKey( Tenant tenant, Profession profession ) {
        return tenant.getTenantCode() + "_" + profession.getId();
    }
}
