/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SyncUnit06.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.app.template;

import com.lanstar.model.tenant.TemplateFile06Item;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.template.SyncUnit;

import java.util.List;

public class SyncUnit06 extends SyncUnit {
    @Override
    protected void copyFileContent() {
        super.copyFileContent();
        Integer templateFileId = getSourceFile().getTemplateFileId();
        if ( templateFileId == null ) return;
        List<com.lanstar.model.system.TemplateFile06Item> items = com.lanstar.model.system.TemplateFile06Item.dao.findByColumn( "R_SID", templateFileId );
        for ( com.lanstar.model.system.TemplateFile06Item item : items ) {
            copyItem( item );
        }
    }

    private void copyItem( com.lanstar.model.system.TemplateFile06Item src ) {
        // 只会发生在第一次拷贝，因此直接新建就好了，也不用判断是否存在
        TemplateFile06Item dest = new TemplateFile06Item();
        ModelKit.clone( src, dest );
        dest.remove( "SID", "R_TENANT", "S_TENANT", "P_TENANT" );
        dest.set( "R_SID", getTargetFile().getTemplateFileId() );
        dest.set( "R_TENANT", getTargetFile().get( "R_TENANT" ) );
        dest.set( "S_TENANT", getTargetFile().get( "S_TENANT" ) );
        dest.set( "P_TENANT", getTargetFile().get( "P_TENANT" ) );
        dest.save();
    }
}
