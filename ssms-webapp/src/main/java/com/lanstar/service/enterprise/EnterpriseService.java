/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseService.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.controller.enterprise.EnterpriseGradeState;
import com.lanstar.identity.TenantContext;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.Profession;
import com.lanstar.model.tenant.GradePlan;

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
        setValue( new ProfessionService( profession, tenantContext ) );
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
    
    /**
     * 获取最新自评方案编号
     */
    public int getPlanId(int eid,int pro){
        String sql = "select sid from SSM_GRADE_E_M SSM_GRADE_PLAN where R_TENANT=? and P_PROFESSION=? and n_state=? order by t_update desc limit 1";
        GradePlan model = GradePlan.dao.findFirst( sql,eid,pro,EnterpriseGradeState.END.getValue() );
        return model==null?0:model.getId();
    }
}
