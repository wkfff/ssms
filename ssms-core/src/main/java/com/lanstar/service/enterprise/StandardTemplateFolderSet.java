/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StandardTemplateFolderSet.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.service.OperateContext;

/**
 * 达标体系目录集合，作为克隆对象存在。
 */
class StandardTemplateFolderSet implements IClonable<OperateContext> {
    private final ClonableList<OperateContext> list = new ClonableList<>();
    private ProfessionTemplateService service;

    public StandardTemplateFolderSet( ProfessionTemplateService service ) {
        this.service = service;
    }

    @Override
    public void cloneTo( OperateContext target ) {
        // 模板目录树
        JdbcRecordSet records = service.getOperateContext().getDbContext().getDBSession()
                                       .query( "SELECT B.* FROM SYS_PROFESSION A\n"
                                               + "INNER JOIN `SYS_STDTMP_FOLDER` B ON A.`R_TEMPLATE` = B.`R_TEMPLATE`\n"
                                               + "WHERE A.SID = ?", new Object[] { service.getProfessionId() } );

        for ( JdbcRecord record : records ) list.add( new StandardTemplateFolder( service, record ) );

        list.cloneTo( target );
    }
}
