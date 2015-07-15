/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoData.java
 * 创建时间：2015-07-15
 * 创建用户：张铮彬
 */

package com.lanstar.service.common.todo;

import com.google.common.collect.Lists;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.model.Done;
import com.lanstar.model.Todo;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.IAtom;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TodoData {
    // 签名
    private TodoType signature;
    // 相关的数据行ID
    private int relationalId;
    // 待办所属租户
    private Tenant tenant;

    private Integer professionId;
    private Integer templateId;

    private String title;
    private Date beginTime;
    private Date endTime;
    private String url;

    private Todo data;

    private TodoData() {}

    public static TodoData with( Tenant tenant, TodoType signature, int relationalId ) {
        TodoData data = new TodoData();

        data.signature = signature;
        data.relationalId = relationalId;
        data.tenant = tenant;

        return data;
    }

    public static TodoDataFetcher withFetcher( Tenant tenant, TodoType signature ) {
        return TodoDataFetcher.with( tenant, signature );
    }

    static TodoData with( Todo data ) {
        TodoData todo = new TodoData();
        todo.data = data;

        todo.signature = TodoType.valueOf( data.getSignature() );
        todo.relationalId = data.getRelationalId();
        todo.tenant = data.getTenant();

        todo.professionId = data.getProfessionId();
        todo.templateId = data.getTemplateId();
        todo.title = data.getTitle();
        todo.beginTime = data.getBeginTime();
        todo.endTime = data.getEndTime();
        todo.url = data.getUrl();

        return todo;
    }

    public TodoData withProfessionId( int professionId ) {
        this.professionId = professionId;
        return this;
    }

    public TodoData withTemplateId( int templateId ) {
        this.templateId = templateId;
        return this;
    }

    public TodoData setTitle( String title ) {
        this.title = title;
        return this;
    }

    public TodoData setUrl( String url ) {
        this.url = url;
        return this;
    }

    public TodoData setBeginTime( Date time ) {
        this.beginTime = time;
        return this;
    }

    public TodoData setEndTime( Date time ) {
        this.endTime = time;
        return this;
    }

    public TodoData exists() throws TodoNotExistsException {
        fetchData();
        if ( data == null ) throw new TodoNotExistsException( this );
        return this;
    }

    public boolean save( Identity operator ) {
        fetchData();
        if ( data == null ) {
            return create( operator );
        }

        return update( operator );
    }

    public boolean create( Identity operator ) {
        data = new Todo();
        // 初始化数据
        data.setSignature( signature.name().toUpperCase() );
        data.setRelationalId( relationalId );
        data.setProfessionId( professionId );
        data.setTemplateId( templateId );
        data.setTitle( title );
        data.setUrl( url );
        data.setBeginTime( beginTime );
        data.setEndTime( endTime );
        data.setTenant( tenant );
        data.setOperator( operator );

        return data.save();
    }

    public boolean update( Identity operator ) {
        fetchData();
        if ( data == null ) return false;
        data.setSignature( signature.name().toUpperCase() );
        data.setRelationalId( relationalId );
        data.setProfessionId( professionId );
        data.setTemplateId( templateId );
        data.setTitle( title );
        data.setUrl( url );
        data.setBeginTime( beginTime );
        data.setEndTime( endTime );
        data.setTenant( tenant );
        data.setOperator( operator );

        return data.update();
    }

    public boolean cancel() {
        fetchData();
        if ( data == null ) return false;
        boolean result = data.delete();
        if ( result == true ) data = null;
        return result;
    }

    public boolean finish( final Identity operator ) {
        fetchData();
        if ( data == null ) return false;
        return Db.use( DbKit.getConfig( Todo.class ).getName() ).tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                archive( data, operator );
                data.delete();
                data = null;
                return true;
            }
        } );
    }

    public TodoType getSignature() {
        return signature;
    }

    public int getRelationalId() {
        return relationalId;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public String getTitle() {
        return title;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getUrl() {
        return url;
    }

    private void archive( Todo data, Identity operator ) {
        Done done = new Done();
        done.setUrl( data.getUrl() );
        done.setTitle( data.getTitle() );
        done.setEndTime( data.getEndTime() );
        done.setBeginTime( data.getBeginTime() );
        //done.setNotifyTime( bean.getNotifyTime() );
        done.setSignature( data.getSignature() );
        done.setRelationalId( data.getRelationalId() );
        done.setTenant( tenant );
        done.setOperator( operator );
        done.save();
    }

    private Todo fetchData() {
        if ( data == null ) {
            List<String> columns = Lists.newArrayList( "C_CONTROL", "R_SID", "R_TENANT", "P_TENANT" );
            List<Object> objects = Lists.newArrayList(
                    (Object) signature, relationalId, tenant.getTenantId(), tenant.getTenantType().getName() );

            if ( professionId != null ) {
                columns.add( "P_PROFESSION" );
                objects.add( professionId );
            }
            if ( templateId != null ) {
                columns.add( "R_TEMPLATE" );
                objects.add( templateId );
            }
            data = Todo.dao.findFirstByColumns( columns, objects );
        }

        return data;
    }
}

