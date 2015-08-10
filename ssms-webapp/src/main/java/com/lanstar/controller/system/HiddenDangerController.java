/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HiddenDangerController.java
 * 创建时间：下午1:47:05
 * 创建用户：苏志亮
 */
package com.lanstar.controller.system;

import java.util.List;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.HiddenDangerHistory;
import com.lanstar.model.system.HiddenDangerModel;
import com.lanstar.model.system.tenant.Government;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.enterprise.UniqueTag;

/**
 * 隐患排查提醒
 *
 */
public class HiddenDangerController extends Controller {
    public void listEnterprise() {
        UniqueTag uniqueTag = IdentityContextWrap.getIdentityContext( this ).getEnterpriseService().getUniqueTag();
        Integer tenantId = uniqueTag.getTenantId();
        String tenantType = TenantType.ENTERPRISE.getName();
        Integer templateId = uniqueTag.getTemplateId();
        Integer professionId = uniqueTag.getProfessionId();
        List<HiddenDangerModel> list = HiddenDangerModel.dao.find( SqlKit.sql( "system.hiddenDanger.listEnterprise" ),
            tenantId, tenantType, tenantId, tenantType, templateId, professionId );
        setAttr( "list", list );
    }

    public void indexGovernment() {
    }

    public void listGovernment() {
        Government government = (Government) IdentityContextWrap.getIdentityContext( this ).getTenant();
        String leave = government.getLevel();
        List<HiddenDangerModel> list = HiddenDangerModel.dao.find( SqlKit.sql( "system.hiddenDanger.listGovernment" + leave ), government.getTenantId(), government.getTenantId(), government.getTenantType().getName() );
        renderJson(list);
    }

    public void readHidden() {
        Integer sid = getParaToInt( "sid" );
        if ( sid == null ) renderJson( false );
        else {
            Tenant tenant = IdentityContextWrap.getIdentityContext( this ).getTenant();
            HiddenDangerHistory model = new HiddenDangerHistory();
            model.setTenantId( tenant.getTenantId() );
            model.setTenantType( tenant.getTenantType().getName() );
            model.setTenantName( tenant.getTenantName() );
            model.setHiddenDangerId( sid );
            model.save();
            renderJson( true );
        }
    }
}
