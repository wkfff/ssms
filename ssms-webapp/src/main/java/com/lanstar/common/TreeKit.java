/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TreeKit.java
 * 创建时间：2015-06-17
 * 创建用户：张铮彬
 */

package com.lanstar.common;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class TreeKit<T> {
    protected final List<Map<String, Object>> list;
    protected final String parentField;

    public TreeKit( List<Map<String, Object>> list, String parentField ) {
        this.list = list;
        this.parentField = parentField;
    }

    public abstract T tree();

    protected final Iterable<Map<String, Object>> children( final Object parentKey ) {
        return Iterables.filter( list, new Predicate<Map<String, Object>>() {
            @Override
            public boolean apply( Map<String, Object> input ) {
                return Objects.equals( parentKey, input.get( parentField ) );
            }
        } );
    }

    protected final Map<String, Object> first( final Object key ) {
        return Iterables.find( list, new Predicate<Map<String, Object>>() {
            @Override
            public boolean apply( Map<String, Object> input ) {
                return Objects.equals( input.get( parentField ), key );
            }
        } );
    }
}
