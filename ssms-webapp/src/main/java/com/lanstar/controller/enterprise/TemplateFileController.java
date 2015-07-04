/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController.java
 * 创建时间：2015-07-04
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.identity.TenantType;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.service.enterprise.UniqueTag;

public abstract class TemplateFileController<T extends Model<T>> extends SimplateController<T> {
    @Override
    public void index() {
        setAttr( "fileid", getPara() );
    }

    @Override
    protected SqlBuilder buildWhere() {
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        return new SqlBuilder().WHERE( "R_TENANT=?", uniqueTag.getTenantId() )
                               ._( "P_TENANT=?", TenantType.ENTERPRISE.getName() )
                               ._( "R_TEMPLATE=?", uniqueTag.getTemplateId() )
                               ._( "P_PROFESSION=?", uniqueTag.getProfessionId() );
    }

    @Override
    protected void beforeSave( T model, boolean[] handled ) {
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        model.set( "R_TEMPLATE", uniqueTag.getTemplateId() );
        model.set( "P_PROFESSION", uniqueTag.getProfessionId() );
    }
}
