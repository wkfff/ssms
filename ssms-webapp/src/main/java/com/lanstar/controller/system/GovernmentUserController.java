/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GovernmentUserController.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.Government;
import com.lanstar.model.system.GovernmentUser;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class GovernmentUserController extends SimplateController<GovernmentUser> {
    @Override
    public void rec() {
        Government model = Government.dao.findById( getPara( "pid" ) );
        setAttr( "tenant", model );
        super.rec();
    }


    public void reg() {
        Government model = Government.dao.findById( getPara( "pid" ) );
        setAttr( "tenant", model );
    }

    public void psw() {

    }

    public void updtePSW() {
        String sid = getPara( "SID" );
        String oldPwd = getPara( "oldPwd" );
        String newPwd = getPara( "newPwd" );

        GovernmentUser user = GovernmentUser.getUser( sid, oldPwd );
        if ( user != null ) {
            user.setPassword( newPwd );
            user.update();
            renderJson( true );
        } else renderJson( false );
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE( "R_SID = ?", getPara( "R_SID" ) );
    }

    @Override
    protected GovernmentUser getDao() {
        return GovernmentUser.dao;
    }

    @Override
    protected void beforeSave( GovernmentUser model, boolean[] handled ) {
        model.initPassword();
    }
}
