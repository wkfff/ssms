/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ProfessionService.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.IAtom;

import java.sql.SQLException;

public class ProfessionService {
    private final Profession profession;

    public ProfessionService( Profession profession ) {
        this.profession = profession;
    }

    /**
     * 同步专业模板到指定租户上下文中
     */
    public void sync( final IdentityContext identityContext ) {
        Db.tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                TemplateSyncProcessor.process(getSystemTemplate(), identityContext);
                return true;
            }
        } );
    }

    public Template getSystemTemplate() {
        return Template.getByProfession( profession.getId() );
    }

    public String getName() {return profession.getName();}

    public Integer getId() {return profession.getId();}
}
