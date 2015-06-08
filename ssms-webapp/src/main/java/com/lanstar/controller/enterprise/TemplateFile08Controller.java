/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile08Controller.java
 * 创建时间：2015年6月8日 上午10:53:24
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.TemplateFile08;

public class TemplateFile08Controller extends SimplateController<TemplateFile08> {

    @Override
    protected TemplateFile08 getDao() {
        return TemplateFile08.dao;
    }
}
