/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：noticeController.java
 * 创建时间：上午11:48:44
 * 创建用户：苏志亮
 */
package com.lanstar.controller.g;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.controller.TableProcessor;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.dialect.JdbcPageRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;

import java.util.Map;

public class noticeController extends DefaultController {

    public noticeController() {
        super("SSM_NOTICE");
    }

    @Override
    public void setFilterFields() {
        this.filterFields.put("T_START", "T_START >= ?");
        this.filterFields.put("T_END", "T_END <= ?");
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return noticeValidator.class;
    }

    /**
     * 查询某条数据
     */
    public ViewAndModel look(HandlerContext context) {
        String sid = (String) context.getValue("sid");
        return context.returnWith().set(context.DB.withTable(TABLENAME).where("SID=?", sid).query());
    }

    /**
     * 发布通知公告
     */
    public void publice(HandlerContext context) {
        ARTable table = context.DB.withTable(TABLENAME);
        String sid = (String) context.getValue("sid");
        table.where("SID=?", sid).value("N_STATE", 1).save();
    }

    /**
     * 显示草稿箱
     */
    public ViewAndModel drafts(HandlerContext context) {
        return context.returnWith();
    }

    /**
     * 显示已发布
     */
    public ViewAndModel publics(HandlerContext context) {
        return context.returnWith();
    }
}
