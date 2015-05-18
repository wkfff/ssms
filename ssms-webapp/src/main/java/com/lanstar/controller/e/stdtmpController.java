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
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.model.Profession;
import com.lanstar.model.Template;
import com.lanstar.service.TenantContext;
import com.lanstar.service.enterprise.ProfessionTemplateService;

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
    public ViewAndModel index( final HandlerContext context ) {
        final Profession profession = context.getIdentityContxt().get( Profession.class );
        // sync tenant template from system template
        context.DB.transaction( new HandlerDbContext.IAtom() {
            @Override
            public boolean execute( HandlerDbContext dbContext ) throws Exception {
                try ( ProfessionTemplateService service = profession.getTemplateService() ) {
                    service.synchronousTo( new TenantContext( context.getIdentity(), context.DB ) );
                }
                return true;
            }
        } );
        // get template instance
        Template template = profession.getTemplate();

        // load template tree data
        JdbcRecordSet folder = context.SYSTEM_DB.query(
                "SELECT CONCAT('D-',CONVERT(SID,CHAR)) SID, C_NAME, CONCAT('D-',CONVERT(R_SID,CHAR)) R_SID\n"
                        + "FROM SSM_STDTMP_FOLDER\n"
                        + "WHERE R_TEMPLATE = ? \n"
                        + "UNION ALL \n"
                        + "SELECT CONCAT('F-',CONVERT(SID,CHAR)) SID, C_NAME, CONCAT('D-',CONVERT(R_SID,CHAR)) R_SID FROM SSM_STDTMP_FILE\n"
                        + "WHERE R_SID IN (\n"
                        + "  SELECT SID FROM SSM_STDTMP_FOLDER\n"
                        + "  WHERE R_TEMPLATE = ?\n"
                        + ")", template.getTemplateId(), template.getTemplateId() );
        List<TreeNode> value = EasyUIControllerHelper.toTree( "D-0", folder, "SID", "R_SID", "C_NAME" );
        if ( value.size() == 1 ) {
            value = value.get( 0 ).getChildren();
        }
        List<TreeNode> tmp = value;
        TreeNode firstRec = null;
        while ( tmp.size() > 0 ) {
            firstRec = tmp.get( 0 );
            tmp = firstRec.getChildren();
        }

        return super.index( context ).put( "R_SID", template.getTemplateId() ).put( "tree", value ).put( "firstRec", firstRec );
    }
}
