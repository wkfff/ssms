/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseProfessionService.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.core.handle.db.impl.TenantDbContext;
import com.lanstar.core.handle.identity.impl.EnterpriseIdentity;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.service.OperateContext;
import com.lanstar.service.TenantService;

import java.util.ArrayList;
import java.util.List;

public final class EnterpriseProfessionService extends TenantService {
    private final OperateContext target;

    EnterpriseProfessionService( OperateContext target, OperateContext operator ) {
        super( operator );
        this.target = target;
    }

    @Override
    public void close() throws Exception {
        super.close();
        target.close();
    }

    public static EnterpriseProfessionService forTenant( String tenantCode, OperateContext operator ) {
        final JdbcRecord record = operator.withTable( "SYS_TENANT_E" ).where( "C_CODE=?", tenantCode )
                                          .query();

        EnterpriseIdentity identity = new EnterpriseIdentity( record );
        return new EnterpriseProfessionService( new OperateContext( identity, new TenantDbContext( identity ) ), operator );
    }

    /**
     * 设置当前企业的专业信息
     *
     * @param professions 专业ID列表
     */
    public void setProfession( List<Integer> professions ) {
        List<Integer> professionSet = listTenantProfession();

        // 处理要添加的
        for ( Integer profession : professions ) {
            if ( professionSet.contains( profession ) ) continue;
            addProfession( profession );
        }
        // 处理要删除的
        for ( Integer profession : professionSet ) {
            if ( professions.contains( profession ) ) continue;
            removeProfession( profession );
        }
    }

    /**
     * 获取当前企业的所有专业ID
     *
     * @return 专业ID列表
     */
    public List<Integer> listTenantProfession() {
        JdbcRecordSet records = getOperateContext().withTable( "SYS_TENANT_E_PROFESSION" )
                                                   .where( "R_TENANT=? AND S_TENANT=? AND P_TENANT=?",
                                                           getOperateContext().getTenantId(),
                                                           getOperateContext().getTenantName(),
                                                           getOperateContext().getTenantType().getName() ).queryList();
        List<Integer> professions = new ArrayList<>();
        for ( JdbcRecord record : records ) {
            professions.add( (Integer) record.get( "SID" ) );
        }
        return professions;
    }

    /**
     * 移除当前企业的指定专业
     *
     * @param professionId 专业ID
     */
    public void removeProfession( int professionId ) {
        getOperateContext().withTable( "SYS_TENANT_E_PROFESSION" )
                           .where( "R_TENANT=? AND S_TENANT=? AND P_TENANT=? AND P_PROFESSION=?",
                                   getOperateContext().getTenantId(),
                                   getOperateContext().getTenantName(),
                                   getOperateContext().getTenantType().getName(),
                                   professionId ).delete();
    }

    /**
     * 添加企业的专业
     *
     * @param professionId 要添加的专业ID
     */
    public void addProfession( int professionId ) {
        // 已经有专业了，则不做处理
        if ( hasProfession( professionId ) ) return;
        JdbcRecord profession = pickProfession( professionId );

        // 1. 设置企业与专业的关联关系
        getOperateContext().withTable( "SYS_TENANT_E_PROFESSION" )
                           .value( "P_PROFESSION", professionId )
                           .value( "S_PROFESSION", profession.getString( "C_NAME" ) )
                           .value( "R_CREATE", getOperateContext().getId() )
                           .value( "S_CREATE", getOperateContext().getName() )
                           .value( "T_CREATE", "@now()" )
                           .value( "R_UPDATE", getOperateContext().getTenantId() )
                           .value( "S_UPDATE", getOperateContext().getName() )
                           .value( "R_TENANT", getOperateContext().getTenantId() )
                           .value( "S_TENANT", getOperateContext().getTenantName() )
                           .value( "P_TENANT", getOperateContext().getTenantType().getName() )
                           .save();

        // 克隆模板
        ProfessionTemplateService.forProfession( professionId, getOperateContext() ).cloneTo( target );
    }

    /**
     * 判断当前企业是否有指定专业
     *
     * @param professionId 专业ID
     *
     * @return 如果返回true则表示有该专业，否则返回false。
     */
    public boolean hasProfession( int professionId ) {
        return getOperateContext().withTable( "SYS_TENANT_E_PROFESSION" )
                                  .where( "R_TENANT=? AND P_PROFESSION=?", getOperateContext().getId(), professionId )
                                  .query() != null;
    }

    /**
     * 获取指定专业信息
     *
     * @param professionId 要获取信息的专业ID
     *
     * @return 专业信息
     */
    protected JdbcRecord pickProfession( int professionId ) {
        return getOperateContext().withTable( "SYS_PROFESSION" ).where( "SID=?", professionId ).query();
    }
}
