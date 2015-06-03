/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateCache.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.google.common.collect.Maps;
import com.lanstar.common.kit.Prop;
import com.lanstar.common.kit.PropKit;

import java.util.Map;

public class StandardTemplateCache extends Cache<String> {
    @Override
    protected void load( Map<String, String> pools ) {
        Prop prop = PropKit.use( "stdtmpmap.properties" );
        pools.putAll( Maps.fromProperties( prop.getProperties() ) );
    }

    @Override
    public String getName() {
        return "标准模板映射信息";
    }
}
