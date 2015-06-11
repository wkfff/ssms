/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewRoutes.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.route;

import com.lanstar.config.Routes;

import com.lanstar.controller.review.*;
import com.lanstar.controller.system.DoneController;
import com.lanstar.controller.system.KnowledgeFileController;
import com.lanstar.controller.system.KnowledgeSortController;
import com.lanstar.controller.system.NoticeController;
import com.lanstar.controller.system.TodoController;

public class ReviewRoutes extends Routes {
    private static Routes me = new ReviewRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        add( "/r", HomeController.class );
        add( "/r/grade_m", GradePlanController.class );
        add( "/r/grade_d", GradeContentController.class );

        add( "/r/review", ReviewController.class );
        add( "/r/reviewer", ReviewerController.class );
        add( "/r/e/stdtmp", TemplateController.class );
        
        add( "/r/tenant_r" , ReviewController.class );
        add( "/r/tenant_ru" , ReviewUserController.class );
        add( "/r/notice", NoticeController.class );
        add( "/r/todo", TodoController.class );
        add( "/r/done", DoneController.class );
        add( "/r/knowledge_sort", KnowledgeSortController.class );
        add( "/r/knowledge_file", KnowledgeFileController.class );
        
        add( "/r/psw_change", PswChangeController.class);
    }
}
