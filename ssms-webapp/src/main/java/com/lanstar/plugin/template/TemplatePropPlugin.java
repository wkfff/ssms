/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplatePropCache.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import com.lanstar.plugin.IPlugin;
import com.lanstar.service.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class TemplatePropPlugin implements IPlugin {
    private static final TemplatePropPlugin me = new TemplatePropPlugin();
    private final Map<String, TemplateProp> map = new ConcurrentSkipListMap<>();

    private TemplatePropPlugin() {
    }

    public static TemplatePropPlugin me() {
        return me;
    }

    public final TemplatePropPlugin add( TemplateProp prop ) {
        map.put( prop.getCode(), prop );
        return this;
    }

    public final TemplatePropPlugin add( TemplateProps props ) {
        for ( TemplateProp templateProp : props.list ) {
            add( templateProp );
        }
        return this;
    }

    public final TemplateProp get( String code ) {
        return map.get( code );
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }

    public List<Parameter> listParameter() {
        List<Parameter> list = new ArrayList<>();
        for ( TemplateProp prop : map.values() ) list.add( prop.getParameter() );
        return list;
    }
}

