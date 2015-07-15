/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PasswordController.java
 * 创建时间：2015年7月14日 上午11:02:45
 * 创建用户：林峰
 */
package com.lanstar.controller;

import java.util.Date;

import com.lanstar.common.kit.DateKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.ClearInterceptor;
import com.lanstar.core.aop.ClearLayer;
import com.lanstar.core.mail.EmailConst;
import com.lanstar.core.mail.EmailUtils;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.EnterpriseUser;
import com.lanstar.model.system.GovernmentUser;
import com.lanstar.model.system.ReviewUser;
import com.lanstar.model.system.TenantUser;
import com.lanstar.render.CaptchaRender;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

/**
 * 密码
 *
 */
@ClearInterceptor(ClearLayer.ALL)
public class PasswordController extends Controller {
    /**
     * 重置密码发送邮件页面
     */
    public void index(){
//        render( "index.ftl" );
    }
    /**
     * 发送重置密码邮件
     */
    public void send(){
        String tenantType = "E";
        int userId = -1;
        //验证码校验
        String vCode = this.getPara("yzm");
        if ( CaptchaRender.validate( this, vCode ) == false ) {
            setAttr( "state", "error" ).setAttr( "msg", "验证码不正确。" );
            this.renderJson();
            return;
        }
        //后面如果启用多用户时要传用户名
//        String userName = "ADMIN";
        //邮件地址校验
        String mail = this.getPara("C_EMAIL");
        if (StrKit.isBlank( mail ) ) {
            setAttr( "state", "error" ).setAttr( "msg", "邮箱地址不正确。" );
            this.renderJson();
            return;
        }else{
            TenantUser user = TenantUser.dao.findFirst( "SELECT * FROM TENANT_USER WHERE C_EMAIL=? ",mail );
            if (user==null){
                setAttr( "state", "error" ).setAttr( "msg", "邮箱地址不正确。" );
                this.renderJson();
                return;
            }else{
                userId = user.getId();
                tenantType = user.getTenantType().getName();
            }
        }

        String k = tenantType+"#"+userId+"#"+DateKit.toStr( new Date(), DateKit.timeFormat );
        k = Base64Encoder.encode( k );
        EmailUtils.sendHtml( EmailConst.SMTP_163, "flinsoft@163.com", "flin0000", "19990653@qq.com", "安全生产标准化管理系统重置密码邮件", "尊敬的用户：<br>您的账号进行了重置密码操作,请点击以下链接设置新密码（1小时内有效）<br> <a href='http://localhost/pwd/reset/"+k+"'>http://localhost/pwd/reset/"+k+"</a> <br>若您没有做过此操作，请忽略此邮件，谢谢！<br>本邮件为系统邮件，请勿直接回复！" );
        //this.render( new JsonRender( "OK" ).forIE() );
        setAttr( "state", "success" ).setAttr( "msg", "发送成功。" );
        this.renderJson();
    }
    /**
     * 发送成功提示页面
     */
    public void success(){
        this.render( "success.ftl" );
    }
    
    /**
     * 重置密码页面
     */
    public void reset(){
        String k = this.getPara( 0 );
        if (StrKit.isBlank( k )) {
            setAttr( "state", "error" ).setAttr( "msg", "无效的重置密码链接。" );
            this.render( "err.ftl" );
            return;
        }
        setAttr("token",k);
        
        k = Base64Decoder.decode( k );
        String[] paras = StrKit.split( k, "#" );
        if(paras.length!=3) {
            setAttr( "state", "error" ).setAttr( "msg", "无效的链接。" );
            this.render( "err.ftl" );
            return;
        }
        Date t = DateKit.toDate( paras[2]);
        Date now = new Date();
        if ( (now.getTime()-t.getTime())/(1000*60*60)>1 ) {
            setAttr( "state", "error" ).setAttr( "msg", "重置密码链接已经过期。" );
            this.render( "err.ftl" );
            return;
        }
        this.render( "reset.ftl" );
    }
    /**
     * 重置密码
     */
    public void resetPassword(){
        String pwd = this.getPara("pwd");
        String k = this.getPara( "token" );
        if (StrKit.isBlank( k )) {
            setAttr( "state", "error" ).setAttr( "msg", "无效的链接。" );
            this.render( "err.ftl" );
            return;
        }
        k = Base64Decoder.decode( k );
        String[] paras = StrKit.split( k, "#" );
        if(paras.length!=3) {
            setAttr( "state", "error" ).setAttr( "msg", "无效的链接。" );
            this.render( "err.ftl" );
            return;
        }
        Date t = DateKit.toDate( paras[2]);
        Date now = new Date();
        if ( (now.getTime()-t.getTime())/(1000*60*60)>1 ) {
            setAttr( "state", "error" ).setAttr( "msg", "重置密码链接已经过期。" );
            this.render( "err.ftl" );
            return;
        }
        String tenantType = paras[0];
        int userId = Integer.parseInt( paras[1] );
        if (tenantType.equals( TenantType.ENTERPRISE.getName() )){
            EnterpriseUser user = EnterpriseUser.dao.findById( userId );
            if (user!=null) {
                user.setPassword( pwd );
                user.update();
            }
        }else if (tenantType.equals( TenantType.REVIEW.getName() )){
            ReviewUser user = ReviewUser.dao.findById( userId );
            if (user!=null) {
                user.setPassword( pwd );
                user.update();
            }
        }else if (tenantType.equals( TenantType.GOVERNMENT.getName() )){
            GovernmentUser user = GovernmentUser.dao.findById( userId );
            if (user!=null) {
                user.setPassword( pwd );
                user.update();
            }
        }
        setAttr("state","success");
        this.renderJson();
    }
    
    /**
     * 重置历史
     */
    public void list(){
        render("historys.ftl");
    }
}
