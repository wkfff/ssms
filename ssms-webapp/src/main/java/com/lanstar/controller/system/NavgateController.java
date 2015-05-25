/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NavgateController.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.Navgate;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

import java.util.List;

public class NavgateController extends SimplateController<Navgate> {
    @Override
    protected Navgate getDao() {
        return Navgate.dao;
    }

    public void tree() {
        List<Navgate> list = Navgate.list();
        renderJson( EasyUIControllerHelper.toTree( "0", ModelKit.toMap( list ), "SID", "R_SID", "C_NAME" ) );
    }

    @Override
    public void rec() {
        super.rec();
        renderJson();
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder().WHERE( "R_SID=?", getPara( "R_SID" ) );
    }
}
