/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：tenant_eController.java
 * 创建时间：2015年4月13日 下午5:36:30
 * 创建用户：林峰
 */
package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.interceptor.Before;

/**
 * 企业租户表
 *
 */
public class tenant_eController extends DefaultController {
    public tenant_eController(  ) {
        super( "SYS_TENANT_E" );
    }
    
    @Before(tenant_eValidator.class)
    public ViewAndModel index( HandlerContext context ) {
        return super.index( context );
    }

    /* (non-Javadoc)
     * @see com.lanstar.controller.BaseController#getValidator()
     */
    @Override
    protected Class<? extends ActionValidator> getValidator() {
        // TODO Auto-generated method stub
        return null;
    }
}
