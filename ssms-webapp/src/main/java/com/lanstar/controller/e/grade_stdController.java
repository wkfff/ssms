/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_stdController.java
 * 创建时间：2015年4月23日 下午3:26:45
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;

/**
 * 自评标准
 *
 */
public class grade_stdController extends DefaultController {
    public grade_stdController() {
        super( "SSM_GRADE_STD" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_stdValidator.class;
    }
}
