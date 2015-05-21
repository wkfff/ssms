/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseRoutes.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.route;

import com.lanstar.config.Routes;
import com.lanstar.controller.enterprise.GradePlantController;

public class EnterpriseRoutes extends Routes {
    private static final Routes me = new EnterpriseRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        add( "/e", com.lanstar.controller.enterprise.HomeController.class );
        add( "/e/stdtmp", com.lanstar.controller.enterprise.TemplateController.class );
        add( "/e/stdtmp_file", com.lanstar.controller.enterprise.TemplateFileController.class );
        add( "/e/stdtmp_file_01", com.lanstar.controller.enterprise.TemplateFile01Controller.class );
        add( "/e/stdtmp_file_02", com.lanstar.controller.enterprise.TemplateFile02Controller.class );
        add( "/e/stdtmp_file_03", com.lanstar.controller.enterprise.TemplateFile03Controller.class );
        add( "/e/stdtmp_file_04", com.lanstar.controller.enterprise.TemplateFile04Controller.class );
        add( "/e/stdtmp_file_05", com.lanstar.controller.enterprise.TemplateFile05Controller.class );

        add("/e/grade_m", GradePlantController.class);
    }
}
