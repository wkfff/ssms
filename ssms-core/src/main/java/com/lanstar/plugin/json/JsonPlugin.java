/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JsonPlugin.java
 * 创建时间：2015-04-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lanstar.common.log.LogHelper;
import com.lanstar.plugin.AppPlugin;

import java.util.List;
import java.util.Map;

public class JsonPlugin extends AppPlugin implements IJsonPlugin {
    @Override
    public void startup() {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }

    @Override
    public String toJson( Object object ) {
        return JSON.toJSONString( object, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.SortField );
    }

    @Override
    public Map<String, Object> parseObject( String text ) {
        try {
            return JSON.parseObject( text );
        } catch ( Exception e ) {
            LogHelper.warn( getClass(), e, "解析过程发生异常" );
            return null;
        }
    }

    @Override
    public List<Object> parseArray( String text ) {
        try {
            return JSON.parseArray( text );
        } catch ( Exception e ) {
            LogHelper.warn( getClass(), e, "解析过程发生异常" );
            return null;
        }
    }
}
