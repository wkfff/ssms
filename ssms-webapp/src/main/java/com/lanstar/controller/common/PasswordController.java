/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PasswordController.java
 * 创建时间：2015年7月14日 上午11:02:45
 * 创建用户：林峰
 */
package com.lanstar.controller.common;

import com.lanstar.common.kit.StrKit;
import com.lanstar.common.log.Logger;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.ClearInterceptor;
import com.lanstar.core.aop.ClearLayer;
import com.lanstar.render.CaptchaRender;
import com.lanstar.service.PasswordResetService;

import javax.servlet.http.HttpServletResponse;

/**
 * 密码
 */
@ClearInterceptor(ClearLayer.ALL)
public class PasswordController extends Controller {
    private final Logger log = Logger.getLogger( this.getClass() );

    /**
     * 重置密码发送邮件页面
     */
    public void index() {}

    /**
     * 发送重置密码邮件
     */
    public void send() {
        String email = getPara( "email" );
        String verify = getPara( "verify" );
        //验证码校验
        if ( CaptchaRender.validate( this, verify ) == false ) {
            setAttr( "state", "error" ).setAttr( "msg", "验证码不正确。" );
            this.renderJson();
            return;
        }
        if ( StrKit.isBlank( email ) ) {
            setAttr( "state", "error" ).setAttr( "msg", "邮箱地址不正确。" );
            this.renderJson();
            return;
        }

        // 需要记录的数据：
        // 申请重置邮箱、申请重置的时间、IP地址、重置令牌、令牌有效性

        // 第一步：先根据邮箱找到对应的用户，即验证用户的合法性，如果邮箱未找到那么就表示当前邮箱不合法。
        // 第二步：记录申请时间，并为申请重置的邮箱生成重置令牌。
        // 第三步：发送邮件（HTML内容根据freemarker模板生成）

        try {
            PasswordResetService service = PasswordResetService.withEmail( email );
            // 发送邮件
            service.sendEmail();
            setAttr( "state", "success" ).setAttr( "msg", "发送成功。" );
            this.renderJson();
        } catch ( Exception e ) {
            log.error( "发送重置密码的邮件时发生了异常", e );
            setAttr( "state", "error" ).setAttr( "msg", "请确认邮箱地址正确。" );
            this.renderJson();
        }
    }

    public void reset() {
        String token = getPara();
        try {
            setAttr( "token", token );
            PasswordResetService.withToken( token );
        } catch ( Exception e ) {
            // TODO: 跳转到更正确的地址
            renderError( HttpServletResponse.SC_FORBIDDEN );
        }
    }

    /**
     * 重置密码
     */
    public void save() {
        String password = getPara( "password" );
        String confirm = getPara( "confirm" );
        String token = getPara( "token" );

        try {
            PasswordResetService service = PasswordResetService.withToken( token );
            // 发送邮件
            service.changePassword( password );
            setAttr( "state", "success" ).setAttr( "msg", "密码修改成功。" );
            this.renderJson();
        } catch ( Exception e ) {
            log.error( "修改密码时发生了异常", e );
            setAttr( "state", "error" ).setAttr( "msg", "密码修改失败。" );
            this.renderJson();
        }
    }
}
