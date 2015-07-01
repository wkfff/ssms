/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateInitializerState.java
 * 创建时间：2015-06-30
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

public enum TemplateInitializerState {
    NONE( "未开始初始化" ),
    FINISH( "完成模板初始化" ),
    STARTUP( "开始模板初始化" );

    private final String msg;

    TemplateInitializerState( String msg ) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
