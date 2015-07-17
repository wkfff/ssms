/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile03Task.java
 * 创建时间：上午9:49:42
 * 创建用户：苏志亮
 */
package com.lanstar.quartz.tenantdb;

import java.util.List;

import com.lanstar.model.tenant.TemplateFile03;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.common.todo.TodoType;

public class TemplateFile03Task extends TemplateFileTask<TemplateFile03> {

    @Override
    public List<TemplateFile03> list() {
        return null;
    }

    @Override
    protected TodoType getTodoType() {
        return TodoType.STDFILE03;
    }


    public boolean validate( TemplateFile03 item ) {
        return true;
    }

    @Override
    protected void buildTodoData( TemplateFile03 file, TodoData data ) {
        data.setTitle( "<<" + file.getName() + ">>" + "本年度未提交" );
    }

}
