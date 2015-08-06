/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NavgateController.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lanstar.common.ModelInjector;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.kit.navgate.NavgateBean;
import com.lanstar.model.kit.navgate.NavgateTreeBuilder;
import com.lanstar.model.system.Navgate;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class NavgateController extends Controller {
    @SuppressWarnings("unchecked")
    public void index() {
        List<Navgate> list = Navgate.dao.find( SqlKit.sql( "system.navgate.listRoot" ), 0 );
        Map<String, Object> root = null;
        @SuppressWarnings("rawtypes")
        List roots = new ArrayList<HashMap>();
        for ( Navgate tree : list ) {
            root = new HashMap<String, Object>();
            root.put( "name", tree.getName() );
            root.put( "id", tree.getId() );
            roots.add( root );
        }
        setAttr( "root", roots );
    }

    public void tree() {
        Integer parentId = getParaToInt( "parentId" );
        List<Navgate> list = Navgate.list();
        NavgateTreeBuilder bulider = new NavgateTreeBuilder( list, "R_SID" );
        List<NavgateBean> trees = bulider.build().getChildren();
        for ( NavgateBean tree : trees ) {
            if ( tree.getId() == parentId ) {
                setAttr( "item", tree.getChildren() );
                setAttr( "title", tree.getName() );
                break;
            }
        }
    }

    public void save() {
        Integer id = getParaToInt( "id" );
        String name = getPara( "name" );
        String icon = getPara( "icon" );
        String url = getPara( "url" );
        String desc = getPara( "desc" );
        Integer index = getParaToInt( "index" );
        Integer parentId = getParaToInt( "parentId" );
        Navgate model = null;
        if ( id != null ) model = Navgate.dao.findById( id );
        else model = new Navgate();

        model.setName( name );
        model.setIcon( icon );
        model.setUrl( url );
        model.setDesc( desc );
        if ( index != null ) {
            model.setIndex( index );
        }
        if ( parentId != null ) {
            model.setParentId( parentId );
        }
        ModelInjector.injectOpreator( model, IdentityContextWrap.getIdentityContext( this ) );

        if ( id == null ) model.save();
        else model.update();

        renderJson( model.getId() );
    }

    public void remove() {
        Integer id = getParaToInt( "id" );
        Navgate model = null;
        if ( id != null ) model = Navgate.dao.findById( id );
        if ( model != null ) {
            renderJson( model.delete() );
        } else {
            renderJson( false );
        }
    }

    @SuppressWarnings("unchecked")
    public void move() {
        Integer currId = getParaToInt( "currId" );
        Integer currIndex = getParaToInt( "currIndex" );
        Integer replaceId = getParaToInt( "replaceId" );
        Integer replaceIndex = getParaToInt( "replaceIndex" );
        @SuppressWarnings("rawtypes")
        List indexs = new ArrayList<Integer>();
        Navgate currModel = null;
        Navgate replaceModel = null;
        if ( currId != null && replaceId != null ) {
            currModel = Navgate.dao.findById( currId );
            replaceModel = Navgate.dao.findById( replaceId );
        }
        if ( currModel != null && replaceModel != null ) {
            currModel.setIndex( replaceIndex );
            currModel.update();
            replaceModel.setIndex( currIndex );
            replaceModel.update();
            indexs.add( currModel.getIndex() );
            indexs.add( replaceModel.getIndex() );
        }

        renderJson( indexs );

    }
}
