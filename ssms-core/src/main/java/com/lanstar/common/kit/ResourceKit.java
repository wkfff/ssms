/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ResourceKit.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

public class ResourceKit {
    public static Map<String, String> readProperties(String resourceName) {
        Properties properties = new Properties();
        URL resource = Resources.getResource(resourceName);
        try {
            properties.load(new InputStreamReader(resource.openStream(), "UTF-8"));
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return Maps.fromProperties(properties);
    }
}
