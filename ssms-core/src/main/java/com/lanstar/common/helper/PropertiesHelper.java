/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PropertiesHelper.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.common.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性的辅助工具类
 */
public class PropertiesHelper {
    /**
     * 从文件中装载属性值
     *
     * @param fname
     * @return
     */
    public static Properties load(String fname) {
        Asserts.notBlank(fname, "属性文件必须指定名称");
        Properties ps = new Properties();
        if (fname.startsWith("classpath:")) {
            fname = fname.substring(10);
            try {
                ps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fname));
            } catch (IOException e) {
                throw new RuntimeException("属性文件读写错误：" + fname, e);
            }
        } else {
            try {
                ps.load(new FileReader(fname));
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("属性文件不存在：" + fname);
            } catch (IOException e) {
                throw new RuntimeException("属性文件读写错误：" + fname, e);
            }
        }
        return ps;
    }

    /**
     * 从指定的类获取资源
     *
     * @param className
     * @param path
     */
    public static Properties load(Class<?> className, String path) {
        Properties ps = new Properties();
        try {
            ps.load(className.getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException("属性文件读写错误:" + path, e);
        }
        return ps;
    }

    /**
     * 获得指定KEY的属性值
     *
     * @param ps     目标属性集合
     * @param key    KEY ，不能为空
     * @param defval 默认值
     * @return 如果为NULL或长度为0则返回默认值defval
     */
    public static String get(Properties ps, String key, String defval) {
        String v = ps.getProperty(key);
        return (v == null || v.length() == 0) ? defval : v;
    }

    /**
     * 获得指定KEY的属性值
     *
     * @param ps     目标属性集合
     * @param key    KEY ，不能为空
     * @param defval 默认值
     * @return 如果为NULL或长度为0则返回默认值defval
     */
    public static int get(Properties ps, String key, int defval) {
        String v = ps.getProperty(key);
        if (v == null || v.length() == 0)
            return defval;
        try {
            return Integer.parseInt(v);
        } catch (Throwable e) {
            return defval;
        }
    }
}
