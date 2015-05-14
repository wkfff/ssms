/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：StoredProcPara.java
 * 创建时间：2015年5月14日 上午11:57:36
 * 创建用户：林峰
 */
package com.lanstar.db;
/**
 * 存储过程使用的参数定义
 *
 */
public class StoredProcPara
{
    private String paraName = null;
    private String paraType = null;
    private String value = null;
    private String realValue = null;
    private String inout = null;
    
    
    public String getParaName()
    {
        return paraName;
    }
    public void setParaName(String paraName)
    {
        this.paraName = paraName;
    }
    public String getParaType() {
        return paraType;
    }
    public void setParaType(String paraType) {
        this.paraType = paraType;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getRealValue() {
        return realValue;
    }
    public void setRealValue(String realValue) {
        this.realValue = realValue;
    }
    public String getInout() {
        return inout;
    }
    public void setInout(String inout) {
        this.inout = inout;
    }
}
