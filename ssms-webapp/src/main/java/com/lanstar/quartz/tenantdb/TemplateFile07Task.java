/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile07Task.java
 * 创建时间：2015年7月7日 上午10:52:55
 * 创建用户：林峰
 */
package com.lanstar.quartz.tenantdb;

import com.lanstar.model.tenant.TemplateFile07;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.common.todo.TodoType;

import java.util.List;

/**
 * 特种人员
 */
public class TemplateFile07Task extends TemplateFileTask<TemplateFile07> {

    @Override
    protected List<TemplateFile07> list() {
        return null;
    }

    @Override
    protected TodoType getTodoType() {
        return TodoType.STDFILE07;
    }

    @Override
    public boolean validate( TemplateFile07 item ) {
        return false;
    }

    @Override
    protected void buildTodoData( TemplateFile07 file, TodoData data ) {
        data.setTitle( "<<" + file.getName() + ">>特种作业人员证书即将到期(复审时间" + file.getDate( "T_TEST_NEXT" ) + ")" );
    }

}
