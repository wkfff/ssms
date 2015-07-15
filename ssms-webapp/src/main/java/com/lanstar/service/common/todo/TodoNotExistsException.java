/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoNotExistsException.java
 * 创建时间：2015-07-15
 * 创建用户：张铮彬
 */

package com.lanstar.service.common.todo;

public class TodoNotExistsException extends Exception {
    private static final long serialVersionUID = -2784246923227771171L;
    private final TodoData todoData;

    public TodoNotExistsException( TodoData todoData ) {
        super( "待办不存在" );
        this.todoData = todoData;
    }
}
