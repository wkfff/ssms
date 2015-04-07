/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ViewAndModel.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core;

public class ViewAndModel {
    private String viewName;
    private ModelBean model = ModelBean.newInstance();

    public String getViewName() {
        return viewName;
    }

    public void setViewName( String viewName ) {
        this.viewName = viewName;
    }

    public ModelBean getModel() {
        return model;
    }

    public void setModel( ModelBean model ) {
        this.model = model;
    }

    public ViewAndModel view(String viewName) {
        setViewName( viewName );
        return this;
    }
    public ViewAndModel model(ModelBean model) {
        setModel( model );
        return this;
    }

    public ViewAndModel put(String key, Object value){
        model.put(key, value);
        return this;
    }
}
