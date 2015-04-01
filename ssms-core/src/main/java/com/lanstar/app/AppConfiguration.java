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
    private Map<String, String> map;
    private Properties ps = new Properties();

    public AppConfiguration(String... paths) {
        try{
            if (paths == null || paths.length == 0) throw new IOException();
            InputStream stream = ContainerHelper.getResource(paths);
            ps.load(stream);
            map = Maps.fromProperties(ps);
        } catch(IOException e){
            throw ExceptionHelper.runtimeException(e, "配置文件[%s]不存在或加载错误...", Arrays.toString(paths));
        }
    }

    public Map<String, String> getPropertiesMap() {
        return map;
    }

    public Properties getProperties() {
        return ps;
    }

    public String getProperty(String propertyName, String defval) {
        return this.getProperties().getProperty(propertyName, defval);
    }
}
