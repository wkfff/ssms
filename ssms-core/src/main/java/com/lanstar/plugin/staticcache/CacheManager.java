/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CacheManager.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.lanstar.common.kit.BeanKit;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheManager {
    private static CacheManager me = new CacheManager();
    private final Map<String, ICache<?>> caches = new LinkedHashMap<>();

    /**
     * 注册缓存
     */
    public <T extends ICache<?>> void register( T cache ) {
        caches.put( cache.getClass().getName(), cache );
    }

    /**
     * 注册缓存
     */
    public <T extends ICache<?>> void register( Class<T> clazz ) {
        T cache = BeanKit.newInstance( clazz );
        register( cache );
    }

    /**
     * 获取缓存
     */
    @SuppressWarnings("unchecked")
    public <T extends ICache<?>> T getCache( Class<T> clazz ) {
        return (T) caches.get( clazz.getName() );
    }

    /**
     * 获取缓存
     */
    public <T> T getCache( Class<? extends ICache<T>> clazz, String key ) {
        return getCache( clazz ).getValue( key );
    }

    /**
     * 刷新缓存
     */
    public void refresh() {
        for ( ICache<?> cache : caches.values() ) {
            cache.refresh();
        }
    }

    /**
     * 清空缓存
     */
    public void clear() {
        for ( ICache<?> cache : caches.values() ) {
            cache.clear();
        }
    }

    public static CacheManager me() {
        return me;
    }
}
