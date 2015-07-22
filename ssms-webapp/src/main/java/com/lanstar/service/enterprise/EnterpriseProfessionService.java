/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseProfessionService.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.model.system.EnterpriseProfession;
import com.lanstar.model.system.Profession;

import java.util.List;

public class EnterpriseProfessionService {
    private final Enterprise enterprise;

    private EnterpriseProfessionService( Enterprise enterprise ) {
        this.enterprise = enterprise;
    }

    public void setProfession( int[] newProfessions ) {
        int[] tenantProfession = listTenantProfession();
        // 处理要添加的
        for ( int professionToAdd : newProfessions ) {
            if ( Ints.contains( tenantProfession, professionToAdd ) ) continue;
            addProfession( professionToAdd );
        }
        // 处理要删除的
        for ( int professionToRemove : tenantProfession ) {
            if ( Ints.contains( newProfessions, professionToRemove ) ) continue;
            removeProfession( professionToRemove );
        }
    }

    /**
     * 获取当前企业的所有专业ID
     *
     * @return 专业ID列表
     */
    public int[] listTenantProfession() {
        List<EnterpriseProfession> list = enterprise.listProfession();
        List<Integer> ids = Lists.transform( list, new Function<EnterpriseProfession, Integer>() {
            @Override
            public Integer apply( EnterpriseProfession input ) {
                return input.getProfessionId();
            }
        } );
        return Ints.toArray( ids );
    }

    /**
     * 添加企业的专业
     *
     * @param professionId 要添加的专业ID
     */
    public void addProfession( final int professionId ) {
        EnterpriseProfession.addProfession( enterprise, professionId );
    }

    /**
     * 移除当前企业的指定专业
     *
     * @param professionId 专业ID
     */
    public boolean removeProfession( int professionId ) {
        return EnterpriseProfession.getProfession( enterprise, professionId ).delete();
    }

    /**
     * 判断当前企业是否有指定专业
     *
     * @param professionId 专业ID
     *
     * @return 如果返回true则表示有该专业，否则返回false。
     */
    public boolean hasProfession( int professionId ) {
        return EnterpriseProfession.hasProfession( enterprise, professionId );
    }

    /**
     * 获取指定专业信息
     *
     * @param professionId 要获取信息的专业ID
     *
     * @return 专业信息
     */
    protected Profession pickProfession( int professionId ) {
        return EnterpriseProfession.getProfession( professionId );
    }

    public static EnterpriseProfessionService forEnterprise( Enterprise model ) {
        return new EnterpriseProfessionService( model );
    }
}
