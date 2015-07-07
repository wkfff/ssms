/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NoticeState.java
 * 创建时间：2015年7月2日 下午12:03:30
 * 创建用户：林峰
 */
package com.lanstar.controller.system;

/**
 * 通知公告状态
 *
 */
public enum NoticeState {
    /**
     * 草稿
     */
    DEAFTS(0),
    /**
     * 已发布
     */
    PUBLISH(1),
    ;

    private int value;

    NoticeState( int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }  

    @Override
    public String toString() {
        return String.valueOf ( this.value );
    }
}