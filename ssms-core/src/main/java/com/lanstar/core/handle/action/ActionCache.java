/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionCache.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.lanstar.common.helper.BeanHelper;
import com.lanstar.core.handle.HandlerContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ActionCache {
    private final Map<String, ControllerBean> controllerMap = new HashMap<>();

    public Action getAction( ActionMeta meta ) throws NoSuchMethodException {
        return getController( meta ).getAction( meta.getAction() );
    }

    private ControllerBean getController( ActionMeta meta ) throws NoSuchMethodException {
        String controllerName = meta.getClassName();
        ControllerBean controller = controllerMap.get( controllerName );
        if ( controller == null ) {
            Object instance;
            try {
                instance = BeanHelper.newInstance( controllerName );
            } catch ( IllegalArgumentException e ) {
                throw new NoSuchMethodException();
            }
            controller = new ControllerBean( instance );
            controllerMap.put( controllerName, controller );
        }
        return controller;
    }

    private static class ControllerBean {
        private final Map<String, Action> actionMap = new HashMap<>();
        private final Class<?> clazz;
        private Object controller;

        public ControllerBean( Object controller ) {
            this.controller = controller;
            clazz = this.controller.getClass();
        }

        public Action getAction( String actionName ) throws NoSuchMethodException {
            Action action = actionMap.get( actionName );
            if ( action == null ) {
                Method method = clazz.getMethod( actionName, HandlerContext.class );
                action = new Action( method, controller );
                actionMap.put( actionName, action );
            }

            return action;
        }
    }
}
