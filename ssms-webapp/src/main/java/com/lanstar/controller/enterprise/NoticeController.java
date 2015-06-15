/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NoticeController.java
 * 创建时间：2015年6月5日 下午4:27:23
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.ModelInjector;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.AttachFile;
import com.lanstar.model.system.AttachText;
import com.lanstar.model.system.Notice;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import java.util.Date;
import java.util.List;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

/**
 * 通知公告
 *
 */
public class NoticeController extends SimplateController<Notice> {

    @Override
    protected Notice getDao() {
        return Notice.dao;
    }

    /**
     * 查看
     */
    public void view() {
        String sid = getPara("sid");
        if (StrKit.isEmpty(sid))
            sid = getPara("SID");
        if (sid == null)
            return;

        Notice model = getDao().findById(sid);
        if (model != null)
            setAttrs(ModelKit.toMap(model));

        String textSql = "SELECT * FROM sys_attach_text WHERE r_table = 'ssm_notice' AND r_sid = ?";
        setAttr("noticeText", AttachText.dao.findFirst(textSql, sid));

        String fileSql = "SELECT * FROM sys_attach_file WHERE r_table = 'ssm_notice' AND r_sid = ?";
        setAttr("noticeFile", AttachFile.dao.find(fileSql,sid));

    }

    public void publics() {

    }

    /**
     * 已发布
     */
    public void publics_list() {
        SqlBuilder select = SQL.SELECT("*");
        SqlBuilder from = new SqlBuilder().FROM(table.getName());
        SqlBuilder where = new SqlBuilder();

        IdentityContext identityContext = IdentityContext.getIdentityContext(this);
//        R_TENANT
//        where.WHERE("R_TENANT=?", identityContext.getTenantId());
        where.WHERE("R_PUBLISH is not null");
        if (where != null)
            from.append(where);
        SqlBuilder order = buildOrder();
        if (order != null)
            from.append(order);

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if (this.isParaExists(PAGE_INDEX) && this.isParaExists(PAGE_SIZE)) {
            Page<Notice> paginate = getDao().paginate(getParaToInt(PAGE_INDEX),
                                                      getParaToInt(PAGE_SIZE),
                                                      selectStatement.getSql(),
                                                      fromStatement.getSql(),
                                                      fromStatement.getParams());
            renderJson(EasyUIControllerHelper.toDatagridResult(paginate));
        } else {
            List<Notice> list = getDao().find(selectStatement.getSql() + " " + fromStatement.getSql(),
                                              fromStatement.getParams());
            renderJson(list);
        }
    }
}
