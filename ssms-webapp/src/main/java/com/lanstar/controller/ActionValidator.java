/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Validator.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.validate.ValidateException;
import com.lanstar.core.validate.Validator;

import javax.servlet.http.HttpServletResponse;

public abstract class ActionValidator extends Validator {
    @Override
    protected void handleError( HandlerContext c ) {
        throw new ValidateException().errorCode( HttpServletResponse.SC_NOT_ACCEPTABLE ).attachObject( this.errorMsg );
    }
}
