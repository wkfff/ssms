/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityKit.java
 * 创建时间：2015-07-22
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.app.Const;
import com.lanstar.common.Asserts;
import com.lanstar.core.Controller;
import com.lanstar.model.system.tenant.UserModel;

import javax.servlet.http.HttpServletRequest;

public class IdentityKit {
    public static Identity toIdentity( final UserModel<?> user ) {
        Asserts.notNull( user, "用户信息不能为空" );
        return new User( user );
    }

    /**
     * 将身份信息绑定到会话中
     */
    public static void bindIdentity( Controller controller, Identity identity ) {
        controller.setSessionAttr( Const.IDENTITY_KEY, new IdentityHolder( identity ) );
    }

    /**
     * 从会话中获取身份信息
     */
    public static IdentityHolder getIdentityHolder( Controller controller ) {
        return controller.getSessionAttr( Const.IDENTITY_KEY );
    }

    /**
     * 从会话中获取身份信息
     */
    public static IdentityHolder getIdentityHolder( HttpServletRequest request ) {
        return (IdentityHolder) request.getSession().getAttribute( Const.IDENTITY_KEY );
    }

    /**
     * 判断会话中是否存在身份信息
     */
    public static boolean hasIdentity( Controller controller ) {
        return getIdentityHolder( controller ) != null;
    }

    /**
     * 为身份标识附加租户信息，从而产生一个的身份标识。
     */
    public static Identity attachTenant( Identity identity, Tenant additional ) {
        return new VirtualIdentity( identity, additional );
    }

    /**
     * 切换默认的身份标识为指定租户
     */
    public static void switchIdentity( IdentityHolder holder, Tenant additional ) {
        holder.switchIdentity( attachTenant( holder.getMaster(), additional ) );
    }

    /**
     * 切换默认的身份标识为指定租户
     */
    public static void switchIdentity( Controller controller, Tenant additional ) {
        switchIdentity( getIdentityHolder( controller ), additional );
    }

    public static void runAs( Controller controller, Tenant additional, IdentityHolder.Action action, boolean reset ) {
        IdentityHolder holder = getIdentityHolder( controller );
        holder.bindAdditional( attachTenant( holder.getMaster(), additional ) );
        holder.switchIdentity();
        holder.runAs( action );
        if (reset) holder.resetDefaultIdentity();
    }
}

