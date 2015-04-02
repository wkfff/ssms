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
import com.lanstar.plugin.template.StringTemplateBean;
import com.lanstar.plugin.template.TemplateBean;
import com.lanstar.plugin.template.TemplateException;

/**
 * Freemarker模板插件
 */
public class FreemarkerPlugin extends AppPlugin implements ITemplatePlugin {

    private final WebFreemarkerProcessor webFreemarkerProcessor;
    private final StringFreemarkerProcessor stringFreemarkerProcessor;

    public FreemarkerPlugin() {
        webFreemarkerProcessor = new WebFreemarkerProcessor();
        stringFreemarkerProcessor = new StringFreemarkerProcessor();
    }

    /**
     * 根据给定的模板bean对象呈现模板内容。
     *
     * @param templateBean 模板bean对象
     */
    @Override
    public void render( TemplateBean templateBean ) throws TemplateException {
        webFreemarkerProcessor.process( templateBean );
    }

    /**
     * 根据给定的模板bean对象计算出对应的结果。
     *
     * @param templateBean 模板bean对象
     *
     * @return 计算的结果。
     */
    @Override
    public String evaluate( StringTemplateBean templateBean ) throws TemplateException {
        stringFreemarkerProcessor.process( templateBean );
        return templateBean.getStringWriter().toString();
    }
}

