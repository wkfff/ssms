/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Template.java
 * 创建时间：2015-06-30
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import com.lanstar.model.TenantModel;
import com.lanstar.model.system.Profession;

public class Template extends TenantModel<Template> {
    public static final Template dao = new Template();

    public void setProfession( Profession profession ) {
        set( "P_PROFESSION", profession.getId() );
    }

    public void setTemplate( com.lanstar.model.system.Template template ) {
        set( "R_TEMPLATE", template.getId() );
        set( "N_VERSION", template.getVersion() );
    }
}
