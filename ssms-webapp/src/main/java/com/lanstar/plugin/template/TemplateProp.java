/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateProp.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.model.system.TemplateFile;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.service.Parameter;

/**
 * 模板属性说明文件
 */
@SuppressWarnings("rawtypes")
public class TemplateProp {
    private String name;
    private String code;
    private ModelWrap systemModelWrap;
    private ModelWrap tenantModelWrap;

    private Parameter parameter;
    private SyncUnitFactory syncUnitFactory;

    public static TemplateProp with( String code, String name,
            Class<? extends ModelExt> systemModelClazz,
            Class<? extends ModelExt> tenantModelClazz ) {
        return with( code, name, systemModelClazz, tenantModelClazz,
                     SyncUnitFactory.DEFAULT );
    }

    public static TemplateProp with( String code, String name,
            Class<? extends ModelExt> systemModelClazz,
            Class<? extends ModelExt> tenantModelClazz,
            SyncUnitFactory unitFactory ) {
        TemplateProp prop = new TemplateProp();
        prop.code = code;
        prop.name = name;
        prop.systemModelWrap = ModelWrap.wrap( systemModelClazz );
        prop.tenantModelWrap = ModelWrap.wrap( tenantModelClazz );
        prop.syncUnitFactory = unitFactory;
        return prop;
    }

    public void sync( TemplateFile source,
            com.lanstar.model.tenant.TemplateFile target,
            TemplateFolder tenantFolder, Tenant targetTenant, Identity operator ) {
        syncUnitFactory.sync( source, target, tenantFolder, targetTenant,
                              operator );
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public ModelWrap getSystemModelWrap() {
        return systemModelWrap;
    }

    public ModelWrap getTenantModelWrap() {
        return tenantModelWrap;
    }

    public final Parameter getParameter() {
        if ( parameter == null ) {
            parameter = new Parameter( code, name );
        }
        return parameter;
    }

    public String getTemplateUrl( int id ) {
        // TODO: 模板页面的规则被固定在这里，可能要考虑迁移出去。
        return "/sys/stdtmp_file_" + code + "/?sid=" + id;
    }
}
