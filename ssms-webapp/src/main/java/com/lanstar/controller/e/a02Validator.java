/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：a02Valieda.java
 * 创建时间：2015-04-12
 * 创建用户：张铮彬
 */

package com.lanstar.controller.e;

import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.validate.Validator;

public class a02Validator extends Validator {
    @Override
    protected void validate( HandlerContext c ) {
        this.validateRequiredString( "abc", "titleMsg", "请输入标题!" );
    }

    @Override
    protected void handleError( HandlerContext c ) {
        // 把错误内容设置到模板上，统一一个key？！那写个统一的基类吧
        c.setValue( "_ERROR_", this.errorMsg );
    }
}
