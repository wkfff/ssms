/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileBean.java
 * 创建时间：2015-06-29
 * 创建用户：张铮彬
 */

package com.lanstar.beans.system;

public class FileBean {
    private int id;
    private String name;
    private Integer index;
    private String templateType;
    
    // TODO add more fields

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex( Integer index ) {
        this.index = index;
    }
    
    public String getTemplateUrl(){
        // TemplateProp templateProp = TemplatePropPlugin.me().get( templateType );
        // return templateProp.getTemplateUrl(id);
        return "";
    }
    
    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType( String code ) {
        this.templateType = code;
    }
}
