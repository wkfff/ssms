/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Config.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.common.log.Logger;
import com.lanstar.config.*;
import com.lanstar.plugin.IPlugin;

import java.util.List;

final class Config {
    private static final Constants constants = new Constants();
    private static final Routes routes = new Routes() {
        public void config() {}
    };
    private static final Plugins plugins = new Plugins();
    private static final Interceptors interceptors = new Interceptors();
    private static final Handlers handlers = new Handlers();
    private static Logger log;

    public static void configRapidware( RapidwareConfig rapidwareConfig ) {
        constants.setEncoding( "UTF-8" );
        rapidwareConfig.configConstant( constants );
        initLoggerFactory();
        rapidwareConfig.configRoute( routes );
        rapidwareConfig.configPlugin( plugins );
        startPlugins();    // very important!!!
        rapidwareConfig.configInterceptor( interceptors );
        rapidwareConfig.configHandler( handlers );
    }

    public static Constants getConstants() {
        return constants;
    }

    public static Routes getRoutes() {
        return routes;
    }

    public static Plugins getPlugins() {
        return plugins;
    }

    public static Interceptors getInterceptors() {
        return interceptors;
    }

    public static Handlers getHandlers() {
        return handlers;
    }

    private static void startPlugins() {
        List<IPlugin> pluginList = plugins.getPluginList();
        if ( pluginList == null )
            return;

        for ( IPlugin plugin : pluginList ) {
            try {
                // process ActiveRecordPlugin devMode
                if ( plugin instanceof com.lanstar.plugin.activerecord.ActiveRecordPlugin ) {
                    com.lanstar.plugin.activerecord.ActiveRecordPlugin arp = (com.lanstar.plugin.activerecord.ActiveRecordPlugin) plugin;
                    if ( arp.getDevMode() == null )
                        arp.setDevMode( constants.getDevMode() );
                }

                if ( plugin.start() == false ) {
                    String message = "Plugin start error: " + plugin.getClass().getName();
                    log.error( message );
                    throw new RuntimeException( message );
                }
            } catch ( Exception e ) {
                String message = "Plugin start error: " + plugin.getClass().getName() + ". \n" + e.getMessage();
                log.error( message, e );
                throw new RuntimeException( message, e );
            }
        }
    }

    private static void initLoggerFactory() {
        Logger.init();
        log = Logger.getLogger( Config.class );
        RapidwareFilter.initLogger();
    }
}
