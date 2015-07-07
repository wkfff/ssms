/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseRoutes.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.route;

import com.lanstar.config.Routes;
import com.lanstar.controller.enterprise.*;

public class EnterpriseRoutes extends Routes {
    private static final Routes me = new EnterpriseRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        add( "/e", HomeController.class );
        add( "/e/charts", ChartsController.class );

        add( "/e/stdtmp", TemplateController.class );
        add( "/e/stdtmp_file_01", TemplateFile01Controller.class );
        add( "/e/stdtmp_file_02", TemplateFile02Controller.class );
        add( "/e/stdtmp_file_03", TemplateFile03Controller.class );
        add( "/e/stdtmp_file_04", TemplateFile04Controller.class );
        add( "/e/stdtmp_file_06", TemplateFile06Controller.class );

        add( "/e/stdtmp_file_07", TemplateFile07Controller.class );
        add( "/e/stdtmp_file_08", TemplateFile08Controller.class );
        add( "/e/stdtmp_file_09", TemplateFile09Controller.class );

        add( "/e/tenant_e", EnterpriseController.class);
        add( "/e/tenant_eu", EnterpriseUserController.class);

        add( "/e/gradeplan", GradePlanController.class );
        add( "/e/gradecontent", GradeContentController.class );
        
        add( "/e/reviewresult", ReviewResultController.class );
        add( "/e/reviewresultcontent", ReviewResultContentController.class );
        add( "/e/reviewresultreport", ReviewResultReportController.class );
        add( "/e/reviewresultcert", ReviewResultCertController.class );
        
        add( "/e/gradereport", GradeReportController.class );
    }
}
