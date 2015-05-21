/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HomeController.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.ClearInterceptor;
import com.lanstar.core.aop.ClearLayer;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.TenantUser;
import com.lanstar.render.CaptchaRender;

public class HomeController extends Controller {

    public void index() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        setAttr( "nav", identityContext.getSystemNavgate() );

        forwardAction( "/" + identityContext.getTenantType().getName().toLowerCase() );
    }

    @ClearInterceptor(ClearLayer.ALL)
    public void login() {
        String vCode = getPara( "yzm" );
        String username = getPara( "username" );
        String password = getPara( "password" );
        if ( !StrKit.isBlank( username, password ) ) {
            // 解析用户名格式
            String[] strings = StrKit.split( username, "@" );
            // 验证验证码
            if ( CaptchaRender.validate( this, vCode ) == false ) {
                setAttr( "state", "error" ).setAttr( "msg", "验证码不正确。" );
            } else if ( strings.length != 2 ) {
                setAttr( "state", "error" ).setAttr( "msg", "用户名格式不正确。" );
            } else {
                username = strings[0];
                String tenant = strings[1];
                // 登录
                if ( login( this, tenant, username, password ) ) setAttr( "state", "success" );
                else setAttr( "state", "error" ).setAttr( "msg", "用户名或密码不正确" );
            }
            renderJson();
        }
    }

    public void logout() {
        if ( IdentityContext.hasIdentityContext( this ) ) {
            // 直接将当前会话无效化掉
            getSession().invalidate();
        }
        redirect( "/login" );
    }

    @ClearInterceptor(ClearLayer.ALL)
    public void vcode() {
        render( CaptchaRender.getInstance() );
    }

    public static boolean login( Controller controller, String tenentCode, String username, String password ) {
        TenantUser tenantUser = TenantUser.getUser( tenentCode, username, password );
        if ( tenantUser != null ) {
            IdentityContext.bindIdentityContext( controller, tenantUser );
            return true;
        }
        return false;
    }
}
