/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MapModelBean.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.plugin.json.JsonHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public final class MapModelBean implements ModelBean {
    private final Map<String, Object> map;

    public MapModelBean() {
        this( new LinkedHashMap<String, Object>() );
    }

    public MapModelBean( Map<String, Object> map ) {
        this.map = map;
    }

    /**
     * @return JSON标准字符串, 带“{”...“}”串
     */
    @Override
    public String toJson() {
        return JsonHelper.toJson( map );
    }

    public void put( String key, Object value ) {
        map.put( key, value );
    }

    @Override
    public Object getValue( String key ) {
        return map.get( key );
    }
}
