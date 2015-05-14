/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TreeItem.java
 * 创建时间：2015-04-25
 * 创建用户：张铮彬
 */

package com.lanstar.common.tree;

import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private TreeNode() {}

    public static List<TreeNode> build( String rootId, JdbcRecordSet records, String idField, String pidField, String textField ) {
        TreeNode rootWrap = new TreeNode();
        for ( JdbcRecord record : records ) {
            String id = record.getString( idField );
            // the children of wrap is the record, if record set contains root id.
            if ( Objects.equals( id, rootId ) ) {
                rootWrap.children.clear();
                // the pid is root pid, build node and add to wrap.
                TreeNode rootNode = new TreeNode();
                rootNode.id = id;
                rootNode.text = record.getString( textField );
                rootNode.attributes = record;
                rootWrap.children.add( rootNode );
                break;
            }

            String pid = record.getString( pidField );
            if ( Objects.equals( pid, rootId ) ) {
                TreeNode rootNode = new TreeNode();
                rootNode.id = id;
                rootNode.text = record.getString( textField );
                rootNode.attributes = record;
                rootWrap.children.add( rootNode );
            }
        }
        // each root in wrap to add children
        for ( TreeNode root : rootWrap.children ) {
            build( root, records, idField, pidField, textField );
        }
        return rootWrap.children;
    }

    public static void build( TreeNode root, JdbcRecordSet records, String idField, String pidField, String textField ) {
        for ( JdbcRecord record : records ) {
            String pid = record.getString( pidField );
            if ( root.getId().equalsIgnoreCase( pid ) ) {
                TreeNode node = getNode( record, idField, textField );
                root.children.add( node );
                build( node, records, idField, pidField, textField );
            }
        }
    }

    public static TreeNode getNode( JdbcRecord record, String idField, String textField ) {
        TreeNode node = new TreeNode();
        node.id = record.getString( idField );
        node.text = record.getString( textField );
        node.attributes = record;
        return node;
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