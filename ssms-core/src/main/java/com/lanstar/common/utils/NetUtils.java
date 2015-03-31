/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NetUtils.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */

package com.lanstar.common.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 网络相关的工具类
 *
 * Zgray创建于2015/2/15.
 */
public class NetUtils {

    /**
     * 检查给定的IP和端口是否被占用。
     *
     * @param host 主机名
     * @param port 端口
     * @return 如果返回true则表示端口被占用，否则返回false。
     */
    public static boolean detectPort(String host, int port) {
        boolean flag = false;
        try {
            Socket sockct = new Socket();
            sockct.bind(new InetSocketAddress(host, port));
            sockct.close();
            flag = true;
        } catch (IOException ignored) {
        }
        return flag;
    }
}
