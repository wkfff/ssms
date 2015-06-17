/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateVersion.java
 * 创建时间：2015年6月16日 上午11:22:54
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import com.alibaba.fastjson.JSON;
import com.lanstar.identity.Tenant;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;
import com.lanstar.plugin.activerecord.ModelExt;

/**
 * 模板版本
 */
public class TemplateVersion extends ModelExt<TemplateVersion> {
    public static TemplateVersion dao = new TemplateVersion();

    public <T> T getArchiveData( Class<T> clazz ) {
        return JSON.parseObject( getStr( "C_ARCH_DATA" ), clazz );
    }

    public int getTemplateId() {
        return getInt( "R_TEMPLATE" );
    }

    public void setTemplateId( int templateId ) {
        set( "R_TEMPLATE", templateId );
    }

    public int getProfession() {
        return getInt( "P_PROFESSION" );
    }

    public void setProfession( int profession ) {
        set( "P_PROFESSION", profession );
    }

    public int getVersion() {
        return getInt( "N_VERSION" );
    }

    public void setVersion( int version ) {
        set( "N_VERSION", version );
    }

    public int getTenantId() {
        return getInt( "R_TENANT" );
    }

    public void setTenantId( int tenantId ) {
        set( "R_TENANT", tenantId );
    }

    public int getId() {
        return getInt( "SID" );
    }

    public void setProfession( Profession profession ) {
        set( "P_PROFESSION", profession.getId() );
    }

    public void setArchiveData( Object data ) {
        set( "C_ARCH_DATA", JSON.toJSONString( data ) );
    }

    public void setTenant( Tenant tenant ) {
        set( "R_TENANT", tenant.getTenantId() );
        set( "S_TENANT", tenant.getTenantName() );
        set( "P_TENANT", tenant.getTenantType().getName() );
    }

    public void setTemplate( Template template ) {
        set( "R_TEMPLATE", template.getId() );
    }
}
