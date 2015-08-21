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
import com.lanstar.controller.system.ReviewNoticeController;
import com.lanstar.model.system.ReviewNotice;

public class ReviewRoutes extends Routes {
    private static Routes me = new ReviewRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        add( "/r", HomeController.class );

        add( "/r/stdtmp", TemplateController.class );
        add( "/r/review", ReviewController.class );
        add( "/r/e/stdtmp", TemplateController.class );

        add( "/r/tenant_r", ReviewController.class );
        add( "/r/tenant_ru", ReviewUserController.class );
        add( "/r/reviewer", ReviewerController.class );

        add( "/r/grade", GradeController.class );
        add( "/r/reviewplan", ReviewPlanController.class );
        add( "/r/reviewcontent", ReviewContentController.class );
        add( "/r/reviewreport", ReviewReportController.class );
        add( "/r/reviewcert", ReviewCertController.class );
        add( "/r/reviewhistory", ReviewHistoryController.class );
        add( "/r/reviewmember", ReviewMemberController.class );
        
        add( "/r/review_notice", ReviewNoticeController.class );
        

    }
}
