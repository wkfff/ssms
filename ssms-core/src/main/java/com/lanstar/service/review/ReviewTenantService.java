/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewTenantService.java
 * 创建时间：2015-05-15
 * 创建用户：张铮彬
 */

package com.lanstar.service.review;

import com.lanstar.core.handle.identity.TenantType;
import com.lanstar.service.IdentityContext;
import com.lanstar.service.TenantService;

public class ReviewTenantService extends TenantService {
    /**
     * 根据身份标识获取租户服务
     */
    public ReviewTenantService( IdentityContext identityContext ) {
        super( identityContext );
    }

    /**
     * 构建特征码
     *
     * @param code 种子编码（当前应传递县级编码）
     *
     * @return 特征码
     */
    public String buildSignature( String code ) {
        // 得到租户特征码：租户类型+县级编码
        String sign = TenantType.REVIEW.getName().toUpperCase() + code;
        // 生成租户编码tenantCode：租户特征码+4位顺序码
        Object[] params = { sign, 4, sign + "@" };
        Object[] objects = getIdentityContext().call( "SP_GETSEQVALUE", params );
        return (String) objects[2];
    }
}
