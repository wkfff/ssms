/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityService.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.service;

/**
 * 租户服务
 */
public abstract class TenantService implements AutoCloseable {
    private final OperateContext operateContext;

    /**
     * 根据身份标识获取租户服务
     */
    public TenantService( OperateContext operateContext ) {
        this.operateContext = operateContext;
    }

    @Override
    public void close() throws Exception {
        operateContext.close();
    }

    public OperateContext getOperateContext() {
        return operateContext;
    }
}
