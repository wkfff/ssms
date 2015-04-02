/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Action.java
 * 创建时间：2015年4月1日 下午2:07:30
 * 创建用户：林峰
 */
package com.lanstar.core;

import java.lang.reflect.Method;

/**
 * Action
 */
public class Action {
	private final Class<? extends Controller> controllerClass;
	private final String controllerKey;
	private final String actionKey;
	private final Method method;
	private final String methodName;
	private final String view;
	
	public Action(String controllerKey, String actionKey, Class<? extends Controller> controllerClass, Method method, String methodName,String view) {
		this.controllerKey = controllerKey;
		this.actionKey = actionKey;
		this.controllerClass = controllerClass;
		this.method = method;
		this.methodName = methodName;
		this.view = view;
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
	
	public String getView() {
		return view;
	}
	
	public String getMethodName() {
		return methodName;
	}
}
