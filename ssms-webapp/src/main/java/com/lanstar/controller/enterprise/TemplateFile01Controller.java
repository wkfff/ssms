/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.common.ModelInjector;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.model.tenant.TemplateFile01Item;
import com.lanstar.plugin.activerecord.ModelKit;

import java.util.Calendar;
import java.util.Date;

public class TemplateFile01Controller extends SimplateController<TemplateFile01> {
    @Override
    protected TemplateFile01 getDao() {
        return TemplateFile01.dao;
    }

    public void index() {
        String sid = getPara( "sid" );
        TemplateFile01 templateFile = getDao().findFirstByColumn( "R_TMPFILE", sid );
        if (templateFile == null) return;
        setAttrs( ModelKit.toMap( templateFile ) );
        TemplateFile file = templateFile.getTemplateFile();
        com.lanstar.model.system.TemplateFile sourceFile = file.getSourceFile();
        if ( sourceFile == null ) return;
        int sourceId = sourceFile.getId();
        com.lanstar.model.system.TemplateFile01 m = com.lanstar.model.system.TemplateFile01.dao.findFirstByColumn( "R_TMPFILE", sourceId );
        if ( m == null ) return;
        int id = m.getId();
        setAttr( "TEMPLATE_ID", id );

        String sql = "select * from SSM_STDTMP_FILE_01_ITEM where R_TMPFILE_01= ?";

        setAttr("pass", TemplateFile01Item.dao.find(sql, templateFile.get("SID")));
    }

    public void pass() {
        TemplateFile01Item model = new TemplateFile01Item();
        Integer sid = getParaToInt("SID");

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        String sql = "select * from SSM_STDTMP_FILE_01_ITEM where R_TMPFILE_01= ? and '" + year + "-01-01' <=T_DATE_01 and T_DATE_01<='" + year + "-12-31'";

        if (model.findFirst(sql, sid) == null) {
            model.set("R_TMPFILE_01", sid);
            model.set("T_DATE_01", new Date());
            ModelInjector.injectOpreator(model, identityContext);

            if (model.save()) {
                //审核通过
                renderJson("1");
            } else {
                //审核不通过
                renderJson("0");
            }
        } else {
            //已审核通过
            renderJson("3");
        }
    }
    
    public void view(){
        super.index();
        setAttr( "@READONLY", "true" );
        render( "index.ftl" );
    }
}
