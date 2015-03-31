/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AppPlugins.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.plugin;

import com.lanstar.common.log.LogHelper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class AppPlugins {
    final List<IAppPlugin> plugins = new LinkedList<>();
    private boolean active;

    public AppPlugins add(Collection<IAppPlugin> ps) {
        plugins.addAll(ps);
        return this;
    }

    public AppPlugins add(IAppPlugin p) {
        plugins.add(p);
        return this;
    }

    /**
     * 启动所有插件
     */
    public void startup() {
        for (IAppPlugin p : plugins) {
            LogHelper.info(this.getClass(), "正在启动插件%s....", p.getClass().getName());
            p.startup();
        }
        active = true;
    }

    /**
     * 终止所有插件
     */
    public void shutdown() {
        // 逆序关闭，确保一些依赖关系的插件之间能够正常关闭
        for (int i = plugins.size() - 1; i>=0; i--){
            plugins.get(i).shutdown();
        }
        active = false;
    }

    /**
     * 获得指定类型（严格相等）的插件
     */
    @SuppressWarnings("unchecked")
    public <T extends IAppPlugin> T getPlugin(Class<T> type) {
        for (IAppPlugin plugin : this.plugins) {
            if (type.isAssignableFrom(plugin.getClass()))
                return (T) plugin;
        }
        return null;
    }

    public boolean Active() {
        return active;
    }
}
