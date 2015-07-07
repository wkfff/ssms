/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NoticeReceiver.java
 * 创建时间：2015年7月2日 上午11:49:32
 * 创建用户：林峰
 */
package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.Model;

/**
 * 通知公告接收者
 *
 */
public class NoticeReceiver extends Model<NoticeReceiver> {
    public static NoticeReceiver dao = new NoticeReceiver();

    private int R_RECEIVER;
    private String S_RECEIVER;
    private String Z_TYPE;
    private int R_NOTICE;

    public int getR_RECEIVER() {
        return this.getInt( "R_RECEIVER" );
    }

    public void setR_RECEIVER( int r_RECEIVER ) {
        R_RECEIVER = r_RECEIVER;
        this.set( "R_RECEIVER", r_RECEIVER );
    }

    public String getS_RECEIVER() {
        return this.getStr( "S_RECEIVER" );
    }

    public void setS_RECEIVER( String s_RECEIVER ) {
        S_RECEIVER = s_RECEIVER;
        this.set( "S_RECEIVER", s_RECEIVER );
    }

    public String getZ_TYPE() {
        return this.getStr( "Z_TYPE" );
    }

    public void setZ_TYPE( String z_TYPE ) {
        Z_TYPE = z_TYPE;
        this.set( "Z_TYPE", z_TYPE );
    }

    public int getR_NOTICE() {
        return this.getInt( "R_NOTICE" );
    }

    public void setR_NOTICE( int r_NOTICE ) {
        R_NOTICE = r_NOTICE;
        this.set( "R_NOTICE", r_NOTICE );
    }

}
