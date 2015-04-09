/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Controller.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.lanstar.core.handle.HandlerContext;
import com.lanstar.plugin.staticcache.Cache;

import java.lang.reflect.Method;
import java.util.Map;

class Controller extends Cache<Action> {
    private Object controller;
    private Class<Object> clazz;

    public Controller( Object controller, Class<Object> clazz ) {
        this.controller = controller;
        this.clazz = clazz;
    }

    @Override
    protected void load( Map<String, Action> pools ) {}

    @Override
    public Action getValue( String actionName ) {
        Action action = super.getValue( actionName );

        if ( action == null ) {
            try {
                Method method = clazz.getMethod( actionName, HandlerContext.class );
                action = new Action( method, controller );
                put( actionName, action );
            } catch ( NoSuchMethodException e ) {
                throw new NoSuchActionException( e );
            }
        }

        return action;
    }

    /**
     * 获取缓存名称
     */
    @Override
    public String getName() {
        return "Controller";
    }
}
