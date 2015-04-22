/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：stdtmp_file_03Validator.java
 * 创建时间：下午4:32:11
 * 创建用户：苏志亮
 */
package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.core.handle.HandlerContext;

public class stdtmp_file_03Validator extends ActionValidator {

    @Override
    protected void validate( HandlerContext c ) {
        this.validateRequiredString( "", "", "" );

    }

    @Override
    protected void handleError( HandlerContext c ) {
        c.setValue( "_ERROR_", this.errorMsg );
    }

}
