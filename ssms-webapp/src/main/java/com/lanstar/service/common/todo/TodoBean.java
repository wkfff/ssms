/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoBean.java
 * 创建时间：2015-06-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.common.todo;

import com.lanstar.common.Asserts;
import com.lanstar.model.Todo;

import java.util.Date;

public class TodoBean {

    private String signature;
    private Date beginTime;
    private Date endTime;
    private String title;
    private Date notifyTime;
    private int srcId;
    private String url;
    private Integer id;

    static TodoBean convert( Todo todo ) {
        if ( todo == null ) return null;
        TodoBean bean = new TodoBean();
        bean.id = todo.getId();
        bean.signature = todo.getSignature();
        bean.beginTime = todo.getBeginTime();
        bean.endTime = todo.getEndTime();
        bean.title = todo.getTitle();
        bean.notifyTime = todo.getNotifyTime();
        bean.srcId = todo.getSrcId();
        bean.url = todo.getUrl();

        return bean;
    }

    static void inject( TodoBean bean, Todo todo ) {
        Asserts.notNull( bean, "bean can not be null" );
        Asserts.notNull( todo, "todo can not be null" );
        todo.setBeginTime( bean.beginTime );
        todo.setEndTime( bean.endTime );
        todo.setTitle( bean.title );
        todo.setUrl( bean.url );
        todo.setSignature( bean.signature );
        todo.setSrcId( bean.srcId );
    }

    public Integer getId() {
        return id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature( String signature ) {
        this.signature = signature;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime( Date beginTime ) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime( Date endTime ) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId( int srcId ) {
        this.srcId = srcId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }
}
