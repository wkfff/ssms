/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemRoutes.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.route;

import com.lanstar.config.Routes;
import com.lanstar.controller.system.*;

public class SystemRoutes extends Routes {
    private static Routes me = new SystemRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        add( "/s", com.lanstar.controller.system.HomeController.class );

        add( "/sys/attachtext", com.lanstar.controller.system.AttachTextController.class );
        add( "/sys/attachfile", com.lanstar.controller.system.AttachFileController.class );

        add( "/sys/nav", com.lanstar.controller.system.NavgateController.class );
        add( "/sys/template", com.lanstar.controller.system.TemplateController.class );
        add( "/sys/stdtmp", com.lanstar.controller.system.TemplateFolderController.class );
        add( "/sys/stdtmp_file", com.lanstar.controller.system.TemplateFileController.class );
        add( "/sys/stdtmp_grade", com.lanstar.controller.system.TemplateGradeController.class );
        add( "/sys/stdtmp_rep", com.lanstar.controller.system.TemplateRepController.class );
        add( "/sys/stdtmp_file_01", TemplateFile01Controller.class );
        add( "/sys/stdtmp_file_02", TemplateFile02Controller.class );
        add( "/sys/stdtmp_file_03", TemplateFile03Controller.class );
        add( "/sys/stdtmp_file_04", TemplateFile04Controller.class );
        add( "/sys/stdtmp_file_05", TemplateFile05Controller.class );
        add( "/sys/stdtmp_file_06", TemplateFile06Controller.class );
        
        add( "/sys/stdtmp_file_07", TemplateFile07Controller.class );
        add( "/sys/stdtmp_file_08", TemplateFile08Controller.class );
        add( "/sys/stdtmp_file_09", TemplateFile09Controller.class );

        add( "/sys/para_multi", com.lanstar.controller.system.MultiParaController.class );
        add( "/sys/industry", com.lanstar.controller.system.IndustryController.class );
        add( "/sys/profession", com.lanstar.controller.system.ProfessionController.class );
        add( "/sys/para_area", com.lanstar.controller.system.AreaParaController.class );

        add( "/sys/tenant_e", com.lanstar.controller.system.EnterpriseController.class );
        add( "/sys/tenant_eu", com.lanstar.controller.system.EnterpriseUserController.class );

        add( "/sys/tenant_g", com.lanstar.controller.system.GovernmentController.class );
        add( "/sys/tenant_gu", com.lanstar.controller.system.GovernmentUserController.class );

        add( "/sys/tenant_r", com.lanstar.controller.system.ReviewController.class );
        add( "/sys/tenant_ru", com.lanstar.controller.system.ReviewUserController.class );

        add("/knowledge", com.lanstar.controller.common.KnowledgeController.class);
    }
}
