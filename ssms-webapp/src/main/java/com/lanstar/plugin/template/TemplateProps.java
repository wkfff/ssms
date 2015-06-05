/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateProps.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TemplateProps {
    List<TemplateProp> list = new ArrayList<>();

    public boolean add( TemplateProp templateProp ) {return list.add( templateProp );}

    public boolean addAll( Collection<? extends TemplateProp> c ) {return list.addAll( c );}

    public boolean addAll( int index, Collection<? extends TemplateProp> c ) {return list.addAll( index, c );}

    public boolean removeAll( Collection<?> c ) {return list.removeAll( c );}

    public void add( int index, TemplateProp element ) {list.add( index, element );}

    public TemplateProp set( int index, TemplateProp element ) {return list.set( index, element );}

    public TemplateProp get( int index ) {return list.get( index );}

    public boolean remove( Object o ) {return list.remove( o );}

    public TemplateProp remove( int index ) {return list.remove( index );}
}
