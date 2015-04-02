/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：a02Controller.java
 * 创建时间：2015年4月1日 下午8:10:07
 * 创建用户：林峰
 */
package com.lanstar.controller;

import com.lanstar.common.log.Logger;
import com.lanstar.core.Controller;
import com.lanstar.render.HtmlRender;

/**
 * @author F
 *
 */
public class a02Controller extends Controller {
	private static final Logger log = Logger.getLogger(a02Controller.class);
	
	public void index(){
		log.debug("test", null);
		HtmlRender hr = new HtmlRender("test....");
		hr.setContext(this.getRequest(), this.getResponse());
		hr.render();
	}
}
