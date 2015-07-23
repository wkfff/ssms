/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FolderTreeBuilder.java
 * 创建时间：2015-06-29
 * 创建用户：张铮彬
 */

package com.lanstar.model.kit.folder;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.lanstar.model.kit.TreeBuilder;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.system.TemplateFolder;

import java.util.List;
import java.util.Objects;

public class SystemFolderTreeBuilder extends TreeBuilder<FolderBean, TemplateFolder> {
    private final List<TemplateFile> files;

    public SystemFolderTreeBuilder( List<TemplateFolder> folders, List<TemplateFile> files, String parentField ) {
        super( folders, parentField );
        this.files = files;
    }

    public static FolderBean tree(int template){
        List<TemplateFolder> folders = TemplateFolder.listByTemplate( template );
        List<TemplateFile> files = TemplateFile.listByTemplate( template );
        // 根据目录信息构造树
        SystemFolderTreeBuilder treeBuilder = new SystemFolderTreeBuilder( folders, files, "R_SID" );
        return treeBuilder.build();
    }

    @Override
    public FolderBean build() {
        final TemplateFolder first = first( 0 );
        FolderBean root = new FolderBean();
        putValues( first, root );

        addChildren( root );
        return root;
    }

    @Override
    public Object getKeyValue( TemplateFolder item ) {
        return item.get( parentField );
    }

    private void putValues( TemplateFolder src, FolderBean desc ) {
        desc.setId( src.getId() );
        desc.setName( src.getName() );
        desc.setDescript( src.getDescript() );
        desc.setIndex( src.getIndex() );
    }

    private void putValues( TemplateFile src, FileBean desc ) {
        desc.setId( src.getId() );
        desc.setName( src.getName() );
        desc.setDesc( src.getDescript() );
        desc.setIndex( src.getIndex() );
        desc.setCycleValue( src.getCycleValue() );
        desc.setCycleUnitCode( src.getCycleUnitCode() );
        desc.setTemplateFileCode( src.getTemplateFileCode() );
        desc.setExplain( src.getExplain() );
        desc.setRemind( src.getRemind() );
    }

    private void addChildren( final FolderBean bean ) {
        for ( final TemplateFolder map : children( bean.getId() ) ) {
            FolderBean child = new FolderBean();
            putValues( map, child );
            bean.addFolder( child );
            addChildren( child );
        }
        Iterable<TemplateFile> files = Iterables.filter( this.files, new Predicate<TemplateFile>() {
            @Override
            public boolean apply( TemplateFile input ) {
                return Objects.equals( input.getInt( "R_SID" ), bean.getId() );
            }
        } );
        for ( TemplateFile file : files ) {
            FileBean fileBean = new FileBean();
            putValues( file, fileBean );
            bean.addFile( fileBean );
        }
    }
}
