/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：InterceptorBuilder.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.core.aop.Before;
import com.lanstar.core.aop.ClearInterceptor;
import com.lanstar.core.aop.ClearLayer;
import com.lanstar.core.aop.Interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class InterceptorBuilder {

    private static final Interceptor[] NULL_INTERCEPTOR_ARRAY = new Interceptor[0];
    private Map<Class<Interceptor>, Interceptor> intersMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    void addToInterceptorsMap( Interceptor[] defaultInters ) {
        for ( Interceptor inter : defaultInters )
            intersMap.put( (Class<Interceptor>) inter.getClass(), inter );
    }

    /**
     * Build interceptors of Controller
     */
    Interceptor[] buildControllerInterceptors( Class<? extends Controller> controllerClass ) {
        Before before = controllerClass.getAnnotation( Before.class );
        return before != null ? createInterceptors( before ) : NULL_INTERCEPTOR_ARRAY;
    }

    /**
     * Build interceptors of Method
     */
    Interceptor[] buildMethodInterceptors( Method method ) {
        Before before = method.getAnnotation( Before.class );
        return before != null ? createInterceptors( before ) : NULL_INTERCEPTOR_ARRAY;
    }

    /**
     * Build interceptors of Action
     */
    Interceptor[] buildActionInterceptors( Interceptor[] defaultInters, Interceptor[] controllerInters, Class<? extends Controller> controllerClass, Interceptor[] methodInters, Method method ) {
        // clear global interceptors if controller has 'ClearInterceptor'
        ClearLayer controllerClearType = getControllerClearType( controllerClass );
        if ( controllerClearType != null ) {
            defaultInters = NULL_INTERCEPTOR_ARRAY;
        }

        ClearLayer methodClearType = getMethodClearType( method );
        if ( methodClearType != null ) {
            // clear controller interceptors if method has 'ClearInterceptor'
            controllerInters = NULL_INTERCEPTOR_ARRAY;
            // clear global interceptors if method has 'ClearInterceptor' and clear type is 'All'
            if ( methodClearType == ClearLayer.ALL ) {
                defaultInters = NULL_INTERCEPTOR_ARRAY;
            }
        }

        int size = defaultInters.length + controllerInters.length + methodInters.length;
        Interceptor[] result = (size == 0 ? NULL_INTERCEPTOR_ARRAY : new Interceptor[size]);

        // merge result
        int index = 0;
        for ( Interceptor defaultInter : defaultInters ) {
            result[index++] = defaultInter;
        }
        for ( Interceptor controllerInter : controllerInters ) {
            result[index++] = controllerInter;
        }
        for ( Interceptor methodInter : methodInters ) {
            result[index++] = methodInter;
        }

        return result;
    }

    private ClearLayer getMethodClearType( Method method ) {
        ClearInterceptor clearInterceptor = method.getAnnotation( ClearInterceptor.class );
        return clearInterceptor != null ? clearInterceptor.value() : null;
    }

    private ClearLayer getControllerClearType( Class<? extends Controller> controllerClass ) {
        ClearInterceptor clearInterceptor = controllerClass.getAnnotation( ClearInterceptor.class );
        return clearInterceptor != null ? clearInterceptor.value() : null;
    }

    /**
     * Create interceptors with Annotation of Before. Singleton version.
     */
    private Interceptor[] createInterceptors( Before beforeAnnotation ) {
        Interceptor[] result = null;
        @SuppressWarnings("unchecked")
        Class<Interceptor>[] interceptorClasses = (Class<Interceptor>[]) beforeAnnotation.value();
        if ( interceptorClasses != null && interceptorClasses.length > 0 ) {
            result = new Interceptor[interceptorClasses.length];
            for ( int i = 0; i < result.length; i++ ) {
                result[i] = intersMap.get( interceptorClasses[i] );
                if ( result[i] != null )
                    continue;

                try {
                    result[i] = interceptorClasses[i].newInstance();
                    intersMap.put( interceptorClasses[i], result[i] );
                } catch ( Exception e ) {
                    throw new RuntimeException( e );
                }
            }
        }
        return result;
    }
}