/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EMailPlugin.java
 * 创建时间：2015-07-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.mail;

import com.lanstar.common.Asserts;
import com.lanstar.common.kit.Prop;
import com.lanstar.common.kit.PropKit;
import com.lanstar.plugin.IPlugin;

public class MailerPlugin implements IPlugin {
    private String config;

    public static MailerConf mailerConf;

    public MailerPlugin() {
        this( "application.properties" );
    }

    public MailerPlugin( String config ) {
        this.config = config;
    }

    @Override
    public boolean start() {
        Asserts.check( mailerConf != null, "MailerPlugin只能被初始化一次！请勿重复初始化！" );

        Prop prop = PropKit.use( config );
        // host
        String host = prop.get( "smtp.host", "" );
        Asserts.notEmpty( host, "email host has not found!" );

        // port
        String port = prop.get( "smtp.port", "" );

        // username
        String user = prop.get( "smtp.user", "" );
        Asserts.notEmpty( user, "email user has not found!" );

        // password
        String password = prop.get( "smtp.password", "" );
        Asserts.notEmpty( password, "email password has not found!" );

        // from
        String from = prop.get( "smtp.from", "" );
        Asserts.notEmpty( from, "email from has not found!" );

        // ssl
        Boolean ssl = prop.getBoolean( "smtp.ssl", false );
        String sslport = prop.get( "smtp.sslport", "" );

        // tls
        Boolean tls = prop.getBoolean( "smtp.tls", false );

        // timeout
        int timeout = prop.getInt( "smtp.timeout", 60000 );
        int connectout = prop.getInt( "smtp.connectout", 60000 );

        // debug flag
        Boolean debug = prop.getBoolean( "smtp.debug", false );

        // send name
        String name = prop.get( "smtp.name", "" );

        // encode
        String encode = prop.get( "smtp.encode", "UTF-8" );
        mailerConf = new MailerConf( host, port, user, password, from );
        if ( ssl ) mailerConf.useSSL( sslport );
        if ( tls ) mailerConf.useTLS();

        mailerConf.setName( name )
                  .setTimeout( timeout )
                  .setConnectOut( connectout )
                  .setEncode( encode )
                  .setDebug( debug );

        return true;
    }

    @Override
    public boolean stop() {
        mailerConf = null;
        return true;
    }
}
