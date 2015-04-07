/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ViewAndModel.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core;

public class ViewAndModel {
    protected String viewName;
    protected ModelBean model;

    public ViewAndModel() {
    }

    ViewAndModel( ViewAndModel parent ) {
        view( parent.viewName ).model( parent.model );
    }

    public String getViewName() {
        return viewName;
    }

    public ModelBean getModel() {
        return model;
    }

    public ViewAndModel view( String viewName ) {
        this.viewName = viewName;
        return this;
    }

    public ViewAndModel model( ModelBean model ) {
        this.model = model;
        return this;
    }

    public ViewAndMapModel put( String key, Object value ) {
        ViewAndMapModel map = new ViewAndMapModel( this );
        return map.put( key, value );
    }

    public ViewAndObjectModel set( Object value ) {
        ViewAndObjectModel vm = new ViewAndObjectModel( this );
        return vm.value( value );
    }
}

