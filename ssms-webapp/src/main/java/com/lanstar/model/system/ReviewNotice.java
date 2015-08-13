/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewNotice.java
 * 创建时间：下午4:26:13
 * 创建用户：苏志亮
 */
package com.lanstar.model.system;

import java.util.Date;

import com.lanstar.plugin.activerecord.Model;

public class ReviewNotice extends Model<ReviewNotice> {
    public static final ReviewNotice dao = new ReviewNotice();

    public Integer getId() {
        return getInt( "SID" );
    }

    public void setTitle( String title ) {
        set( "C_TITLE", title );
    }

    public void setContent( String content ) {
        set( "C_CONTENT", content );
    }

    public void setState( boolean stete ) {
        set( "N_STATE", stete );
    }

    public void setRceiverId( Integer tenantId ) {
        set( "R_RECEIVER", tenantId );
    }

    public void setProfessionId( Integer professionId ) {
        set( "P_PROFESSION", professionId );
    }

    public void setPublishId( Integer id ) {
        set( "R_PUBLISH", id );
    }

    public void setPublishName( String name ) {
        set( "S_PUBLISH", name );
    }

    public void setPublishTime( Date date ) {
        set( "T_PUBLISH", date );
    }

    public boolean getReader() {
        String reader = getStr( "B_READER" );
        if ( reader != null ) {
            if ( reader.equals( "1" ) ) return true;
            else return false;
        } else {
            return false;
        }
    }

    public void setReader( boolean reader ) {
        set( "B_READER", reader );
    }

}
