/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PasswordResetService.java
 * 创建时间：2015-07-30
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.common.Asserts;
import com.lanstar.common.kit.StrKit;
import com.lanstar.common.log.Logger;
import com.lanstar.core.render.FreeMarkerRender;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.PasswordResetHistory;
import com.lanstar.model.system.tenant.UserModel;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.IAtom;
import com.lanstar.plugin.mail.ExecutorMailer;
import com.lanstar.plugin.sqlinxml.SqlKit;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

public class PasswordResetService {
    private String email;
    private PasswordResetHistory history;
    private Date applyTime;
    private String token;
    private UserModel<?> user;

    private final Logger logger = Logger.getLogger( PasswordResetService.class );

    private PasswordResetService() {
    }

    public static PasswordResetService withEmail( String email ) throws IllegalArgumentException {
        PasswordResetService service = new PasswordResetService();

        service.email = email;
        service.startWitEmail();

        return service;
    }

    public static PasswordResetService withToken( String token ) throws IllegalArgumentException {
        PasswordResetService service = new PasswordResetService();

        service.token = token;
        service.startWitToken();

        return service;
    }

    /**
     * 根据邮箱开始的服务初始化过程
     */
    private void startWitEmail() {
        // 根据邮箱获取下历史看看，如果可以获取到历史那么用历史中的信息来初始化
        // sys_pwdreset是一个视图，只显示还没过期的历史。
        history = PasswordResetHistory.dao.findFirst( SqlKit.sql( "system.passwordResetHistory.getValidHistoryWithEmail" ), email );
        if ( history != null ) {
            applyTime = history.getApplyTime();
            token = history.getToken();
            user = history.getUser();
        } else {
            applyTime = new Date();
            token = createToken();
            user = TenantType.findUserByEmail( email );
            Asserts.notNull( user, "给定的邮箱没有对应的用户，请确保输入的邮箱的有效性。" );
        }
    }

    /**
     * 根据TOKEN开始的服务初始化过程
     */
    private void startWitToken() {
        // 根据邮箱获取下历史看看，如果可以获取到历史那么用历史中的信息来初始化
        // sys_pwdreset是一个视图，只显示还没过期的历史。
        history = PasswordResetHistory.dao.findFirst( SqlKit.sql( "system.passwordResetHistory.getValidHistoryWithToken" ), token );
        Asserts.notNull( history, "令牌无效" );
        applyTime = history.getApplyTime();
        token = history.getToken();
        user = history.getUser();
    }

    /**
     * 发送邮件
     */
    public boolean sendEmail() {
        return Db.tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                // 1. 记录日志
                // 2. 生成模板
                // 3. 发送邮件
                logHistory();
                try {
                    String subject = "安全生产标准化管理系统重置密码邮件";
                    String htmlContent = getHtmlContent();
                    ExecutorMailer.sendHtml( subject, htmlContent, email );
                    return true;
                } catch ( EmailException e ) {
                    logger.error( "发送邮件时出现了异常!", e );
                    return false;
                }
            }
        } );
    }

    public boolean changePassword( final String password ) {
        return Db.tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                history.set( "N_STATE", 1 );
                history.update();
                return user.ForceChangePassword( password );
            }
        } );
    }

    private void logHistory() {
        if ( this.history == null ) {
            this.history = new PasswordResetHistory();
            history.setTenant( this.user.getOwner() );
            history.setOperator( ServiceUser.INST );
            history.setUser( user );
            history.setToken( token );
            history.setApplyTime( applyTime );
            history.setEmail( email );
            history.save();
        }
    }

    private String getHtmlContent() {
        try {
            // TODO: 将模板配置文件移动到配置文件汇总进行配置，不要在这里直接写死。
            Template template = FreeMarkerRender.getConfiguration().getTemplate( "/WEB-INF/mail.ftl" );
            StringWriter writer = new StringWriter();
            template.process( this, writer );
            return writer.toString();
        } catch ( IOException | TemplateException e ) {
            logger.error( "生成邮件内容的时候出现了错误", e );
            throw new RuntimeException( e );
        }
    }

    public String getEmail() {
        return email;
    }

    public PasswordResetHistory getHistory() {
        return history;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public String getToken() {
        return token;
    }

    public UserModel<?> getUser() {
        return user;
    }

    public Logger getLogger() {
        return logger;
    }

    private static String createToken() {
        return StrKit.toMD5( String.valueOf( System.currentTimeMillis() + new Random().nextInt() ) );
    }
}
