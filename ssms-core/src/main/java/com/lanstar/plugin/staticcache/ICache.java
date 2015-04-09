/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ICache.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

public interface ICache<V> {
    /**
     * 获取缓存名称
     */
    String getName();

    /**
     * 刷新缓存
     */
    void refresh();

    /**
     * 情况缓存
     */
    void clear();

    /**
     * 从缓存里面取值
     */
    V getValue( String key );

    /**
     * 设置值
     */
    void put(String key, V value);
}
