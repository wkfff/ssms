/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ChartsController.java
 * 创建时间：2015-07-06
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.render.jfreechart.JFreeChartRender;
import com.lanstar.service.enterprise.EnterpriseService;
import com.lanstar.service.enterprise.ProfessionService;

public class ChartsController extends Controller {
    public void chart01() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        EnterpriseService enterpriseService = identityContext.getEnterpriseService();
        ProfessionService professionService = enterpriseService.getProfessionService();
        TemplateFolder folder = professionService.getTenantTemplateFolder();

        DbPro db = identityContext.getTenantDb();
        Long fileCount = db.queryLong( "select COUNT(*) from ssm_stdtmp_file where N_COUNT=0 AND R_TEMPLATE=? AND P_PROFESSION=? AND R_TENANT=? AND P_TENANT='E'",
                professionService.getSystemTemplate().getId(),
                professionService.getId(),
                identityContext.getTenantId() );

        render( JFreeChartRender.PIE( "达标体系完成情况分析" )
                                .setValue( "已完成", folder.getFileCount() - fileCount )
                                .setValue( "未完成", fileCount )
                                .setWidth( 350 )
                                .setHeight( 200 )
                                .render() );
    }

    public void chart02() {
        // 当年已检
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        EnterpriseService enterpriseService = identityContext.getEnterpriseService();
        ProfessionService professionService = enterpriseService.getProfessionService();

        DbPro db = identityContext.getTenantDb();
        Long c1 = db.queryLong( "select COUNT(*) from ssm_stdtmp_file_08 where YEAR(T_TEST_LAST) = YEAR(now()) AND R_TEMPLATE=? AND P_PROFESSION=? AND R_TENANT=? AND P_TENANT='E'",
                professionService.getSystemTemplate().getId(),
                professionService.getId(),
                identityContext.getTenantId() );

        Long c2 = db.queryLong( "select COUNT(*) from ssm_stdtmp_file_08 where YEAR(T_TEST_NEXT) = YEAR(now()) AND R_TEMPLATE=? AND P_PROFESSION=? AND R_TENANT=? AND P_TENANT='E'",
                professionService.getSystemTemplate().getId(),
                professionService.getId(),
                identityContext.getTenantId() );


        render( JFreeChartRender.PIE( "特种设备年检率" )
                                .setValue( "当年未检", c2 )
                                .setValue( "当年已检", c1 )
                                .setWidth( 350 )
                                .setHeight( 200 )
                                .render() );
    }
}
