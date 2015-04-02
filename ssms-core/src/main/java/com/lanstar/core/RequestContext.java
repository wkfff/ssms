/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RequestContext.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestContext {
    private final String uri;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public RequestContext(String uri, HttpServletRequest request, HttpServletResponse responst) {
        this.uri = uri;
        this.request = request;
        this.response = responst;
    }

    public String getUri() {
        return uri;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
