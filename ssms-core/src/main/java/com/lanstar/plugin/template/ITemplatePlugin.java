/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ITemplatePlugin.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import com.lanstar.plugin.IAppPlugin;

public interface ITemplatePlugin extends IAppPlugin {

    /**
     * 根据给定的模板bean对象呈现模板内容。
     *
     * @param templateBean 模板bean对象
     */
    void render( TemplateBean templateBean ) throws TemplateException;

    /**
     * 根据给定的模板bean对象计算出对应的结果。
     *
     * @param templateBean 模板bean对象
     *
     * @return 计算的结果。
     */
    String evaluate( StringTemplateBean templateBean ) throws TemplateException;
}
