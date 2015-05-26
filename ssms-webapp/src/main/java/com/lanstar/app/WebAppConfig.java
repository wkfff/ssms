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
import com.lanstar.app.model.SystemModelMapping;
import com.lanstar.app.model.TenantModelMapping;
import com.lanstar.app.route.EnterpriseRoutes;
import com.lanstar.app.route.GovernmentRoutes;
import com.lanstar.app.route.ReviewRoutes;
import com.lanstar.app.route.SystemRoutes;
import com.lanstar.common.kit.JsonKit;
import com.lanstar.common.kit.ServletKit;
import com.lanstar.config.*;
import com.lanstar.controller.HomeController;
import com.lanstar.core.Rapidware;
import com.lanstar.core.render.FreeMarkerRender;
import com.lanstar.identity.IdentityInterceptor;
import com.lanstar.plugin.activerecord.ActiveRecordPlugin;
import com.lanstar.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.lanstar.plugin.druid.DruidPlugin;
import com.lanstar.plugin.sqlinxml.SqlInXmlPlugin;
import com.lanstar.plugin.staticcache.StandardTemplateCache;
import com.lanstar.plugin.staticcache.StaticCachePlugin;
import com.lanstar.plugin.tlds.ThreadLocalDataSourcePlugin;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.Configuration;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class WebAppConfig extends RapidwareConfig {
    @Override
    public void configConstant( Constants me ) {
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

        me.setBaseViewPath( "/WEB-INF/views" );
        me.setDevMode( devMode );
        configTemplate( FreeMarkerRender.getConfiguration() );
    }

    private void configTemplate( Configuration me ) {
        try {
            me.setSharedVariable( "_TITLE_", "安全生产标准化管理系统" );
            me.setSharedVariable( "BASE_PATH", Rapidware.me().getContextPath() );
            // 添加JSON扩展方法               by 张铮彬#2015-5-7
            me.setSharedVariable( "json", new JsonMethod() );
        } catch ( TemplateModelException ignored ) {
        }
    }

    @Override
    public void configRoute( Routes me ) {
        me.add( "/", HomeController.class );
        me.add( EnterpriseRoutes.me() );
        me.add( ReviewRoutes.me() );
        me.add( GovernmentRoutes.me() );
        me.add( SystemRoutes.me() );
    }

    @Override
    public void configPlugin( Plugins me ) {
        me.add( new SqlInXmlPlugin().setPath( "/sqls" ) );
        me.add( new StaticCachePlugin( new StandardTemplateCache() ) );

        // main ds
        DruidPlugin c3p0Plugin = new DruidPlugin( getProperty( "jdbc_url" ), getProperty( "jdbc_user" ), getProperty( "jdbc_password" ) );
        me.add( c3p0Plugin );

        // tenant ds
        ThreadLocalDataSourcePlugin threadLocalDataSourcePlugin = new ThreadLocalDataSourcePlugin();
        // TODO: add tenant db config
        threadLocalDataSourcePlugin.set( "tenant0", c3p0Plugin );
        me.add( threadLocalDataSourcePlugin );

        ActiveRecordPlugin arp = new ActiveRecordPlugin( c3p0Plugin )
                .setShowSql( true )
                .setContainerFactory( new CaseInsensitiveContainerFactory() );
        me.add( arp );
        new SystemModelMapping().mappingTo( arp );

        // tenant ds
        ActiveRecordPlugin arp2 = new ActiveRecordPlugin( Const.TENANT_DB_NAME, c3p0Plugin )
                .setShowSql( true )
                .setContainerFactory( new CaseInsensitiveContainerFactory() );
        me.add( arp2 );
        new TenantModelMapping().mappingTo( arp );
    }

    @Override
    public void configInterceptor( Interceptors me ) {
        me.add( new IdentityInterceptor() );
        me.add( new TenantDsSwitcher() );
        me.add( new TxByActionMethods( "save", "batchSave" ) );
    }

    @Override
    public void configHandler( Handlers me ) {
        me.add( new ActionJsHandle() );
    }

    private class JsonMethod implements TemplateMethodModelEx {
        @Override
        public Object exec( List arguments ) throws TemplateModelException {
            if ( arguments.size() != 1 ) return "";
            Object object = arguments.get( 0 );
            if ( object instanceof WrapperTemplateModel ) {
                object = ((WrapperTemplateModel) object).getWrappedObject();
            }
            return JsonKit.toJson( object );
        }
    }
}
