/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.lanstar.common.kit.DateKit;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.CycleType;
import com.lanstar.service.common.todo.TodoBean;
import com.lanstar.service.common.todo.TodoService;
import com.lanstar.service.common.todo.TodoType;

import java.util.Date;
import java.util.List;

public class TemplateFile01Task extends TemplateFileTask<TemplateFile01> {
    private final String NOW;

    public TemplateFile01Task() {
        NOW = DateKit.toStr( new Date() );
    }

    @Override
    public List<TemplateFile01> list() {
        return TemplateFile01.dao.find( SqlKit.sql( "tenant.todo.01" ) );
    }

    @Override
    public boolean validate( TemplateFile01 item ) {
        Date date = getDate( item );
        return DateKit.toStr( date ).compareTo( NOW ) >= 0;
    }

    private Date getDate( TemplateFile01 item ) {
        TemplateFile file = item.getTemplateFile();
        CycleType cycleType = file.getCycleUnit();
        Date date = item.getDate( "T_DATE_04" );
        if ( cycleType != null ) {
            date = cycleType.advance( file.getCycleValue(), date );
        }
        return date;
    }

    @Override
    protected TodoBean genTodoBean( TemplateFile01 file ) {
        int professionId = file.getProfessionId();
        int templateFileId = file.getTemplateId();
        TodoBean bean = new TodoBean();
        bean.setTemplateId( templateFileId );
        bean.setProfessionId( professionId );
        bean.setSrcId( file.getId() );
        bean.setUrl( "..." );
        bean.setTitle( "<<" + file.getName() + ">>将于" + DateKit.toStr( getDate( file ) ) + "到期" );
        return bean;
    }

    @Override
    protected void createTodo( TemplateFile01 item, TodoBean bean ) {
        TodoType.STDFILE01.createTodo( TodoService.with( item.getTenant() ), bean, TodoUser.INST );
    }
}
