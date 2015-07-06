/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Constants.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.config;

import com.lanstar.common.kit.PathKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Const;
import com.lanstar.core.render.ViewType;
import com.lanstar.core.token.ITokenCache;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class Constants {
    private Map<Integer, String> errorViewMapping = new HashMap<>();

    private boolean devMode = Const.DEFAULT_DEV_MODE;
    private String encoding = Const.DEFAULT_ENCODING;
    private String urlParaSeparator = Const.DEFAULT_URL_PARA_SEPARATOR;
    private ViewType viewType = Const.DEFAULT_VIEW_TYPE;
    private String freeMarkerViewExtension = Const.DEFAULT_FREEMARKER_VIEW_EXTENSION;
    private Integer maxPostSize = Const.DEFAULT_MAX_POST_SIZE;
    private int freeMarkerTemplateUpdateDelay = Const.DEFAULT_FREEMARKER_TEMPLATE_UPDATE_DELAY;
    private String fileRenderPath;
    private String uploadedFileSaveDirectory;
    private ITokenCache tokenCache;

    public boolean getDevMode() {
        return devMode;
    }

    public void setDevMode( boolean devMode ) {
        this.devMode = devMode;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding( String encoding ) {
        this.encoding = encoding;
    }

    /**
     * Set error 404 view.
     *
     * @param error404View the error 404 view
     */
    public void setError404View( String error404View ) {
        errorViewMapping.put( 404, error404View );
    }

    /**
     * Set error 500 view.
     *
     * @param error500View the error 500 view
     */
    public void setError500View( String error500View ) {
        errorViewMapping.put( 500, error500View );
    }

    /**
     * Set error 401 view.
     *
     * @param error401View the error 401 view
     */
    public void setError401View( String error401View ) {
        errorViewMapping.put( 401, error401View );
    }

    /**
     * Set error 403 view.
     *
     * @param error403View the error 403 view
     */
    public void setError403View( String error403View ) {
        errorViewMapping.put( 403, error403View );
    }

    public void setErrorView( int errorCode, String errorView ) {
        errorViewMapping.put( errorCode, errorView );
    }

    public String getErrorView( int errorCode ) {
        return errorViewMapping.get( errorCode );
    }

    public String getUrlParaSeparator() {
        return urlParaSeparator;
    }

    public void setUrlParaSeparator( String urlParaSeparator ) {
        if ( StrKit.isBlank( urlParaSeparator ) || urlParaSeparator.contains( "/" ) )
            throw new IllegalArgumentException( "urlParaSepartor can not be blank and can not contains \"/\"" );
        this.urlParaSeparator = urlParaSeparator;
    }

    public int getFreeMarkerTemplateUpdateDelay() {
        return freeMarkerTemplateUpdateDelay;
    }

    public void setFreeMarkerTemplateUpdateDelay( int delayInSeconds ) {
        if ( delayInSeconds < 0 )
            throw new IllegalArgumentException( "template_update_delay must more than -1." );
        this.freeMarkerTemplateUpdateDelay = delayInSeconds;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType( ViewType viewType ) {
        if ( viewType == null )
            throw new IllegalArgumentException( "viewType can not be null" );

        if ( viewType != ViewType.OTHER )    // setMainRenderFactory will set ViewType.OTHER
            this.viewType = viewType;
    }

    public String getFreeMarkerViewExtension() {
        return freeMarkerViewExtension;
    }

    public void setFreeMarkerViewExtension( String freeMarkerViewExtension ) {
        this.freeMarkerViewExtension = freeMarkerViewExtension.startsWith( "." ) ?
                freeMarkerViewExtension :
                "." + freeMarkerViewExtension;
    }

    public String getUploadedFileSaveDirectory() {
        return uploadedFileSaveDirectory;
    }

    public void setUploadedFileSaveDirectory( String uploadedFileSaveDirectory ) {
        if ( StrKit.isBlank( uploadedFileSaveDirectory ) )
            throw new IllegalArgumentException( "uploadedFileSaveDirectory can not be blank" );

        if ( uploadedFileSaveDirectory.endsWith( "/" ) || uploadedFileSaveDirectory.endsWith( "\\" ) )
            this.uploadedFileSaveDirectory = uploadedFileSaveDirectory;
        else
            this.uploadedFileSaveDirectory = uploadedFileSaveDirectory + File.separator;
    }

    public int getMaxPostSize() {
        return maxPostSize;
    }

    public void setMaxPostSize( Integer maxPostSize ) {
        if ( maxPostSize != null && maxPostSize > 0 ) {
            this.maxPostSize = maxPostSize;
        }
    }

    public ITokenCache getTokenCache() {
        return tokenCache;
    }

    public void setTokenCache( ITokenCache tokenCache ) {
        this.tokenCache = tokenCache;
    }

    public void setBaseViewPath( String baseViewPath ) {
        Routes.setBaseViewPath( baseViewPath );
    }

    public String getFileRenderPath() {
        return fileRenderPath;
    }

    /**
     * Set the path of file render of controller.
     * <p>
     * The path is start with root path of this web application.
     * The default value is "/download" if you do not config this parameter.
     */
    public void setFileRenderPath( String fileRenderPath ) {
        if ( StrKit.isBlank( fileRenderPath ) )
            throw new IllegalArgumentException( "The argument fileRenderPath can not be blank" );

        if ( !fileRenderPath.startsWith( "/" ) && !fileRenderPath.startsWith( File.separator ) )
            fileRenderPath = File.separator + fileRenderPath;
        this.fileRenderPath = PathKit.getWebRootPath() + fileRenderPath;
    }
}
