/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PswChangeController.java
 * 创建时间：上午11:00:16
 * 创建用户：苏志亮
 */
package com.lanstar.controller.enterprise;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.EnterpriseUser;

/**
 * @author Administrator
 *
 */
public class PswChangeController extends Controller {
    public void psw() {

    }

    public void updatePSW() {
        int id=IdentityContext.getIdentityContext( this ).getId();
        String oldPwd = getPara( "oldPwd" );
        String newPwd = getPara( "newPwd" );
        EnterpriseUser user = EnterpriseUser.getUser( String.valueOf( id ), oldPwd );
        if ( user != null ) {
            user.setPassword( newPwd );
            user.update();
            renderJson( true );
        } else {
            renderJson( false );
        }
    }

}
