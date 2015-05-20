/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderFactory.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.config.Constants;
import com.lanstar.core.Const;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Locale;

public class RenderFactory {
    private static RenderFactory instance = new RenderFactory();

    private static ServletContext servletContext;
    private static IErrorRenderFactory errorRenderFactory;
    private Constants constants;
    private FreeMarkerRenderFactory mainRenderFactory;

    public static RenderFactory me() {
        return instance;
    }

    public void init( Constants constants, ServletContext servletContext ) {
        this.constants = constants;
        RenderFactory.servletContext = servletContext;

        // init Render
        Render.init( constants.getEncoding(), constants.getDevMode() );
        initFreeMarkerRender( servletContext );

        // create mainRenderFactory
        if ( mainRenderFactory == null ) {
            mainRenderFactory = new FreeMarkerRenderFactory();
        }

        // create errorRenderFactory
        if ( errorRenderFactory == null ) {
            errorRenderFactory = new ErrorRenderFactory();
        }
    }

    public Render getDefaultRender( String view ) {
        ViewType viewType = constants.getViewType();
        if ( viewType == ViewType.FREE_MARKER ) {
            return new FreeMarkerRender( view + constants.getFreeMarkerViewExtension() );
        } else {
            return mainRenderFactory.getRender( view + mainRenderFactory.getViewExtension() );
        }
    }

    public Render getRender( String view ) {
        return mainRenderFactory.getRender( view );
    }

    public Render getFreeMarkerRender( String view ) {
        return new FreeMarkerRender( view );
    }

    public Render getJsonRender() {
        return new JsonRender();
    }

    public Render getJsonRender( String key, Object value ) {
        return new JsonRender( key, value );
    }

    public Render getJsonRender( String[] attrs ) {
        return new JsonRender( attrs );
    }

    public Render getJsonRender( String jsonText ) {
        return new JsonRender( jsonText );
    }

    public Render getJsonRender( Object object ) {
        return new JsonRender( object );
    }

    public Render getTextRender( String text ) {
        return new TextRender( text );
    }

    public Render getTextRender( String text, String contentType ) {
        return new TextRender( text, contentType );
    }

    public Render getTextRender( String text, ContentType contentType ) {
        return new TextRender( text, contentType );
    }

    public Render getFileRender( String fileName ) {
        return new FileRender( fileName );
    }

    public Render getFileRender( File file ) {
        return new FileRender( file );
    }

    public Render getRedirectRender( String url ) {
        return new RedirectRender( url );
    }

    public Render getRedirectRender( String url, boolean withQueryString ) {
        return new RedirectRender( url, withQueryString );
    }

    public Render getRedirect301Render( String url ) {
        return new Redirect301Render( url );
    }

    public Render getRedirect301Render( String url, boolean withQueryString ) {
        return new Redirect301Render( url, withQueryString );
    }

    public Render getNullRender() {
        return new NullRender();
    }

    public Render getJavascriptRender( String jsText ) {
        return new JavascriptRender( jsText );
    }

    public Render getHtmlRender( String htmlText ) {
        return new HtmlRender( htmlText );
    }

    public Render getXmlRender( String view ) {
        return new XmlRender( view );
    }

    public Render getErrorRender( int errorCode, String view ) {
        return errorRenderFactory.getRender( errorCode, view );
    }

    public Render getErrorRender( int errorCode ) {
        return errorRenderFactory.getRender( errorCode, constants.getErrorView( errorCode ) );
    }

    private void initFreeMarkerRender( ServletContext servletContext ) {
        try {
            Class.forName( "freemarker.template.Template" );    // detect freemarker.jar
            FreeMarkerRender.init( servletContext, Locale.getDefault(), constants.getFreeMarkerTemplateUpdateDelay() );
        } catch ( ClassNotFoundException e ) {
            // System.out.println("freemarker can not be supported!");
        }
    }

    private static final class FreeMarkerRenderFactory implements IMainRenderFactory {
        public Render getRender( String view ) {
            return new FreeMarkerRender( view );
        }

        public String getViewExtension() {
            return Const.DEFAULT_FREEMARKER_VIEW_EXTENSION;
        }
    }

    private static final class ErrorRenderFactory implements IErrorRenderFactory {
        public Render getRender( int errorCode, String view ) {
            return new ErrorRender( errorCode, view );
        }
    }
}
