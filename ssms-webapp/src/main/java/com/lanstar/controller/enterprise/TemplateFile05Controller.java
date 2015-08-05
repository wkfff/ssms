/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile05Controller.java
 * 创建时间：上午11:12:22
 * 创建用户：苏志亮
 */
package com.lanstar.controller.enterprise;

import java.util.List;

import com.lanstar.common.Asserts;
import com.lanstar.common.ListKit;
import com.lanstar.common.ModelInjector;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.identity.TenantType;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.model.tenant.TemplateFile05;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile05Controller extends Controller {
    public void index() {
        Integer templatefileId = getParaToInt();
        Asserts.notNull( templatefileId, "发现非法的参数请求" );
        UniqueTag opertion = IdentityContextWrap.getIdentityContext( this ).getEnterpriseService().getUniqueTag();
        TemplateFile file = TemplateFile.findFirst( opertion, templatefileId );
        TemplateFile05 model05 = getModel( opertion );
        if ( model05 == null ) {
            TemplateFile05 model = new TemplateFile05();
            model.setTemplateId( opertion.getTemplateId() );
            model.setProfessionId( opertion.getProfessionId() );
            model.setTemplateFileId( templatefileId );
            ModelInjector.injectOpreator( model, IdentityContextWrap.getIdentityContext( this ) );
            model.save();
            setAttr( "SID", model.getId() );
        } else {
            setAttr( "SID", model05.getId() );
        }
        List<TemplateFile01> list01 = TemplateFile01.dao.list( opertion );
        setAttr( "title", file.getName() );
        setAttr( "files", list01 );
    }

    private TemplateFile05 getModel( UniqueTag opertion ) {
        TemplateFile05 model = TemplateFile05.dao.findFirstByColumns(
            ListKit.newArrayList( "R_TENANT", "P_TENANT", "P_PROFESSION", "R_TEMPLATE" ),
            ListKit.newObjectArrayList(
                opertion.getTenantId(),
                TenantType.ENTERPRISE.getName(),
                opertion.getProfessionId(),
                opertion.getTemplateId() ) );
        return model;
    }

}
