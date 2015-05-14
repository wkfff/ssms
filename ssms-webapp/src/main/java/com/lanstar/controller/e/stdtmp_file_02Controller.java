/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：stdtmp_file_02Controller.java
 * 创建时间：2015-05-14
 * 创建用户：张铮彬
 */
package com.lanstar.controller.e;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;

public class stdtmp_file_02Controller extends DefaultController {
    public stdtmp_file_02Controller() {
        super( "SSM_STDTMP_FILE_02" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {

        return stdtmp_file_02Validator.class;
    }
}
