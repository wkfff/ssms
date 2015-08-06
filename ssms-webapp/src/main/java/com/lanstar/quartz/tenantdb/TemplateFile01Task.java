/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.lanstar.app.Const;
import com.lanstar.common.kit.DateKit;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.RecordKit;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.CycleType;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.common.todo.TodoType;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TemplateFile01Task extends TemplateFileTask<TemplateFile01> {
    private final String NOW;

    public TemplateFile01Task() {
        NOW = DateKit.toStr( new Date() );
    }

    @Override
    public List<TemplateFile01> list() {
        List<Record> records = Db.use( Const.TENANT_DB_NAME ).find( SqlKit.sql( "tenant.todo.01" ) );
        return RecordKit.toModel( TemplateFile01.class, records );
    }

    @Override
    protected TodoType getTodoType() {
        return TodoType.STDFILE01;
    }

    @Override
    public boolean validate( TemplateFile01 item ) {
        Date date = getDate( item );
        if (date == null) return false;
        return DateKit.toStr( date ).compareTo( NOW ) >= 0;
    }

    private Date getDate( TemplateFile01 item ) {
        TemplateFile file = item.getTemplateFile();
        CycleType cycleType = file.getCycleUnit();
        Date date = item.getDate( "T_DATE_04" );
        if (date == null) return null;
        if ( cycleType != null ) {
            date = cycleType.advance( file.getCycleValue(), date );
        } else { // 制度文件默认一年一更新
            Calendar cd = Calendar.getInstance();
            cd.setTime( date );
            cd.add( Calendar.YEAR, 1 );
        }
        return date;
    }

    @Override
    protected void buildTodoData( TemplateFile01 file, TodoData data ) {
        data.setTitle( "<<" + file.getName() + ">>将于" + DateKit.toStr( getDate( file ) ) + "到期" );
    }
}
