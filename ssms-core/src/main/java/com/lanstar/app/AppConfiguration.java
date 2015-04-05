/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AppConfiguration.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.app;

import com.google.common.collect.Maps;
import com.lanstar.app.container.ContainerHelper;
import com.lanstar.common.helper.ExceptionHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class AppConfiguration implements IAppConfiguration {
    public static final String TEMPLATE_SUFFIX = "template_suffix";
    private static final String VIEWS_FOLDER = "views_folder";
    private static final String RESOURCE_FOLDER = "resource_folder";

    private Map<String, String> map;
    private Properties ps = new Properties();

    private String templateSuffix;
    private String viewsFolder;
    private String resourceFolder;

    public AppConfiguration( String... paths ) {
        try {
            if ( paths == null || paths.length == 0 ) throw new IOException();
            InputStream stream = ContainerHelper.getResource( paths );
            ps.load( stream );
            map = Maps.fromProperties( ps );
        } catch ( IOException e ) {
            throw ExceptionHelper.runtimeException( e, "配置文件[%s]不存在或加载错误...", Arrays.toString( paths ) );
        }

        parse();
    }

    private void parse() {
        templateSuffix = getProperty( TEMPLATE_SUFFIX, "ftl" );
        if ( !templateSuffix.startsWith( "." ) ) templateSuffix = "." + templateSuffix;
        viewsFolder = getProperty( VIEWS_FOLDER, "views" );
        resourceFolder = getProperty( RESOURCE_FOLDER, "resource" );
    }

    public Map<String, String> getPropertiesMap() {
        return map;
    }

    public Properties getProperties() {
        return ps;
    }

    public String getProperty( String propertyName, String defval ) {
        return this.getProperties().getProperty( propertyName, defval );
    }

    @Override
    public String getTemplateSuffix() {
        return templateSuffix;
    }

    @Override
    public String getViewFolder() {
        return viewsFolder;
    }

    @Override
    public String getResourceFolder() {
        return resourceFolder;
    }
}
