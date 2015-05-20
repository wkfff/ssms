/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Rapidware.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.plugin.activerecord.PathKit;
import com.lanstar.config.Constants;
import com.lanstar.config.RapidwareConfig;
import com.lanstar.core.render.RenderFactory;
import com.lanstar.core.token.ITokenCache;
import com.lanstar.core.token.TokenManager;
import com.lanstar.core.upload.OreillyCos;
import com.lanstar.plugin.IPlugin;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

public class Rapidware {
    private static final Rapidware me = new Rapidware();
    private ServletContext servletContext;
    private String contextPath;
    private Dispatcher dispatcher;
    private ActionMapping actionMapping;

    public static Rapidware me() {
        return me;
    }

    Dispatcher getDispatcher() {
        return dispatcher;
    }

    public boolean init( ServletContext servletContext, RapidwareConfig config ) {
        this.servletContext = servletContext;
        this.contextPath = servletContext.getContextPath();

        initPathUtil();

        Config.configRapidware( config );

        initActionMapping();
        initDispatcher();
        initRender();

        initOreillyCos();
        initTokenManager();

        return true;
    }

    private void initPathUtil() {
        String path = servletContext.getRealPath( "/" );
        PathKit.setWebRootPath( path );
    }

    private void initActionMapping() {
        actionMapping = new ActionMapping( Config.getRoutes(), Config.getInterceptors() );
        actionMapping.buildActionMapping();
    }

    private void initDispatcher() {
        dispatcher = new Dispatcher( Config.getHandlers().getHandlerList() );
        ActionHandler actionHandler = new ActionHandler( actionMapping, Config.getConstants() );
        dispatcher.add( actionHandler );
    }

    private void initRender() {
        RenderFactory renderFactory = RenderFactory.me();
        renderFactory.init( Config.getConstants(), servletContext );
    }

    private void initOreillyCos() {
        Constants ct = Config.getConstants();
        if ( OreillyCos.isMultipartSupported() ) {
            String uploadedFileSaveDirectory = ct.getUploadedFileSaveDirectory();
            if ( uploadedFileSaveDirectory == null || "".equals( uploadedFileSaveDirectory.trim() ) ) {
                uploadedFileSaveDirectory = PathKit.getWebRootPath() + File.separator + "upload" + File.separator;
                ct.setUploadedFileSaveDirectory( uploadedFileSaveDirectory );

				/*File file = new File(uploadedFileSaveDirectory);
                if (!file.exists())
					file.mkdirs();*/
            }
            OreillyCos.init( uploadedFileSaveDirectory, ct.getMaxPostSize(), ct.getEncoding() );
        }
    }

    private void initTokenManager() {
        ITokenCache tokenCache = Config.getConstants().getTokenCache();
        if (tokenCache != null)
            TokenManager.init( tokenCache );
    }

    public void stopPlugins() {
        List<IPlugin> plugins = Config.getPlugins().getPluginList();
        if ( plugins != null ) {
            for ( int i = plugins.size() - 1; i >= 0; i-- ) {        // stop plugins
                boolean success;
                try {
                    success = plugins.get( i ).stop();
                } catch ( Exception e ) {
                    success = false;
                    e.printStackTrace();
                }
                if ( !success ) {
                    System.err.println( "Plugin stop error: " + plugins.get( i ).getClass().getName() );
                }
            }
        }
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public String getContextPath() {
        return contextPath;
    }

    public Constants getConstants() {
        return Config.getConstants();
    }
}
