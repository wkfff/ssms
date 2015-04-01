/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DialectFactory.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.db.dialect;

import com.lanstar.app.container.ContainerHelper;
import com.lanstar.common.helper.Asserts;

import java.util.HashMap;
import java.util.Map;

public class DialectFactory {
    private final Map<String, IDialect> dialectMap = new HashMap<>();

    public DialectFactory(Map<String, IDialect> map) {
        for (String s : map.keySet()) {
            dialectMap.put(s.toUpperCase(), map.get(s));
        }
        Asserts.notEmpty(dialectMap.keySet(), "无法找到任何IDialect实现！");
    }

    public IDialect getDialect(String dialect) {
        return dialectMap.get(dialect);
    }

    public static DialectFactory me(){
        return ContainerHelper.getBean(DialectFactory.class);
    }
}
