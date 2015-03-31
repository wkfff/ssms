/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileHelper.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.common.helper;

import java.io.*;

/**
 * 文件的工具类
 */
public final class FileHelper {

    /**
     * 检查文件是否存在
     * @param path  路径
     * @return TRUE = 是一个文件，并且存在 FALSE = 不存在或者不是文件
     */
    public static boolean fileExists(String path) {
        File f = new File(path);
        return f.exists() && f.isFile();
    }

    /**
     * 遍历目录，如果是文件的话就处理当前文件
     *
     * @param file
     * @param filer
     */
    public static void each(final File file, final IFileProcessor filer) {
        if (!file.exists())
            return;
        if (file.isFile()) {
            filer.process(file);
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File f : files) {
                each(f, filer);
            }
        }
    }

    /**
     * 判断文件是否由指定后缀名构成
     * @param f
     * @param extname
     * @return
     */
    public static boolean withExtname(File f, String... extname) {
        String fname = f.getName().toUpperCase();
        for (String n : extname) {
            if (fname.endsWith(n.toUpperCase()))
                return true;
        }
        return false;
    }

    /**
     * 根据编码方式读文本文件内容
     * @param fs
     * @param encoding
     * @return
     */
    public static String getTextContent(InputStream fs, String encoding) {
        if (fs == null)
            return null;
        StringBuilder str = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(fs, encoding));
            try {
                String data = "";
                while ((data = br.readLine()) != null) {
                    str.append(data).append("\n");
                }
            } catch (Exception e) {
                str.append(e.toString());
            }
            return str.toString();
        } catch (IOException es) {
            throw ExceptionHelper.runtimeException(es, "文件流读写错误[%s]", fs.toString());
        } finally {
            try {
                fs.close();
            } catch (Throwable ignored) {
            }
        }
    }

    /** 文件处理器，用于FileHelper.each()使用 */
    public static interface IFileProcessor {
        void process(File file);
    }

    public static File join(String... path) {
        if (path.length == 0)
            return null;
        File f = new File(path[0]);
        for (int i = 1; i < path.length; i++) {
            f = new File(f, path[i]);
        }
        return f;
    }
}
