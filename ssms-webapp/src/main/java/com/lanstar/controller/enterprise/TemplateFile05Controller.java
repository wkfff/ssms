/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile05Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.TemplateFile05;

public class TemplateFile05Controller extends SimplateController<TemplateFile05> {
    @Override
    protected TemplateFile05 getDao() {
        return TemplateFile05.dao;
    }

    @Override
    protected TemplateFile05 newModel() {
        return new TemplateFile05();
    }
}
