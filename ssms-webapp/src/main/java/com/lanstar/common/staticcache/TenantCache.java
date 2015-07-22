/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantCache.java
 * 创建时间：2015-06-27
 * 创建用户：张铮彬
 */

package com.lanstar.common.staticcache;

import com.lanstar.identity.Tenant;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.model.system.tenant.Government;
import com.lanstar.model.system.tenant.Review;
import com.lanstar.plugin.staticcache.Cache;

import java.util.List;
import java.util.Map;

public class TenantCache extends Cache<Tenant> {
    public Tenant getValue( int tenantId, TenantType tenantType ) {
        for ( Tenant tenant : getValues() ) {
            if ( tenant.getTenantType().equals( tenantType ) && tenant.getTenantId() == tenantId ) return tenant;
        }
        return null;
    }

    @Override
    public String getName() {
        return "租户缓存";
    }

    @Override
    protected void load( Map<String, Tenant> pools ) {
        // 系统租户
        put( pools, Tenant.SYSTEM_TENANT );
        // 企业租户
        put( pools, Enterprise.dao.findAll() );
        // 评审租户
        put( pools, Review.dao.findAll() );
        // 政府租户
        put( pools, Government.dao.findAll() );
    }

    private void put( Map<String, Tenant> pools, List<? extends Tenant> tenants ) {
        for ( Tenant tenant : tenants ) put( pools, tenant );
    }

    private void put( Map<String, Tenant> pools, Tenant tenant ) {
        pools.put( tenant.getTenantCode(), tenant );
    }
}
