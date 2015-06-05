/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateCache.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class StandardTemplateCache extends Cache<TemplateProp> {
    private final Map<String, TemplateProp> map = new ConcurrentSkipListMap<>();

    public final StandardTemplateCache add( TemplateProp prop ) {
        map.put( prop.getCode(), prop );
        return this;
    }

    @Override
    public String getName() {
        return "标准模板映射信息";
    }

    @Override
    protected void load( Map<String, TemplateProp> pools ) {
        pools.putAll( map );
    }
}