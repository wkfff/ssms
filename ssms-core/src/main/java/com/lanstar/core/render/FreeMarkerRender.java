/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FreeMarkerRender.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.common.kit.RequestKit;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;

public class FreeMarkerRender extends Render {

    private static final String encoding = getEncoding();
    private static final String contentType = "text/html; charset=" + encoding;
    private static final Configuration config = new Configuration();

    public FreeMarkerRender( String view ) {
        this.view = view;
    }

    /**
     * freemarker can not load freemarker.properies automatically
     */
    public static Configuration getConfiguration() {
        return config;
    }

    /**
     * Set freemarker's property.
     * The value of template_update_delay is 5 seconds.
     * Example: FreeMarkerRender.setProperty("template_update_delay", "1600");
     */
    public static void setProperty( String propertyName, String propertyValue ) {
        try {
            FreeMarkerRender.getConfiguration().setSetting( propertyName, propertyValue );
        } catch ( TemplateException e ) {
            throw new RuntimeException( e );
        }
    }

    public static void setProperties( Properties properties ) {
        try {
            FreeMarkerRender.getConfiguration().setSettings( properties );
        } catch ( TemplateException e ) {
            throw new RuntimeException( e );
        }
    }

    static void init( ServletContext servletContext, Locale locale, int template_update_delay ) {
        // Initialize the FreeMarker configuration;
        // - Create a configuration instance
        // config = new Configuration();
        // - Templates are stoted in the WEB-INF/templates directory of the Web app.
        config.setServletContextForTemplateLoading( servletContext, "/" );    // "WEB-INF/templates"
        // - Set update dealy to 0 for now, to ease debugging and testing.
        //   Higher value should be used in production environment.

        if ( getDevMode() ) {
            config.setTemplateUpdateDelay( 0 );
        } else {
            config.setTemplateUpdateDelay( template_update_delay );
        }

        // - Set an error handler that prints errors so they are readable with
        //   a HTML browser.
        // config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        config.setTemplateExceptionHandler( TemplateExceptionHandler.RETHROW_HANDLER );

        // - Use beans wrapper (recommmended for most applications)
        config.setObjectWrapper( new BeansWrapper( Configuration.getVersion() ) );
        // - Set the default charset of the template files
        config.setDefaultEncoding( encoding );        // config.setDefaultEncoding("ISO-8859-1");
        // - Set the charset of the output. This is actually just a hint, that
        //   templates may require for URL encoding and for generating META element
        //   that uses http-equiv="Content-type".
        config.setOutputEncoding( encoding );            // config.setOutputEncoding("UTF-8");
        // - Set the default locale
        config.setLocale( locale /* Locale.CHINA */ );        // config.setLocale(Locale.US);
        config.setLocalizedLookup( false );

        // 去掉int型输出时的逗号, 例如: 123,456
        // config.setNumberFormat("#");		// config.setNumberFormat("0"); 也可以
        config.setNumberFormat( "#0.#####" );
        config.setDateFormat( "yyyy-MM-dd" );
        config.setTimeFormat( "HH:mm:ss" );
        config.setDateTimeFormat( "yyyy-MM-dd HH:mm:ss" );
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void render() {
        response.setContentType( contentType );

        PrintWriter writer = null;
        try {
            Template template = config.getTemplate( view );
            writer = response.getWriter();
            template.process( new FreemarkerModel( request ), writer );        // Merge the data-model and the template
        } catch ( Exception e ) {
            throw new RenderException( e );
        } finally {
            if ( writer != null )
                writer.close();
        }
    }

    /**
     * Freemarker模型
     */
    private class FreemarkerModel implements TemplateHashModel {
        private final BeansWrapper wrapper = new BeansWrapper( Configuration.getVersion() );
        private final HttpServletRequest request;

        public FreemarkerModel( HttpServletRequest request ) {
            this.request = request;

            // 避免使用?keys遍历map中时会获取到混合了自定义方法的数据      by 张铮彬(cnzgray@qq.com)
            if ( !wrapper.isSimpleMapWrapper() ) wrapper.setSimpleMapWrapper( true );
        }

        @Override
        public TemplateModel get( String key ) throws TemplateModelException {
            return wrapper.wrap( RequestKit.getValue( request, key ) );
        }

        @Override
        public boolean isEmpty() throws TemplateModelException {
            return false;
        }
    }
}