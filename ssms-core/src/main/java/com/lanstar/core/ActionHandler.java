/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionHandler.java
 * 创建时间：2015年4月1日 下午12:06:01
 * 创建用户：林峰
 */
package com.lanstar.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanstar.common.exception.WebException;
import com.lanstar.common.log.Logger;

/**
 * Action处理器
 */
public class ActionHandler extends Handler {
	private static final Logger log = Logger.getLogger(ActionHandler.class);

	
	public final void handle(String url, HttpServletRequest request, HttpServletResponse response){			
		try {
			Action action = ActionMapping.getAction(url);
			action.setContext(request, response);
			action.invoke();	
		} catch (WebException e) {			
			log.error(e, e.getMessage());
		}
	}
}
