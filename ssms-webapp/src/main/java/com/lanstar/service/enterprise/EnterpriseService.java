/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseService.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.identity.TenantContext;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.Profession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnterpriseService {
    private final TenantContext tenantContext;
    private Map<Class<?>, Object> valueMap = new ConcurrentHashMap<>();

    public EnterpriseService( TenantContext tenantContext ) {
        this.tenantContext = tenantContext;
    }

    public List<Profession> getProfessions() {
        TenantType tenantType = tenantContext.getTenantType();
        return Profession.list( tenantType, tenantContext.getTenantId() );
    }

    public ProfessionService getProfessionService() {
        return getValue( ProfessionService.class );
    }

    public void setProfessionService( Profession profession ) {
        setValue( new ProfessionService( profession ) );
    }

    /** 将指定值与上下文绑定 */
    void setValue( Object value ) {
        if ( value == null ) return;
        valueMap.put( value.getClass(), value );
    }

    /** 根据值的类型从上下文中取出值 */
    @SuppressWarnings("unchecked")
    <T> T getValue( Class<T> clazz ) {
        return (T) valueMap.get( clazz );
    }
}
