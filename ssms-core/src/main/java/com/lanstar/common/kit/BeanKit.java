/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：BeanHelper.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class BeanKit {

    /**
     * 创建指定类名的实例
     */
    public static <T> T newInstance( String className, Class<T> target ) {
        try {
            Class<?> tmp = Class.forName( className );
            if ( target.isAssignableFrom( tmp ) ) {
                return (T) tmp.newInstance();
            } else
                throw new IllegalArgumentException( "错误的系统配置类:" + className );
        } catch ( ClassNotFoundException e ) {
            throw new IllegalArgumentException( "无法找到系统配置类:" + className, e );
        } catch ( InstantiationException e ) {
            throw new IllegalArgumentException( "无法创建系统配置类:" + className, e );
        } catch ( IllegalAccessException e ) {
            throw new IllegalArgumentException( "无法访问系统配置类:" + className, e );
        }
    }

    /**
     * 实例化类
     *
     * @param type 类
     * @param <T>  转换的目标类
     *
     * @return 类对应的实例
     */
    public static <T> T newInstance( Class<?> type ) {
        if ( type == null )
            return null;
        try {
            return (T) type.newInstance();
        } catch ( Throwable e ) {
            throw new IllegalArgumentException( "无法实例化类:" + type.getName(), e );
        }
    }

    /**
     * 实例化类
     *
     * @param type 类名称
     * @param <T>  转换的目标类
     *
     * @return 类对应的实例
     */
    public static <T> T newInstance( String type, T defval ) {
        try {
            return (T) Class.forName( type ).newInstance();
        } catch ( Throwable e ) {
            return defval;
        }
    }

    /**
     * 将当前的JAVA对象复制成一个新的
     *
     * @param source 要拷贝的源对象
     * @param <T>    类型，必须继承Serializable才行
     *
     * @return 新的JAVA对象，深度复制，与原有的没有关系
     */
    public static <T> T clone( T source ) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream( bos );
            oos.writeObject( source );
            // 反序列化
            ByteArrayInputStream bis = new ByteArrayInputStream( bos.toByteArray() );
            ObjectInputStream ois = new ObjectInputStream( bis );

            return (T) ois.readObject();
        } catch ( Throwable e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * 获得类
     */
    public static <T> Class<T> getClass( String typeName ) {
        try {
            return (Class<T>) Class.forName( typeName );
        } catch ( Throwable e ) {
            return null;
        }
    }

    public static Map<String, Object> transToMap( Object obj ) {
        if ( obj == null ) return null;
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo( obj.getClass() );
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for ( PropertyDescriptor property : propertyDescriptors ) {
                String key = property.getName();

                // 过滤class属性
                if ( !key.equals( "class" ) ) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    if ( !getter.isAccessible() ) getter.setAccessible( true );
                    Object value = getter.invoke( obj );

                    map.put( key, value );
                }

            }
        } catch ( Exception ignored ) {
        }

        return map;
    }
}
