/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoType.java
 * 创建时间：2015-06-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.common.todo;

import com.lanstar.identity.Identity;

import java.util.List;

public enum TodoType {
    STDFILE,
    STDFILE01,
    STDFILE02,
    STDFILE03,
    STDFILE04,
    STDFILE06,
    STDFILE07,
    STDFILE08,
    STDFILE09;

    /**
     * 创建待办
     *
     * @param service  待办服务
     * @param bean     待办对象
     * @param operator 处理人员
     */
    public final void createTodo( TodoService service, TodoBean bean, Identity operator ) {
        bean.setSignature( getName() );
        service.create( bean, operator );
    }

    /**
     * 创建或更新已经存在的待办
     *
     * @param service  待办服务
     * @param bean     待办对象
     * @param operator 处理人员
     */
    public final void saveTodo( TodoService service, TodoBean bean, Identity operator ) {
        bean.setSignature( getName() );

        if ( service.exists( bean ) == false ) service.create( bean, operator, true );
        else service.update( bean, operator, true );
    }

    /**
     * 完成待办
     *
     * @param service  待办服务
     * @param srcId    记录行id
     * @param operator 处理人员
     */
    public void finishTodo( TodoService service, int srcId, Integer profession, Integer template, Identity operator ) {
        service.finish( getName(), srcId, profession, template, operator );
    }

    /**
     * 获取待办列表
     *
     * @param service 待办服务
     */
    public List<TodoBean> listTodo( TodoService service, Integer profession, Integer template ) {
        return service.listTodoBean( getName(), profession, template );
    }

    /**
     * 获取待办列表
     */
    public List<TodoBean> listTodo( TodoService service, Integer profession, Integer template, int size ) {
        return service.listTodoBean( getName(), profession, template, size );
    }

    /**
     * 取消待办
     *
     * @param service 待办服务
     * @param srcId   记录行id
     */
    public void cancelTodo( TodoService service, int srcId ) {
        service.cancelTodo( this.getName(), srcId );
    }

    private String getName() {
        return name().toUpperCase();
    }
}
