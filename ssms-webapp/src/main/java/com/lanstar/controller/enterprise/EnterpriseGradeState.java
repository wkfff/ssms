/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseState.java
 * 创建时间：2015年6月4日 下午2:18:14
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

/**
 * 企业自评状态
 *
 */
public enum EnterpriseGradeState {
    /**
     * 未开始
     */
    NONE(-1),

    /**
     * 开始自评
     */
    START(0),

    /**
     * 已完成自评填报，未完成自评报告
     */
    CONTENT(1),
    /**
     * 未完成自评填报，已完成自评报告
     */
    REPORT(2),
    /**
     * 自评结束
     */
    END(3)
    ;

    private int value;

    EnterpriseGradeState( int value) {
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
