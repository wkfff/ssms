/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ChartPlugin.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.charts;

import com.lanstar.plugin.IPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ChartPlugin implements IPlugin {
    private final static ChartPlugin me = new ChartPlugin();
    private final Map<String, ChartProp> map = new ConcurrentSkipListMap<>();

    public static ChartPlugin me() {
        return me;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }

    public final ChartPlugin add( ChartProp prop ) {
        map.put( prop.getCode(), prop );
        return this;
    }

    public final ChartPlugin add( ChartProps props ) {
        for ( ChartProp prop : props.list ) {
            add( prop );
        }
        return this;
    }

    public final ChartProp get( String code ) {
        return map.get( code );
    }

}
