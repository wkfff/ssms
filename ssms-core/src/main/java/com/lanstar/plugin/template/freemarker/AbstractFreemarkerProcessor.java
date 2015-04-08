/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FreemarkerProcessor.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template.freemarker;

import com.lanstar.common.helper.Asserts;
import com.lanstar.plugin.template.TemplateBean;
import com.lanstar.plugin.template.TemplateException;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

/**
 * Freemarker处理器抽象类
 */
abstract class AbstractFreemarkerProcessor {
    protected final Configuration cfg;

    public AbstractFreemarkerProcessor() {
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.22) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        cfg = new Configuration( FreemarkerPlugin.VERSION );

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        TemplateLoader loader = getTemplateLoader();
        Asserts.notNull( loader, "TempateLoader不能为空!" );
        cfg.setTemplateLoader( loader );

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding( "UTF-8" );

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler( TemplateExceptionHandler.HTML_DEBUG_HANDLER );
    }

    /**
     * 获取模板加载器
     */
    protected abstract TemplateLoader getTemplateLoader();

    /**
     * 根据给定的模板bean对象处理模板。
     *
     * @param templateBean 模板bean对象
     */
    public void process( TemplateBean templateBean ) throws TemplateException {
        try {
            Template template = cfg.getTemplate( templateBean.getTemplate() );
            template.process( templateBean.getModel(), templateBean.getOut() );
        } catch ( IOException e ) {
            throw new TemplateException( String.format( "获取模板文件[%s]时出现了异常...", templateBean.getTemplate() ), e );
        } catch ( freemarker.template.TemplateException e ) {
            throw new TemplateException( String.format( "解析模板文件[%s]时出现了异常...", templateBean.getTemplate() ), e );
        }
    }
}
