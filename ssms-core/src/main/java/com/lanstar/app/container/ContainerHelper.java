/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ContainerHelper.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.app.container;

import com.lanstar.app.App;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

/** IOC容器，这个插件的唯一对外服务类 */
public class ContainerHelper {
    public static IContainer getContainer() {
        return App.getContainer();
    }

    /**
     * 从IOC容器中获得指定ID的BEAN
     *
     * @param id
     * @param <T>
     * @return
     */
    public static <T> T getBean( String id ){
        return getContainer().getBean(id);
    }

    /**
     * 从IOC容器中获得指定类的BEAN
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean( Class<T> type ){
        return getContainer().getBean(type);
    }

    /**
     * 根据注解类别来获取所有的BEAN实例
     *
     * @param annotation
     * @param <T>
     * @return
     */
    public static <T extends Object> Collection<T> getBeansWithAnnotation( Class<? extends Annotation> annotation ){
        return getContainer().getBeansWithAnnotation(annotation);
    }

    /**
     * 根据路径获取资源(多个路径，第一个匹配的为优先)
     *
     * @param paths 指定路径，根据IOC容器类型确定规则，当前使用SPRING规则
     * @return 允许为空
     */
    public static InputStream getResource( String... paths ){
        return getContainer().getResource(paths);
    }

    /**
     * 获得指定的资源
     *
     * @param paths 指定路径，根据IOC容器类型确定规则，当前使用SPRING规则，可变长变量
     * @return 允许为空
     */
    public static Collection<InputStream> getResources( String... paths ){
        return getContainer().getResources(paths);
    }

    /**
     * 根据类型获得多个BEAN实例
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Map<String,T> getBeansByType( Class<T> type ){
        return getContainer().getBeansByType(type);
    }
}
