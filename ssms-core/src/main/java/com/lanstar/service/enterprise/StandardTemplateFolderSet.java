/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateFolderSet.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.common.tree.TreeNode;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.service.TenantContext;

import java.util.List;

/**
 * 达标体系目录集合，作为克隆对象存在。
 */
class StandardTemplateFolderSet implements IClonable<TenantContext> {
    private final ClonableList<TenantContext> list = new ClonableList<>();
    private ProfessionTemplateService service;

    public StandardTemplateFolderSet( ProfessionTemplateService service ) {
        this.service = service;
    }

    @Override
    public void cloneTo( TenantContext target ) {
        // get standard template records
        JdbcRecordSet records = service.source.getDBSession()
                                              .query( "SELECT B.* FROM SYS_PROFESSION A\n"
                                                      + "INNER JOIN `SYS_STDTMP_FOLDER` B ON A.`R_TEMPLATE` = B.`R_TEMPLATE`\n"
                                                      + "WHERE A.SID = ?", new Object[] { service.getProfessionId() } );
        // convert to tree struct
        List<TreeNode> roots = TreeNode.build( "0", records, "SID", "R_SID", "C_NAME" );

        // clone root, and clone children in this folder object.
        for ( TreeNode root : roots ) list.add( new StandardTemplateFolder( service, root ) );

        list.cloneTo( target );
    }
}
