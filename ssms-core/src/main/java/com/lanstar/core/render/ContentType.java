/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ContentType.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

/**
 * ContentType
 * <br>
 * TOMCAT-HOME/conf/web.xml
 * <br>
 * http://tool.oschina.net/commons
 */
public enum ContentType {

    TEXT( "text/plain" ),
    HTML( "text/html" ),
    XML( "text/xml" ),
    JSON( "application/json" ),
    JAVASCRIPT( "application/javascript" );

    private final String value;

    ContentType( String value ) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String toString() {
        return value;
    }
}