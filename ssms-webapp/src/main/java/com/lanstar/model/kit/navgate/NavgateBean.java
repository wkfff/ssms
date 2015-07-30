/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NavgateBean.java
 * 创建时间：上午10:36:16
 * 创建用户：苏志亮
 */
package com.lanstar.model.kit.navgate;

import java.util.ArrayList;
import java.util.List;

public class NavgateBean {
    private int id;
    private String name;
    private String url;
    private String icon;
    private String desc;
    private Integer index;
    private List<NavgateBean> children = new ArrayList<>();

    public boolean addNavgate( NavgateBean bean ) {
        return children.add( bean );
    }

    public List<NavgateBean> getChildren() {
        return children;
    }

    public void setChildren( List<NavgateBean> children ) {
        this.children = children;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon( String icon ) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc( String desc ) {
        this.desc = desc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex( Integer index ) {
        this.index = index;
    }

}
