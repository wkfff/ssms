/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Controller.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import java.util.Map;

import com.google.common.base.Strings;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;

public abstract class DefaultController extends BaseController {
    public DefaultController( String tablename ) {
        super( tablename );
    }

    /**
     * 主页面
     */
    public ViewAndModel index( HandlerContext context ) {
        return context.returnWith();
    }

    /**
     * 列表数据
     */
    public ViewAndModel list( HandlerContext context ) {
        ARTable arTable = context.DB.withTable( TABLENAME );        
        Map<String, String> filter = context.getFilter();
        if (!filter.isEmpty()) arTable.where( StringHelper.join( filter.keySet(), " and ", false ), filter.values().toArray());
        DBPaging paging = context.getPaging();
        JdbcRecordSet list = arTable.queryPaging(paging);
        return context.returnWith().set( list.toJson() );
    }
    
    /**
     * 表单数据
     */
    public ViewAndModel rec( HandlerContext context ) {
        String sid = (String) context.getValue( "sid" );
        return context.returnWith().set( context.DB.withTable( TABLENAME ).where( "SID=?", sid ).query() );
    }

    /**
     * 表单.保存
     */
    public ViewAndModel save( HandlerContext context ) {
        // 先验证下参数
        validatePara( context );
        String sid = (String) context.getValue( "sid" );
        ARTable table = context.DB.withTable( TABLENAME );
        mergerValues( table, context, MergerType.withSid( sid ) );
        // 根据sid的存在设置where语句
        // TODO：一律过滤"null"值？
        table.where( !StringHelper.isBlank( sid ) && !sid.equals( "null" ), "SID=?", sid )
             .save();

        /*if ( !Strings.isNullOrEmpty( sid ) && !sid.equals( "null" ) ) {
            table = table.where( "SID=?", sid );
            table.update();
        } else
            table.insert();*/
        return context.returnWith().set( "{}" );
    }

    /**
     * 列表.删除
     */
    public ViewAndModel dels( HandlerContext context ) {
        String ids = (String) context.getValue( "ids" );
        if ( !Strings.isNullOrEmpty( ids ) ) {
            context.DB.withTable( TABLENAME ).where( "SID in (" + ids + ")" ).delete();
        }
        return context.returnWith().set( "{}" );
    }
    /**
     * 表单.删除
     * @param context
     * @return
     */
    public ViewAndModel del( HandlerContext context ) {
        String sid = (String) context.getValue( "sid" );
        if ( !Strings.isNullOrEmpty( sid ) ) {
            context.DB.withTable( TABLENAME ).where( "SID = ?",sid).delete();
        }
        return context.returnWith().set( "{}" );
    }
}
