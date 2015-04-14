/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderResolverFactory.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.core.render.resolver;

import com.lanstar.app.container.ContainerHelper;

import java.util.HashMap;
import java.util.Map;

public class RenderResolverFactory {
    private final Map<String, RenderResolver> map = new HashMap<>();

    public static RenderResolverFactory me() {
        return ContainerHelper.getBean( RenderResolverFactory.class );
    }

    public RenderResolverFactory( Map<String, RenderResolver> map ) {
        for ( String key : map.keySet() ) {
            this.map.put( key.toUpperCase(), map.get( key ) );
        }
    }

    public RenderResolver getResolver( String type ) {
        return map.get( type.toUpperCase() );
    }
}
