/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TreeNode.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.common;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TreeNode {
    private String id;
    private String text;
    private int level;
    private Map<String, Object> attributes;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode( String id, String text, Map<String, Object> attributes ) {
        this.id = id;
        this.text = text;
        this.attributes = attributes;
    }

    private TreeNode() {}

    public static List<TreeNode> build( String rootId, List<Map<String, Object>> records, String idField, String pidField, String textField ) {
        TreeNode rootWrap = new TreeNode();
        for ( Map<String, Object> record : records ) {
            String id = getString( record.get( idField ) );
            // the children of wrap is the record, if record set contains root id.
            if ( Objects.equals( id, rootId ) ) {
                rootWrap.children.clear();
                // the pid is root pid, build node and add to wrap.
                TreeNode rootNode = new TreeNode();
                rootNode.id = id;
                rootNode.text = getString( record.get( textField ) );
                rootNode.attributes = record;
                rootWrap.children.add( rootNode );
                break;
            }

            String pid = getString( record.get( pidField ) );
            if ( Objects.equals( pid, rootId ) ) {
                TreeNode rootNode = new TreeNode();
                rootNode.id = id;
                rootNode.text = getString( record.get( textField ) );
                rootNode.attributes = record;
                rootNode.level = 0;
                rootWrap.children.add( rootNode );
            }
        }
        // each root in wrap to add children
        for ( TreeNode root : rootWrap.children ) {
            build( root, records, idField, pidField, textField );
        }
        return rootWrap.children;
    }

    private static String getString( Object value ) {
        if ( value == null ) return "";
        // value is byte[]
        if ( "[B".equals( value.getClass().getName() ) ) {
            return new String( (byte[]) value, StandardCharsets.UTF_8 );
        }
        return String.valueOf( value );
    }

    public static void build( TreeNode root, List<Map<String, Object>> records, String idField, String pidField, String textField ) {
        for ( Map<String, Object> record : records ) {
            String pid = getString( record.get( pidField ) );
            if ( root.getId().equalsIgnoreCase( pid ) ) {
                TreeNode node = getNode( record, idField, textField );
                node.level = root.level+1;
                root.children.add( node );
                build( node, records, idField, pidField, textField );
            }
        }
    }

    public static TreeNode getNode( Map<String, Object> record, String idField, String textField ) {
        TreeNode node = new TreeNode();
        node.id = getString( record.get( idField ) );
        node.text = getString( record.get( textField ) );
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

    public int getLevel() {
        return level;
    }
}