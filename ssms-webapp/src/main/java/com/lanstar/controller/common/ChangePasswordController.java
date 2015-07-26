/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ChangePasswordController.java
 * 创建时间：2015-07-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.common;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.identity.User;

public class ChangePasswordController extends Controller {
    public void index() {}

    public void submit() {
        IdentityContext context = IdentityContextWrap.getIdentityContext( this );
        User user = (User) context.getIdentity();
        if ( user.getUser().ChangePassword( getPara( "old" ), getPara( "new" ) ) ) renderJson( "密码修改成功" );
        else renderJson( "密码修改失败" );
    }
}
