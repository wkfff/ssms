/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Navgate.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import java.util.List;

import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class Navgate extends ModelExt<Navgate> {
    public static Navgate dao = new Navgate();

    public static List<Navgate> list() {
        return Navgate.dao.find( SqlKit.sql( "system.navgate.list" ) );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }

    public String getIcon() {
        return getStr( "C_ICON" );
    }

    public void setIcon( String icon ) {
        set( "C_ICON", icon );
    }

    public String getUrl() {
        return getStr( "C_URL" );
    }

    public void setUrl( String url ) {
        set( "C_URL", url );
    }

    public Integer getIndex() {
        return getInt( "N_INDEX" );
    }

    public void setIndex( Integer url ) {
        set( "N_INDEX", url );
    }

    public String getDesc() {
        return getStr( "C_DESC" );
    }

    public void setDesc( String url ) {
        set( "C_DESC", url );
    }

    public int getParentId(){
        return getInt( "R_SID" );
    }
    
    public void setParentId( Integer parentId ) {
        set( "R_SID", parentId );
    }

}
