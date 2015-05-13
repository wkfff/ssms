/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseProfessionService.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.identity.Tenant;
import com.lanstar.core.handle.identity.TenantType;
import com.lanstar.db.DS;
import com.lanstar.db.DbContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.service.IdentityContext;
import com.lanstar.service.TenantContext;
import com.lanstar.service.TenantService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class EnterpriseProfessionService extends TenantService {
    private final TenantContext target;

    EnterpriseProfessionService( TenantContext target, IdentityContext operator ) {
        super( operator );
        this.target = target;
    }

    @Override
    public void close() throws Exception {
        super.close();
        target.close();
    }

    public static EnterpriseProfessionService forTenant( String tenantCode, IdentityContext operator ) {
        final JdbcRecord record = operator.withTable( "SYS_TENANT_E" ).where( "C_CODE=?", tenantCode )
                                          .query();

        Tenant tenant = new Tenant() {
            @Override
            public int getTenantId() {
                return (int) record.get( "SID" );
            }

            @Override
            public String getTenantName() {
                return record.getString( "C_NAME" );
            }

            protected String getTenantDbCode() {
                // TODO: 要调整为根据当前用户的租户信息来获取
                return "tenant01";
            }

            @Override
            public DbContext getDbContext() throws SQLException {
                return DS.getDbContext( getTenantDbCode() );
            }

            @Override
            public TenantType getTenantType() {
                return TenantType.ENTERPRISE;
            }
        };
        return new EnterpriseProfessionService( new TenantContext( tenant ), operator );
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
        JdbcRecordSet records = getIdentityContext().withTable( "SYS_TENANT_E_PROFESSION" )
                                                    .where( "R_TENANT=? AND S_TENANT=? AND P_TENANT=?",
                                                            target.getTenantId(),
                                                            target.getTenantName(),
                                                            target.getTenantType().getName() ).queryList();
        List<Integer> professions = new ArrayList<>();
        for ( JdbcRecord record : records ) {
            professions.add( (Integer) record.get( "SID" ) );
        }
        return professions;
    }

    /**
     * 添加企业的专业
     *
     * @param professionId 要添加的专业ID
     */
    public void addProfession( final int professionId ) {
        // 已经有专业了，则不做处理
        if ( hasProfession( professionId ) ) return;
        final JdbcRecord profession = pickProfession( professionId );

        getIdentityContext().transaction( new HandlerDbContext.IAtom() {
            @Override
            public boolean execute( HandlerDbContext dbContext ) {
                // 1. 设置企业与专业的关联关系
                ARTable table = getIdentityContext().withTable( "SYS_TENANT_E_PROFESSION" );
                IdentityContext.injection( table, getIdentityContext().getIdentity(), false );
                table.value( "P_PROFESSION", professionId )
                     .value( "S_PROFESSION", profession.getString( "C_NAME" ) )
                     .value( "R_TENANT", target.getTenantId() )
                     .value( "S_TENANT", target.getTenantName() )
                     .value( "P_TENANT", target.getTenantType().getName() )
                     .save();

                return target.transaction( new HandlerDbContext.IAtom() {
                    @Override
                    public boolean execute( HandlerDbContext dbContext ) {
                        try {
                            // 克隆模板
                            ProfessionTemplateService.forProfession( professionId, getIdentityContext() )
                                                     .cloneTo( target );
                            return true;
                        } catch ( Exception e ) {
                            return false;
                        }
                    }
                } );
            }
        } );
    }

    /**
     * 移除当前企业的指定专业
     *
     * @param professionId 专业ID
     */
    public void removeProfession( int professionId ) {
        getIdentityContext().withTable( "SYS_TENANT_E_PROFESSION" )
                            .where( "R_TENANT=? AND S_TENANT=? AND P_TENANT=? AND P_PROFESSION=?",
                                    target.getTenantId(),
                                    target.getTenantName(),
                                    target.getTenantType().getName(),
                                    professionId ).delete();
    }

    /**
     * 判断当前企业是否有指定专业
     *
     * @param professionId 专业ID
     *
     * @return 如果返回true则表示有该专业，否则返回false。
     */
    public boolean hasProfession( int professionId ) {
        return getIdentityContext().withTable( "SYS_TENANT_E_PROFESSION" )
                                   .where( "R_TENANT=? AND P_PROFESSION=?", target.getTenantId(), professionId )
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
        return getIdentityContext().withTable( "SYS_PROFESSION" ).where( "SID=?", professionId ).query();
    }
}
