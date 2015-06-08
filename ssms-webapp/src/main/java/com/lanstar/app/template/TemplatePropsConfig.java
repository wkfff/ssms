/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplatePropsConfig.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.app.template;

import com.lanstar.plugin.template.TemplateProp;
import com.lanstar.plugin.template.TemplateProps;

public class TemplatePropsConfig extends TemplateProps {
    public TemplatePropsConfig() {
        add( TemplateProp.with( "01", "制度文件", com.lanstar.model.system.TemplateFile01.class, com.lanstar.model.tenant.TemplateFile01.class ) );
        add( TemplateProp.with( "02", "通知文件", com.lanstar.model.system.TemplateFile02.class, com.lanstar.model.tenant.TemplateFile02.class ) );
        add( TemplateProp.with( "03", "执行文件", com.lanstar.model.system.TemplateFile03.class, com.lanstar.model.tenant.TemplateFile03.class ) );
        add( TemplateProp.with( "04", "培训执行文件", com.lanstar.model.system.TemplateFile04.class, com.lanstar.model.tenant.TemplateFile04.class ) );
        //add( TemplateProp.with( "05", "培训文件", com.lanstar.model.system.TemplateFile05.class, com.lanstar.model.tenant.TemplateFile05.class ) );
        add( TemplateProp.with( "06", "隐患汇总登记台帐", com.lanstar.model.system.TemplateFile06.class, com.lanstar.model.tenant.TemplateFile06.class ) );
    }
}
