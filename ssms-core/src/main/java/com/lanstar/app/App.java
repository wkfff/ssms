/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：App.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.lanstar.app.container.IContainer;
import com.lanstar.common.helper.ServletHelper;
import com.lanstar.plugin.IAppPlugin;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.InputStream;

public class App {
    private static ApplicationContext context;

    static void startup(ServletContext servletContext) {
        initLogContext(servletContext);

        context = new ApplicationContext(servletContext);
        context.startup();
    }

    static void shutdown() {
        if (context!=null) context.shutdown();
    }

    private static void initLogContext(ServletContext servletContext) {
        // 初始化日志
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            // 按顺序搜索配置文件
            InputStream resource = ServletHelper.getResource(servletContext, "/logback.xml", "/WEB-INF/logback.xml");
            if (resource == null)
                resource = ApplicationInitializer.class.getResourceAsStream("/conf/logback.xml");
            configurator.doConfigure(resource);
        } catch (JoranException ignored) {
        }
    }

    public static IContainer getContainer() {
        return context.container;
    }

    public static ServletContext getServletContext() {
        return context.servletContext;
    }

    public static IAppConfiguration config(){
        return context.config();
    }

    public static <T extends IAppPlugin> T getPlugin(Class<T> type){
        return context.getPlugin(type);
    }
}
