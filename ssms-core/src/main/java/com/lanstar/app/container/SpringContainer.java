/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SpringContainer.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.app.container;

import com.lanstar.common.helper.ExceptionHelper;
import com.lanstar.common.log.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class SpringContainer implements IContainer {
    private static final Logger log = Logger.getLogger(SpringContainer.class);
    private final ApplicationContext applicationContext;

    SpringContainer() {
        applicationContext = createRootApplicationContext();
    }

    protected abstract WebApplicationContext createRootApplicationContext();

    @Override
    public <T> T getBean(String id) {
        return (T) applicationContext.getBean(id);
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    @Override
    public <T> Collection<T> getBeansWithAnnotation(Class<? extends Annotation> annotation) {
        Map<String, Object> results = applicationContext.getBeansWithAnnotation(annotation);
        for (Map.Entry<String, Object> entry : results.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        return (Collection<T>) results.values();
    }

    /**
     * 根据类型获得相关的BEAN实例
     *
     * @param type 类型
     * @param <T>  类型
     * @return MAP：key = BEAN定义的ID，如果没有使用内部ID ，VALUE=bean的实例
     */
    @Override
    public <T> Map<String, T> getBeansByType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    @Override
    public InputStream getResource(String... paths) {
        for (String path : paths) {
            org.springframework.core.io.Resource res = applicationContext.getResource(path);
            if (res == null)
                continue;
            try {
                return res.getInputStream();
            } catch (IOException e) {
                throw ExceptionHelper.runtimeException(e, "获取资源[%s]错误", path);
            }
        }
        return null;
    }

    @Override
    public Collection<InputStream> getResources(String... paths) {
        List<InputStream> res = new ArrayList<>();
        for (String p : paths) {
            try {
                Resource[] rs = applicationContext.getResources(p);
                if (rs == null || rs.length == 0)
                    continue;
                for (Resource r : rs) {
                    res.add(r.getInputStream());
                }
            } catch (IOException e) {
                log.warn(e, "获取[" + p + "]资源出错");
            }
        }
        return res;
    }

    /**
     * 获取Spring的应用程序上下文。
     */
    public ApplicationContext getApplicationContext(){
        return this.applicationContext;
    }
}
