/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantContext.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.common.Asserts;
import com.lanstar.common.TreeNode;
import com.lanstar.model.system.Navgate;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.service.AttachFileService;
import com.lanstar.service.AttachTextService;
import com.lanstar.service.enterprise.EnterpriseService;
import com.lanstar.service.review.ReviewService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class TenantContextImpl implements TenantContext {
    private static final Map<TenantType, String> TENANT_TYPE_NAV_MAP = new HashMap<>();

    static {
        TENANT_TYPE_NAV_MAP.put( TenantType.ENTERPRISE, "企业端导航" );
        TENANT_TYPE_NAV_MAP.put( TenantType.REVIEW, "评审端导航" );
        TENANT_TYPE_NAV_MAP.put( TenantType.GOVERNMENT, "政府端导航" );
        TENANT_TYPE_NAV_MAP.put( TenantType.SYSTEM, "系统运维端导航" );
    }

    private final Tenant tenant;
    private Map<Class<?>, Object> valueMap = new ConcurrentHashMap<>();

    TenantContextImpl( Tenant tenant ) {
        this.tenant = tenant;
    }

    /** 根据租户信息获取租户上下文实例。 */
    public static TenantContext with( Tenant tenant ) {
        return new TenantContextImpl( tenant );
    }

    /** 根据值的类型从上下文中取出值 */
    @SuppressWarnings("unchecked")
    <T> T getValue( Class<T> clazz ) {
        if ( valueMap == null || valueMap.isEmpty() ) return null;
        Object value = valueMap.get( clazz );
//        if (value == null) throw new RuntimeException( "can not retrive instance of" + clazz.getName() );
        return (T) value;
    }

    /**
     * 判断当前上下文中是否有指定类型的值
     */
    <T> boolean hasValue( Class<T> clazz ) {
        return valueMap.containsKey( clazz );
    }

    /** 获取评审服务 */
    @Override
    public synchronized void initReviewService( Enterprise tenant, Profession profession ) {
        Asserts.notNull( tenant, "tenant can not be null" );
        Asserts.notNull( profession, "profession can not be null" );
//        if ( TenantType.REVIEW.equals( getTenantType() ) == false )
//            throw new RuntimeException( "tenant type must equals 'REVIEW'" );

        ReviewService service = getValue( ReviewService.class );
        if ( service == null ) {
            service = new ReviewService( this, new TenantContextImpl( tenant ) );
            this.setValue( service );
            service.getEnterpriseContext().getEnterpriseService().initProfessionService( profession );
        } else {
            if ( tenant.getTenantCode().equalsIgnoreCase( service.getEnterpriseContext().getTenantCode() ) == false ||
                    profession.getId()
                              .equals( service.getEnterpriseContext()
                                              .getEnterpriseService()
                                              .getProfessionService()
                                              .getId() ) ) {
                service.setEnterpriseContext( new TenantContextImpl( tenant ) );
                service.getEnterpriseContext().getEnterpriseService().initProfessionService( profession );
            }
        }
    }

    @Override
    public String getTenantName() {return tenant.getTenantName();}

    @Override
    public TenantType getTenantType() {return tenant.getTenantType();}

    @Override
    public int getTenantId() {return tenant.getTenantId();}

    @Override
    public String getTenantCode() {return tenant.getTenantCode();}

    @Override
    public String getTenantDbCode() {
        return tenant.getTenantDbCode();
    }

    @Override
    public synchronized DbPro getTenantDb() {
        return TenantKit.getTenantDb( tenant );
    }

    @Override
    public Tenant getTenant() {
        return tenant;
    }

    /**
     * 获取当前租户的系统导航
     */
    @Override
    public List<TreeNode> getSystemNavgate() {
        List<Navgate> list = Navgate.list();
        List<Map<String, Object>> navList = new ArrayList<>();
        for ( Navgate navgate : list ) {
            navList.add( ModelKit.toMap( navgate ) );
        }
        Collection<TreeNode> nodes = TreeNode.build( "0", navList, "SID", "R_SID", "C_NAME" );
        TreeNode node = findNode( nodes, getTenantType() );
        return node != null ? node.getChildren() : null;
    }

    /** 获取企业服务 */
    @Override
    public synchronized EnterpriseService getEnterpriseService() {
        if ( TenantType.ENTERPRISE.equals( getTenantType() ) == false )
            throw new RuntimeException( "tenant type must equals 'ENTERPRISE'" );
        EnterpriseService service = getValue( EnterpriseService.class );
        if ( service == null ) {
            service = new EnterpriseService( this );
            this.setValue( service );
        }
        return service;
    }

    /** 获取富文本服务 */
    @Override
    public synchronized AttachTextService getAttachTextService() {
        AttachTextService service = getValue( AttachTextService.class );
        if ( service == null ) {
            service = new AttachTextService( this.getTenant() );
            setValue( service );
        }
        return service;
    }

    /** 获取附件服务 */
    @Override
    public synchronized AttachFileService getAttachFileService() {
        AttachFileService service = getValue( AttachFileService.class );
        if ( service == null ) {
            service = new AttachFileService( this );
            setValue( service );
        }
        return service;
    }

    @Override
    public synchronized ReviewService getReviewService() {
        return this.getValue( ReviewService.class );
    }

    /** 将指定值与上下文绑定 */
    void setValue( Object value ) {
        if ( value == null ) return;
        valueMap.put( value.getClass(), value );
    }

    private TreeNode findNode( Collection<TreeNode> nodes, TenantType tenantType ) {
        String navName = TENANT_TYPE_NAV_MAP.get( tenantType );
        for ( TreeNode node : nodes ) {
            if ( navName.equalsIgnoreCase( node.getText() ) ) return node;
            TreeNode tmp = findNode( node.getChildren(), tenantType );
            if ( tmp != null ) return tmp;
        }
        return null;
    }
}
