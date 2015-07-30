/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityHolder.java
 * 创建时间：2015-07-23
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.common.Asserts;

public class IdentityHolder {
    private Identity master;
    private Identity additional;

    private Identity defaultIdentity;

    IdentityHolder( Identity master ) {
        this.master = master;
        defaultIdentity = this.master;
    }

    /**
     * 获取主身份标识
     */
    public Identity getMaster() {
        return master;
    }

    /**
     * 获取附加的身份标识
     */
    public Identity getAdditional() {
        return additional;
    }

    /**
     * 获取默认的身份标识
     */
    public Identity getDefaultIdentity() {
        return defaultIdentity;
    }

    /**
     * 绑定附加的身份标识
     */
    public IdentityHolder bindAdditional( Identity additional ) {
        Asserts.notNull( additional, "additional不能为空!" );
        this.additional = additional;
        return this;
    }

    /**
     * 清除附加的身份标识
     */
    public IdentityHolder clearAdditional() {
        this.additional = null;
        return this;
    }

    /**
     * 切换当前默认的身份标识为给定的身份标识对象
     *
     * @param additional 该参数将作为附加的身份标识
     */
    public IdentityHolder switchIdentity( Identity additional ) {
        bindAdditional( additional );
        switchIdentity();
        return this;
    }

    /**
     * 切换当前默认的身份标识为第二身份标识，使用该方法前请先进行绑定身份操作。
     */
    public IdentityHolder switchIdentity() {
        Asserts.notNull( additional, "请先绑定附加的身份标识!" );
        this.defaultIdentity = additional;
        return this;
    }

    /**
     * 重置默认身份标识为主身份标识。
     */
    public IdentityHolder resetDefaultIdentity() {
        this.defaultIdentity = master;

        return this;
    }

    /**
     * 以默认的身份执行操作
     */
    public IdentityHolder runAs( Action action ) {
        Asserts.notNull( action, "action不能为空!" );
        action.invoke( this.defaultIdentity );

        return this;
    }

    public interface Action {
        void invoke( Identity identity );
    }
}
