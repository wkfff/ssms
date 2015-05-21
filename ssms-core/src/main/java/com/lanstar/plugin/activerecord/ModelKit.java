/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ModelKit.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

/**
 * Copyright (c) 2011-2013, kidzhou 周磊 (zhouleib1412@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lanstar.plugin.activerecord;

import com.lanstar.common.log.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ModelKit {

    protected final static Logger logger = Logger.getLogger( ModelKit.class );

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Record toRecord( Model model ) {
        Record record = new Record();
        Set<Entry<String, Object>> attrs = model.getAttrsEntrySet();
        for ( Entry<String, Object> entry : attrs ) {
            record.set( entry.getKey(), entry.getValue() );
        }
        return record;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String, Object> toMap( Model model ) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.putAll( model.getAttrs() );
        return map;
    }

    public static Model<?> fromBean( Class<? extends Model<?>> clazz, Object bean ) {
        Model<?> model = null;
        try {
            model = clazz.newInstance();
        } catch ( Exception e ) {
            logger.error( e.getMessage(), e );
            return model;
        }
        //TODO bean to model
        return model;
    }

    @SuppressWarnings("rawtypes")
    public static void copyColumns( Model src, Model desc, String... columns ) {
        for ( String column : columns ) {
            String[] res = column.split( "," );
            if ( res.length == 1 ) {
                desc.set( column, src.get( column ) );
            } else {
                desc.set( res[1], src.get( res[0] ) );
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static void clone( Model src, Model desc ) {
        @SuppressWarnings("unchecked")
        Set<Entry<String, Object>> attrs = src.getAttrsEntrySet();
        for ( Entry<String, Object> attr : attrs ) {
            String key = attr.getKey();
            Object value = attr.getValue();
            desc.set( key, value );
        }
    }

    public static int hashCode( Model<?> model ) {
        final int prime = 31;
        int result = 1;
        Table tableinfo = TableMapping.me().getTable( model.getClass() );
        Set<Entry<String, Object>> attrsEntrySet = model.getAttrsEntrySet();
        for ( Entry<String, Object> entry : attrsEntrySet ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Class<?> clazz = tableinfo.getColumnType( key );
            if ( clazz == Integer.class ) {
                result = prime * result + (Integer) value;
            } else if ( clazz == Short.class ) {
                result = prime * result + (Short) value;
            } else if ( clazz == Long.class ) {
                result = prime * result + (int) ((Long) value ^ ((Long) value >>> 32));
            } else if ( clazz == Float.class ) {
                result = prime * result + Float.floatToIntBits( (Float) value );
            } else if ( clazz == Double.class ) {
                long temp = Double.doubleToLongBits( (Double) value );
                result = prime * result + (int) (temp ^ (temp >>> 32));
            } else if ( clazz == Boolean.class ) {
                result = prime * result + ((Boolean) value ? 1231 : 1237);
            } else if ( clazz == Model.class ) {
                result = hashCode( (Model<?>) value );
            } else {
                result = prime * result + ((value == null) ? 0 : value.hashCode());
            }
        }
        return result;
    }

    public static boolean equals( Model<?> model, Object obj ) {
        if ( model == obj )
            return true;
        if ( obj == null )
            return false;
        if ( model.getClass() != obj.getClass() )
            return false;
        Model<?> other = (Model<?>) obj;
        Table tableinfo = TableMapping.me().getTable( model.getClass() );
        Set<Entry<String, Object>> attrsEntrySet = model.getAttrsEntrySet();
        for ( Entry<String, Object> entry : attrsEntrySet ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Class<?> clazz = tableinfo.getColumnType( key );
            if ( clazz == Float.class ) {
            } else if ( clazz == Double.class ) {
            } else if ( clazz == Model.class ) {
            } else {
                if ( value == null ) {
                    if ( other.get( key ) != null )
                        return false;
                } else if ( !value.equals( other.get( key ) ) )
                    return false;
            }
        }
        return true;
    }
}