/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EasyUIControllerHelper.java
 * 创建时间：2015-04-25
 * 创建用户：张铮彬
 */

package com.lanstar.helper.easyui;

import com.lanstar.common.tree.TreeNode;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.dialect.JdbcPageRecordSet;

import java.util.List;

public class EasyUIControllerHelper {
    public static Object toDatagridResult( JdbcPageRecordSet list ) {
        return new DatagridResult( list.getTotal(), list.getData() );
    }

    public static List<TreeNode> toTree( String rootId, JdbcRecordSet records, String idField, String pidField, String textField ) {
        return TreeNode.build( rootId, records, idField, pidField, textField );
    }
}

