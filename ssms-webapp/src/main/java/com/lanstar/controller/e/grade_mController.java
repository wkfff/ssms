/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_mController.java
 * 创建时间：2015年4月23日 上午10:45:42
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;

/**
 * 自评历史
 *
 */
public class grade_mController extends DefaultController {

    public grade_mController() {
        super( "SSM_GRADE_E_M" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_mValidator.class;
    }

    @Override
    public ViewAndModel rec( HandlerContext context ) {
        // TODO:根据企业的信息来设置
        context.setValue( "S_TANENT", "XX企业" );
        context.setValue( "P_PROFESSION", "1" );
        return super.rec( context );
    }
}
