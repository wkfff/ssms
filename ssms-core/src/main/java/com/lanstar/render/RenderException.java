/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RenderException.java
 * 创建时间：2015年4月2日 上午3:58:58
 * 创建用户：林峰
 */
package com.lanstar.render;

/**
 * RenderException.
 */
public class RenderException extends RuntimeException {
	
	private static final long serialVersionUID = -6448434551667513804L;
	
	public RenderException() {
		super();
	}
	
	public RenderException(String message) {
		super(message);
	}
	
	public RenderException(Throwable cause) {
		super(cause);
	}
	
	public RenderException(String message, Throwable cause) {
		super(message, cause);
	}
}
