/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AnnotationWebSpringContainer.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.app.container;

import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

public abstract class AnnotationWebSpringContainer extends SpringContainer {
    private AnnotationConfigWebApplicationContext applicationContext;
    private final ServletContext servletContext;

    public AnnotationWebSpringContainer(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        Class<?>[] configClasses = getRootConfigClasses();
        if (!ObjectUtils.isEmpty(configClasses)) {
            AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
            rootAppContext.register(configClasses);
            applicationContext = rootAppContext;
            return rootAppContext;
        } else {
            return null;
        }
    }

    @Override
    public void startup() {
        applicationContext.setServletContext(servletContext);
        applicationContext.refresh();
    }

    protected abstract Class<?>[] getRootConfigClasses();

    public WebApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
