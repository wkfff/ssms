/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.app.Const;
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.ModelInjector;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.model.tenant.TemplateFile01Item;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

public class TemplateFile01Controller extends SimplateController<TemplateFile01> {
    @Override
    protected TemplateFile01 getDao() {
        return TemplateFile01.dao;
    }

    public void index() {
        //先判断sid 是否有值，如果有值根据模板获R_TMPFILE取到对应的模板，如果没值根据SID获取到模板。。。
        TemplateFile01 model = null;
        String sid = getPara("sid");
        if (StrKit.isEmpty(sid)) {
            sid = getPara("SID");
            if (sid == null)
                return;
            model = getDao().findById(sid);
        } else {
            model = getDao().findFirstByColumn("R_TMPFILE", sid);
        }

        if (model != null)
            setAttrs(ModelKit.toMap(model));

        // 绕一圈获取到模板id
        TemplateFile file = TemplateFile.dao.findById(getAttrForInt(Const.TEMPLATE_FILE_PARENT_FIELD));
        com.lanstar.model.system.TemplateFile sourceFile = file.getSourceFile();

        setAttr("TEMPLATE_ID", sourceFile.getId());

        String sql = "select * from SSM_STDTMP_FILE_01_ITEM where R_TMPFILE_01= ?";

        setAttr("pass", TemplateFile01Item.dao.find(sql, model.get("SID")));
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
