/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StaticCachePlugin.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.lanstar.plugin.IAppPlugin;

import java.util.List;

public class StaticCachePlugin extends CacheManager implements IAppPlugin {
    public StaticCachePlugin( List<? extends ICache<?>> caches ) {
        for ( ICache<?> cache : caches ) {
            this.register( cache );
        }
    }

    /**
     * 启动插件运行
     */
    @Override
    public void startup() {
        refresh();
    }

    /**
     * 关闭插件运行
     */
    @Override
    public void shutdown() {
        clear();
    }
}
