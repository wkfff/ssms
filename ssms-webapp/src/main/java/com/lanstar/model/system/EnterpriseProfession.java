/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseProfession.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

public class EnterpriseProfession extends Model<EnterpriseProfession> {
    public static final EnterpriseProfession dao = new EnterpriseProfession();

    public static List<EnterpriseProfession> listProfession( int enterpriseId ) {
        return dao.find( SqlKit.sql( "system.enterpriseProfession.listProfession" ), enterpriseId );
    }

    public Integer getProfessionId() {
        return Integer.valueOf( getStr( "P_PROFESSION" ) );
    }

    public static boolean hasProfession( Enterprise enterprise, int professionId ) {
        String sql = SqlKit.sql( "system.enterpriseProfession.getProfession" );
        return dao.findFirst( sql, enterprise.getId(), professionId ) != null;
    }

    public static Profession getProfession( int professionId ) {
        return Profession.dao.findById( professionId );
    }

    public static EnterpriseProfession getProfession( Enterprise enterprise, int professionId ) {
        String sql = SqlKit.sql( "system.enterpriseProfession.getProfession" );
        return dao.findFirst( sql, enterprise.getId(), professionId );
    }

    public static boolean addProfession( Enterprise enterprise, int professionId ) {
        if ( hasProfession( enterprise, professionId ) ) return false;
        Profession profession = getProfession( professionId );

        EnterpriseProfession model = new EnterpriseProfession();
        model.setProfession( profession );
        model.setEnterprise( enterprise );

        return model.save();
    }

    private void setEnterprise( Enterprise enterprise ) {
        set( "R_TENANT", enterprise.getId() );
        set( "S_TENANT", enterprise.getId() );
        set( "P_TENANT", enterprise.getTenantType().getName() );
    }

    private void setProfession( Profession profession ) {
        set( "P_PROFESSION", profession.getId() );
        set( "S_PROFESSION", profession.getName() );
    }
}
