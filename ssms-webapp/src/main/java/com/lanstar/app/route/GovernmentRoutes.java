/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GovernmentRoutes.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.route;

import com.lanstar.config.Routes;
import com.lanstar.controller.government.HomeController;
import com.lanstar.controller.system.KnowledgeFileController;
import com.lanstar.controller.system.KnowledgeSortController;
import com.lanstar.controller.system.NoticeController;

public class GovernmentRoutes extends Routes {
    private static Routes me = new GovernmentRoutes();

    public static Routes me() {
        return me;
    }

    @Override
    public void config() {
        this.add( "/g", HomeController.class );

        this.add( "/g/notice", NoticeController.class );
        this.add( "/g/knowledge_sort", KnowledgeSortController.class );
        this.add( "/g/knowledge_file", KnowledgeFileController.class );
    }
}
