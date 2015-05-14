/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateFolder.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.common.tree.TreeNode;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.service.TenantContext;

class StandardTemplateFolder implements IClonable<TenantContext> {
    private final ProfessionTemplateService service;
    private final TreeNode node; // current folder node
    private StandardTemplateFolder parent; // parent folder
    private ClonableList<TenantContext> children = new ClonableList<>();
    private int sid; // current folder sid

    public StandardTemplateFolder( ProfessionTemplateService service, TreeNode node ) {
        this.service = service;
        this.node = node;

        // build children folder
        for ( TreeNode child : node.getChildren() ) {
            children.add( new StandardTemplateFolder( service, child, this ) );
        }
    }

    private StandardTemplateFolder( ProfessionTemplateService service, TreeNode node, StandardTemplateFolder parent ) {
        this( service, node );
        this.parent = parent;
    }

    @Override
    public void cloneTo( TenantContext target ) {
        // -----------------------------------------
        // first, clone current folder
        // second, clone children folder
        // -----------------------------------------

        // skip copy folder if folder exists
        if ( !exists( target ) ) {
            // clone folder, and set current sid
            this.sid = cloneFolder( target );
        }
        // clone children
        children.cloneTo( target );

        // clone files
        cloneFiles( target );
    }

    private int cloneFolder( TenantContext target ) {
        ARTable table = target.withTable( "SSM_STDTMP_FOLDER" )
                              .values( node.getAttributes() )
                              .value( "R_TENANT", target.getTenantId() )
                              .value( "S_TENANT", target.getTenantName() )
                              .value( "P_TENANT", target.getTenantType().getName() );
        // set R_SID with parent sid if parent exists
        if ( this.parent != null ) {
            table.value( "R_SID", parent.sid );
        }
        table.value( "R_SOURCE", node.getId() );
        table.getValues().remove( "SID" );
        table.insert();

        return target.getDbContext().getSID();
    }

    private void cloneFiles( TenantContext target ) {
        JdbcRecordSet files = service.source.withTable( "SYS_STDTMP_FILE" )
                                            .where( "R_SID=?", node.getAttributes().get( "SID" ) )
                                            .queryList();
        ClonableList<TenantContext> fileList = new ClonableList<>();
        for ( JdbcRecord file : files ) {
            fileList.add( new StandardTemplateFile( service, file, sid ) );
        }
        fileList.cloneTo( target );
    }

    private boolean exists( TenantContext target ) {
        int tenantId = target.getTenantId();
        String tenantType = target.getTenantType()
                                  .getName();
        JdbcRecord folder = target.withTable( "SSM_STDTMP_FOLDER" )
                                  .where( "R_SOURCE=? and R_TENANT=? and P_TENANT=?", node.getId(), tenantId, tenantType )
                                  .query();
        if ( folder != null ) this.sid = (int) folder.get( "SID" );
        return folder != null;
    }
}
