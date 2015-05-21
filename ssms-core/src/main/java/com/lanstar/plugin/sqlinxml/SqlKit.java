/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlKit.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.sqlinxml;

import com.google.common.collect.Maps;
import com.lanstar.common.kit.DirectorySteper;
import com.lanstar.common.kit.JaxbKit;
import com.lanstar.common.kit.PathKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.common.log.Logger;

import java.io.File;
import java.util.Map;

public class SqlKit {

    protected static final Logger LOG = Logger.getLogger( SqlKit.class );

    private static Map<String, String> sqlMap;
    private static File rootClassPath;

    public static String sql( String groupNameAndsqlId ) {
        if ( sqlMap == null ) {
            throw new NullPointerException( "SqlInXmlPlugin not start" );
        }
        return sqlMap.get( groupNameAndsqlId );
    }

    static void clearSqlMap() {
        sqlMap.clear();
    }

    static void init() {
        init( null );
    }

    private final static DirectorySteper steper = new DirectorySteper( new DirectorySteper.IFilePicker() {
        @Override
        public void pick( File file ) {
            if ( file.isFile() && file.getName().endsWith( "sql.xml" ) ) {
                SqlGroup group = JaxbKit.unmarshal( file, SqlGroup.class );
                String name = group.name;
                if ( name == null || name.trim().equals( "" ) ) {
                    if ( group.sqlGroups.size() == 0 ) name = file.getName();
                }
                String parent = file.getParentFile()
                                    .getAbsolutePath()
                                    .substring( rootClassPath.getAbsolutePath().length() );
                if (parent.startsWith( "/" ) || parent.startsWith( "\\" )) parent = parent.substring( 1 );
                for ( SqlItem sqlItem : group.sqlItems ) {
                    String key = name + "." + sqlItem.id;
                    if ( StrKit.isEmpty( parent ) == false ) key = parent + "." + key;
                    sqlMap.put( key, sqlItem.value );
                }

                for ( SqlGroup sqlGroup : group.sqlGroups ) {
                    if ( StrKit.isEmpty( sqlGroup.name ) )
                        throw new RuntimeException( "sqlGroup attribte 'name' can not be empty" );
                    for ( SqlItem sqlItem : sqlGroup.sqlItems ) {
                        String key = name + "." + sqlGroup.name + "." + sqlItem.id;
                        if ( StrKit.isEmpty( parent ) == false ) key = parent + "." + key;
                        sqlMap.put( key, sqlItem.value );
                    }
                }
            }
        }
    } );

    static void init( String path ) {
        sqlMap = Maps.newHashMap();
        String rootClassPath = PathKit.getRootClassPath();
        if ( StrKit.notEmpty( path ) ) rootClassPath = rootClassPath + path;
        SqlKit.rootClassPath = new File( rootClassPath );
        steper.step( SqlKit.rootClassPath );
        LOG.debug( "sqlMap" + sqlMap );
    }
}