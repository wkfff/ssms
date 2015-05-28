/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradePlanR.java
 * 创建时间：2015年5月27日 下午12:01:53
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import java.util.Date;

import com.lanstar.common.kit.DateKit;
import com.lanstar.plugin.activerecord.Model;

/**
 * 评审
 *
 */
public class GradePlanR extends Model<GradePlanR> {
    public static GradePlanR dao = new GradePlanR();

    public String getTitle() {
        return getStr( "C_TITLE" );
    }

    public void setTitle( String title ) {
        set( "C_TITLE", title );
    }

    public Integer getState() {
        return getInt( "N_STATE" );
    }

    public void setState( int state ) {
        set( "N_STATE", state );
    }

    public String getStartDate() {
        return DateKit.toStr( getTimestamp( "T_START" ) );
    }

    public void setStartDate( Date date ) {
        set( "T_START", date );
    }

    public String getEndDate() {
        return DateKit.toStr( getTimestamp( "T_END" ) );
    }

    public void setEndtDate( Date date ) {
        set( "T_END", date );
    }

    public Integer getId() {
        return getInt( "SID" );
    }
}
