/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ITenantContext.java
 * 创建时间：2015-07-26
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.common.TreeNode;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.service.AttachFileService;
import com.lanstar.service.AttachTextService;
import com.lanstar.service.enterprise.EnterpriseService;
import com.lanstar.service.review.ReviewService;

import java.util.List;

public interface TenantContext {
    TenantContext SYSTEM = new TenantContextImpl( Tenant.SYSTEM_TENANT );

    void initReviewService( Enterprise tenant, Profession profession );

    String getTenantName();

    TenantType getTenantType();

    int getTenantId();

    String getTenantCode();

    String getTenantDbCode();

    DbPro getTenantDb();

    Tenant getTenant();

    List<TreeNode> getSystemNavgate();

    EnterpriseService getEnterpriseService();

    AttachTextService getAttachTextService();

    AttachFileService getAttachFileService();

    ReviewService getReviewService();
}
