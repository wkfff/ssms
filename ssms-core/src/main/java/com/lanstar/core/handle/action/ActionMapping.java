/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionMapping.java
 * 创建时间：2015年4月2日 上午10:50:29
 * 创建用户：林峰
 */
package com.lanstar.core.handle.action;

import com.lanstar.common.helper.BeanHelper;
import com.lanstar.common.log.Logger;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.controller.Controller;
import com.lanstar.core.handle.HandlerContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Action集合
 */
public final class ActionMapping {
    private final static String CONTROLLERPACKAGENAME = "com.lanstar.controller";
    private final static String CONTROLLERSUFFIX = "Controller";
    private static final String SLASH = "/";
    private final static Map<String, Action> mapping = new HashMap<>();
    private static final Logger log = Logger.getLogger( ActionMapping.class );

    public static void init() {
        buildActionMapping( CONTROLLERPACKAGENAME );
    }

    /**
     * 获取Action
     */
    public static Action getAction( ActionMeta meta ) {
        String actionKey = getActionKey( meta );
        return mapping.get( actionKey );
    }

    /**
     * 从元数据中获取Action标识
     */
    private static String getActionKey( ActionMeta meta ) {
        String controllerKey = meta.getController();
        String methodName = meta.getAction();
        return SLASH + controllerKey + SLASH + methodName;
    }

    /**
     * 构造所有控制器中的Action集合
     */
    private static void buildActionMapping( String pkgName ) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            // 按文件的形式去查找  
            String strFile = pkgName.replaceAll( "\\.", "/" );
            Enumeration<URL> urls = loader.getResources( strFile );
            while ( urls.hasMoreElements() ) {
                URL url = urls.nextElement();
                if ( url != null ) {
                    String protocol = url.getProtocol();
                    String pkgPath = url.getPath();
                    if ( "file".equals( protocol ) ) {
                        // 直接类文件
                        findClassName( pkgName, pkgPath );
                    } else if ( "jar".equals( protocol ) ) {
                        // 在jar包中 
                        findClassName( pkgName, url );
                    }
                }
            }
        } catch ( IOException ignored ) {
        }
        log.debug( "共加载Action" + mapping.size() + "个" );
    }

    /**
     * 直接找类文件
     */
    private static void findClassName( String pkgName, String pkgPath ) {
        File[] files = new File( pkgPath ).listFiles();
        if ( files != null ) {
            for ( File f : files ) {
                String fileName = f.getName();
                if ( f.isFile() && fileName.endsWith( ".class" ) ) {

                    int endIndex = fileName.lastIndexOf( "." );
                    String clazz = fileName.substring( 0, endIndex );
                    String clazzName = pkgName + "." + clazz;

                    String controllerKey = clazz.replace( CONTROLLERSUFFIX, "" );
                    addAction( controllerKey, clazzName );
                }
            }
        }
    }

    /**
     * 从jar包里查找类
     *
     * @throws IOException
     */
    private static void findClassName( String pkgName, URL url ) throws IOException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        log.debug( "jarFile:%s", jarFile.getName() );
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while ( jarEntries.hasMoreElements() ) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class  
            String clazzName = jarEntryName.replace( "/", "." );
            int endIndex = clazzName.lastIndexOf( "." );
            String prefix = null;
            if ( endIndex > 0 ) {
                String prefix_name = clazzName.substring( 0, endIndex );
                endIndex = prefix_name.lastIndexOf( "." );
                if ( endIndex > 0 ) {
                    prefix = prefix_name.substring( 0, endIndex );
                }
            }
            if ( prefix != null && jarEntryName.endsWith( ".class" ) ) {
                if ( prefix.equals( pkgName ) ) {
                    String controllerKey = prefix.replace( CONTROLLERSUFFIX, "" );
                    addAction( controllerKey, clazzName );
                }
            }
        }
    }

    private static void addAction( String controllerKey, String clazzName ) {
        if ( clazzName != null ) {
            Class<? extends Controller> controllerClass = BeanHelper.getClass( clazzName );
            if ( controllerClass != null ) {
                Controller controller;
                try {
                    controller = controllerClass.newInstance();
                } catch ( Exception e ) {
                    log.warn( e, "控制器[%s]没有默认的构造函数", controllerClass.getName() );
                    return;
                }
                Method[] methods = controllerClass.getMethods();
                for ( Method method : methods ) {
                    String methodName = method.getName();
                    if ( method.getParameterTypes().length != 1 ) continue;
                    if ( !HandlerContext.class.isAssignableFrom( method.getParameterTypes()[0] ) ) continue;
                    if ( !ViewAndModel.class.isAssignableFrom( method.getReturnType() ) ) continue;

                    String actionKey = SLASH + controllerKey + SLASH + methodName;
                    Action action = new Action( controllerKey, actionKey, controller, method, methodName );
                    mapping.put( actionKey, action );
                }
            }
        }
    }
}
