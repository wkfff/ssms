package com.lanstar.controller.g;

import java.util.Map;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.controller.sys.orgValidator;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.dialect.JdbcPageRecordSet;

public class noticeController extends DefaultController {

	public noticeController() {
		 super("SSM_NOTICE");
	   }

	@Override
	protected Class<? extends ActionValidator> getValidator() {
		
		return noticeValidator.class;
	   }
	/**
	 * 查询某条数据
	 */
    public ViewAndModel look( HandlerContext context ) {
	     String sid = (String) context.getValue( "sid" );
	     return context.returnWith().set( context.DB.withTable( TABLENAME ).where( "SID=?", sid ).query() );
	   }
    /**
     * 发布通知公告
     */
    public void publice( HandlerContext context ) {
    	ARTable table = context.DB.withTable( TABLENAME );
        String sid = (String) context.getValue( "sid" );
        table.where( "SID=?", sid ).value("N_STATE", 1).save();
    }
    /**
     * 显示草稿箱
     */
    public ViewAndModel drafts( HandlerContext context ){
    	return context.returnWith();
       }
    /**
     * 显示已发布
     */
    public ViewAndModel publics( HandlerContext context ){
    	return context.returnWith();
       }
    /**
     * 遍历草稿箱数据
     *
     */
    public ViewAndModel list_drafts( HandlerContext context ) {
        ARTable arTable = context.DB.withTable( TABLENAME ).where("N_STATE=0").orderby("T_UPDATE desc");        
        Map<String, String> filter = context.getFilter();
        if (!filter.isEmpty()) arTable.where( StringHelper.join( filter.keySet(), " and ", false ), filter.values().toArray());
        DBPaging paging = context.getPaging();
        JdbcPageRecordSet list = arTable.queryPaging( paging );
        return context.returnWith().set( list );
        }
    /**
     * 遍历已发布公告数据
     * 
     */
    public ViewAndModel list_publics( HandlerContext context ) {
        ARTable arTable = context.DB.withTable( TABLENAME ).where("N_STATE=1").orderby("T_PUBLIC desc");        
        Map<String, String> filter = context.getFilter();
        if (!filter.isEmpty()) arTable.where( StringHelper.join( filter.keySet(), " and ", false ), filter.values().toArray());
        DBPaging paging = context.getPaging();
        JdbcPageRecordSet list = arTable.queryPaging( paging );
        return context.returnWith().set( list );
        }
    

}
