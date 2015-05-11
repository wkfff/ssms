/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：industryController.java
 * 创建时间：2015-05-11
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;

public class industryController extends DefaultController {
    public industryController( ) {
        super( "SYS_INDUSTRY" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return null;
    }
}
