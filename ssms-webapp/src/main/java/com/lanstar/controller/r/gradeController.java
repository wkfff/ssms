/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：gradeController.java
 * 创建时间：2015年5月8日 下午4:15:29
 * 创建用户：林峰
 */
package com.lanstar.controller.r;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;

/**
 * 评审
 *
 */
public class gradeController extends DefaultController {

    public gradeController() {
        super( "SSM_GRADE_R_M" );
    }

    @Override
    public void setFilterFields() {
        this.filterFields.put( "T_START", "T_START >= ?" );
        this.filterFields.put( "T_END", "T_END <= ?" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return gradeValidator.class;
    }
    /**
     * 选择企业
     */
    public ViewAndModel select( HandlerContext context ) {
        return context.returnWith();
    }
}
