/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：OreillyCos.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.upload;

public class OreillyCos {

    private static Boolean isMultipartSupported = null;

    public static boolean isMultipartSupported() {
        if ( isMultipartSupported == null ) {
            detectOreillyCos();
        }
        return isMultipartSupported;
    }

    public static void init( String saveDirectory, int maxPostSize, String encoding ) {
        if ( isMultipartSupported() ) {
            MultipartRequest.init( saveDirectory, maxPostSize, encoding );
        }
    }

    private static void detectOreillyCos() {
        try {
            Class.forName( "com.oreilly.servlet.MultipartRequest" );
            isMultipartSupported = true;
        } catch ( ClassNotFoundException e ) {
            isMultipartSupported = false;
        }
    }
}