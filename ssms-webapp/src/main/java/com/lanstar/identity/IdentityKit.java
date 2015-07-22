/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityKit.java
 * 创建时间：2015-07-22
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.common.Asserts;
import com.lanstar.model.system.tenant.UserModel;

public class IdentityKit {
    public static Identity toIdentity( final UserModel<?> user ) {
        Asserts.notNull( user, "用户信息不能为空" );
        return new User( user );
    }
}

