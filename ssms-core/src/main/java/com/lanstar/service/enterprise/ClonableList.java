/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ClonableList.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import java.util.ArrayList;
import java.util.Collection;

public class ClonableList<T> implements IClonable<T> {
    ArrayList<IClonable<T>> list = new ArrayList<>();

    @Override
    public void cloneTo( T target ) {
        for ( IClonable<T> item : list ) {
            item.cloneTo( target );
        }
    }

    public void add( IClonable<T> item ) {
        list.add( item );
    }

    public void add( int index, IClonable<T> element ) {
        list.add( index, element );
    }

    public boolean addAll( Collection<? extends IClonable<T>> c ) {
        return list.addAll( c );
    }

    public boolean addAll( int index, Collection<? extends IClonable<T>> c ) {
        return list.addAll( index, c );
    }
}
