/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EasyUIControllerHelper.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.common;

import com.lanstar.plugin.activerecord.Page;

import java.util.List;
import java.util.Map;

public class EasyUIControllerHelper {
    public static final String PAGE_INDEX = "page";
    public static final String PAGE_SIZE = "rows";

    public static <T> Object toDatagridResult( Page<T> list ) {
        return new DatagridResult( list.getTotalRow(), list.getList() );
    }

    public static List<TreeNode> toTree( String rootId, List<Map<String, Object>> records, String idField, String pidField, String textField ) {
        return TreeNode.build( rootId, records, idField, pidField, textField );
    }
}