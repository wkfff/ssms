/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import java.util.List;

import com.lanstar.common.ListKit;
import com.lanstar.identity.TenantType;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile01 extends TemplateFileModel<TemplateFile01> {
    public static TemplateFile01 dao = new TemplateFile01();

    public List<TemplateFile01> list( UniqueTag opertion ) {
        return dao.findByColumns(
            ListKit.newArrayList( "R_TENANT", "P_TENANT", "P_PROFESSION", "R_TEMPLATE" ),
            ListKit.newObjectArrayList(
                opertion.getTenantId(),
                TenantType.ENTERPRISE.getName(),
                opertion.getProfessionId(),
                opertion.getTemplateId() ) );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }
}
