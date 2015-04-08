/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：XmlWebSpringContainer.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.app.container;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;

public class XmlWebSpringContainer extends SpringContainer {
    private XmlWebApplicationContext applicationContext;
    private ContextLoader loader;

    private final ServletContext servletContext;
    private final String configLocation;

    public XmlWebSpringContainer(ServletContext servletContext, String configLocation) {
        this.servletContext = servletContext;
        this.configLocation = configLocation;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        applicationContext = new XmlWebApplicationContext();
        return applicationContext;
    }

    @Override
    public void startup() {
        applicationContext.setConfigLocation(configLocation);
        loader = new ContextLoader(applicationContext);
        loader.initWebApplicationContext(servletContext);
    }

    @Override
    public void shutdown() {
        loader.closeWebApplicationContext(servletContext);
    }
}
