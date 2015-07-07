/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HomeTodo.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.identity.IdentityContext;
import com.lanstar.model.tenant.TemplateFolder;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.RecordKit;
import com.lanstar.service.enterprise.EnterpriseService;
import com.lanstar.service.enterprise.ProfessionService;

import java.util.List;
import java.util.Map;

public class HomeTodo {
    private final IdentityContext identityContext;
    private final EnterpriseService enterpriseService;
    private final ProfessionService professionService;
    private final DbPro db;

    public HomeTodo( IdentityContext identityContext ) {
        this.identityContext = identityContext;
        enterpriseService = identityContext.getEnterpriseService();
        professionService = enterpriseService.getProfessionService();
        db = identityContext.getTenantDb();
    }

    public int getFileCount() {
        TemplateFolder folder = professionService.getTenantTemplateFolder();
        Integer count = folder.getFileCount();
        return count == null ? 0 : count;
    }

    public long getNotCreateFileCount() {
        DbPro db = identityContext.getTenantDb();
        return db.queryLong( "select COUNT(*) from ssm_stdtmp_file where N_COUNT=0 AND R_TEMPLATE=? AND P_PROFESSION=? AND R_TENANT=? AND P_TENANT='E'",
                professionService.getSystemTemplate().getId(),
                professionService.getId(),
                identityContext.getTenantId() );
    }

    public List<Map<String, Object>> getCreateTodo() {
        List<Record> todolist = db.find( "select * from ssm_stdtmp_file where N_COUNT=0 AND R_TEMPLATE=? AND P_PROFESSION=? AND R_TENANT=? AND P_TENANT='E' limit 10",
                professionService.getSystemTemplate().getId(),
                professionService.getId(),
                identityContext.getTenantId() );
        return RecordKit.toMap( todolist );
    }
}
