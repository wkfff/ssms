/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemRoutes.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.route;

import com.lanstar.config.Routes;

public class SystemRoutes extends Routes {
    private static Routes me = new SystemRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        add( "/s", com.lanstar.controller.system.HomeController.class );

        add( "/sys/attachtext", com.lanstar.controller.system.AttachTextController.class );
        add( "/sys/nav", com.lanstar.controller.system.NavgateController.class );
        add( "/sys/template", com.lanstar.controller.system.TemplateController.class );
        add( "/sys/stdtmp", com.lanstar.controller.system.TemplateFolderController.class );
        add( "/sys/stdtmp_file", com.lanstar.controller.system.TemplateFileController.class );
        add( "/sys/stdtmp_grade", com.lanstar.controller.system.TemplateGradeController.class );
        add( "/sys/stdtmp_file_01", com.lanstar.controller.system.TemplateFileController01.class );
        add( "/sys/stdtmp_file_02", com.lanstar.controller.system.TemplateFileController02.class );
        add( "/sys/stdtmp_file_03", com.lanstar.controller.system.TemplateFileController03.class );
        add( "/sys/stdtmp_file_04", com.lanstar.controller.system.TemplateFileController04.class );
        add( "/sys/stdtmp_file_05", com.lanstar.controller.system.TemplateFileController05.class );

        add( "/sys/para_multi", com.lanstar.controller.system.MultiParaController.class );
        add( "/sys/industry", com.lanstar.controller.system.IndustryController.class );
        add( "/sys/profession", com.lanstar.controller.system.ProfessionController.class );
        add( "/sys/para_area", com.lanstar.controller.system.AreaParaController.class );

        add( "/sys/tenant_e", com.lanstar.controller.system.EnterpriseController.class );
        add( "/sys/tenant_eu", com.lanstar.controller.system.EnterpriseUserController.class );
    }
}
