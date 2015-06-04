/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewSyncService.java
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

package com.lanstar.service.review;

import com.lanstar.identity.TenantContext;

class ReviewSyncService {
    private final TenantContext source;
    private final TenantContext target;

    private ReviewSyncService( TenantContext source, TenantContext target ) {
        this.source = source;
        this.target = target;
    }

    public static boolean sync( TenantContext source, TenantContext target ) {
        return new ReviewSyncService(source, target).execute();
    }

    private boolean execute() {
        // TODO 同步企业的部分数据到评审中
        return false;
    }
}
