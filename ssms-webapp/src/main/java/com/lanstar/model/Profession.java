/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Profession.java
 * 创建时间：2015-05-14
 * 创建用户：张铮彬
 */

package com.lanstar.model;

import com.lanstar.core.handle.db.impl.SystemDbContext;
import com.lanstar.core.handle.identity.IdentityContext;
import com.lanstar.db.JdbcRecord;

public class Profession {
    private final IdentityContext identityContxt;
    private final JdbcRecord record;
    private final int sid;
    private Template template;
    private final String name;

    public Profession( IdentityContext identityContxt, JdbcRecord record ) throws Exception {
        this.identityContxt = identityContxt;
        this.record = record;
        this.sid = (int) record.get( "SID" );
        this.name = record.getString( "C_NAME" );

        try ( SystemDbContext dbContext = new SystemDbContext() ) {
            JdbcRecord templateRecord = dbContext.first( "SELECT D.* from SYS_TENANT_E A\n"
                    + "inner join SYS_TENANT_E_PROFESSION B on A.SID = B.R_TENANT\n"
                    + "inner join SYS_PROFESSION C on B.P_PROFESSION = C.SID\n"
                    + "INNER join SYS_TEMPLATE D on C.R_TEMPLATE = D.SID\n"
                    + "where A.SID = ? AND C.SID = ?", identityContxt.getTenantId(), this.sid );
            if ( templateRecord != null ) this.template = new Template( templateRecord );
        }
    }

    public int getProfessionId() {
        return this.sid;
    }

    public String getProfessionName() {
        return this.name;
    }

    public Template getTemplate() {
        return this.template;
    }
}
