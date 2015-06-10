/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController01.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.TemplateFile01;
import com.lanstar.plugin.activerecord.ModelKit;

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
    }

    public void view() {
        this.index();
        setAttr( "@READONLY", "true" );
        render( "index.ftl" );
    }

}
