/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Routes.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.config;

import com.lanstar.common.Asserts;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class Routes {

    private final Map<String, Class<? extends Controller>> map = new HashMap<>();
    private final Map<String, String> viewPathMap = new HashMap<>();

    /**
     * you must implement config method and use add method to config route
     */
    public abstract void config();

    public Routes add( Routes routes ) {
        if ( routes != null ) {
            routes.config();    // very important!!!
            map.putAll( routes.map );
            viewPathMap.putAll( routes.viewPathMap );
        }
        return this;
    }

    /**
     * Add route
     *
     * @param controllerKey   A key can find controller
     * @param controllerClass Controller Class
     * @param viewPath        View path for this Controller
     */
    public Routes add( String controllerKey, Class<? extends Controller> controllerClass, String viewPath ) {
        Asserts.notBlank( controllerKey, "The controllerKey can not be blank" );
        Asserts.notNull( controllerClass, "The controllerClass can not be null" );
        controllerKey = controllerKey.trim();

        if ( !controllerKey.startsWith( "/" ) )
            controllerKey = "/" + controllerKey;
        if ( map.containsKey( controllerKey ) )
            throw new IllegalArgumentException( "The controllerKey already exists: " + controllerKey );

        map.put( controllerKey, controllerClass );

        if ( StrKit.isBlank( viewPath ) )    // view path is controllerKey by default
            viewPath = controllerKey;

        viewPath = viewPath.trim();
        if ( !viewPath.startsWith( "/" ) )                    // "/" added to prefix
            viewPath = "/" + viewPath;

        if ( !viewPath.endsWith( "/" ) )                    // "/" added to postfix
            viewPath = viewPath + "/";

        if ( baseViewPath != null )                        // support baseViewPath
            viewPath = baseViewPath + viewPath;

        viewPathMap.put( controllerKey, viewPath );
        return this;
    }

    /**
     * Add url mapping to controller. The view path is controllerKey
     *
     * @param controllerkey   A key can find controller
     * @param controllerClass Controller Class
     */
    public Routes add( String controllerkey, Class<? extends Controller> controllerClass ) {
        return add( controllerkey, controllerClass, controllerkey );
    }

    public Set<Entry<String, Class<? extends Controller>>> getEntrySet() {
        return map.entrySet();
    }

    public String getViewPath( String key ) {
        return viewPathMap.get( key );
    }

    private static String baseViewPath;

    /**
     * Set the base path for all views
     */
    static void setBaseViewPath( String baseViewPath ) {
        Asserts.notBlank( baseViewPath, "The baseViewPath can not be blank" );
        baseViewPath = baseViewPath.trim();

        if ( !baseViewPath.startsWith( "/" ) )            // add prefix "/"
            baseViewPath = "/" + baseViewPath;

        if ( baseViewPath.endsWith( "/" ) )                // remove "/" in the end of baseViewPath
            baseViewPath = baseViewPath.substring( 0, baseViewPath.length() - 1 );

        Routes.baseViewPath = baseViewPath;
    }
}
