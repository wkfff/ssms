/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Handler.java
 * 创建时间：2015年4月1日 下午2:01:26
 * 创建用户：林峰
 */
package com.lanstar.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理器基类
 */
public abstract class Handler {
	
	public abstract void handle(String url, HttpServletRequest request, HttpServletResponse response);;
}
