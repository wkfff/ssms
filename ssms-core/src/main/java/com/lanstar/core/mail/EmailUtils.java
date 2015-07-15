/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EmailUtils.java
 * 创建时间：2015年7月14日 下午7:30:06
 * 创建用户：林峰
 */
package com.lanstar.core.mail;

import java.util.List;

import com.lanstar.common.kit.StrKit;
import com.lanstar.common.log.Logger;

/**
 * 邮件发送工具类
 *
 */
public class EmailUtils {
    public static Logger log = Logger.getLogger( EmailUtils.class );
    /**
     * 发送邮件
     * @param smtp        邮件协议
     * @param fromAddress 发送人地址
     * @param fromPass    发送人密码
     * @param toaddress   收件人地址
     * @param subject     发送主题
     * @param content     发送内容
     * @throws Exception
     */
    public static void sendHtml(String smtp,String fromAddress,String fromPass,String toAddress, String subject, String content){
        try{
            log.debug("开始向" + toAddress + "发送邮件");
            EmailHandle emailHandle = new EmailHandle(smtp);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNeedAuth(true);
            emailHandle.setSubject(subject);
            emailHandle.setContent(content);
            emailHandle.setTo(toAddress);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNamePass(fromAddress, fromPass);
            emailHandle.sendHtmlEmail();
            log.debug("邮件发送结束!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 发送邮件
     * @param smtp        邮件协议
     * @param fromAddress 发送人地址
     * @param fromPass    发送人密码
     * @param toaddress   收件人地址
     * @param subject     发送主题
     * @param content     发送内容
     * @throws Exception
     */
    public static void send(String smtp,String fromAddress,String fromPass,String toAddress, String subject, String content){
        try{
            log.debug("开始向" + toAddress + "发送邮件");
            EmailHandle emailHandle = new EmailHandle(smtp);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNeedAuth(true);
            emailHandle.setSubject(subject);
            emailHandle.setBody(content);
            emailHandle.setTo(toAddress);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNamePass(fromAddress, fromPass);
            emailHandle.sendEmail();
            log.debug("邮件发送结束!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 发送邮件
     * @param smtp        邮件协议
     * @param fromAddress 发送人地址
     * @param fromPass    发送人密码
     * @param toAddress   收件人地址
     * @param ccAdress    抄送人地址
     * @param subject     发送主题
     * @param content     发送内容
     * @throws Exception
     */
    public static void send(String smtp,String fromAddress,String fromPass,String toAddress,String ccAdress, String subject, String content){
        try{
            log.debug("开始向" + toAddress + "发送邮件");
            EmailHandle emailHandle = new EmailHandle(smtp);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNeedAuth(true);
            emailHandle.setSubject(subject);
            emailHandle.setBody(content);
            emailHandle.setTo(toAddress);
            /**添加抄送**/
            if(!StrKit.isEmpty(ccAdress)){
                emailHandle.setCopyTo(ccAdress);
            }
            emailHandle.setFrom(fromAddress);
            emailHandle.setNamePass(fromAddress, fromPass);
            emailHandle.sendEmail();
            log.debug("邮件发送结束!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 发送邮件
     * @param smtp        邮件协议
     * @param fromAddress 发送人地址
     * @param fromPass    发送人密码
     * @param toaddress   收件人地址
     * @param subject     发送主题
     * @param content     发送内容
     * @throws Exception
     */
    public static void send(String smtp,String fromAddress,String fromPass,String toAddress,String subject, String content,List<String> fileList){
        try{
            log.debug("开始向" + toAddress + "发送邮件");
            EmailHandle emailHandle = new EmailHandle(smtp);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNeedAuth(true);
            emailHandle.setSubject(subject);
            emailHandle.setBody(content);
            emailHandle.setTo(toAddress);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNamePass(fromAddress, fromPass);
            /** 附件文件路径 **/
            for(String file : fileList){
                emailHandle.addFileAffix(file);
            }
            emailHandle.sendEmail();
            log.debug("邮件发送结束!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 发送邮件
     * @param smtp        邮件协议
     * @param fromAddress 发送人地址
     * @param fromPass    发送人密码
     * @param toAddress   收件人地址
     * @param ccAdress    抄送人地址
     * @param subject     发送主题
     * @param content     发送内容
     * @throws Exception
     */
    public static void send(String smtp,String fromAddress,String fromPass,String toAddress,String ccAdress,String subject, String content,List<String> fileList){
        try{
            log.debug("开始向" + toAddress + "发送邮件");
            EmailHandle emailHandle = new EmailHandle(smtp);
            emailHandle.setFrom(fromAddress);
            emailHandle.setNeedAuth(true);
            emailHandle.setSubject(subject);
            emailHandle.setBody(content);
            emailHandle.setTo(toAddress);
            /**添加抄送**/
            if(!StrKit.isEmpty(ccAdress)){
                emailHandle.setCopyTo(ccAdress);
            }
            emailHandle.setFrom(fromAddress);
            emailHandle.setNamePass(fromAddress, fromPass);
            /** 附件文件路径 **/
            for(String file : fileList){
                emailHandle.addFileAffix(file);
            }
            emailHandle.sendEmail();
            log.debug("邮件发送结束!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}