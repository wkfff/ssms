/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemRoutes.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.route;

import com.lanstar.config.Routes;
import com.lanstar.controller.common.KnowledgeController;
import com.lanstar.controller.system.*;
import com.lanstar.controller.system.attachtext.AttachTextController;

public class SystemRoutes extends Routes {
    private static Routes me = new SystemRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        add( "/s", HomeController.class );

        add( "/sys/attachtext", AttachTextController.class );
        add( "/sys/attachfile", AttachFileController.class );

        add( "/sys/nav", NavgateController.class );
        add( "/sys/template", TemplateController.class );
        add( "/sys/stdtmp", TemplateFolderController.class );
        add( "/sys/stdtmp_file", TemplateFileController.class );
        add( "/sys/stdtmp_grade", TemplateGradeController.class );
        add( "/sys/stdtmp_rep", TemplateRepController.class );
        add( "/sys/stdtmp_file_01", TemplateFile01Controller.class );
        add( "/sys/stdtmp_file_02", TemplateFile02Controller.class );
        add( "/sys/stdtmp_file_03", TemplateFile03Controller.class );
        add( "/sys/stdtmp_file_04", TemplateFile04Controller.class );
        add( "/sys/stdtmp_file_06", TemplateFile06Controller.class );

        add( "/sys/stdtmp_file_07", TemplateFile07Controller.class );
        add( "/sys/stdtmp_file_08", TemplateFile08Controller.class );
        add( "/sys/stdtmp_file_09", TemplateFile09Controller.class );

        add( "/sys/para_multi", MultiParaController.class );
        add( "/sys/industry", IndustryController.class );
        add( "/sys/profession", ProfessionController.class );
        add( "/sys/para_area", AreaParaController.class );

        add( "/sys/tenant_e", EnterpriseController.class );
        add( "/sys/tenant_eu", EnterpriseUserController.class );

        add( "/sys/tenant_g", GovernmentController.class );
        add( "/sys/tenant_gu", GovernmentUserController.class );

        add( "/sys/tenant_r", ReviewController.class );
        add( "/sys/tenant_ru", ReviewUserController.class );

        add( "/sys/professions", ProfessionsController.class );

        add( "/knowledge", KnowledgeController.class );
    }
}
