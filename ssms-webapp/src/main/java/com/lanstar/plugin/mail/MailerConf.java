/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MailerConf.java
 * 创建时间：2015-07-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.mail;

public class MailerConf {
    private static final String charset = "UTF-8";
    private String host;
    private String port;
    private String user;
    private String password;
    private int timeout = 60000;
    private int connectout = 60000;
    private boolean ssl = false;
    private String sslport;
    private boolean tls = false;
    private boolean debug = false;
    private String name;
    private String from;
    private String encode = "UTF-8";

    public MailerConf( String host, String port, String user, String password, String from ) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.from = from;
    }

    public void useSSL( String sslport ) {
        ssl = true;
        this.sslport = sslport;
    }

    public void useTLS() {
        tls = true;
    }

    public MailerConf setName( String name ) {
        this.name = name;
        return this;
    }

    public MailerConf setTimeout( int timeout ) {
        this.timeout = timeout;
        return this;
    }

    public MailerConf setConnectOut( int connectout ) {
        this.connectout = connectout;
        return this;
    }

    public MailerConf setEncode( String encode ) {
        this.encode = encode;
        return this;
    }

    public MailerConf setDebug( Boolean debug ) {
        this.debug = debug;
        return this;
    }

    public static String getCharset() {
        return charset;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getConnectout() {
        return connectout;
    }

    public boolean isSsl() {
        return ssl;
    }

    public String getSslport() {
        return sslport;
    }

    public boolean isTls() {
        return tls;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getEncode() {
        return encode;
    }
}