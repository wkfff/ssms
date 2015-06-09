/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FilterCallbackLinstener.java
 * 创建时间：2015-06-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

@SuppressWarnings("unchecked")
public abstract class FilterCallbackLinstener<T> implements CallbackListener {
    @Override
    public void beforeSave( Model<?> m ) {
        if ( filterType( m ) == false ) return;
        innerBeforeSave( (T) m );
    }

    @Override
    public void afterSave( Model<?> m ) {
        if ( filterType( m ) == false ) return;
        innerAfterSave( (T) m );
    }

    @Override
    public void beforeUpdate( Model<?> m ) {
        if ( filterType( m ) == false ) return;
        innerBeforeUpdate( (T) m );
    }

    @Override
    public void afterUpdate( Model<?> m ) {
        if ( filterType( m ) == false ) return;
        innerAfterUpdate( (T) m );
    }

    @Override
    public void beforeDelete( Model<?> m ) {
        if ( filterType( m ) == false ) return;
        innerBeforeDelete( (T) m );
    }

    @Override
    public void afterDelete( Model<?> m ) {
        if ( filterType( m ) == false ) return;
        innerAfterDelete( (T) m );
    }

    protected abstract void innerBeforeSave( T m );

    protected abstract void innerAfterSave( T m );

    protected abstract void innerBeforeUpdate( T m );

    protected abstract void innerAfterUpdate( T m );

    protected abstract void innerBeforeDelete( T m );

    protected abstract void innerAfterDelete( T m );

    protected abstract boolean filterType( Model<?> m );
}
