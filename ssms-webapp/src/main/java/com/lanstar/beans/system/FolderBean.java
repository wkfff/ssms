/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FolderBean.java
 * 创建时间：2015-06-29
 * 创建用户：张铮彬
 */

package com.lanstar.beans.system;

import java.util.ArrayList;
import java.util.List;

public class FolderBean {
    private int id;
    private String name;
    private Integer index;
    // TODO add more fields
    private List<FolderBean> children = new ArrayList<>();
    private List<FileBean> files = new ArrayList<>();

    public boolean addFile( FileBean bean ) {return files.add( bean );}

    public boolean addFolder( FolderBean bean ) {return children.add( bean );}

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

    public List<FolderBean> getChildren() {
        return children;
    }

    public void setChildren( List<FolderBean> children ) {
        this.children = children;
    }

    public List<FileBean> getFiles() {
        return files;
    }

    public void setFiles( List<FileBean> files ) {
        this.files = files;
    }
}
