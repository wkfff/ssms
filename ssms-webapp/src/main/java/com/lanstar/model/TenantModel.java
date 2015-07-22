/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantModel.java
 * 创建时间：2015-06-27
 * 创建用户：张铮彬
 */

package com.lanstar.model;

import com.lanstar.common.staticcache.TenantCache;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantType;
import com.lanstar.plugin.activerecord.*;
import com.lanstar.plugin.staticcache.CacheManager;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public abstract class TenantModel<T extends TenantModel<T>> extends ModelExt<T> {
    public Tenant getTenant() {
        return CacheManager.me().getCache( TenantCache.class ).getValue( getTenantId(), getTenantType() );
    }

    @SuppressWarnings("unchecked")
    protected Set<String> modifyFlag = new CaseInsensitiveContainerFactory.CaseInsensitiveSet();

    private Config getConfig() {
        return DbKit.getConfig( getClass() );
    }

    public boolean isModified() {
        return modifyFlag.isEmpty() == false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T set( String attr, Object value ) {
        Object oldValue = get( attr );
        if ( Objects.equals( oldValue, value ) ) return (T) this;
        T set = super.set( attr, value );
        modifyFlag.add( attr );
        return set;
    }

    /**
     * 设置所属租户
     */
    public void setTenant( Tenant target ) {
        set( "R_TENANT", target.getTenantId() );
        set( "S_TENANT", target.getTenantName() );
        set( "P_TENANT", target.getTenantType().getName() );
    }

    public Identity getOperator() {
        return new Operator( getTenant() );
    }

    public void setOperator( Identity operator ) {
        final Table table = TableMapping.me().getTable( getClass() );
        Object id = get( table.getPrimaryKey() );
        if ( id == null ) { // for insert
            set( "R_CREATE", operator.getId() );
            set( "S_CREATE", operator.getName() );
            set( "T_CREATE", new Date() );
        }
        if ( id == null || isModified() ) { // for update
            set( "R_UPDATE", operator.getId() );
            set( "S_UPDATE", operator.getName() );
            set( "T_UPDATE", new Date() );
        }
    }

    private int getTenantId() {
        return getInt( "R_TENANT" );
    }

    private TenantType getTenantType() {
        return TenantType.getValue( getStr( "P_TENANT" ) );
    }

    private class Operator implements Identity {
        private final Tenant tenant;

        public Operator( Tenant tenant ) {this.tenant = tenant;}

        @Override
        public int getId() {
            return get( "R_UPDATE" );
        }

        @Override
        public String getName() {
            return get( "S_UPDATE" );
        }

        @Override
        public String getCode() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Tenant getTenant() {
            return tenant;
        }

        @Override
        public int getTenantId() {return tenant.getTenantId();}

        @Override
        public String getTenantName() {return tenant.getTenantName();}

        @Override
        public String getTenantCode() {return tenant.getTenantCode();}

        @Override
        public TenantType getTenantType() {return tenant.getTenantType();}
    }
}
