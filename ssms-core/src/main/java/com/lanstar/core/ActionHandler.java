/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActionHandler.java
 * 创建时间：2015年4月1日 下午12:06:01
 * 创建用户：林峰
 */
package com.lanstar.core;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lanstar.common.helper.BeanHelper;
import com.lanstar.common.log.Logger;

/**
 * Action处理器
 */
public class ActionHandler extends Handler {
	private static final Logger log = Logger.getLogger(ActionHandler.class);

	/*
	 * 
	 * url如：/a02/index.html
	 */
	public final void handle(String url, HttpServletRequest request, HttpServletResponse response){			
		try {
			Action action = getAction(url);
			Controller controller = action.getControllerClass().newInstance();
			controller.init(request, response, "");
			action.getMethod().invoke(controller,new Object[]{});		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/*
	 * 
	 * url如：/a02/index.html
	 * 控制器统一放在包com.lanstar.controller下，类名以Controller结尾
	 */
	public Action getAction(String url) throws Exception{				
		String[] urlPara = (url.indexOf("?")==-1?url:url.substring(0, url.indexOf("?"))).replace(".", "/").split("/");		
		if (urlPara.length!=4)	throw new Exception("无效路径...");
		
		String controllerKey = urlPara[1];
		String methodName = urlPara[2];
		String view = urlPara[3];
		String actionKey = "/"+controllerKey+"/"+methodName;
		
		Class<? extends Controller> controllerClass = BeanHelper.getClass("com.lanstar.controller."+controllerKey+"Controller");
		Method method = controllerClass.getMethod(methodName);
		Action action = new Action(controllerKey,actionKey,controllerClass,method,methodName,view);	
		return action;			
	}
}
