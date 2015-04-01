/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FreemarkerProcessor.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template.freemarker;

import com.lanstar.common.helper.Asserts;
import com.lanstar.common.log.LogHelper;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;

abstract class AbstractFreemarkerProcessor {
    protected final Configuration cfg;
    protected TemplateHashModel model;

    public AbstractFreemarkerProcessor() {
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.22) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        cfg = new Configuration(Configuration.VERSION_2_3_22);

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        TemplateLoader loader = getTemplateLoader();
        Asserts.notNull(loader, "TempateLoader不能为空!");
        cfg.setTemplateLoader(loader);

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    protected abstract TemplateLoader getTemplateLoader();

    public void process(String name,Writer out){
        try {
            Template template = cfg.getTemplate(name);
            template.process(model, out);
        } catch (IOException e) {
            LogHelper.error(getClass(), e, "获取模板文件[%s]时出现了异常...", name);
        } catch (TemplateException e) {
            LogHelper.error(getClass(), e, "解析模板文件[%s]时出现了异常...", name);
        }
    }
}
