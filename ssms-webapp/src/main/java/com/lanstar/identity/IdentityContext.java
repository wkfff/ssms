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

import javax.servlet.http.HttpServletRequest;

public class IdentityContext extends TenantContext {

    private final Identity identity;

    private IdentityContext( Identity identity ) {
        super( identity );
        this.identity = identity;
    }

    public static IdentityContext getIdentityContext( Controller controller ) {
        return controller.getSessionAttr( Const.IDENTITY_KEY );
    }

    public static IdentityContext getIdentityContext( HttpServletRequest request ) {
        Object context = request.getSession().getAttribute( Const.IDENTITY_KEY );
        if ( context instanceof IdentityContext ) return (IdentityContext) context;
        return null;
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

    public String getName() {return identity.getName();}

    public String getCode() {return identity.getCode();}
}
