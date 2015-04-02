/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Controller.java
 * 创建时间：2015年4月1日 下午2:27:14
 * 创建用户：林峰
 */
package com.lanstar.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制器基类
 *
 */
public abstract class Controller {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String urlPara;
	
	public void init(HttpServletRequest request, HttpServletResponse response, String urlPara) {
		this.request = request;
		this.response = response;
		this.urlPara = urlPara;
	}
	
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpSession getSession() {
		return request.getSession();
	}
	
	public String getPara(String name) {
		return request.getParameter(name);
	}

	/**
	 * @return the urlPara
	 */
	public String getUrlPara() {
		return urlPara;
	}

	/**
	 * @param urlPara the urlPara to set
	 */
	public void setUrlPara(String urlPara) {
		this.urlPara = urlPara;
	}		
}
