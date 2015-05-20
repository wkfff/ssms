/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionInvocation.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.core.aop.Interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ActionInvocation {

    private Controller controller;
    private Interceptor[] inters;
    private Action action;
    private int index = 0;

    private static final Object[] NULL_ARGS = new Object[0];    // Prevent new Object[0] by jvm for paras of action invocation.

    // ActionInvocationWrapper need this constructor
    protected ActionInvocation() {

    }

    ActionInvocation( Action action, Controller controller ) {
        this.controller = controller;
        this.inters = action.getInterceptors();
        this.action = action;
    }

    /**
     * Invoke the action.
     */
    public void invoke() {
        if ( index < inters.length )
            inters[index++].intercept( this );
        else if ( index++ == inters.length )    // index++ ensure invoke action only one time
            // try {action.getMethod().invoke(controller, NULL_ARGS);} catch (Exception e) {throw new RuntimeException(e);}
            try {
                action.getMethod().invoke( controller, NULL_ARGS );
            } catch ( InvocationTargetException e ) {
                Throwable cause = e.getTargetException();
                if ( cause instanceof RuntimeException )
                    throw (RuntimeException) cause;
                throw new RuntimeException( e );
            } catch ( RuntimeException e ) {
                throw e;
            } catch ( Exception e ) {
                throw new RuntimeException( e );
            }
    }

    /**
     * Return the controller of this action.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Return the action key.
     * actionKey = controllerKey + methodName
     */
    public String getActionKey() {
        return action.getActionKey();
    }

    /**
     * Return the controller key.
     */
    public String getControllerKey() {
        return action.getControllerKey();
    }

    /**
     * Return the method of this action.
     * <p>
     * You can getMethod.getAnnotations() to get annotation on action method to do more things
     */
    public Method getMethod() {
        return action.getMethod();
        /*
        try {
			return controller.getClass().getMethod(action.getMethod().getName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}*/
    }

    /**
     * Return the method name of this action's method.
     */
    public String getMethodName() {
        return action.getMethodName();
    }

    /**
     * Return view path of this controller.
     */
    public String getViewPath() {
        return action.getViewPath();
    }
}

