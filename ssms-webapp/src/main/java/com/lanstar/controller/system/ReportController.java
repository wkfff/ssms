/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReportController.java
 * 创建时间：2015年7月1日 上午1:16:36
 * 创建用户：林峰
 */
package com.lanstar.controller.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.core.render.JsonRender;
import com.lanstar.model.system.TemplateRep;
import com.lanstar.plugin.activerecord.ModelKit;

/**
 * 自评、评审报告
 *
 */
public class ReportController  extends SimplateController<TemplateRep> {

    @Override
    protected TemplateRep getDao() {
        return TemplateRep.dao;
    }
    
    @Override
    public void rec() {
        TemplateRep model = null;
        String sid = this.getPara( "sid" );
        if ( StrKit.isEmpty( sid ) ) sid = this.getPara( "SID" );
        if ( sid == null ) {
            sid = this.getPara( "R_SID" );
            String type = this.getPara( "type" );
            model = this.getDao().findFirst( "select * from sys_report_template where r_sid=? and z_type=?", sid, type );
        } else model = this.getDao().findById( sid );

        if ( model != null ) this.setAttrs( ModelKit.toMap( model ) );
    }

    @Override
    public void save() {
        TemplateRep model = getModel();
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) model.save();
        else model.update();
        sid = model.getInt( "SID" );
        setAttr( "SID",sid );
        render( new JsonRender( sid ).forIE() );
    }
}
