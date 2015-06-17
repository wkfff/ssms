/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FolderTreeBuilder.java
 * 创建时间：2015-06-17
 * 创建用户：张铮彬
 */

package com.lanstar.beans;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.lanstar.common.TreeKit;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FolderTreeBuilder extends TreeKit<FolderBean> {
    private final List<Map<String, Object>> files;

    public FolderTreeBuilder( List<Map<String, Object>> folders, List<Map<String, Object>> files, String parentField ) {
        super( folders, parentField );
        this.files = files;
    }

    @Override
    public FolderBean tree() {
        FolderBean rootFolder = new FolderBean();
        rootFolder.attrs.putAll( first( 0 ) );
        addChildren( rootFolder );
        return rootFolder;
    }

    private void addChildren( FolderBean bean ) {
        for ( final Map<String, Object> map : children( bean.attrs.get( "SID" ) ) ) {
            FolderBean child = new FolderBean();
            child.attrs.putAll( map );
            bean.folders.add( child );
            Iterable<Map<String, Object>> files = Iterables.filter( this.files, new Predicate<Map<String, Object>>() {
                @Override
                public boolean apply( Map<String, Object> input ) {
                    return Objects.equals( input.get( "R_SID" ), map.get( "SID" ) );
                }
            } );
            for ( Map<String, Object> file : files ) {
                FileBean fileBean = new FileBean();
                fileBean.attrs.putAll( file );
                bean.files.add( fileBean );
            }

            addChildren( child );
        }
    }
}
