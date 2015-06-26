/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantModelMapping.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.model;

import com.lanstar.model.Done;
import com.lanstar.model.Todo;
import com.lanstar.model.tenant.*;

public class TenantModelMapping extends ActiveRecordMapping {
    public TenantModelMapping() {
        addMapping( "SSM_STDTMP_FOLDER", "SID", TemplateFolder.class );
        addMapping( "SSM_STDTMP_FILE", "SID", TemplateFile.class );
        addMapping( "SSM_STDTMP_FILE_01", "SID", TemplateFile01.class );
        addMapping( "SSM_STDTMP_FILE_01_ITEM", "SID", TemplateFile01Item.class );
        addMapping( "SSM_STDTMP_FILE_02", "SID", TemplateFile02.class );
        addMapping( "SSM_STDTMP_FILE_03", "SID", TemplateFile03.class );
        addMapping( "SSM_STDTMP_FILE_04", "SID", TemplateFile04.class );
        addMapping( "SSM_STDTMP_FILE_06", "SID", TemplateFile06.class );

        addMapping( "SSM_STDTMP_FILE_07", "SID", TemplateFile07.class );
        addMapping( "SSM_STDTMP_FILE_08", "SID", TemplateFile08.class );
        addMapping( "SSM_STDTMP_FILE_09", "SID", TemplateFile09.class );

        addMapping( "SSM_GRADE_E_M", "SID", GradePlan.class );
        addMapping( "SSM_GRADE_E_D", "SID", GradeContent.class );

        addMapping( "SSM_GRADE_R_M", "SID", GradePlanR.class );
        addMapping( "SSM_GRADE_R_D", "SID", GradeContentR.class );
        addMapping( "SSM_REVIEWER", "SID", Reviewer.class );

        addMapping( "SSM_TEMPLATE_VERSION", "SID", TemplateVersion.class );

        // 租户待办表
        addMapping( "SYS_TODO", "SID", Todo.class );
        addMapping( "SYS_DONE", "SID", Done.class );
    }
}
