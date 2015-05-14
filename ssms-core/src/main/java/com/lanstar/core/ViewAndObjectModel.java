/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ViewAndObjectModel.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.core;

public final class ViewAndObjectModel extends ViewAndModel {
    private final ObjectModelBean objectModelBean;
    
    public ViewAndObjectModel( ViewAndModel parent ) {
        super( parent );
        if ( parent.model == null || !ObjectModelBean.class.isAssignableFrom( parent.model.getClass() ) ) {
            objectModelBean = new ObjectModelBean();
            model( objectModelBean );
        } else {
            objectModelBean = (ObjectModelBean) parent.model;
        }
        parent.model = objectModelBean;
    }

    public ViewAndObjectModel value( Object value ) {
        objectModelBean.set( value );
        return this;
    }
}
