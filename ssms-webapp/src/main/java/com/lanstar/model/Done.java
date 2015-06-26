/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Task.java
 * 创建时间：2015年6月5日 下午4:50:56
 * 创建用户：林峰
 */
package com.lanstar.model;

import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.ModelExt;

import java.util.Date;

/**
 * 已办
 */
public class Done extends ModelExt<Done> {
    public static final Done dao = new Done();

    public int getId() {
        return getInt( "SID" );
    }

    /** 获取待办特征码 */
    public String getSignature() {
        return getStr( "C_CONTROL" );
    }

    public void setSignature( String signature ) {
        set( "C_CONTROL", signature );
    }

    /** 获取待办记录表单的SID */
    public int getSrcId() {
        return getInt( "R_SID" );
    }

    public void setSrcId( int id ) {
        set( "R_SID", id );
    }

    /** 获取待办任务的标题 */
    public String getTitle() {
        return getStr( "C_TITLE" );
    }

    /** 设置待办任务的标题 */
    public void setTitle( String title ) {
        set( "C_TITLE", title );
    }

    /** 获取待办任务的访问地址 */
    public String getUrl() {
        return getStr( "C_URL" );
    }

    /** 设置待办任务的访问地址 */
    public void setUrl( String url ) {
        set( "C_URL", url );
    }

    /** 获取任务要求的开始时间 */
    public Date getBeginTime() {
        return getDate( "T_BEGIN" );
    }

    /** 设置任务要求的开始时间 */
    public void setBeginTime( Date date ) {
        set( "T_BEGIN", date );
    }

    /** 获取任务要求的完成时间 */
    public Date getEndTime() {
        return getDate( "T_END" );
    }

    /** 设置任务要求的完成时间 */
    public void setEndTime( Date date ) {
        set( "T_END", date );
    }

    /**
     * 获取待办任务的通知时间
     */
    public Date getNotifyTime() {
        return getDate( "T_NOTIFY" );
    }

    public void setNotifyTime( Date date ) {
        set( "T_NOTIFY", date );
    }

    public void setTenant( Tenant tenant ) {
        set( "R_TENANT", tenant.getTenantId() );
        set( "S_TENANT", tenant.getTenantName() );
        set( "P_TENANT", tenant.getTenantType().getName() );
    }
}
