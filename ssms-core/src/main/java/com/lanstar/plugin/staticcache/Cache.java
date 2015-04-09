/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Cache.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.lanstar.common.log.LogHelper;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Cache<V> implements ICache<V> {
    private Map<String, V> pools = new ConcurrentHashMap<>();

    @Override
    public void refresh() {
        LogHelper.info( Cache.class, "开始缓存[%s]数据......", this.getName() );
        try {
            clear(); // 清空原有数据
            load( this.pools );
            LogHelper.info( Cache.class, "[%s]缓存了%s条记录。", this.getName(), this.pools.size() );
        } catch ( Exception e ) {
            LogHelper.error( Cache.class, e, "载入失败！" );
        }
    }

    protected abstract void load( Map<String, V> pools );

    @Override
    public void clear() {
        pools.clear();
    }

    @Override
    public V getValue( String key ) {
        return pools.get( key );
    }

    /**
     * 设置值
     */
    @Override
    public void put( String key, V value ) {
        pools.put( key, value );
    }

    /**
     * 获取所有值
     */
    @Override
    public Iterator<V> getValues() {
        return pools.values().iterator();
    }
}
