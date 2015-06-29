/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemModelMapping.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.model;

import com.lanstar.model.system.*;

public class SystemModelMapping extends ActiveRecordMapping {
    public SystemModelMapping() {
        addMapping( "SYS_TENANT_E_USER", "SID", EnterpriseUser.class );
        addMapping( "SYS_TENANT_E", "SID", Enterprise.class );
        addMapping( "SYS_TENANT_E_PROFESSION", "SID", EnterpriseProfession.class );

        addMapping( "SYS_TENANT_G_USER", "SID", GovernmentUser.class );
        addMapping( "SYS_TENANT_G", "SID", Government.class );

        addMapping( "SYS_TENANT_R_USER", "SID", ReviewUser.class );
        addMapping( "SYS_TENANT_R", "SID", Review.class );

        addMapping( "TENANT_USER", TenantUser.class );
        addMapping( "SYS_NAV", "SID", Navgate.class );
        addMapping( "SYS_PROFESSION", "SID", Profession.class );
        addMapping( "SYS_TEMPLATE", "SID", Template.class );

        addMapping( "SYS_ATTACH_TEXT", "SID", AttachText.class );
        addMapping( "SYS_ATTACH_FILE", "SID", AttachFile.class );

        addMapping( "SYS_STDTMP_FOLDER", "SID", TemplateFolder.class );
        addMapping( "SYS_STDTMP_FILE", "SID", TemplateFile.class );
        addMapping( "SYS_GRADE_STD", "SID", TemplateGrade.class );
        //addMapping( "SYS_GRADE_REPORT_TMP", "SID", TemplateRep.class );
        addMapping( "SYS_REPORT_TEMPLATE", "SID", TemplateRep.class );
        addMapping( "SYS_STDTMP_FILE_01", "SID", TemplateFile01.class );
        addMapping( "SYS_STDTMP_FILE_02", "SID", TemplateFile02.class );
        addMapping( "SYS_STDTMP_FILE_03", "SID", TemplateFile03.class );
        addMapping( "SYS_STDTMP_FILE_04", "SID", TemplateFile04.class );
        addMapping( "SYS_STDTMP_FILE_06", "SID", TemplateFile06.class );
        
        addMapping( "SYS_STDTMP_FILE_07", "SID", TemplateFile07.class );
        addMapping( "SYS_STDTMP_FILE_08", "SID", TemplateFile08.class );
        addMapping( "SYS_STDTMP_FILE_09", "SID", TemplateFile09.class );

        addMapping( "SYS_PARA_MULTI", "SID", MultiPara.class );
        addMapping( "SYS_INDUSTRY", "SID", Industry.class );
        addMapping( "SYS_PARA_AREA", "SID", AreaPara.class );
        
        addMapping( "SSM_NOTICE", "SID", Notice.class );
        addMapping( "SYS_TODO", "SID", Todo.class );
        addMapping( "SYS_DONE", "SID", Done.class );
        addMapping( "SSM_KNOWLEDGE_SORT", "SID", KnowledgeSort.class );
        addMapping( "SSM_KNOWLEDGE_FILE", "SID", KnowledgeFile.class );
    }
}
