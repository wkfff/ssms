/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile06Task.java
 * 创建时间：2015年7月7日 上午10:52:55
 * 创建用户：林峰
 */
package com.lanstar.quartz.tenantdb;

import com.lanstar.common.kit.DateKit;
import com.lanstar.model.tenant.TemplateFile06;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.common.todo.TodoType;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 隐患排查
 */
public class TemplateFile06Task extends TemplateFileTask<TemplateFile06> {
    @Override
    public List<TemplateFile06> list() {
        return TemplateFile06.dao.find( "tenant.todo.06" );
    }

    @Override
    protected TodoType getTodoType() {
        return TodoType.STDFILE06;
    }

    @Override
    public boolean validate( TemplateFile06 item ) {
        // 已完成就不创建待办了
        if ( item.isFinish() ) return false;
        // 90天内的要提醒
        Calendar cd = Calendar.getInstance();
        cd.setTime( item.getRectification() );
        cd.add( Calendar.DATE, 90 );
        String d1 = DateKit.toStr( cd.getTime() );
        String d2 = DateKit.toStr( new Date() );
        return d1.compareTo( d2 ) >= 0;
    }

    @Override
    protected void buildTodoData( TemplateFile06 file, TodoData data ) {
        // “隐患名称”（要求整改时间：XXXX年XX月XX日）
        data.setTitle( "“" + file.getName() + "”(要求整改时间：" + DateKit.toStr( file.getRectification() ) + ")" );
    }
}
