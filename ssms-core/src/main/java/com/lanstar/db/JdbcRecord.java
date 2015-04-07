/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdbcRecord.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.db;

import com.lanstar.common.bean.IJsonable;
import com.lanstar.plugin.json.JsonHelper;

import java.util.HashMap;
import java.util.Map;

public class JdbcRecord extends HashMap<String, Object> implements IJsonable {
    public String getString( String key ) {
        return String.valueOf( get( key ) );
    }

    /**
     * 从当前记录集合中创建记录
     */
    public static JdbcRecord create( Map<String, Object> rs ) {
        JdbcRecord record = new JdbcRecord();
        record.putAll( rs );
        return record;
    }
    
    JdbcRecord() {
    }

    @Override
    public String toJson() {
        return JsonHelper.toJson( this );
    }
}
