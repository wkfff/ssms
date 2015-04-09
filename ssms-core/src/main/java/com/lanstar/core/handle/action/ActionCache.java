/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ControllerBean.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.google.common.base.Joiner;
import com.lanstar.common.log.LogHelper;
import com.lanstar.plugin.staticcache.Cache;

import java.util.Map;

public class ActionCache extends Cache<Action> {
    private final ControllerCache controllerCache = new ControllerCache();

    @Override
    protected void load( Map<String, Action> pools ) {}

    public synchronized Action getValue( ActionMeta meta ) {
        String key = getActionKey( meta );
        Action action = getValue( key );
        if ( action == null ) {
            LogHelper.debug( ActionCache.class, "加载Action[%s]", key );
            Controller controller = controllerCache.getValue( meta );
            action = controller.getValue( meta.getAction() );
            put( key, action );
        }

        return action;
    }

    private String getActionKey( ActionMeta meta ) {
        return Joiner.on( '.' ).join( meta.getClassName(), meta.getAction() );
    }

    /**
     * 获取缓存名称
     */
    @Override
    public String getName() {
        return "Action Cache";
    }
}
