/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile09Task.java
 * 创建时间：2015年7月7日 上午10:52:55
 * 创建用户：林峰
 */
package com.lanstar.quartz.tenantdb;

import com.lanstar.common.kit.DateKit;
import com.lanstar.model.tenant.TemplateFile09;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.common.todo.TodoType;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 安全附件
 */
public class TemplateFile09Task extends TemplateFileTask<TemplateFile09> {

    @Override
    protected List<TemplateFile09> list() {
        return null;
    }

    @Override
    protected TodoType getTodoType() {
        return TodoType.STDFILE09;
    }

    @Override
    public boolean validate( TemplateFile09 item ) {
        Calendar cd = Calendar.getInstance();
        cd.setTime( item.getTestNext() );
        String d1 = DateKit.toStr( cd.getTime() );
        String d2 = DateKit.toStr( new Date() );
        return d1.compareTo( d2 ) >= 0;
    }

    @Override
    protected void buildTodoData( TemplateFile09 file, TodoData data ) {
        data.setTitle( "<<" + file.getName() + ">>临近下次检验(" + DateKit.toStr( file.getTestNext() ) + ")" );
    }

}
