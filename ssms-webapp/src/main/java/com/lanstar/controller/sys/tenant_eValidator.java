/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：tenant_eValidator.java
 * 创建时间：2015年4月13日 下午7:35:41
 * 创建用户：林峰
 */
package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.core.handle.HandlerContext;

/**
 * 验证
 *
 */
public class tenant_eValidator extends ActionValidator {
    @Override
    protected void validate( HandlerContext c ) {
        this.validateRequiredString( "", "", "" );
    }

    @Override
    protected void handleError( HandlerContext c ) {
        c.setValue( "_ERROR_", this.errorMsg );
    }
}
