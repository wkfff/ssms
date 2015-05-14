/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateContext.java
 * 创建时间：2015年5月14日 上午10:28:33
 * 创建用户：林峰
 */
package com.lanstar.plugin.template.sql;

import java.util.HashMap;
import java.util.Map;

/**
 * 一般的模板上下文处理类，用MAP来保存变量值
 *
 */
public class TemplateContext implements ITemplateContext{

    private Map<String, String> variables = new HashMap<String, String>();

    public TemplateContext() {}

    public TemplateContext(Map<String, String> map) {
        this.variables.putAll(map);
    }

    public void setVariable(String key, String value) {
        variables.put(key, value);
    }

    public String getVariable(String name) {
        return this.variables.get(name);
    }

}
