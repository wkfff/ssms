/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityContext.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.app.Const;
import com.lanstar.common.TreeNode;
import com.lanstar.core.Controller;
import com.lanstar.model.Navgate;
import com.lanstar.model.Profession;
import com.lanstar.plugin.activerecord.ModelKit;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class IdentityContext {
    private static final Map<TenantType, String> IDENTITY_TYPE_NAV_MAP = new HashMap<>();

    static {
        IDENTITY_TYPE_NAV_MAP.put( TenantType.ENTERPRISE, "企业端导航" );
        IDENTITY_TYPE_NAV_MAP.put( TenantType.REVIEW, "评审端导航" );
        IDENTITY_TYPE_NAV_MAP.put( TenantType.GOVERNMENT, "政府端导航" );
        IDENTITY_TYPE_NAV_MAP.put( TenantType.SYSTEM, "系统运维端导航" );
    }

    private final Identity identity;

    private IdentityContext( Identity identity ) {
        this.identity = identity;
    }

    public static IdentityContext getIdentityContext( Controller controller ) {
        return controller.getSessionAttr( Const.IDENTITY_KEY );
    }

    public static void bindIdentityContext( Controller controller, Identity identity ) {
        controller.setSessionAttr( Const.IDENTITY_KEY, new IdentityContext( identity ) );
    }

    public static boolean hasIdentityContext( Controller controller ) {
        return getIdentityContext( controller ) != null;
    }

    public Identity getIdentity() {
        return identity;
    }

    public int getId() {return identity.getId();}

    public String getTenantName() {return identity.getTenantName();}

    public TenantType getTenantType() {return identity.getTenantType();}

    public int getTenantId() {return identity.getTenantId();}

    public String getTenantCode() {return identity.getTenantCode();}

    public String getName() {return identity.getName();}

    public String getCode() {return identity.getCode();}

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

    private TreeNode findNode( Collection<TreeNode> nodes, TenantType tenantType ) {
        String navName = IDENTITY_TYPE_NAV_MAP.get( tenantType );
        for ( TreeNode node : nodes ) {
            if ( navName.equalsIgnoreCase( node.getText() ) ) return node;
            TreeNode tmp = findNode( node.getChildren(), tenantType );
            if ( tmp != null ) return tmp;
        }
        return null;
    }

    private Map<Class<?>, Object> valueMap = new ConcurrentHashMap<>();

    /** 将指定值与上下文绑定 */
    public void setValue( Object value ) {
        if ( value == null ) return;
        valueMap.put( value.getClass(), value );
    }

    /** 根据值的类型从上下文中取出值 */
    @SuppressWarnings("unchecked")
    public <T> T getValue( Class<T> clazz ) {
        return (T) valueMap.get( clazz );
    }

    /**
     * 判断当前上下文中是否有指定类型的值
     */
    public <T> boolean hasValue( Class<T> clazz ) {
        return valueMap.containsKey( clazz );
    }

    public List<Profession> getProfessions() {
        TenantType tenantType = getTenantType();
        return Profession.list( tenantType, getTenantId() );
    }

    public void setProfession( Profession profession ) {
        setValue( profession );
    }

    public String getTenantDbCode() {
        if ( getTenantType() == TenantType.SYSTEM ) return null;
        // TODO: 租户库与用户信息绑定
        return "tenant01";
    }
}
