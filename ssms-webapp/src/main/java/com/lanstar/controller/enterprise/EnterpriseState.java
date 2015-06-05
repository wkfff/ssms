/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseState.java
 * 创建时间：2015年6月4日 下午2:18:14
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

/**
 * 企业状态
 *
 */
public enum EnterpriseState {
    /**
     * 未缴费
     */
    UNPAID(0),

    /**
     * 已缴费
     */
    PAID(1)
    ;

    private int value;

    EnterpriseState( int value) {
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
