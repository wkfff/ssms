/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeReportController.java
 * 创建时间：2015年7月1日 上午1:16:36
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

import com.lanstar.common.Asserts;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.core.render.JsonRender;
import com.lanstar.model.tenant.GradeReport;
import com.lanstar.model.tenant.TemplateFile02;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.render.aspose.AsposeRender;
import com.lanstar.render.aspose.OutputFormat;

/**
 * 自评报告
 *
 */
public class GradeReportController extends SimplateController<GradeReport> {

    @Override
    protected GradeReport getDao() {
        return GradeReport.dao;
    }

    @Override
    public void rec() {
        GradeReport model = null;
        String sid = this.getPara( "sid" );
        if ( StrKit.isEmpty( sid ) ) sid = this.getPara( "SID" );
        if ( sid == null ) {
            sid = this.getPara( "R_SID" );
            if (!StrKit.isBlank( sid )) model = this.getDao().getReport( Integer.parseInt( sid ) );
        } else model = this.getDao().findById( sid );

        if ( model != null ) this.setAttrs( ModelKit.toMap( model ) );
    }

    @Override
    public void save() {
        GradeReport model = this.getModel();
        Integer sid = model.getInt( "SID" );
        if ( sid == null ) model.save();
        else {
            model.setState( 1 );
            model.update();
        }
        sid = model.getInt( "SID" );
        this.setAttr( "SID", sid );
        this.render( new JsonRender( sid ).forIE() );
    }
    
    public void view(){
        rec();
    }
    
    public void export() {
        Integer sid = getParaToInt();
        Asserts.notNull( sid, "非法的参数请求" );
        GradeReport rep = GradeReport.dao.findById( sid );
        String content = rep.getContent();
        render( AsposeRender.me( content, "自评报告", OutputFormat.PDF ) );
    }
}
