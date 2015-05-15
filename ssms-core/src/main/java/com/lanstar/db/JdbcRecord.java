/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdbcRecord.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import org.springframework.util.LinkedCaseInsensitiveMap;

import java.nio.charset.StandardCharsets;

public class JdbcRecord extends LinkedCaseInsensitiveMap<Object> {
    private static final long serialVersionUID = 3629559736555206157L;

    public String getString( String key ) {
        Object value = get( key );
        if ( value == null ) return "";
        // value is byte[]
        if ( "[B".equals( value.getClass().getName() ) ) {
            return new String( (byte[]) value, StandardCharsets.UTF_8 );
        }
        return String.valueOf( value );
    }
}
