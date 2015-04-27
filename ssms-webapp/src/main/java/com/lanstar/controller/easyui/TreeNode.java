/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TreeItem.java
 * 创建时间：2015-04-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.easyui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeNode {
    private String id;
    private String text;
    private Map<String, Object> attributes;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode( String id, String text, Map<String, Object> attributes ) {
        this.id = id;
        this.text = text;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
