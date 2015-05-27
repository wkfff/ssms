/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ResourceKit.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

public abstract class ResourceKit {
    private static ResourceService service;

    static void init( ResourceService service ) {
        ResourceKit.service = service;
    }

    /**
     * 获取一个当前可用的文件服务器。
     */
    public static ResourceService getResourceService() {
        // TODO：以后按一定算法从一组文件服务中获取一个文件服务。
        return service;
    }

    /**
     * 根据文件服务器代码获取文件服务
     *
     * @param serviceCode 文件服务器代码
     */
    public static ResourceService getResourceService( String serviceCode ) {
        return getResourceService();
    }
}
