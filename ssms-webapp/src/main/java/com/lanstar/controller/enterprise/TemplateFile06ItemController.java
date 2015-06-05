/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile06ItemController.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile06Item;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Page;

import java.util.Map;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

public class TemplateFile06ItemController extends SimplateController<TemplateFile06Item> {
    @Override
    public void rec() {
        TemplateFile templateFile = TemplateFile.dao.findById( getParaToInt( "fileid" ) );

        setAttr( "list", EasyUIControllerHelper.toDatagridResult( pageList() ) );
        setAttr( "template", templateFile );
    }

    public void recJson() {
        renderJson( EasyUIControllerHelper.toDatagridResult( pageList() ) );
    }

    @Override
    protected TemplateFile06Item getDao() {
        return TemplateFile06Item.dao;
    }

    private Page<Map<String, Object>> pageList() {
        Integer pageNumber = getParaToInt( PAGE_INDEX, 1 );
        Integer pageSize = getParaToInt( PAGE_SIZE, 3 );
        Page<TemplateFile06Item> paginate = getDao().paginate( pageNumber, pageSize, "select *",
                "from " + table.getName() + " where R_SID=?", getParaToInt( "pid" ) );
        return ModelKit.toMap( paginate, "SID", "C_NAME", "C_EXAMINER", "T_EXAMINE", "P_LEVEL", "C_MEASURE", "C_RESPONSIBLE", "T_RECTIFICATION", "C_ACCEPTANCE" );
    }
}
