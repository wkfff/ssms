/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ApplicationContext.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import com.lanstar.app.container.ContainerHelper;
import com.lanstar.app.container.IContainer;
import com.lanstar.app.container.XmlWebSpringContainer;
import com.lanstar.plugin.AppPlugins;
import com.lanstar.plugin.IAppPlugin;

import javax.servlet.ServletContext;

/**
 * 应用程序上下文，整个系统的核心。
 */
class ApplicationContext {
    public static final String CLASSPATH_CONF_SPRING_XML = "classpath:/conf/spring.xml";
    ServletContext servletContext;
    IContainer container;
    AppPlugins plugins = new AppPlugins();
    private IAppConfiguration config;

    public ApplicationContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        // TODO：以后替换容器直接修改这个地方。
        container = new XmlWebSpringContainer(servletContext, CLASSPATH_CONF_SPRING_XML);
    }

    public void startup() {
        // 启动过程：
        // 1. 容器优先启动
        // 2. 读取配置文件
        // 3. 启动其他所有插件
        container.startup();
        config();
        // 加载所有插件
        plugins.add(this.container.getBeansByType(IAppPlugin.class).values());
        plugins.startup();
    }

    public void shutdown() {
        // 关闭过程，和启动过程刚好相反。
        plugins.shutdown();
        container.shutdown();
    }

    public <T extends IAppPlugin> T getPlugin(Class<T> type) {
        return plugins.getPlugin(type);
    }

    public IAppConfiguration config() {
        if (config == null) config = ContainerHelper.getBean( IAppConfiguration.class );
        return config;
    }
}
