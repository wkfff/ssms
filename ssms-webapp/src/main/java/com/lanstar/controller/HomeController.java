/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：homeController.java
 * 创建时间：2015年4月21日 下午3:56:40
 * 创建用户：林峰
 */
package com.lanstar.controller;

import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.common.tree.TreeNode;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.identity.TenantType;
import com.lanstar.db.JdbcRecordSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页
 */
public abstract class HomeController {
    private static final Map<TenantType, String> IDENTITY_TYPE_NAV_MAP = new HashMap<>();

    static {
        IDENTITY_TYPE_NAV_MAP.put( TenantType.ENTERPRISE, "企业端导航" );
        IDENTITY_TYPE_NAV_MAP.put( TenantType.REVIEW, "评审端导航" );
        IDENTITY_TYPE_NAV_MAP.put( TenantType.GOVERNMENT, "政府端导航" );
        IDENTITY_TYPE_NAV_MAP.put( TenantType.SYSTEM, "系统运维端导航" );
    }

    public ViewAndModel index( HandlerContext context ) {
        JdbcRecordSet records = context.SYSTEM_DB.withTable( "SYS_NAV" )
                                                 .orderby( "R_SID, N_INDEX" )
                                                 .queryList();
        Collection<TreeNode> nodes = EasyUIControllerHelper.toTree("0", records, "SID", "R_SID", "C_NAME" );
        TreeNode node = findNode( nodes, IDENTITY_TYPE_NAV_MAP.get( context.getIdentity().getTenantType() ) );
        ViewAndModel returnWith = context.returnWith();
        if ( node != null ) returnWith = returnWith.put( "nav", node.getChildren() );
        return returnWith;
    }

    private TreeNode findNode( Collection<TreeNode> nodes, String navName ) {
        for ( TreeNode node : nodes ) {
            if ( navName.equalsIgnoreCase( node.getText() ) ) return node;
            TreeNode tmp = findNode( node.getChildren(), navName );
            if ( tmp != null ) return tmp;
        }
        return null;
    }
}
