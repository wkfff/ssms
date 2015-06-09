/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemTemplateFileLinstener.java
 * 创建时间：2015-06-09
 * 创建用户：张铮彬
 */

package com.lanstar.app.template;

import com.lanstar.model.tenant.TemplateFileModel;
import com.lanstar.plugin.activerecord.FilterCallbackLinstener;
import com.lanstar.plugin.activerecord.Model;

public class SystemTemplateFileLinstener extends FilterCallbackLinstener<TemplateFileModel<?>> {
    @Override
    protected void innerBeforeSave( TemplateFileModel<?> m ) {
        m.setVersion( 0 );
    }

    @Override
    protected void innerAfterSave( TemplateFileModel<?> m ) {

    }

    @Override
    protected void innerBeforeUpdate( TemplateFileModel<?> m ) {
        if ( m.isModified() == false ) return;
        m.setVersion( m.getVersion() + 1 );
    }

    @Override
    protected void innerAfterUpdate( TemplateFileModel<?> m ) {

    }

    @Override
    protected void innerBeforeDelete( TemplateFileModel<?> m ) {

    }

    @Override
    protected void innerAfterDelete( TemplateFileModel<?> m ) {

    }

    @Override
    protected boolean filterType( Model<?> m ) {
        return TemplateFileModel.class.isAssignableFrom( m.getClass() );
    }
}
