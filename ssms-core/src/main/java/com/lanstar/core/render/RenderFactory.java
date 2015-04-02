/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderFactory.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.app.container.ContainerHelper;
import com.lanstar.common.helper.Asserts;
import com.lanstar.core.HandlerContext;

import java.util.HashMap;
import java.util.Map;

public class RenderFactory {
    private final Map<String, IRender> renderMap = new HashMap<>();

    public RenderFactory( Map<String, IRender> map ) {
        for ( String key : map.keySet() ) {
            renderMap.put( key.toLowerCase(), renderMap.get( key ) );
        }
        Asserts.notEmpty( renderMap.keySet(), "无未配置任何的Render" );
    }

    public void render( String renderName, HandlerContext context ) {
        IRender render = renderMap.get( renderName );
        Asserts.notNull( render, "无法找到对应的render实现" );
        try {
            render.render( context );
        } catch ( Exception e ) {
            throw new RenderException( "render过程中出现了异常", e );
        }
    }

    private static volatile RenderFactory singleton;

    public static RenderFactory me() {
        if ( singleton == null ) synchronized ( RenderFactory.class ) {
            if ( singleton == null ) singleton = ContainerHelper.getBean( RenderFactory.class );
        }
        return singleton;
    }
}
