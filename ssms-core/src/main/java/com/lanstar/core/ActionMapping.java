/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionMapping.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.config.Interceptors;
import com.lanstar.config.Routes;
import com.lanstar.core.aop.Interceptor;

import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

final class ActionMapping {
    private static final String SLASH = "/";
    private Routes routes;
    private Interceptors interceptors;

    private final Map<String, Action> mapping = new HashMap<>();

    ActionMapping( Routes routes, Interceptors interceptors ) {
        this.routes = routes;
        this.interceptors = interceptors;
    }

    private Set<String> buildExcludedMethodName() {
        Set<String> excludedMethodName = new HashSet<>();
        Method[] methods = Controller.class.getMethods();
        for ( Method m : methods ) {
            if ( m.getParameterTypes().length == 0 )
                excludedMethodName.add( m.getName() );
        }
        return excludedMethodName;
    }

    void buildActionMapping() {
        mapping.clear();
        Set<String> excludedMethodName = buildExcludedMethodName();
        InterceptorBuilder interceptorBuilder = new InterceptorBuilder();
        Interceptor[] defaultInters = interceptors.getInterceptorArray();
        interceptorBuilder.addToInterceptorsMap( defaultInters );
        for ( Entry<String, Class<? extends Controller>> entry : routes.getEntrySet() ) {
            Class<? extends Controller> controllerClass = entry.getValue();
            Interceptor[] controllerInters = interceptorBuilder.buildControllerInterceptors( controllerClass );
            Method[] methods = controllerClass.getMethods();
            for ( Method method : methods ) {
                String methodName = method.getName();
                // exclude method if method in excluded list.
                if ( excludedMethodName.contains( methodName ) || method.getParameterTypes().length != 0 ) continue;
                // get method interceptor
                Interceptor[] methodInters = interceptorBuilder.buildMethodInterceptors( method );
                // action interceptor = global + controller + method
                Interceptor[] actionInters = interceptorBuilder.buildActionInterceptors( defaultInters, controllerInters, controllerClass, methodInters, method );
                String controllerKey = entry.getKey();

                ActionKey ak = method.getAnnotation( ActionKey.class );
                if ( ak != null ) {
                    String actionKey = ak.value().trim();
                    if ( "".equals( actionKey ) )
                        throw new IllegalArgumentException( controllerClass.getName() + "." + methodName
                                + "(): The argument of ActionKey can not be blank." );

                    if ( !actionKey.startsWith( SLASH ) )
                        actionKey = SLASH + actionKey;

                    if ( mapping.containsKey( actionKey ) ) {
                        warnning( actionKey, controllerClass, method );
                        continue;
                    }

                    Action action = new Action( controllerKey, actionKey, controllerClass, method, methodName, actionInters, routes
                            .getViewPath( controllerKey ) );
                    mapping.put( actionKey, action );
                } else if ( methodName.equals( "index" ) ) {
                    String actionKey = controllerKey;

                    Action action = new Action( controllerKey, actionKey, controllerClass, method, methodName, actionInters, routes
                            .getViewPath( controllerKey ) );
                    action = mapping.put( actionKey, action );

                    if ( action != null ) {
                        warnning( action.getActionKey(), action.getControllerClass(), action.getMethod() );
                    }
                } else {
                    String actionKey = controllerKey.equals( SLASH ) ?
                            SLASH + methodName :
                            controllerKey + SLASH + methodName;

                    if ( mapping.containsKey( actionKey ) ) {
                        warnning( actionKey, controllerClass, method );
                        continue;
                    }

                    Action action = new Action( controllerKey, actionKey, controllerClass, method, methodName, actionInters, routes
                            .getViewPath( controllerKey ) );
                    mapping.put( actionKey, action );
                }
            }
        }

        // support url = controllerKey + urlParas with "/" of controllerKey
        Action actoin = mapping.get( "/" );
        if ( actoin != null )
            mapping.put( "", actoin );
    }

    private static void warnning( String actionKey, Class<? extends Controller> controllerClass, Method method ) {
        StringBuilder sb = new StringBuilder();
        sb.append( "--------------------------------------------------------------------------------\nWarnning!!!\n" )
          .append( "ActionKey already used: \"" ).append( actionKey ).append( "\" \n" )
          .append( "Action can not be mapped: \"" )
          .append( controllerClass.getName() ).append( "." ).append( method.getName() ).append( "()\" \n" )
          .append( "--------------------------------------------------------------------------------" );
        System.out.println( sb.toString() );
    }

    /**
     * Support four types of url
     * 1: http://abc.com/controllerKey                 ---> 00
     * 2: http://abc.com/controllerKey/para            ---> 01
     * 3: http://abc.com/controllerKey/method          ---> 10
     * 4: http://abc.com/controllerKey/method/para     ---> 11
     */
    Action getAction( String url, String[] urlPara ) {
        Action action = mapping.get( url );
        if ( action != null ) {
            return action;
        }

        // --------
        int i = url.lastIndexOf( SLASH );
        if ( i != -1 ) {
            action = mapping.get( url.substring( 0, i ) );
            urlPara[0] = url.substring( i + 1 );
        }

        return action;
    }

    List<String> getAllActionKeys() {
        List<String> allActionKeys = new ArrayList<>( mapping.keySet() );
        Collections.sort( allActionKeys );
        return allActionKeys;
    }
}
