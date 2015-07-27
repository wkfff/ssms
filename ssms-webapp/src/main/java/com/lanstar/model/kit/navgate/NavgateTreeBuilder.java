/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NavgateTreeBuilder.java
 * 创建时间：上午10:41:05
 * 创建用户：苏志亮
 */
package com.lanstar.model.kit.navgate;

import java.util.List;


import com.lanstar.model.kit.TreeBuilder;
import com.lanstar.model.system.Navgate;

public class NavgateTreeBuilder extends TreeBuilder<NavgateBean, Navgate> {

    public NavgateTreeBuilder( List<Navgate> navgates, String parentField ) {
        super( navgates, parentField );
    }

    private void addChildren( final NavgateBean bean ) {
        for ( final Navgate map : children( bean.getId() ) ) {
            NavgateBean child = new NavgateBean();
            putValues( map, child );
            bean.addNavgate( child );
            addChildren( child );
        }
    }

    private void putValues( Navgate nav, NavgateBean navBean ) {
        navBean.setId( nav.getId() );
        navBean.setName( nav.getName() );
        navBean.setIcon( nav.getIcon() );
        navBean.setUrl( nav.getUrl() );
        navBean.setIndex( nav.getIndex() );
        navBean.setDesc( nav.getDesc() );
    }

    @Override
    public NavgateBean build() {
        final Navgate first = first( 0 );
        NavgateBean root = new NavgateBean();
        putValues( first, root );

        addChildren( root );
        return root;
    }

    @Override
    public Object getKeyValue( Navgate item ) {
        return item.get( parentField );
    }

}
