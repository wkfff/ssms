/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ConsolePlugin.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.console;

import com.lanstar.plugin.IAppPlugin;

import java.io.IOException;

/** 命令行控制台的插件 */
public class ConsolePlugin implements IAppPlugin {
    @Override
    public void startup(){
        if( !thread.isAlive() ) thread.start();
        else infor( "请输入EN进入命令模式，可以在控制台通过命令行进行命令控制。" );
    }

    @Override
    public void shutdown(){
        thread.interrupt();
    }

    private ICommandParser parser = new CommandParser();

    private final Thread thread = new Thread(){
        @Override
        public void run(){
            infor( "请输入EN进入命令模式，可以在控制台通过命令行进行命令控制。" );
            boolean commandMode = false;
            while( true ){
                String cmd = readLine( commandMode ? "控制台#" : "控制台>" ).trim().toUpperCase();
                if( !commandMode ){
                    if( cmd.equalsIgnoreCase( "EN" ) ) commandMode = true;
                    else infor( "请输入EN进入命令模式后才能使用！" );
                    continue;
                }
                if( cmd.equals( "" ) ) continue;
                if( cmd.compareToIgnoreCase( "quit" ) == 0 || cmd.compareToIgnoreCase( "q" ) == 0 || cmd.compareToIgnoreCase( "exit" ) == 0 ){
                    infor( "正在关闭服务器............" );
                    // 退出事件统一用Runtime时间来完成，不增加处理
                    System.exit( 0 );
                }

                if( parser != null ) infor( parser.parser( cmd ) );
            }
        }
    };

    /* 输出信息 */
    public void infor( String msg ){
        System.out.println( msg );
        System.out.flush();
    }

    /* 处理命令行输入 */
    public String readLine( String msg ){
        System.out.print( msg );
        System.out.flush();
        String val = "";

        for( boolean done = false; !done; ){
            try{
                int ch = System.in.read();
                if( ch < 0 || (char) ch == '\n' ) done = true;
                else if( (char) ch != '\r' ) val += (char) ch;
            } catch(IOException e){
                done = true;
            }
        }
        return val;
    }

}
