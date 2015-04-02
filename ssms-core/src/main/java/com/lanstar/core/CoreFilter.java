/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CoreFilter.java
 * 创建时间：2015年3月31日 下午9:13:20
 * 创建用户：林峰
 */
package com.lanstar.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 *
 */
public class CoreFilter implements Filter {
	private String[] expaths;
//	private ActionMapping actionMapping;
	private Handler handler;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//排除路径 
		expaths = filterConfig.getInitParameter("exclude-paths").split(",");
		
//		ActionMapping actionMapping = new ActionMapping();
//		actionMapping.buildMapping();
		ActionMapping.init();
		
		handler = new ActionHandler();
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {				
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;		
		String uri = req.getRequestURI();					
		for (String p : expaths){
			if (uri.startsWith(req.getContextPath() + p.trim())) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		handler.handle(uri, req, res);
	}

	@Override
	public void destroy() {
		
	}
}
