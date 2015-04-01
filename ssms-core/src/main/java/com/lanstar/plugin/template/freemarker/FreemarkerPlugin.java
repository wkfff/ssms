/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FreemarkerPlugin.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template.freemarker;

import com.lanstar.plugin.AppPlugin;
import com.lanstar.plugin.template.ITemplatePlugin;

import java.io.StringWriter;
import java.io.Writer;

public class FreemarkerPlugin extends AppPlugin implements ITemplatePlugin {

    private final WebFreemarkerProcessor webFreemarkerProcessor;
    private final StringFreemarkerProcessor stringFreemarkerProcessor;

    public FreemarkerPlugin() {
        webFreemarkerProcessor = new WebFreemarkerProcessor();
        stringFreemarkerProcessor = new StringFreemarkerProcessor();
    }

    @Override
    public void render(String templatePath, Writer out) {
        webFreemarkerProcessor.process(templatePath, out);
    }

    @Override
    public String evaluate(String source) {
        StringWriter writer = new StringWriter();
        stringFreemarkerProcessor.process(source, writer);
        return writer.toString();
    }
}

