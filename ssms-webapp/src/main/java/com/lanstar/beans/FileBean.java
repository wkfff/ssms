/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileBean.java
 * 创建时间：2015-06-17
 * 创建用户：张铮彬
 */

package com.lanstar.beans;

import java.util.HashMap;
import java.util.Map;

public class FileBean {
    Map<String, Object> attrs = new HashMap<>(  );

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs( Map<String, Object> attrs ) {
        this.attrs = attrs;
    }
}
