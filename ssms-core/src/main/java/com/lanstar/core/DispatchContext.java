/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DispatchContext.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchContext {
    private final String target;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private boolean handled;

    public DispatchContext( String target, HttpServletRequest request, HttpServletResponse response ) {
        this.target = target;
        this.request = request;
        this.response = response;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled( boolean handled ) {
        this.handled = handled;
    }

    public String getTarget() {
        return target;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
