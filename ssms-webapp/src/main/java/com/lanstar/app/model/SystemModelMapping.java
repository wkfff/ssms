/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemModelMapping.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.model;

import com.lanstar.model.system.*;

public class SystemModelMapping extends ActiveRecordMapping{
    public SystemModelMapping() {
        addMapping( "SYS_TENANT_E_USER", "SID", EnterpriseUser.class );
        addMapping( "SYS_TENANT_E", "SID", Enterprise.class );
        addMapping( "TENANT_USER", TenantUser.class );
        addMapping( "SYS_NAV", "SID", Navgate.class );
        addMapping( "SYS_PROFESSION", "SID", Profession.class );
        addMapping( "SYS_TEMPLATE", "SID", Template.class );
        addMapping( "SYS_ATTACH_TEXT", "SID", AttachText.class );

        addMapping( "SYS_STDTMP_FILE", "SID", TemplateFile.class );
        addMapping( "SYS_STDTMP_FILE_01", "SID", TemplateFile01.class );
        addMapping( "SYS_STDTMP_FILE_02", "SID", TemplateFile02.class );
        addMapping( "SYS_STDTMP_FILE_03", "SID", TemplateFile03.class );
        addMapping( "SYS_STDTMP_FILE_04", "SID", TemplateFile04.class );
        addMapping( "SYS_STDTMP_FILE_05", "SID", TemplateFile05.class );
    }
}
