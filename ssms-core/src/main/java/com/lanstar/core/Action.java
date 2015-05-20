/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Action.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.core.aop.Interceptor;

import java.lang.reflect.Method;

class Action {

    private final Class<? extends Controller> controllerClass;
    private final String controllerKey;
    private final String actionKey;
    private final Method method;
    private final String methodName;
    private final Interceptor[] interceptors;
    private final String viewPath;

    public Action( String controllerKey, String actionKey, Class<? extends Controller> controllerClass, Method method, String methodName, Interceptor[] interceptors, String viewPath ) {
        this.controllerKey = controllerKey;
        this.actionKey = actionKey;
        this.controllerClass = controllerClass;
        this.method = method;
        this.methodName = methodName;
        this.interceptors = interceptors;
        this.viewPath = viewPath;
    }

    public Class<? extends Controller> getControllerClass() {
        return controllerClass;
    }

    public String getControllerKey() {
        return controllerKey;
    }

    public String getActionKey() {
        return actionKey;
    }

    public Method getMethod() {
        return method;
    }

    public Interceptor[] getInterceptors() {
        return interceptors;
    }

    public String getViewPath() {
        return viewPath;
    }

    public String getMethodName() {
        return methodName;
    }
}
