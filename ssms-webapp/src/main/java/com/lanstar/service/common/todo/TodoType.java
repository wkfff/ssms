/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoType.java
 * 创建时间：2015-06-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.common.todo;

import com.lanstar.identity.Identity;

public enum TodoType {
    STDFILE,
    STDFILE01,STDFILE02,STDFILE03,STDFILE04,STDFILE06,STDFILE07,STDFILE08,STDFILE09;

    /**
     * 创建待办
     *
     * @param service  待办服务
     * @param bean     待办对象
     * @param operator 处理人员
     */
    public final void createTodo( TodoService service, TodoBean bean, Identity operator ) {
        bean.setSignature( name() );
        service.create( bean, operator );
    }

    /**
     * 完成待办
     *
     * @param service  待办服务
     * @param srcId    记录行id
     * @param operator 处理人员
     */
    public void finishTodo( TodoService service, int srcId, Identity operator ) {
        service.finish( name(), srcId, operator );
    }

    /**
     * 获取待办列表
     *
     * @param service 待办服务
     */
    public void listTodo( TodoService service ) {
        service.listTodoBean( name() );
    }

    /**
     * 取消待办
     *
     * @param service 待办服务
     * @param srcId   记录行id
     */
    public void cancelTodo( TodoService service, int srcId ) {
        service.cancelTodo( this.name(), srcId );
    }
}
