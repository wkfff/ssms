/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ExecutorMailer.java
 * 创建时间：2015-07-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorMailer {

    private static ExecutorService executorService = null;

    public static ExecutorService getExecutorService() {
        if ( executorService == null )
            executorService = Executors.newCachedThreadPool();
        return executorService;
    }

    public static void setExecutorService( ExecutorService executorService ) {
        ExecutorMailer.executorService = executorService;
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param recipients 收件人
     */
    public static void sendText( final String subject, final String body, final String... recipients ) {
        getExecutorService().execute( getSendTextRunnable( subject, body, recipients ) );
    }

    private static Runnable getSendTextRunnable( final String subject, final String body, final String... recipients ) {
        return new Runnable() {
            @Override
            public void run() {
                Mailer.sendText( subject, body, recipients );
            }
        };
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param recipients 收件人
     */
    public static void sendHtml( final String subject, final String body, final String... recipients ) throws EmailException {
        sendHtml( subject, body, null, recipients );
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param attachment 附件
     * @param recipients 收件人
     */
    public static void sendHtml( final String subject, final String body, final EmailAttachment attachment, final String... recipients ) {
        getExecutorService().execute( getSendHtmlRunable( subject, body, attachment, recipients ) );
    }

    private static Runnable getSendHtmlRunable( final String subject, final String body, final EmailAttachment attachment, final String... recipients ) {
        return new Runnable() {
            @Override
            public void run() {
                Mailer.sendHtml( subject, body, attachment, recipients );
            }
        };
    }

    /**
     * @param subject    主题
     * @param body       内容
     * @param attachment 附件
     * @param recipients 收件人
     */
    public static void sendAttachment( final String subject, final String body, final EmailAttachment attachment, final String... recipients ) {
        getExecutorService().execute( getSendAttachRunnable( subject, body, attachment, recipients ) );
    }

    private static Runnable getSendAttachRunnable( final String subject, final String body, final EmailAttachment attachment, final String... recipients ) {
        return new Runnable() {
            @Override
            public void run() {
                Mailer.sendAttachment( subject, body, attachment, recipients );
            }
        };
    }

    public void shutdown() {
        getExecutorService().shutdown();
    }

    public List<Runnable> shutdownNow() {
        return getExecutorService().shutdownNow();
    }
}