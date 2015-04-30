/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_resultController.java
 * 创建时间：2015年4月29日 上午9:57:08
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;

/**
 * 自评结果
 *
 */
public class grade_resultController extends DefaultController {
    public grade_resultController() {
        super( "SSM_GRADE_RESULT" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_resultValidator.class;
    }
 
}

