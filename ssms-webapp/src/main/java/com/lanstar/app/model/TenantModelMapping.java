/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantModelMapping.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.model;

import com.lanstar.model.tenant.*;

public class TenantModelMapping extends ActiveRecordMapping {
    public TenantModelMapping() {
        addMapping( "SSM_STDTMP_FOLDER", "UID", TemplateFolder.class );
        addMapping( "SSM_STDTMP_FILE", "UID", TemplateFile.class );
        addMapping( "SSM_STDTMP_FILE_01", "SID", TemplateFile01.class );
        addMapping( "SSM_STDTMP_FILE_01_ITEM", "SID", TemplateFile01Item.class );
        addMapping( "SSM_STDTMP_FILE_02", "SID", TemplateFile02.class );
        addMapping( "SSM_STDTMP_FILE_03", "SID", TemplateFile03.class );
        addMapping( "SSM_STDTMP_FILE_04", "SID", TemplateFile04.class );
        addMapping( "SSM_STDTMP_FILE_06", "SID", TemplateFile06.class );

        addMapping( "SSM_STDTMP_FILE_07", "SID", TemplateFile07.class );
        addMapping( "SSM_STDTMP_FILE_08", "SID", TemplateFile08.class );
        addMapping( "SSM_STDTMP_FILE_09", "SID", TemplateFile09.class );
        addMapping( "SSM_TEMPLATE_VERSION", "SID", TemplateVersion.class );
        addMapping( "SSM_TEMPLATE", "SID", Template.class );

//        addMapping( "SSM_GRADE_E_M", "SID", GradePlan.class );
//        addMapping( "SSM_GRADE_E_D", "SID", GradeContent.class );        
//        addMapping( "SSM_GRADE_R_M", "SID", GradePlanR.class );
//        addMapping( "SSM_GRADE_R_D", "SID", GradeContentR.class );
        addMapping( "SSM_REVIEWER", "SID", Reviewer.class );
        
        //20150618后修改
        addMapping( "SSM_GRADE_PLAN", "SID", GradePlan.class );                 //企业自评方案表
        addMapping( "SSM_GRADE_CONTENT", "SID", GradeContent.class );            //企业自评内容表
        
        addMapping( "SSM_REVIEW_PLAN", "SID", ReviewPlan.class );               //评审方案表
        addMapping( "SSM_REVIEW_MEMBERS", "SID", ReviewMember.class );          //评审成员表
        addMapping( "SSM_REVIEW_CONTENT", "SID", ReviewContent.class );         //评审内容表
        addMapping( "SSM_REVIEW_CERT", "SID", ReviewCert.class );               //评审证书表
        
        addMapping( "SSM_RESULT_PLAN", "SID", ReviewResultPlan.class );         //评审方案结果表
        addMapping( "SSM_RESULT_MEMBERS", "SID", ReviewResultMember.class );    //评审成员结果表
        addMapping( "SSM_RESULT_CONTENT", "SID", ReviewResultContent.class );   //评审内容结果表
        addMapping( "SSM_RESULT_REPORT", "SID", ReviewResultReport.class );     //评审报告结果表
        addMapping( "SSM_RESULT_CERT", "SID", ReviewResultCert.class );         //评审证书结果表
    }
}
