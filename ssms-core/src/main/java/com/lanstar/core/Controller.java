/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Controller.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.core.render.ContentType;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderFactory;
import com.lanstar.core.token.TokenManager;
import com.lanstar.core.upload.MultipartRequest;
import com.lanstar.core.upload.UploadFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Controller {
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String urlPara;
    private String[] urlParaArray;

    private static final String[] NULL_URL_PARA_ARRAY = new String[0];
    private static final String URL_PARA_SEPARATOR = Config.getConstants().getUrlParaSeparator();

    public void init( HttpServletRequest request, HttpServletResponse response, String urlPara ) {
        this.request = request;
        this.response = response;
        this.urlPara = urlPara;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public HttpSession getSession( boolean create ) {
        return request.getSession( create );
    }

    // request para--------------
    public String getPara( String name ) {
        return request.getParameter( name );
    }

    public String getPara( String name, String defaultValue ) {
        String result = request.getParameter( name );
        return result != null && !"".equals( result ) ? result : defaultValue;
    }

    public Map<String, String[]> getParaMap() {
        return request.getParameterMap();
    }

    public Enumeration<String> getParaNames() {
        return request.getParameterNames();
    }

    public String[] getParaValues( String name ) {
        return request.getParameterValues( name );
    }

    public Integer[] getParaValuesToInt( String name ) {
        String[] values = request.getParameterValues( name );
        if ( values == null )
            return null;
        Integer[] result = new Integer[values.length];
        for ( int i = 0; i < result.length; i++ )
            result[i] = Integer.parseInt( values[i] );
        return result;
    }

    public Integer getParaToInt( String name ) {
        return toInt( request.getParameter( name ), null );
    }

    public Integer getParaToInt( String name, Integer defaultValue ) {
        return toInt( request.getParameter( name ), defaultValue );
    }

    public Long getParaToLong( String name ) {
        return toLong( request.getParameter( name ), null );
    }

    public Long getParaToLong( String name, Long defaultValue ) {
        return toLong( request.getParameter( name ), defaultValue );
    }

    public Boolean getParaToBoolean( String name ) {
        return toBoolean( request.getParameter( name ), null );
    }

    public Boolean getParaToBoolean( String name, Boolean defaultValue ) {
        return toBoolean( request.getParameter( name ), defaultValue );
    }

    public Date getParaToDate( String name ) {
        return toDate( request.getParameter( name ), null );
    }

    public Date getParaToDate( String name, Date defaultValue ) {
        return toDate( request.getParameter( name ), defaultValue );
    }

    public boolean isParaBlank( String paraName ) {
        String value = request.getParameter( paraName );
        return value == null || value.trim().length() == 0;
    }

    public boolean isParaExists( String paraName ) {
        return request.getParameterMap().containsKey( paraName );
    }
    // --------------request para

    // url para--------------
    public String getPara() {
        if ( "".equals( urlPara ) )    // urlPara maybe is "" see ActionMapping.getAction(String)
            urlPara = null;
        return urlPara;
    }

    public String getPara( int index ) {
        if ( index < 0 )
            return getPara();

        if ( urlParaArray == null ) {
            if ( urlPara == null || "".equals( urlPara ) )    // urlPara maybe is "" see ActionMapping.getAction(String)
                urlParaArray = NULL_URL_PARA_ARRAY;
            else
                urlParaArray = urlPara.split( URL_PARA_SEPARATOR );

            for ( int i = 0; i < urlParaArray.length; i++ )
                if ( "".equals( urlParaArray[i] ) )
                    urlParaArray[i] = null;
        }
        return urlParaArray.length > index ? urlParaArray[index] : null;
    }

    public String getPara( int index, String defaultValue ) {
        String result = getPara( index );
        return result != null && !"".equals( result ) ? result : defaultValue;
    }

    public Integer getParaToInt() {
        return toInt( getPara(), null );
    }

    public Integer getParaToInt( int index, Integer defaultValue ) {
        return toInt( getPara( index ), defaultValue );
    }

    public Long getParaToLong() {
        return toLong( getPara(), null );
    }

    public Long getParaToLong( int index ) {
        return toLong( getPara( index ), null );
    }

    public Long getParaToLong( int index, Long defaultValue ) {
        return toLong( getPara( index ), defaultValue );
    }

    public Boolean getParaToBoolean() {
        return toBoolean( getPara(), null );
    }

    public Boolean getParaToBoolean( int index ) {
        return toBoolean( getPara( index ), null );
    }

    public Boolean getParaToBoolean( int index, Boolean defaultValue ) {
        return toBoolean( getPara( index ), defaultValue );
    }

    public Date getParaToDate() {
        return toDate( getPara(), null );
    }

    public Date getParaToDate( int index ) {
        return toDate( getPara( index ), null );
    }

    public Date getParaToDate( int index, Date defaultValue ) {
        return toDate( getPara( index ), defaultValue );
    }

    public boolean isParaBlank( int index ) {
        String value = getPara( index );
        return value == null || value.trim().length() == 0;
    }

    public boolean isParaExists( int index ) {
        return getPara( index ) != null;
    }
    // --------------url para

    // attr--------------
    public Enumeration<String> getAttrNames() {
        return request.getAttributeNames();
    }

    public <T> T getAttr( String name ) {
        return (T) request.getAttribute( name );
    }

    public String getAttrForStr( String name ) {
        return (String) request.getAttribute( name );
    }

    public Integer getAttrForInt( String name ) {
        return (Integer) request.getAttribute( name );
    }

    public Controller setAttr( String name, Object value ) {
        request.setAttribute( name, value );
        return this;
    }

    public Controller removeAttr( String name ) {
        request.removeAttribute( name );
        return this;
    }

    public Controller setAttrs( Map<String, Object> attrMap ) {
        for ( Map.Entry<String, Object> entry : attrMap.entrySet() )
            request.setAttribute( entry.getKey(), entry.getValue() );
        return this;
    }
    // --------------attr

    // session--------------
    public <T> T getSessionAttr( String key ) {
        HttpSession session = request.getSession( false );
        return session != null ? (T) session.getAttribute( key ) : null;
    }

    public Controller setSessionAttr( String key, Object value ) {
        request.getSession().setAttribute( key, value );
        return this;
    }

    public Controller removeSessionAttr( String key ) {
        HttpSession session = request.getSession( false );
        if ( session != null )
            session.removeAttribute( key );
        return this;
    }
    // --------------session

    // cookie--------------

    public String getCookie( String name, String defaultValue ) {
        Cookie cookie = getCookieObject( name );
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    public String getCookie( String name ) {
        return getCookie( name, null );
    }

    public Integer getCookieToInt( String name ) {
        String result = getCookie( name );
        return result != null ? Integer.parseInt( result ) : null;
    }

    public Integer getCookieToInt( String name, Integer defaultValue ) {
        String result = getCookie( name );
        return result != null ? Integer.parseInt( result ) : defaultValue;
    }

    public Long getCookieToLong( String name ) {
        String result = getCookie( name );
        return result != null ? Long.parseLong( result ) : null;
    }

    public Long getCookieToLong( String name, Long defaultValue ) {
        String result = getCookie( name );
        return result != null ? Long.parseLong( result ) : defaultValue;
    }

    public Cookie getCookieObject( String name ) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null )
            for ( Cookie cookie : cookies )
                if ( cookie.getName().equals( name ) )
                    return cookie;
        return null;
    }

    public Cookie[] getCookieObjects() {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    public Controller setCookie( Cookie cookie ) {
        response.addCookie( cookie );
        return this;
    }

    public Controller setCookie( String name, String value, int maxAgeInSeconds, String path ) {
        setCookie( name, value, maxAgeInSeconds, path, null );
        return this;
    }

    public Controller setCookie( String name, String value, int maxAgeInSeconds, String path, String domain ) {
        Cookie cookie = new Cookie( name, value );
        if ( domain != null )
            cookie.setDomain( domain );
        cookie.setMaxAge( maxAgeInSeconds );
        cookie.setPath( path );
        response.addCookie( cookie );
        return this;
    }

    public Controller setCookie( String name, String value, int maxAgeInSeconds ) {
        setCookie( name, value, maxAgeInSeconds, "/", null );
        return this;
    }

    public Controller removeCookie( String name ) {
        setCookie( name, null, 0, "/", null );
        return this;
    }

    public Controller removeCookie( String name, String path ) {
        setCookie( name, null, 0, path, null );
        return this;
    }

    public Controller removeCookie( String name, String path, String domain ) {
        setCookie( name, null, 0, path, domain );
        return this;
    }
    // --------------cookie

    private Integer toInt( String value, Integer defaultValue ) {
        if ( value == null || "".equals( value.trim() ) )
            return defaultValue;
        if ( value.startsWith( "N" ) || value.startsWith( "n" ) )
            return -Integer.parseInt( value.substring( 1 ) );
        return Integer.parseInt( value );
    }

    private Long toLong( String value, Long defaultValue ) {
        if ( value == null || "".equals( value.trim() ) )
            return defaultValue;
        if ( value.startsWith( "N" ) || value.startsWith( "n" ) )
            return -Long.parseLong( value.substring( 1 ) );
        return Long.parseLong( value );
    }

    private Boolean toBoolean( String value, Boolean defaultValue ) {
        if ( value == null || "".equals( value.trim() ) )
            return defaultValue;
        value = value.trim().toLowerCase();
        if ( "1".equals( value ) || "true".equals( value ) )
            return Boolean.TRUE;
        else if ( "0".equals( value ) || "false".equals( value ) )
            return Boolean.FALSE;
        throw new RuntimeException( "Can not parse the parameter \"" + value + "\" to boolean value." );
    }

    private Date toDate( String value, Date defaultValue ) {
        if ( value == null || "".equals( value.trim() ) )
            return defaultValue;
        try {
            return new java.text.SimpleDateFormat( "yyyy-MM-dd" ).parse( value );
        } catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
    }

    // File--------
    public List<UploadFile> getFiles( String saveDirectory, Integer maxPostSize, String encoding ) {
        if ( request instanceof MultipartRequest == false )
            request = new MultipartRequest( request, saveDirectory, maxPostSize, encoding );
        return ((MultipartRequest) request).getFiles();
    }

    public UploadFile getFile( String parameterName, String saveDirectory, Integer maxPostSize, String encoding ) {
        getFiles( saveDirectory, maxPostSize, encoding );
        return getFile( parameterName );
    }

    public List<UploadFile> getFiles( String saveDirectory, int maxPostSize ) {
        if ( request instanceof MultipartRequest == false )
            request = new MultipartRequest( request, saveDirectory, maxPostSize );
        return ((MultipartRequest) request).getFiles();
    }

    public UploadFile getFile( String parameterName, String saveDirectory, int maxPostSize ) {
        getFiles( saveDirectory, maxPostSize );
        return getFile( parameterName );
    }

    public List<UploadFile> getFiles( String saveDirectory ) {
        if ( request instanceof MultipartRequest == false )
            request = new MultipartRequest( request, saveDirectory );
        return ((MultipartRequest) request).getFiles();
    }

    public UploadFile getFile( String parameterName, String saveDirectory ) {
        getFiles( saveDirectory );
        return getFile( parameterName );
    }

    public List<UploadFile> getFiles() {
        if ( request instanceof MultipartRequest == false )
            request = new MultipartRequest( request );
        return ((MultipartRequest) request).getFiles();
    }

    public UploadFile getFile() {
        List<UploadFile> uploadFiles = getFiles();
        return uploadFiles.size() > 0 ? uploadFiles.get( 0 ) : null;
    }

    public UploadFile getFile( String parameterName ) {
        List<UploadFile> uploadFiles = getFiles();
        for ( UploadFile uploadFile : uploadFiles ) {
            if ( uploadFile.getParameterName().equals( parameterName ) ) {
                return uploadFile;
            }
        }
        return null;
    }

    // token-------------------

    /**
     * Create a token.
     *
     * @param tokenName        the token name used in view
     * @param secondsOfTimeOut the seconds of time out, secondsOfTimeOut >= Const.MIN_SECONDS_OF_TOKEN_TIME_OUT
     */
    public void createToken( String tokenName, int secondsOfTimeOut ) {
        TokenManager.createToken( this, tokenName, secondsOfTimeOut );
    }

    /**
     * Create a token with default token name and with default seconds of time out.
     */
    public void createToken() {
        createToken( Const.DEFAULT_TOKEN_NAME, Const.DEFAULT_SECONDS_OF_TOKEN_TIME_OUT );
    }

    /**
     * Create a token with default seconds of time out.
     *
     * @param tokenName the token name used in view
     */
    public void createToken( String tokenName ) {
        createToken( tokenName, Const.DEFAULT_SECONDS_OF_TOKEN_TIME_OUT );
    }

    /**
     * Check token to prevent resubmit.
     *
     * @param tokenName the token name used in view's form
     *
     * @return true if token is correct
     */
    public boolean validateToken( String tokenName ) {
        return TokenManager.validateToken( this, tokenName );
    }

    /**
     * Check token to prevent resubmit  with default token key ---> "JFINAL_TOKEN_KEY"
     *
     * @return true if token is correct
     */
    public boolean validateToken() {
        return validateToken( Const.DEFAULT_TOKEN_NAME );
    }
    // -------------------token

    // model----------------

    /**
     * Get model from http request.
     */
    public <T> T getModel( Class<T> modelClass ) {
        return (T) ModelInjector.inject( modelClass, request, false );
    }

    /**
     * Get model from http request.
     */
    public <T> T getModel( Class<T> modelClass, String modelName ) {
        return (T) ModelInjector.inject( modelClass, modelName, request, false );
    }
    // ----------------model

    // render--------------------------
    private static final RenderFactory renderFactory = RenderFactory.me();
    private Render render;

    public Render getRender() {
        return render;
    }

    public void render( Render render ) {
        this.render = render;
    }

    public void render( String view ) {
        render = renderFactory.getRender( view );
    }

    public void renderFreeMarker( String view ) {
        render = renderFactory.getFreeMarkerRender( view );
    }

    public void renderJson( String key, Object value ) {
        render = renderFactory.getJsonRender( key, value );
    }

    public void renderJson() {
        render = renderFactory.getJsonRender();
    }

    public void renderJson( String[] attrs ) {
        render = renderFactory.getJsonRender( attrs );
    }

    public void renderJson( String jsonText ) {
        render = renderFactory.getJsonRender( jsonText );
    }

    public void renderJson( Object object ) {
        render = renderFactory.getJsonRender( object );
    }

    public void renderText( String text ) {
        render = renderFactory.getTextRender( text );
    }

    public void renderText( String text, String contentType ) {
        render = renderFactory.getTextRender( text, contentType );
    }

    public void renderText( String text, ContentType contentType ) {
        render = renderFactory.getTextRender( text, contentType );
    }

    public void forwardAction( String actionUrl ) {
        render = new ActionRender( actionUrl );
    }

    public void renderFile( String fileName ) {
        render = renderFactory.getFileRender( fileName );
    }

    public void renderFile( File file ) {
        render = renderFactory.getFileRender( file );
    }

    public void redirect( String url ) {
        render = renderFactory.getRedirectRender( url );
    }

    public void redirect( String url, boolean withQueryString ) {
        render = renderFactory.getRedirectRender( url, withQueryString );
    }

    public void render( String view, int status ) {
        render = renderFactory.getRender( view );
        response.setStatus( status );
    }

    public void redirect301( String url ) {
        render = renderFactory.getRedirect301Render( url );
    }

    public void redirect301( String url, boolean withQueryString ) {
        render = renderFactory.getRedirect301Render( url, withQueryString );
    }

    public void renderError( int errorCode, String view ) {
        throw new ActionException( errorCode, renderFactory.getErrorRender( errorCode, view ) );
    }

    public void renderError( int errorCode, Render render ) {
        throw new ActionException( errorCode, render );
    }

    public void renderError( int errorCode ) {
        throw new ActionException( errorCode, renderFactory.getErrorRender( errorCode ) );
    }

    public void renderNull() {
        render = renderFactory.getNullRender();
    }

    public void renderJavascript( String javascriptText ) {
        render = renderFactory.getJavascriptRender( javascriptText );
    }

    public void renderHtml( String htmlText ) {
        render = renderFactory.getHtmlRender( htmlText );
    }

    public void renderXml( String view ) {
        render = renderFactory.getXmlRender( view );
    }
    // --------------------------render
}
