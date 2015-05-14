/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：stdtmpContoller.java
 * 创建时间：2015-05-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller.e;

import com.lanstar.common.tree.TreeNode;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.model.Profession;
import com.lanstar.model.Template;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;

import java.util.List;

public class stdtmpController extends DefaultController {
    public stdtmpController() {
        super( "SSM_STDTMP_FOLDER" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return null;
    }

    @Override
    public ViewAndModel index( HandlerContext context ) {
        Profession profession = context.getIdentityContxt().get( Profession.class );
        Template template = profession.getTemplate();
        return super.index( context ).put( "R_SID", template.getTemplateId() );
    }

    public ViewAndModel tree( HandlerContext context ) {
        String template = context.getValue( "template" );
        JdbcRecordSet folder = context.SYSTEM_DB.query( "SELECT SID, C_NAME, R_SID\n"
                + "FROM SSM_STDTMP_FOLDER\n"
                + "WHERE R_TEMPLATE = ? \n"
                + "UNION ALL \n"
                + "SELECT SID, C_NAME, R_SID FROM SSM_STDTMP_FILE\n"
                + "WHERE R_SID IN (\n"
                + "  SELECT SID FROM SSM_STDTMP_FOLDER\n"
                + "  WHERE R_TEMPLATE = ?\n"
                + ")", template, template );
        List<TreeNode> value = EasyUIControllerHelper.toTree( folder, "SID", "R_SID", "C_NAME" );
        if ( value.size() == 1 ) {
            value = value.get( 0 ).getChildren();
        }
        return context.returnWith().set( value );
    }
}
