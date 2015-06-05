/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewState.java
 * 创建时间：2015年6月2日 下午3:16:51
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

/**
 * 评审状态
 *
 */
public  enum ReviewState {
    /**
     * 未开始
     */
    NONE(-1),
    /**
     * 评审中
     */
    START(0),
    /**
     * 已完成评审填报
     */
    CONTENT(1),
    /**
     * 已完成评审报告
     */
    REPORT(2),
    /**
     * 已完成评审结果上传
     */
    UPLOAD(3),
    /**
     * 评审结束
     */
    END(4)
    ;

    private int value;

    ReviewState( int value) {
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