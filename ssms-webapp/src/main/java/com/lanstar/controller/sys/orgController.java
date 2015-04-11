/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：orgController.java
 * 创建时间：2015年4月8日 上午10:03:05
 * 创建用户：林峰
 */
package com.lanstar.controller.sys;

import com.google.common.base.Strings;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;

/**
 * @author F
 *
 */
public class orgController {
	private final String TABLENAME = "sys_org";
//	private final Logger log = Logger.getLogger( orgController.class );
	/**
	 * 主页面
	 * @param context
	 * @return
	 */
	public ViewAndModel index( HandlerContext context ) {
		return context.returnWith();        
    }
		
	/**
	 * 列表数据
	 * @param context
	 * @return
	 */
	public ViewAndModel list( HandlerContext context ) {
		//传入的过滤条件，格式为field=value
		String filter = (String) context.getValue("_filter");
		JdbcRecordSet list;
		if (Strings.isNullOrEmpty(filter))
			list = context.DB.withTable(TABLENAME).queryList();
		else{
			String[] f = filter.split("=");
			if (f.length==2)
				list = context.DB.withTable(TABLENAME).where(f[0]+" like ?","%"+f[1]+"%").queryList();
			else
				list = context.DB.withTable(TABLENAME).queryList();
		}		
        return context.returnWith().put("total", list.size()).put("rows",list.toArray());
	}
	/**
	 * 表单数据
	 * @param context
	 * @return
	 */
	public ViewAndModel rec( HandlerContext context ) {	
		String sid = (String) context.getValue("sid");
		return context.returnWith().set(context.DB.withTable(TABLENAME).where( "SID=?", sid ).query());
	}
	/**
	 * 表单.保存
	 * @param context
	 * @return
	 */
	public ViewAndModel save( HandlerContext context ) {
        String sid = (String) context.getValue("sid");
        ARTable table = context.DB.withTable(TABLENAME);
        table.values(context.getParameterMap());        
        if (!Strings.isNullOrEmpty(sid) && !sid.equals("null")){
        	table = table.where( "SID=?", sid );	        
	        table.update();
        }else
        	table.insert();
		return context.returnWith().set("{}");
	}
	/**
	 * 列表.删除
	 * @param context
	 * @return
	 */
	public ViewAndModel del( HandlerContext context ) {
		String ids = (String) context.getValue("ids");
		if (!Strings.isNullOrEmpty(ids)){			
			context.DB.withTable(TABLENAME).where("SID in ("+ids+")").delete();
		}
		return context.returnWith().set("{}");
	}
}
