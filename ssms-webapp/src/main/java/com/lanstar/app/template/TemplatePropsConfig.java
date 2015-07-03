/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplatePropsConfig.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.app.template;

import com.lanstar.plugin.template.ModelType;
import com.lanstar.plugin.template.TemplateProps;

public class TemplatePropsConfig extends TemplateProps {
    public TemplatePropsConfig() {
        with( "01", "制度文件" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile01.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile01.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile01.class );

        with( "02", "通知文件" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile02.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile02.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile02.class );

        with( "03", "执行文件" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile03.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile03.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile03.class );

        with( "04", "培训执行文件" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile04.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile04.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile04.class );

        with( "06", "隐患汇总登记台帐" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile06.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile06.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile06.class );

        with( "07", "特种作业人员持证登记表" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile07.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile07.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile07.class );

        with( "08", "特种设备台账及定期检验记录" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile08.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile08.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile08.class );

        with( "09", "安全附件定期检查检验记录" )
                .putModel( ModelType.SYSTEM, com.lanstar.model.system.TemplateFile09.class )
                .putModel( ModelType.SYSTEM_ARCHIVE, com.lanstar.model.system.archive.TemplateFile09.class )
                .putModel( ModelType.TENANT, com.lanstar.model.tenant.TemplateFile09.class );
    }
}
