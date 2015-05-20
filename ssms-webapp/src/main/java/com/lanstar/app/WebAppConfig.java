/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：WebAppConfig.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.lanstar.common.kit.ServletKit;
import com.lanstar.config.*;
import com.lanstar.controller.HomeController;
import com.lanstar.core.Rapidware;
import com.lanstar.core.render.FreeMarkerRender;
import com.lanstar.identity.IdentityInterceptor;
import com.lanstar.model.Enterprise;
import com.lanstar.model.EnterpriseUser;
import com.lanstar.model.Navgate;
import com.lanstar.model.TenantUser;
import com.lanstar.plugin.activerecord.ActiveRecordPlugin;
import com.lanstar.plugin.druid.DruidPlugin;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class WebAppConfig extends RapidwareConfig {
    @Override
    public void configConstant( Constants constants ) {
        // 初始化日志
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext( context );
            context.reset();
            // 按顺序搜索配置文件
            InputStream resource = ServletKit.getResource( "/logback.xml", "/WEB-INF/logback.xml" );
            if ( resource == null )
                resource = WebAppConfig.class.getResourceAsStream( "/logback.xml" );
            configurator.doConfigure( resource );
        } catch ( JoranException ignored ) {
        }

        loadPropertyFile( "system.properties" );
        Boolean devMode = getPropertyToBoolean( "devMode", false );

        constants.setBaseViewPath( "/WEB-INF/views" );
        constants.setDevMode( devMode );
        configTemplate( FreeMarkerRender.getConfiguration() );
    }

    private void configTemplate( Configuration configuration ) {
        try {
            configuration.setSharedVariable( "_TITLE_", "安全生产标准化管理系统" );
            configuration.setSharedVariable( "BASE_PATH", Rapidware.me().getContextPath() );
        } catch ( TemplateModelException ignored ) {
        }
    }

    @Override
    public void configRoute( Routes routes ) {
        routes.add( "/", HomeController.class );
    }

    @Override
    public void configPlugin( Plugins plugins ) {
        DruidPlugin c3p0Plugin = new DruidPlugin( getProperty( "jdbc_url" ), getProperty( "jdbc_user" ), getProperty( "jdbc_password" ) );
        plugins.add( c3p0Plugin );

        ActiveRecordPlugin arp = new ActiveRecordPlugin( c3p0Plugin );
        plugins.add( arp );
        arp.addMapping( "SYS_TENANT_E_USER", "SID", EnterpriseUser.class );
        arp.addMapping( "SYS_TENANT_E", "SID", Enterprise.class );
        arp.addMapping( "TENANT_USER", TenantUser.class );
        arp.addMapping( "SYS_NAV", "SID", Navgate.class );
    }

    @Override
    public void configInterceptor( Interceptors interceptors ) {
        interceptors.add( new IdentityInterceptor() );
    }

    @Override
    public void configHandler( Handlers handlers ) {
    }
}
