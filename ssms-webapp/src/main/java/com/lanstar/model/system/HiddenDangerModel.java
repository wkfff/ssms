/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HiddenDangerTask.java
 * 创建时间：上午10:56:46
 * 创建用户：苏志亮
 */
package com.lanstar.model.system;

import java.util.Date;

import com.lanstar.plugin.activerecord.Model;

public class HiddenDangerModel extends Model<HiddenDangerModel> {

    public static final HiddenDangerModel dao = new HiddenDangerModel();

    public void setTemplateId( Integer templateId ) {
        set( "R_TEMPLATE", templateId );

    }

    public void setProfessionId( Integer ProfessionId ) {
        set( "P_PROFESSION", ProfessionId );

    }

    public void setTenantId( Integer tenantId ) {
        set( "R_TENANT", tenantId );
    }

    public void setTenantName( String tenantName ) {
        set( "S_TENANT", tenantName );

    }

    public void setTenantType( String tenantType ) {
        set( "P_TENANT", tenantType );

    }

    public void setCreateTime( Date createTime ) {
        set( "T_CREATE", createTime );

    }
}