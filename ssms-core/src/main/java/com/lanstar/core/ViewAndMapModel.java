/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ViewAndMapModel.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */

package com.lanstar.core;

public final class ViewAndMapModel extends ViewAndModel {
    private final MapModelBean mapModel;

    public ViewAndMapModel( ViewAndModel parent ) {
        super( parent );
        if ( parent.model == null || !MapModelBean.class.isAssignableFrom( parent.model.getClass() ) ) {
            if ( parent.model != null ) mapModel = new MapModelBean( parent.model.getValues() );
            else mapModel = new MapModelBean();
            model( mapModel );
        } else {
            mapModel = (MapModelBean) parent.model;
        }
        parent.model = mapModel;
    }

    @Override
    public ViewAndMapModel put( String key, Object value ) {
        mapModel.put( key, value );
        return this;
    }
}
