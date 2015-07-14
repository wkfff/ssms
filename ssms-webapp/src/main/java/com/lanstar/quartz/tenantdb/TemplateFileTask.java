/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileTask.java
 * 创建时间：2015-07-13
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.lanstar.service.common.todo.TodoBean;

import javax.sql.DataSource;
import java.util.List;

public abstract class TemplateFileTask<T> implements Task {
    @Override
    public void execute( DataSource dataSource ) {
        List<T> list = list();
        for ( T item : list ) {
            createTodo( item );
        }
    }

    /**
     * 获取所有要创建待办的项目
     */
    protected abstract List<T> list();

    /**
     * 创建待办。
     */
    protected final void createTodo( T item ) {
        if ( validate( item ) ) {
            TodoBean bean = genTodoBean( item );
            createTodo( item, bean );
        }
    }

    /**
     * 判断是否需要创建待办。
     *
     * @param item 进行待办创建判断的项
     *
     * @return 如果返回true则表示要创建待办，否则为false。
     */
    public abstract boolean validate( T item );

    /**
     * 获取待办Bean
     */
    public abstract TodoBean genTodoBean( T item );

    /**
     * 创建待办
     */
    protected abstract void createTodo( T item, TodoBean bean );
}
