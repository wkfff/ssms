/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseUserController.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.core.render.JsonRender;
import com.lanstar.model.system.Enterprise;
import com.lanstar.model.system.EnterpriseUser;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class EnterpriseUserController extends SimplateController<EnterpriseUser> {
    @Override
    protected EnterpriseUser getDao() {
        return EnterpriseUser.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE( "R_SID = ?", getPara( "R_SID" ) );
    }

    @Override
    public void rec() {
        Enterprise enterprise = Enterprise.dao.findById( getPara( "pid" ) );
        setAttr( "tenant", enterprise );
    }

    public void json() {
        super.rec();
        render( new JsonRender().forIE() );
    }

    public void reg() {
        rec();
    }

    public void updtePSW() {
        String sid = getPara( "SID" );
        String oldPwd = getPara( "oldPwd" );
        String newPwd = getPara( "newPwd" );

        EnterpriseUser user = EnterpriseUser.getUser( sid, oldPwd );
        if ( user != null ) {
            user.setPassword( newPwd );
            user.update();
            renderJson( true );
        } else renderJson( false );
    }

    public void psw() {

    }
}
