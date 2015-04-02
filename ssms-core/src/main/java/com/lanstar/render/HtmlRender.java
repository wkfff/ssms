/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HtmlRender.java
 * 创建时间：2015年4月2日 上午3:56:17
 * 创建用户：林峰
 */
package com.lanstar.render;

import com.lanstar.core.render.RenderException;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * HtmlRender.
 */
public class HtmlRender extends Render {
	
	private static final String contentType = "text/html; charset=" + getEncoding();
	private String text;
	
	public HtmlRender(String text) {
		this.text = text;
	}
	
	public void render() {
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        
			response.setContentType(contentType);
	        writer = response.getWriter();
	        writer.write(text);
	        writer.flush();
		} catch (IOException e) {
			throw new RenderException(e);
		}
		finally {
			if (writer != null)
				writer.close();
		}
	}
}