/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewUserController.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.tenant.Review;
import com.lanstar.model.system.tenant.ReviewUser;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class ReviewUserController extends SimplateController<ReviewUser> {
    @Override
    public void rec() {
        Review model = Review.dao.findById( getPara( "pid" ) );
        setAttr( "tenant", model );
        super.rec();
    }

    public void reg() {
        Review model = Review.dao.findById( getPara( "pid" ) );
        setAttr( "tenant", model );
    }

    public void psw() {

    }

    public void resetPsw() {
        String sid = getPara( "sid" );
        ReviewUser user = ReviewUser.dao.findById( sid );
        if ( user != null ) {
            user.setPassword( StrKit.toMD5( "123456" ) );
            user.update();
            renderJson( true );
        } else renderJson( false );
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE( "R_SID = ?", getPara( "R_SID" ) );
    }

    @Override
    protected ReviewUser getDao() {
        return ReviewUser.dao;
    }

    @Override
    protected void beforeSave( ReviewUser model, boolean[] handled ) {
        model.initPassword();
    }
}
