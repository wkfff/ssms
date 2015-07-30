/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Mailer.java
 * 创建时间：2015-07-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.mail;

import com.lanstar.common.Asserts;
import com.lanstar.common.log.Logger;
import org.apache.commons.mail.*;

/**
 * Mailer.sendHtml("测试","173956022@qq.com","<a href='www.dreampie.cn'>Dreampie</a>");
 */
public class Mailer {
    private static Logger logger = Logger.getLogger( Mailer.class );

    /**
     * @param subject    主题
     * @param body       内容
     * @param recipients 收件人
     */
    public static void sendText( String subject, String body, String... recipients ) {
        try {
            SimpleEmail simpleEmail = getSimpleEmail( subject, body, recipients );
            simpleEmail.send();
            logger.debug( "send email to %s", recipients );
        } catch ( EmailException e ) {
            throw new RuntimeException( "Unabled to send email", e );
        }
    }

    /**
     * @param subject    主题
     * @param recipients 收件人
     */
    public static SimpleEmail getSimpleEmail( String subject, String... recipients ) throws EmailException {
        return getSimpleEmail( subject, null, recipients );
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param recipients 收件人
     */
    public static SimpleEmail getSimpleEmail( String subject, String body, String... recipients ) throws EmailException {
        SimpleEmail simpleEmail = new SimpleEmail();
        configEmail( subject, simpleEmail, recipients );
        if ( body != null )
            simpleEmail.setMsg( body );
        return simpleEmail;
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param recipients 收件人
     */
    public static void sendHtml( String subject, String body, String... recipients ) {
        sendHtml( subject, body, null, recipients );
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param attachment 附件
     * @param recipients 收件人
     */
    public static void sendHtml( String subject, String body, EmailAttachment attachment, String... recipients ) {
        try {
            HtmlEmail htmlEmail = getHtmlEmail( subject, body, attachment, recipients );
            htmlEmail.send();
            logger.debug( "send email to %s", recipients );
        } catch ( EmailException e ) {
            throw new RuntimeException( "Unabled to send email", e );
        }
    }

    /**
     * @param subject    主题
     * @param recipients 收件人
     */
    public static HtmlEmail getHtmlEmail( String subject, String... recipients ) {
        return getHtmlEmail( subject, null, null, recipients );
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param attachment 附件
     * @param recipients 收件人
     */
    public static HtmlEmail getHtmlEmail( String subject, String body, EmailAttachment attachment, String... recipients ) {
        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            configEmail( subject, htmlEmail, recipients );
            if ( body != null )
                htmlEmail.setHtmlMsg( body );
            // set the alter native message
            htmlEmail.setTextMsg( "Your email client does not support HTML messages" );
            if ( attachment != null )
                htmlEmail.attach( attachment );
            return htmlEmail;
        } catch ( EmailException e ) {
            throw new RuntimeException( "Unabled to send email", e );
        }
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param attachment 附件
     * @param recipients 收件人
     */
    public static void sendAttachment( String subject, String body, EmailAttachment attachment, String... recipients ) {
        try {
            MultiPartEmail multiPartEmail = getMultiPartEmail( subject, body, attachment, recipients );
            multiPartEmail.send();
            logger.debug( "send email to %s", recipients );
        } catch ( EmailException e ) {
            throw new RuntimeException( "Unabled to send email", e );
        }
    }

    /**
     * @param subject    主题
     * @param recipients 收件人
     */
    public static MultiPartEmail getMultiPartEmail( String subject, String... recipients ) {
        return getMultiPartEmail( subject, null, null, recipients );
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param attachment 附件
     * @param recipients 收件人
     */
    public static MultiPartEmail getMultiPartEmail( String subject, String body, EmailAttachment attachment, String... recipients ) {
        try {
            MultiPartEmail multiPartEmail = new MultiPartEmail();
            configEmail( subject, multiPartEmail, recipients );
            if ( body != null )
                multiPartEmail.setMsg( body );
            // add the attachment
            if ( attachment != null )
                multiPartEmail.attach( attachment );
            return multiPartEmail;
        } catch ( EmailException e ) {
            throw new RuntimeException( "Unabled to send email", e );
        }
    }

    private static void configEmail( String subject, Email email, String... recipients ) throws EmailException {
        Asserts.notNull( recipients, "Recipients not found." );
        MailerConf mailerConf = MailerPlugin.mailerConf;
        email.setCharset( MailerConf.getCharset() );
        email.setSocketTimeout( mailerConf.getTimeout() );
        email.setSocketConnectionTimeout( mailerConf.getConnectout() );
        email.setCharset( mailerConf.getEncode() );
        email.setHostName( mailerConf.getHost() );
        if ( !mailerConf.getSslport().isEmpty() )
            email.setSslSmtpPort( mailerConf.getSslport() );
        if ( !mailerConf.getPort().isEmpty() )
            email.setSmtpPort( Integer.parseInt( mailerConf.getPort() ) );
        email.setSSLOnConnect( mailerConf.isSsl() );
        email.setStartTLSEnabled( mailerConf.isTls() );
        email.setDebug( mailerConf.isDebug() );
        email.setAuthentication( mailerConf.getUser(), mailerConf.getPassword() );
        email.setFrom( mailerConf.getFrom(), mailerConf.getName() );
        email.setSubject( subject );
        email.addTo( recipients );
    }
}