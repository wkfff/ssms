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
public  enum ReviewGradeState {
    /**
     * 未开始
     */
    NONE(""),
    /**
     * 评审中,第一位表示评审内容，第二位表示评审报告，第三位表示评审证书,为1时表示完成
     */
    START("000"),
    /**
     * 评审结束
     */
    END("111")
    ;

    private String value;

    ReviewGradeState( String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }  

    @Override
    public String toString() {
        return this.value;
    }
}