/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileLinstener.java
 * 创建时间：2015-06-09
 * 创建用户：张铮彬
 */

package com.lanstar.app.template;

import com.lanstar.model.tenant.FileContentState;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFileModel;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.FilterCallbackLinstener;
import com.lanstar.plugin.activerecord.Model;

public class TenantTemplateFileLinstener extends FilterCallbackLinstener<TemplateFileModel<?>> {
    @Override
    public void innerBeforeSave( TemplateFileModel<?> m ) {
    }

    @Override
    public void innerAfterSave( TemplateFileModel<?> m ) {
        TemplateFile file = m.getTemplateFile();
        int count = file.getFileCount();
        file.setFileCount( count + 1 );
        file.update();
        addFolderCount( file.getFolder(), 1 );
    }

    @Override
    public void innerBeforeUpdate( TemplateFileModel<?> m ) {
        if ( FileContentState.CLONED.equals( m.getStatus() ) || FileContentState.UNKNOWN.equals( m.getStatus() ) ) {
            TemplateFile file = m.getTemplateFile();
            int count = file.getFileCount();
            file.setFileCount( count + 1 );
            file.update();
            m.setStatus( FileContentState.CHANGED );
        }
        m.setVersion( m.getVersion() + 1 );
    }

    @Override
    public void innerAfterUpdate( TemplateFileModel<?> m ) {
    }

    @Override
    public void innerBeforeDelete( TemplateFileModel<?> m ) {

    }

    @Override
    public void innerAfterDelete( TemplateFileModel<?> m ) {
        TemplateFile file = m.getTemplateFile();
        int count = file.getFileCount();
        file.setFileCount( count - 1 );
        file.update();

        addFolderCount( file.getFolder(), -1 );
    }

    @Override
    protected boolean filterType( Model<?> m ) {
        return TemplateFileModel.class.isAssignableFrom( m.getClass() );
    }

    private void addFolderCount( TemplateFolder folder, int value ) {
        if ( folder == null ) return;
        Integer count = folder.getFileCount();
        folder.setFileCount( count + value );
        folder.update();

        TemplateFolder parent = folder.getParent();
        addFolderCount( parent, value );
    }
}
