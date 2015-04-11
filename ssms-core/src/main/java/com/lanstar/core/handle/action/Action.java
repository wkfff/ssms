/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Action.java
 * 创建时间：2015年4月1日 下午2:07:30
 * 创建用户：林峰
 */
package com.lanstar.core.handle.action;

import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;

import java.lang.reflect.Method;

/**
 * Action
 */
class Action {
    private final Method method;
    private final Object controller;

    public Action( Method method, Object controller ) {
        this.method = method;
        this.controller = controller;
    }

    public ViewAndModel invoke( HandlerContext context ) throws ActionException {
        try {
            return (ViewAndModel) method.invoke( controller, context );
        } catch ( ReflectiveOperationException e ) {
            throw new ActionException( e.getLocalizedMessage(), e.getCause() );
        }
    }
}
