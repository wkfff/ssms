/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityContext.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.app.Const;
import com.lanstar.core.Controller;

public class IdentityContext {
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

    public static boolean hasIdentity( Controller controller ) {
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
}
