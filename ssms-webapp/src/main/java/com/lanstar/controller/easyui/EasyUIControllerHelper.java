/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EasyUIControllerHelper.java
 * 创建时间：2015-04-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.easyui;

import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.dialect.JdbcPageRecordSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EasyUIControllerHelper {
    public static Object toDatagridResult( JdbcPageRecordSet list ) {
        return new DatagridResult( list.getTotal(), list.getData() );
    }

    public static Collection<TreeNode> toTree( JdbcRecordSet records, String idField, String pidField, String textField ) {
        Map<String, TreeNode> map = new HashMap<>();
        Map<String, TreeNode> rootMap = new HashMap<>();
        for ( JdbcRecord record : records ) {
            String id = record.getString( idField );
            String text = record.getString( textField );
            String pid = record.getString( pidField );
            // 先试着根据父id取树节点，如果取不到则当前节点就当一个根节点。后续要再验证一次就是了。
            TreeNode item = map.get( pid );
            if ( item == null ) {
                item = new TreeNode( id, text, record );
                map.put( id, item );
                rootMap.put( id, item );
            } else {
                TreeNode e = new TreeNode( id, text, record );
                item.getChildren().add( e );
                map.put( id, e );
            }
            if ( item.getChildren().size() > 0 ) rootMap.remove( id );
        }
        return rootMap.values();
    }
}

