/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionCache.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.lanstar.common.helper.BeanHelper;
import com.lanstar.plugin.staticcache.Cache;

import java.util.Map;

class ControllerCache extends Cache<Controller> {
    public Controller getValue( ActionMeta meta ) {
        String controllerName = meta.getClassName();
        Controller controller = getValue( controllerName );
        if ( controller == null ) {
            Class<Object> clazz = BeanHelper.getClass( controllerName );
            Object instance = BeanHelper.newInstance( clazz );
            controller = new Controller( instance, clazz );
            put( controllerName, controller );
        }
        return controller;
    }

    @Override
    protected void load( Map<String, Controller> pools ) {}

    /**
     * 获取缓存名称
     */
    @Override
    public String getName() {
        return "Controller Cache";
    }
}
