/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ResourceService.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

import java.io.IOException;

public interface ResourceService {
    /**
     * 根据定位符获取资源
     *
     * @param location 相对路径
     */
    Resource getResource( String location );

    /**
     * 将资源保存到定位符表示的路径中
     *
     * @param source   资源
     * @param location 相对路径
     */
    long saveResource( InputStreamSource source, String location ) throws IOException;

    void removeResouce( String path );

    /**
     * 获取文件服务器代码
     */
    String getServerCode();
}