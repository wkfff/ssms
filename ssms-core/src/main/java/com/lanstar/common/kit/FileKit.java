/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileKit.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import java.io.*;

public class FileKit {
    public static void delete( File file ) {
        if ( file != null && file.exists() ) {
            if ( file.isFile() ) {
                file.delete();
            } else if ( file.isDirectory() ) {
                File files[] = file.listFiles();
                for ( int i = 0; i < files.length; i++ ) {
                    delete( files[i] );
                }
            }
            file.delete();
        }
    }

    /**
     * 根据编码方式读文本文件内容
     */
    public static String getTextContent( InputStream fs, String encoding ) {
        if ( fs == null )
            return null;
        StringBuilder str = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader( new InputStreamReader( fs, encoding ) );
            try {
                String data;
                while ( (data = br.readLine()) != null ) {
                    str.append( data ).append( "\n" );
                }
            } catch ( Exception e ) {
                str.append( e.toString() );
            }
            return str.toString();
        } catch ( IOException es ) {
            throw new RuntimeException( "文件流读写错误[" + fs + "]", es );
        } finally {
            try {
                fs.close();
            } catch ( Throwable ignored ) {
            }
        }
    }
}