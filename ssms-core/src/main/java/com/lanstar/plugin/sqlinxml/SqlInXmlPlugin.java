/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlInXmlPlugin.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.sqlinxml;

import com.lanstar.common.Asserts;
import com.lanstar.plugin.IPlugin;

public class SqlInXmlPlugin implements IPlugin {

    private String path;

    public SqlInXmlPlugin() {
    }

    @Override
    public boolean start() {
        SqlKit.init( path );
        return true;
    }

    @Override
    public boolean stop() {
        SqlKit.clearSqlMap();
        return true;
    }

    public SqlInXmlPlugin setPath( String path ) {
        Asserts.notEmpty( path, "path can not be empty" );
        if ( path.startsWith( "/" ) == false ) path = "/" + path;
        if ( path.endsWith( "/" ) ) path = path.substring( 0, path.length() - 1 );

        this.path = path;
        return this;
    }

    public String getPath() {
        return path;
    }
}