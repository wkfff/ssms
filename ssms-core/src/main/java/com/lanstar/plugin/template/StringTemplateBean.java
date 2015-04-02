/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StringTemplateBean.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import java.io.StringWriter;

public class StringTemplateBean extends TemplateBean {

    /**
     * 根据模板路径，模型对象构建一个新实例。
     *
     * @param templatePath 模板路径
     * @param model        模型对象
     */
    public StringTemplateBean( String templatePath, Object model ) {
        super( templatePath, model, new StringWriter() );
    }

    public StringWriter getStringWriter(){
        return (StringWriter) super.getOut();
    }
}
