/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseUserController.java
 * 创建时间：下午4:51:29
 * 创建用户：苏志亮
 */
package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.tenant.EnterpriseUser;
import com.lanstar.plugin.activerecord.ModelKit;

/**
 * @author Administrator
 */
public class EnterpriseUserController extends SimplateController<EnterpriseUser> {

    @Override
    protected EnterpriseUser getDao() {
        // TODO Auto-generated method stub
        return EnterpriseUser.dao;
    }

    public void rec() {
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        int id = identityContext.getId();
        String tenantName = identityContext.getTenantName();
        int tenantId = identityContext.getTenantId();
        EnterpriseUser model = getDao().findById( id );
        if ( model != null ) {
            setAttr( "tenantId", tenantId );
            setAttr( "tenantName", tenantName );
            setAttrs( ModelKit.toMap( model ) );
        }
    }

    @Override
    public void save() {
        EnterpriseUser model = getModel();

        if ( model != null ) {
            model.update();
            setAttr( "SID", model.getInt( "SID" ) );
            renderJson();
        }
    }
}
